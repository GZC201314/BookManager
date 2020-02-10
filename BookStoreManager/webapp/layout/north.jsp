<%@ page language="java" pageEncoding="UTF-8"%>
<div class="easyui-panel" data-options="fit:true,border:false"
	style="margin-top: 10px; background-color: #f9f9f9;">
	<div class="head_font">网上图书商城</div>
	<div style="float: right; float: right;">
		<img alt="" class="avatar" src="img/default_user.jpg" height="20"
			width="20"> <a id="layout_north_userName" href="#" class="easyui-menubutton"
			data-options="menu:'#mm2'">请登录</a>
	</div>
</div>
<div id="mm2" style="width: 100px;">
	<div>个人中心</div>
	<div>帐号设置</div>
	<div onclick="logout();">退出</div>
</div>
<script type="text/javascript">
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
</script>