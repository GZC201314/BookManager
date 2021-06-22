package org.bsm.pageModel;

/**
 * @author GZC-
 * @create 2021-06-21 20:17
 */
public class SendEmail {
  private String to; // 收件人地址
  private String affix; // 附件地址
  private String affixName; // 附件名称
  private String subject; // 邮件标题
  private String[] tos;
  private String context;

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getAffix() {
    return affix;
  }

  public void setAffix(String affix) {
    this.affix = affix;
  }

  public String getAffixName() {
    return affixName;
  }

  public void setAffixName(String affixName) {
    this.affixName = affixName;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String[] getTos() {
    return tos;
  }

  public void setTos(String[] tos) {
    this.tos = tos;
  }
}
