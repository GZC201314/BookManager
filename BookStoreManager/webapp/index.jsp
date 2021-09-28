<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="referrer" content="no-referrer" charset="UTF-8">
    <script type="text/javascript"
            src="pages/lib/jquery-easyui-1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="pages/lib/jquery.cookie.js"></script>
    <script type="text/javascript" src="pages/lib/jquery.session.js"></script>
    <script type="text/javascript"
            src="pages/lib/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="pages/lib/jquery-easyui-1.7.0/datagrid-export.js"></script>
    <script type="text/javascript"
            src="pages/lib/jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="pages/lib/ValidateUtil.js"></script>
    <script type="text/javascript" src="pages/lib/verUpload/verUpload.js"></script>
    <script type="text/javascript">
        //切换验证码
        function changeCheckCode() {
            var nowTime = new Date;
            $("#image").attr("src", '${pageContext.request.contextPath}/userAction!check.action' + "?time=" + nowTime.getTime());
        }
    </script>
    <style type="text/css">
        .avatar {
            display: inline-block;
            overflow: hidden;
            line-height: 1;
            vertical-align: middle;
            border-radius: 3px;
        }

        .head_font {
            width: 90%;
            float: left;
            font-family: cursive;
            font-size: xx-large;
            font-weight: bolder;
            text-align: center;
        }

        .ibx-uc-uimg {
            padding-top: 80px;
            position: relative;
            text-align: center;
        }

        .ibx-uc-unick {
            line-height: 80px;
            font-size: 20px;
            margin-bottom: 4px;
        }

        div img {
            cursor: pointer;
            transition: all 0.6s;
        }

        div img:hover {
            transform: scale(1.4);
        }

        body {
            height: 100vh;
            background-position: center;
            overflow: hidden;
        }

        h1 {
            color: #fff;
            text-align: center;
            font-weight: 100;
            margin-top: 40px;
        }

        #media {
            width: 280px;
            height: 250px;
            margin: 10px auto 0;
            border-radius: 30px;
            overflow: hidden;
            opacity: 0.7;
        }

        #video {

        }

        #canvas {
            display: none;
        }

        #btn {
            width: 160px;
            height: 50px;
            background: #03a9f4;
            margin: 70px auto 0;
            text-align: center;
            line-height: 50px;
            color: #fff;
            border-radius: 40px;
        }
    </style>

    <link rel="stylesheet"
          href="pages/lib/jquery-easyui-1.7.0/themes/default/easyui.css"
          type="text/css"></link>
    <link rel="stylesheet"
          href="pages/lib/jquery-easyui-1.7.0/themes/icon.css" type="text/css"></link>

    <%--加载看板娘--%>
    <link rel="stylesheet" type="text/css" href="pages/lib/kanbanniang/assets/waifu.css"/>

    <title>BSM</title>
</head>
<body class="easyui-layout">
<%--看板娘--%>
<div class="waifu" id="waifu" style="z-index: 999;">
    <div class="waifu-tips" style="opacity: 1;"></div>
    <canvas id="live2d" width="280" height="250" class="live2d"></canvas>
    <div class="waifu-tool">
        <span class="fui-home"></span>
        <span class="fui-chat"></span>
        <span class="fui-eye"></span>
        <span class="fui-user"></span>
        <span class="fui-photo"></span>
        <span class="fui-info-circle"></span>
        <span class="fui-cross"></span>
    </div>
</div>
<div data-options="region:'north'"
     style="height: 60px; background-color: #f9f9f9;">
    <jsp:include page="layout/north.jsp"></jsp:include>
</div>
<div data-options="region:'south'" style="height: 60px;"></div>
<div data-options="region:'west'" style="width: 200px;">
    <jsp:include page="layout/west.jsp"></jsp:include>
</div>
<div data-options="region:'east',title:'East'" style="width: 200px;">
</div>
<!-- 中间tab内容显示 -->
<div data-options="region:'center'">
    <div id="index_centerTab" class="easyui-tabs"
         data-options="fit:true,border:false"
         style="text-align: -webkit-center;">
        <div title="首页"></div>
    </div>
</div>


<script src="pages/lib/kanbanniang/assets/live2d.min.js"></script>
<script src="pages/lib/kanbanniang/assets/waifu-tips.js"></script>
<script type="text/javascript">initModel()</script>

</body>
<!-- 登录弹窗 -->
<div id="user_login_loginDialog" class="easyui-dialog"
     data-options="title:'登录',closable:false,modal:true,
			buttons:[{
				text:'人脸识别',
				handler:function(){
				$('#user_face_regDialog').dialog('open');
               if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
                   navigator.mediaDevices.getUserMedia({
                       video: true
                   }).then(function (stream) {
	  				video.srcObject = stream;
	  				video.onloadmetadate = function(e){
	  					video.play();
	  				}
                   }).catch(function(err){
                       console.log(err);
                   });
               }else if(navigator.getMedia){
                   navigator.getMedia({
                       video: true
                   }).then(function (stream) {
                       console.log(stream);
                       MediaStreamTrack=stream.getTracks()[1];
                       video.src=(window.webkitURL).createObjectURL(stream);
                       video.play();
                   }).catch(function(err){
                       console.log(err);
                   });
               }
				}
			},{
				text:'注册',
				handler:function(){
				$('#user_reg_regDialog').dialog('open');
				}
			},{
				text:'登录',
				handler:function(){
				$('#user_login_loginForm').submit();
				}
			}]">
    <form id="user_login_loginForm" method="post">

        <div style="margin: 20px;">
            <input name="name" class="easyui-textbox"
                   data-options="required:true,missingMessage:'登陆名必填'" label="用户名称:"
                   style="width: 300px;">
        </div>
        <div style="margin: 20px;">
            <input name="pwd" class="easyui-passwordbox"
                   data-options="required:true,missingMessage:'登陆密码必填'" label="登陆密码:"
                   style="width: 300px;">
        </div>
        <div style="margin: 20px;">
            <input name="validateCode" class="easyui-textbox" required="true"
                   label="验证码:"
                   data-options="required:true,validType:'eqvalidateCode[\'#user_login_loginForm input[name=validateCode]\']'"
                   style="width: 300px;">
        </div>
        <div style="margin: 20px;">
            <img id="image"
                 src="${pageContext.request.contextPath}/userAction!check.action"
                 style="float: left; height: 24px; margin-right: 30px;"/> <a
                href="javascript:void(0)" onclick="changeCheckCode();return false;">看不清，换一张</a>
        </div>
    </form>
</div>

<!-- 人脸识别弹窗 -->
<div id="user_face_regDialog" style="width: 350px;"
     class="easyui-dialog"
     data-options="title:'人脸识别',closed:true,closable: false,modal:true,buttons:[{
				text:'人脸识别',
				iconCls:'icon-edit',
				handler:function(){
					query();
				}
			}]">

    <form
            action="${pageContext.request.contextPath}/userAction!facelogin.action"
            method="get">
        <dl class="admin_login">
            <dt>
                <strong>请把你的脸放摄像头面前</strong>
            </dt>
            <div id="media">
                <video id="video" width="400" height="300" autoplay></video>
                <canvas id="canvas" width="400" height="300"></canvas>
            </div>
        </dl>
    </form>
</div>


<!-- 注册弹窗 -->
<div id="user_reg_regDialog" style="width: 350px;" class="easyui-dialog"
     data-options="title:'注册',closed:true,modal:true,buttons:[{
				text:'注册',
				iconCls:'icon-edit',
				handler:function(){
					$('#user_reg_regForm').submit();
				}
			}]">
    <form id="user_reg_regForm" method="post">
        <table>
            <tr>
                <th>登录邮箱</th>
                <td><input id="emailAddress" name="name" class="easyui-textbox" style="width: 250px"
                           data-options="validType:'isEmail[\'#user_reg_regForm input[name=name]\']'"
                           validType="validateSameName"/></td>
            </tr>
            <tr>
                <th>密码</th>
                <td><input name="pwd" class="easyui-passwordbox" style="width: 250px" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <th>重复密码</th>
                <td><input name="rePwd" class="easyui-passwordbox" style="width: 250px"
                           class="easyui-validatebox"
                           data-options="required:true,validType:'eqPwd[\'#user_reg_regForm input[name=pwd]\']'"/></td>
            </tr>
            <tr>
                <th>邮箱验证码</th>
                <td><input name="EmailValidCode" class="easyui-textbox" style="width: 150px"
                           class="easyui-validatebox"
                           data-options="required:true,validType:'ValidEmailCode[\'#user_reg_regForm input[name=name]\']'"/>
                    <a id="sendEmail" href="#" class="easyui-linkbutton" onclick="sendEmail()" style="float: right">发送验证码</a>
                </td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript">
    function sendEmail() {
        //发送邮件
        var emailAddress = $("#emailAddress").val();
        debugger;
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/userAction!sendEmailCode.action",
            data: {"emailAddress": emailAddress},
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                if (obj.success) {

                } else {
                    alert("面容识别失败,请继续验证");
                }
            }
        });
        $("#sendEmail").linkbutton('disable');
    }

    /* 设置页面长时间没有操作,退出用户登录 */

    var lastTime = new Date().getTime();
    var currentTime = new Date().getTime();
    var timeOut = 5 * 60 * 1000; //设置超时时间： 10分
    $(function () {
        /* 鼠标移动事件 */
        $(document).mouseover(function () {
            lastTime = new Date().getTime(); //更新操作时间
        });
    });

    function testTime() {
        currentTime = new Date().getTime(); //更新当前时间
        console.log((currentTime - lastTime) / 1000);
        if (currentTime - lastTime > timeOut && typeof ($.cookie("token")) != "undefined" && $.cookie("token").length > 0) { //判断是否超时
            // 打开弹窗提示即将推出登录
            alert_totalQuery('由于你长时间没有操作,系统将在10秒钟后退出登录!', '', 10);
            setTimeout(() => {
//			清理cookie
                $.cookie('token', '', {
                    expires: -1
                });
                $.cookie('refreshToken', '', {
                    expires: -1
                });
                $.cookie('role', '', {
                    expires: -1
                });
                window.location.reload();
            }, 10000);
        }
    }

    /* 定时器  间隔10秒检测是否长时间未操作页面  */
    window.setInterval(testTime, 10000);

    (function ($) {
        //备份jquery的ajax方法
        var _ajax = $.ajax;
        //重写jquery的ajax方法
        $.ajax = function (opt) {
            //备份opt中error和success方法
            var fn = {
                error: function (d) {
                    d = d.status;
                    if (d == 402) {
                        // TODO
                        alert_totalQuery('Token is error,please re-acquire!', '', 10);
                        setTimeout(() => {
//							清理cookie
                            $.cookie('token', '', {
                                expires: -1
                            });
                            $.cookie('refreshToken', '', {
                                expires: -1
                            });
                            $.cookie('role', '', {
                                expires: -1
                            });
                            window.location.reload();
                        }, 10000);
                    }
                    if (d == 403) {
                        alert_totalQuery('This token has expired!', '', 10);
                        setTimeout(() => {
//							清理cookie
                            $.cookie('token', '', {
                                expires: -1
                            });
                            $.cookie('refreshToken', '', {
                                expires: -1
                            });
                            $.cookie('role', '', {
                                expires: -1
                            });
                            window.location.reload();
                        }, 10000);
                    }
                }
            }
            if (opt.error) {
                fn.error = opt.error;
            }
            //扩展增强处理
            var _opt = $.extend(opt, {
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    //错误方法增强处理
                    fn.error(XMLHttpRequest, textStatus, errorThrown);
                }
            });
            _ajax(_opt);
        };

    })(jQuery);


    var video = document.getElementById('video');
    var context = canvas.getContext('2d');
    var con = {
        audio: false,
        video: {
            width: 1980,
            height: 1024,
        }
    };

    function query() {
        //把流媒体数据画到convas画布上去
        context.drawImage(video, 0, 0, 400, 300);
        var base = getBase64();
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/userAction!facelogin.action",
            data: {"base": base},
            success: function (data) {
                var obj = jQuery.parseJSON(data);
                ;
                if (obj.success) {
                    //关闭摄像头
                    video.srcObject.getTracks()[0].stop();

                    /* 登录成功把Token和refreshToken放到cookies中 */
                    $.cookie('token', obj.obj.token);
                    $.cookie('refreshToken', obj.obj.refreshToken);
                    $.cookie('role', obj.obj.role);
                    $.cookie('userName', obj.obj.userName);
                    $.cookie('userlog', decodeURI(obj.obj.userlog));
                    $.cookie('isFaceValid', obj.obj.isFaceValid);
                    //取消人脸注册的选项
                    if (obj.obj.isFaceValid == "1") {
                        $('#faceReg').remove();
                    }
                    $('#user_face_regDialog').dialog('close');
                    $('#user_login_loginDialog').dialog('close');
                    $('#layout_north_userName').text(obj.obj.userName);
                    if (!(typeof (obj.obj.userlog) == "undefined" || obj.obj.userlog == "")) {
                        $('#admin_north_headIcon').attr("src", decodeURI(obj.obj.userlog));
                    }
                    //加载 目录
                    $('#layout_west_tree').tree({
                        url: '${pageContext.request.contextPath}/menuAction!getTreeNote.action',
                        parentField: 'pid',
                        lines: true,
                        onClick: function (node) {
                            if (node.attributes.url) {
                                var url = '${pageContext.request.contextPath}' + node.attributes.url;
                                addTab({
                                    title: node.text,
                                    closable: true,
                                    href: url
                                });
                            }
                        }
                    });
                } else {
                    alert("面容识别失败,请继续验证");
                }
            }
        });

    }

    function getBase64() {
        var imgSrc = document.getElementById("canvas").toDataURL(
            "image/png");
        return imgSrc.split("base64,")[1];

    };


    //新增tab页签
    function addTab(opts) {
        var t = $('#index_centerTab');
        if (t.tabs('exists', opts.title)) {
            t.tabs('select', opts.title);
        } else {
            t.tabs('add', opts);
        }
    }

    $(function () {
        $('#user_login_loginForm').form({
            url: '${pageContext.request.contextPath}/userAction!login.action',
            success: function (r) {
                var obj = jQuery.parseJSON(r);
                if (obj.success) {
                    /* 登录成功把Token和refreshToken放到cookies中 */
                    $.cookie('token', obj.obj.token);
                    $.cookie('refreshToken', obj.obj.refreshToken);
                    $.cookie('role', obj.obj.role);
                    $.cookie('userName', obj.obj.userName);
                    $.cookie('userlog', decodeURI(obj.obj.userlog));
                    $.cookie('isFaceValid', obj.obj.isFaceValid);
                    $('#user_login_loginDialog').dialog('close');
                    $('#layout_north_userName').text(obj.obj.userName);
                    if (!(typeof (obj.obj.userlog) == "undefined" || obj.obj.userlog == "")) {
                        $('#admin_north_headIcon').attr("src", decodeURI(obj.obj.userlog));
                    }
                    if (obj.obj.isFaceValid == "1") {
                        $('#faceReg').remove();
                    }
                    //加载 目录
                    $('#layout_west_tree').tree({
                        url: '${pageContext.request.contextPath}/menuAction!getTreeNote.action',
                        parentField: 'pid',
                        lines: true,
                        onClick: function (node) {
                            if (node.attributes.url) {
                                var url = '${pageContext.request.contextPath}' + node.attributes.url;
                                addTab({
                                    title: node.text,
                                    closable: true,
                                    href: url
                                });
                            }
                        }
                    });

                }
                $.messager.show({
                    title: '提示',
                    msg: obj.msg
                });
            }
        });
        $('#user_login_loginForm input').bind('keyup', function (event) {/* 增加回车提交功能 */
            if (event.keyCode == '13') {
                $('#user_login_loginForm').submit();
            }
        });

        if (typeof ($.cookie("token")) != "undefined" && $.cookie("token").length > 0) {
            $('#user_login_loginDialog').dialog('close');
            $('#layout_north_userName').text($.cookie("userName"));
            $('#admin_north_headIcon').attr("src", $.cookie("userlog"));

            //加载 目录
            $('#layout_west_tree').tree({
                url: '${pageContext.request.contextPath}/menuAction!getTreeNote.action',
                parentField: 'pid',
                lines: true,
                onClick: function (node) {
                    if (node.attributes.url) {
                        var url = '${pageContext.request.contextPath}' + node.attributes.url;
                        addTab({
                            title: node.text,
                            closable: true,
                            href: url
                        });
                    }
                }
            });
        }

        $('#user_reg_regForm').form({
            url: '${pageContext.request.contextPath}/userAction!reg.action',
            success: function (r) {
                var obj = jQuery.parseJSON(r);
                if (obj.success) {
                    $('#user_reg_regDialog').dialog('close');
                }
                $.messager.show({
                    title: '提示',
                    msg: obj.msg
                });
            }
        });
        $('#user_reg_regForm input').bind('keyup', function (event) {/* 增加回车提交功能 */
            if (event.keyCode == '13') {
                $('#user_reg_regForm').submit();
            }
        });

    });
</script>
</html>

<link rel="stylesheet" type="text/css" href="pages/lib/kanbanniang/assets/flat-ui.min.css"/>
