package org.bsm.action;

import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.bsm.model.Tuser;
import org.bsm.pageModel.*;
import org.bsm.service.BaiduAIServiceI;
import org.bsm.service.UserServiceI;
import org.bsm.util.JWTUtil;
import org.bsm.util.MailUtil;
import org.bsm.util.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Namespace("/")
@Action(value = "userAction")
public class UserAction extends BaseAction implements ModelDriven<PageUser> {
  private static final Logger logger = LogManager.getLogger(UserAction.class.getName());

  private PageUser pageUser = new PageUser();

  private File upload; // 定义一个File ,变量名要与jsp中的input标签的name一致
  private String uploadContentType; // 上传文件的mimeType类型
  private String uploadFileName; // 上传文件的名称

  public File getUpload() {
    return upload;
  }

  public void setUpload(File upload) {
    this.upload = upload;
  }

  public String getUploadContentType() {
    return uploadContentType;
  }

  public void setUploadContentType(String uploadContentType) {
    this.uploadContentType = uploadContentType;
  }

  public String getUploadFileName() {
    return uploadFileName;
  }

  public void setUploadFileName(String uploadFileName) {
    this.uploadFileName = uploadFileName;
  }

  @Autowired private UserServiceI userServiceI;
  @Autowired StringRedisTemplate redisTemplate;
  @Autowired private BaiduAIServiceI baiduAIServiceI;

  @Override
  public PageUser getModel() {
    return pageUser;
  }

  /** 生成验证码 */
  public void check() {
    try {
      ValidateCode validateCode = new ValidateCode();
      HttpServletResponse response = ServletActionContext.getResponse();
      response.setContentType("application/octet-stream");
      response.addHeader("Content-Disposition", "attachment;filename=vcode.jpeg");
      String number = validateCode.getNumber(4);
      Cookie cookie = new Cookie("validateCode", number);
      response.addCookie(cookie);
      validateCode.getImage(response.getOutputStream(), number);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
  }

  /** 注册 */
  public void reg() {
    logger.info("into the reg function");
    Json j = new Json();
    try {
      userServiceI.save(pageUser);
      j.setSuccess(true);
      j.setMsg("注册成功.");
    } catch (Exception e) {
      j.setMsg(e.getMessage());
    }
    super.writeJson(j);
    logger.info("out into the reg function");
  }

  /** 新增用户 */
  public void add() {
    logger.info("into the add User function");
    Json j = new Json();
    try {
      PageUser pageUser1 = userServiceI.save(pageUser);
      j.setSuccess(true);
      j.setMsg("新增用户成功.");
      j.setObj(pageUser1);
    } catch (Exception e) {
      j.setMsg(e.getMessage());
    }
    super.writeJson(j);
    logger.info("out into the Add User  function....");
  }

  /** 修改用户 */
  public void edit() {
    logger.info("into the edit User function");
    Json j = new Json();
    Integer resultCode = 1;
    try {
      pageUser.setLastmodifytime(new Date());
      resultCode = userServiceI.update(pageUser);
      j.setSuccess(true);
      j.setObj(resultCode);
    } catch (Exception e) {
      j.setMsg(e.getMessage());
      j.setObj(resultCode);
      j.setSuccess(false);
    }
    super.writeJson(j);
    logger.info("out into the edit User  function....");
  }

  /** 获取用户列表 */
  public void datagrid() {
    logger.info("into the datagrid function");
    PageDataGrid pageDataGrid = new PageDataGrid();
    try {
      pageDataGrid = userServiceI.datagrid(pageUser);
    } catch (Exception e) {
    }
    super.writeJson(pageDataGrid);
    logger.info("out into the datagrid function");
  }

  /** 删除用户 */
  public void removeUser() {
    logger.info("into the removeUser function");
    Json json = new Json();
    try {
      userServiceI.removeUser(pageUser);
      json.setMsg("删除用户成功.");
      json.setSuccess(true);
    } catch (Exception e) {
    }
    super.writeJson(json);
    logger.info("out into the removeUser function");
  }

  /** 校验用户 */
  public void validateName() {
    logger.info("into the validateName function");
    Json json = new Json();
    try {
      Tuser tuser = userServiceI.validateName(pageUser.getName());
      boolean result = false;
      if (StringUtils.isEmpty(tuser)) {
        result = true;
      }
      json.setSuccess(result);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    super.writeJson(json);
    logger.info("out into the validateName function");
  }

  /** 校验邮箱验证码 */
  public void validateEmailCode() {
    // TODO 校验邮箱验证码
    logger.info("into the validateEmailCode function");
    Json json = new Json();
    String emailCode = redisTemplate.opsForValue().get(pageUser.getEmailAddress());
    if (StringUtils.isEmpty(emailCode) || !pageUser.getEmailCode().equals(emailCode)) {
      json.setMsg("邮箱验证码错误！");
      json.setSuccess(false);
    } else {
      json.setMsg("邮箱验证码正确！");
      json.setSuccess(true);
    }
    super.writeJson(json);
    logger.info("out into the validateEmailCode function");
  }

  /** 发送邮箱验证码 */
  public void sendEmailCode() throws Exception {
    // TODO 发送邮箱验证码
    logger.info("into the sendEmailCode function");
    ValidateCode validateCode = new ValidateCode();
    Json json = new Json();
    String emailCode = validateCode.getNumber(6);
    redisTemplate.opsForValue().set(pageUser.getEmailAddress(), emailCode);
    // 验证码过期时间是10分钟
    redisTemplate.expire(pageUser.getEmailAddress(), 10 * 60, TimeUnit.SECONDS);
    SendEmail sendEmail = new SendEmail();
    sendEmail.setSubject("BSM注册邮件");
    sendEmail.setTo(pageUser.getEmailAddress());
    sendEmail.setTos(new String[] {pageUser.getEmailAddress()});
    String context =
        "<p style=\"margin: 0.0pt 0.0pt 5.0pt;\"><span style=\"font-size: 14.0px;\">"
            + "<span style=\"font-family: arial , helvetica , sans-serif;\"><span style=\"background: rgb(255,255,255);\">"
            + "<span style=\"color: rgb(65,65,65);\">尊敬的用户您好：</span></span></span></span><br/><br/> "
            + "&nbsp; &nbsp; &nbsp; &nbsp;您正在注册BSM用户，你的验证码是：<b style=\\\"color:blue;\\\">"
            + emailCode
            + "</b><br/></p>";
    sendEmail.setContext(context);
    // 发送邮件
    boolean result = MailUtil.send(sendEmail);
    json.setSuccess(result);
    super.writeJson(json);
    logger.info("out into the sendEmailCode function");
  }

  /** 登录 */
  public void login() {
    logger.info("into the login function");
    Json j = new Json();
    try {
      Tuser tuser = userServiceI.login(pageUser);
      if (tuser != null) {
        // 生成token
        String token = JWTUtil.generateToken(tuser.getName());
        // 生成refreshToken
        String refreshToken = UUID.randomUUID().toString();
        // 数据放入redis
        redisTemplate.opsForHash().put(refreshToken, "token", token);
        redisTemplate.opsForHash().put(refreshToken, "username", tuser.getName());
        redisTemplate.opsForHash().put(refreshToken, "role", tuser.getTrole().getRolename());
        redisTemplate.opsForHash().put(refreshToken, "isFaceValid", tuser.getIsFaceValid() + "");

        // 设置token的过期时间
        redisTemplate.expire(refreshToken, JWTUtil.REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        j.setObj(
            new AuthResult(
                token,
                refreshToken,
                tuser.getTrole().getRoleid(),
                tuser.getName(),
                tuser.getUserlog(),
                tuser.getIsFaceValid()));
        j.setMsg("登录成功.");
        j.setSuccess(true);

      } else {
        j.setMsg("登录失败.");
      }

    } catch (Exception e) {
      j.setMsg(e.getMessage());
      j.setSuccess(false);
    }
    super.writeJson(j);
    logger.info("out into the login function");
  }

  /** 人脸识别登录 */
  public void facelogin() {
    logger.info("into the facelogin function");
    Json j = new Json();
    try {
      AipFaceResult aipFaceResult = baiduAIServiceI.facelogin(pageUser);
      // 如果人脸识别成功
      if (aipFaceResult != null && aipFaceResult.getError_code() == 0) {
        // 根据人脸识别的结果查询本地是否有用户记录,如果有的话,直接登录
        String username = aipFaceResult.getResult().getUser_list().get(0).getUser_id();
        double score = aipFaceResult.getResult().getUser_list().get(0).getScore();
        // 如果在人脸库中找到了记录,并且匹配度达到了70%以上,允许该用户登录
        if (score >= 70) {
          Tuser tuser = userServiceI.validateName(username);

          if (tuser != null) {
            // 生成token
            String token = JWTUtil.generateToken(tuser.getName());
            // 生成refreshToken
            String refreshToken = UUID.randomUUID().toString();
            // 数据放入redis
            redisTemplate.opsForHash().put(refreshToken, "token", token);
            redisTemplate.opsForHash().put(refreshToken, "username", tuser.getName());
            redisTemplate.opsForHash().put(refreshToken, "role", tuser.getTrole().getRolename());
            redisTemplate
                .opsForHash()
                .put(refreshToken, "isFaceValid", tuser.getIsFaceValid() + "");

            // 设置token的过期时间
            redisTemplate.expire(refreshToken, JWTUtil.REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
            j.setObj(
                new AuthResult(
                    token,
                    refreshToken,
                    tuser.getTrole().getRoleid(),
                    tuser.getName(),
                    tuser.getUserlog(),
                    tuser.getIsFaceValid()));
            j.setMsg("登录成功.");
            j.setSuccess(true);

          } else {
            j.setMsg("登录失败.");
          }

        } else {
          j.setMsg("没有在人脸库中找到你的记录,请先添加你的记录!");
        }
        j.setMsg("登录成功.");
        j.setSuccess(true);

      } else {
        j.setMsg("登录失败.");
      }

    } catch (Exception e) {
      j.setMsg(e.getMessage());
      j.setSuccess(false);
    }
    super.writeJson(j);
    logger.info("out into the facelogin function");
  }

  /** 登出 */
  public void logout() {
    logger.info("into the logout function");
    Json j = new Json();
    try {
      // 删除redis里边的登录信息
      redisTemplate.opsForHash().delete(pageUser.getRefreshToken(), "username", "token", "role");
      ;
      j.setMsg("登出成功.");
      j.setSuccess(true);

    } catch (Exception e) {
      j.setMsg(e.getMessage());
      j.setSuccess(false);
      logger.error(e.getMessage());
    }
    super.writeJson(j);
    logger.info("out into the logout function");
  }

  /** 上传头像图像 */
  public void uploadHeadIcon() {
    logger.info("into the uploadHeadIcon function");
    Json j = new Json();
    try {
      pageUser.setUploadImg(upload);
      boolean result = userServiceI.uploadHeadIcon(pageUser);
      if (result) {
        j.setMsg("上传头像成功.");
        j.setSuccess(true);
      } else {
        j.setMsg("上传头像失败.");
        j.setSuccess(false);
      }

    } catch (Exception e) {
      j.setMsg(e.getMessage());
      j.setSuccess(false);
      logger.error(e.getMessage());
    }
    super.writeJson(j);
    logger.info("out into the uploadHeadIcon function");
  }

  /** 查询用户信息 */
  public void userInfo() {
    logger.info("into the userInfo function");
    Json j = new Json();
    try {
      // 查询用户的详细信息
      PageUser repageUser = userServiceI.userInfo(pageUser);
      if (StringUtils.isEmpty(repageUser)) {

        j.setMsg("获取用户信息失败.");
        j.setSuccess(false);
      } else {
        j.setMsg("获取用户信息成功.");
        j.setSuccess(true);
        j.setObj(repageUser);
      }

    } catch (Exception e) {
      j.setMsg(e.getMessage());
      j.setSuccess(false);
      logger.error(e.getMessage());
    }
    super.writeJson(j);
    logger.info("out into the userInfo function");
  }

  /***
   * 人脸注册
   */
  public void faceReg() {
    logger.info("into the faceReg function");
    Json j = new Json();
    try {
      AipFaceResult result = baiduAIServiceI.faceReg(pageUser);
      if (result != null && result.getError_code() == 0) {
        j.setMsg("人脸注册成功");
        j.setSuccess(true);
      } else {
        j.setMsg("人脸注册失败!");
        j.setSuccess(false);
      }
    } catch (Exception e) {
      j.setMsg(e.getMessage());
      j.setSuccess(false);
      j.setMsg("人脸注册失败");
      logger.error(e.getMessage());
    }
    super.writeJson(j);
    logger.info("out into the faceReg function");
  }
}
