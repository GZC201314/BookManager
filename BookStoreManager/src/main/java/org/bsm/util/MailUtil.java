package org.bsm.util;

import com.alibaba.druid.filter.config.ConfigTools;
import com.sun.mail.util.MailSSLSocketFactory;
import org.bsm.Email.MailAuthenticator0;
import org.bsm.pageModel.SendEmail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailUtil {
  static String HOST = "";
  static String FROM = "";
  static String USER = "";
  static String PWD = "";

  static {
    try {
      Properties props = new Properties();
      props.load(
          Thread.currentThread()
              .getContextClassLoader()
              .getResourceAsStream("emailConfig.properties")); // 从自定义配置文件获取相关参数
      HOST = props.getProperty("mail.smtp.host");
      FROM = props.getProperty("from");
      USER = props.getProperty("user");
      PWD = props.getProperty("pwd");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** 发送邮件 */
  public static boolean send(SendEmail sendEmail) {
    // 配置信息
    Properties pro = new Properties();
    pro.put("mail.smtp.host", "smtp.163.com");
    pro.put("mail.smtp.auth", "true");
    // SSL加密
    try {
      MailSSLSocketFactory sf = null;
      sf = new MailSSLSocketFactory();
      // 设置信任所有的主机
      sf.setTrustAllHosts(true);
      pro.put("mail.smtp.ssl.enable", "true");
      pro.put("mail.smtp.ssl.socketFactory", sf);
      // 根据邮件的会话属性构造一个发送邮件的Session，这里需要注意的是用户名那里不能加后缀，否则便不是用户名了
      // 还需要注意的是，这里的密码不是正常使用邮箱的登陆密码，而是客户端生成的另一个专门的授权码
      MailAuthenticator0 authenticator = new MailAuthenticator0(USER, ConfigTools.decrypt(PWD));
      Session session = Session.getInstance(pro, authenticator);
      // 根据Session 构建邮件信息
      Message message = new MimeMessage(session);
      // 创建邮件发送者地址
      Address from = new InternetAddress(USER);
      // 设置邮件消息的发送者
      message.setFrom(from);
      // 验证收件人邮箱地址
      List<String> toAddressList = new ArrayList<>();
      toAddressList.add(sendEmail.getTo());
      StringBuffer buffer = new StringBuffer();
      if (!toAddressList.isEmpty()) {
        String regEx =
            "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(regEx);
        for (int i = 0; i < toAddressList.size(); i++) {
          Matcher match = p.matcher(toAddressList.get(i));
          if (match.matches()) {
            buffer.append(toAddressList.get(i));
            if (i < toAddressList.size() - 1) {
              buffer.append(",");
            }
          }
        }
      }
      String toAddress = buffer.toString();
      if (!toAddress.isEmpty()) {
        // 创建邮件的接收者地址
        Address[] to = InternetAddress.parse(toAddress);
        // 设置邮件接收人地址
        message.setRecipients(Message.RecipientType.TO, to);
        // 邮件主题
        // message.setSubject("java邮件测试");
        message.setSubject("注册邮件");

        // 添加附件
        List<String> fileAddressList = new ArrayList<String>();
        if (sendEmail.getAffix() != null) {
          fileAddressList.add(sendEmail.getAffix());
        }
        if (fileAddressList.size() > 0) {
          // 邮件容器
          MimeMultipart mimeMultiPart = new MimeMultipart();

          // 设置HTML
          BodyPart bodyPart = new MimeBodyPart();
          // 邮件内容
          // String htmlText = "java邮件测试111";
          String htmlText = sendEmail.getContext();
          bodyPart.setContent(htmlText, "text/html;charset=utf-8");
          mimeMultiPart.addBodyPart(bodyPart);

          BodyPart attchPart = null;
          // 如果发送的邮件没有附件
          for (int i = 0; i < fileAddressList.size(); i++) {
            if (!fileAddressList.get(i).isEmpty()) {
              attchPart = new MimeBodyPart();
              // 附件数据源
              DataSource source = new FileDataSource(fileAddressList.get(i));
              // 将附件数据源添加到邮件体
              attchPart.setDataHandler(new DataHandler(source));
              // 设置附件名称为原文件名
              attchPart.setFileName(MimeUtility.encodeText(source.getName()));
              mimeMultiPart.addBodyPart(attchPart);
            }
          }
          message.setContent(mimeMultiPart);
          message.setSentDate(new Date());
          // 保存邮件
          message.saveChanges();
          // 发送邮件
          Transport.send(message);

        } else {

          message.setContent(sendEmail.getContext(), "text/html;charset=utf-8");

          message.setSentDate(new Date());
          // 保存邮件
          message.saveChanges();
          // 发送邮件
          Transport.send(message);
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}
