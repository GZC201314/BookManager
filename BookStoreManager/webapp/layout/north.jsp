<%@ page language="java" pageEncoding="UTF-8"%>
<div class="easyui-panel" data-options="fit:true,border:false"
	style="margin-top: 10px; background-color: #f9f9f9;">
	<div class="head_font">网上图书商城</div>
	<div style="float: right; float: right;">
		<img id="admin_north_headIcon" alt="" href="#"
			style="cursor: pointer;" class="avatar" onclick="userCenter();"
			src="img/default_user.jpg" height="20" width="20"> <a
			id="layout_north_userName" href="#" class="easyui-menubutton"
			data-options="menu:'#mm2'">请登录</a>
	</div>
</div>
<div id="mm2" style="width: 100px;">
	<div onclick="userCenter();">个人中心</div>
	<div id="faceReg" onclick="faceReg();">人脸注册</div>
	<div>帐号设置</div>
	<div onclick="logout();">退出</div>
</div>
<script type="text/javascript">
	//人脸注册

	function faceReg() {
		$('#user_face_regDialog').dialog({
			title : '人脸注册',
			closed : true,
			modal : true,
			buttons : [ {
				text : '人脸注册',
				handler : function() {
					faceRegCommit();
				}
			} ]
		});
		$('#user_face_regDialog').dialog('open');
		var video = document.getElementById('video');
		var context = canvas.getContext('2d');
               if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
                   navigator.mediaDevices.getUserMedia({
                       video: true
                   }).then(function (stream) {
	  				video.srcObject = stream;
	  				video.onloadmetadate = function(e){
	  					video.play();
	  				}
                   });
               }else if(navigator.getMedia){
                   navigator.getMedia({
                       video: true
                   }).then(function (stream) {
                       console.log(stream);
                       MediaStreamTrack=stream.getTracks()[1];
                       video.src=(window.webkitURL).createObjectURL(stream);
                       video.play();
                   });
               }
	}

	function faceRegCommit() {
		//把流媒体数据画到convas画布上去
		context.drawImage(video, 0, 0, 400, 300);
		var base = getBase64();
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/userAction!faceReg.action",
			data : {
				"base" : base
			},
			success : function(data) {
				//关闭摄像头
				video.srcObject.getTracks()[0].stop();
				$('#user_face_regDialog').dialog('close');
				$('#faceReg').remove();
				var obj = jQuery.parseJSON(data);
				$.messager.show({
					title : '提示',
					msg : obj.msg
				});

			}
		});

	}

	function logout() {
		var token = $.cookie("token");
		var refreshToken = $.cookie("refreshToken");
		if (typeof (token) != "undefined" && token.length > 0) {
			$.ajax({
				url : '${pageContext.request.contextPath}/userAction!logout.action',
				data : {
					token : token,
					refreshToken : refreshToken
				},
				type : 'post',
				dataType : "json",
				success : function(data) {
					if (data.success == true) {
						//清理cookie
						$.cookie('token', '', {
							expires : -1
						});
						$.cookie('refreshToken', '', {
							expires : -1
						});
						$.cookie('role', '', {
							expires : -1
						});
						window.location.reload();
					}
					$.messager.show({
						title : '提示',
						msg : data.msg
					});
				}
			});
		}
	}

	function userCenter() {
		$.ajax({
			url : '${pageContext.request.contextPath}/userAction!userInfo.action',
			data : {},
			type : 'post',
			dataType : "json",
			success : function(data) {
				if (data.success == true) {
					//打开个人中心的tab
					var opts = {
						title : "个人中心",
						closable : true,
						href : "/BookStoreManager/admin/grzx.jsp"
					}
					addTab(opts);
					//选中树节点 layout_west_tree
					var tree = $('#layout_west_tree');
					var node = tree.tree('find', "grzx");
					tree.tree('select', node.target);
					$('#admin_grzx_userName').text(data.obj.name);
					$('#admin_grzx_roleName').text(data.obj.roleid);
					$('#admin_grzx_createTime').text(data.obj.createdatetime);
					$('#admin_grzx_modifyTime').text(data.obj.lastmodifytime);
					if (!(data.obj.userlog == "" || typeof (data.obj.userlog) == "undefined")) {
						$('#admin_grzx_headIcon').attr("src", decodeURI(data.obj.userlog));
						$('#admin_north_headIcon').attr("src", decodeURI(data.obj.userlog));
						$('#admin_xxxg_uploadImg').attr("src", decodeURI(data.obj.userlog));
						$("#logo").attr('src', "user.jpg");
					}
				}
				$.messager.show({
					title : '提示',
					msg : data.msg
				});
			}
		});
	}
</script>