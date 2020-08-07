<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--  -->

<div id="cc" class="easyui-layout" data-options="border:false"
	style="width: 800px; height: 400px;">
	<div data-options="region:'west',border:false" style="width: 400px;">
		<div class="ibx-uc-uimg">
			<img id="admin_grzx_headIcon" alt="" href="#" style="cursor: pointer;" class="avatar"
				onclick="userCenter();" src="img/default_user.jpg" height="78"
				width="78">
		</div>
		<div class="ibx-uc-unick">
			<a id="admin_grzx_userName" target="_blank" style="display: block; text-align: center;"
				class="ibx-uc-nick"></a>

		</div>
	</div>
	<div data-options="region:'center',border:false">

		<div style="margin-top: 60px;">
			<label
				style="line-height: 100px; font-size: x-large; font-family: inherit; width: 150px; display: inline-block;"
				for="name">角色：</label> <font id="admin_grzx_roleName" style="font-size: large;"></font>
		</div>
		<div>
			<label
				style="line-height: 100px; font-size: x-large; font-family: inherit; width: 150px; display: inline-block;"
				for="name">创建时间：</label> <font id="admin_grzx_createTime" style="font-size: large;"></font>
		</div>
		<div>
			<label
				style="line-height: 100px; font-size: x-large; font-family: inherit; width: 150px; display: inline-block;"
				for="name">修改时间：</label> <font id="admin_grzx_modifyTime" style="font-size: large;"></font>
		</div>

	</div>
</div>
<script type="text/javascript">
$(function() {
	userCenter();
});
</script>
