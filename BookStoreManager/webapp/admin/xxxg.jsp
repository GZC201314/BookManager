<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--  -->

<div id="cc" class="easyui-layout" data-options="border:false"
	style="width: 800px; height: 400px;">

	<div class="ibx-uc-uimg">
		<img id="admin_xxxg_uploadImg" enctype="multipart/form-data" alt="" href="#" style="cursor: pointer;" class="avatar"
			 src="img/default_user.jpg" height="78"
			width="78">
	</div>
</div>
<
<script type="text/javascript">
	new verUpload({
		files : "#admin_xxxg_uploadImg",
		name : "upload",
		method : "${pageContext.request.contextPath}/userAction!uploadHeadIcon.action",
		load_list : true,
		success : function(d) {
			alert(d);
		},
		fail : function(d) {
			alert(d)
		},
		size : 1024 * 4,
		ext : [ 'jpg', 'jpeg', 'png', 'gif' ]
	});
</script>
