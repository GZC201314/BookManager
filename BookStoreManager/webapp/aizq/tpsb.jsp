<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div style="text-align: center; margin-top: 30px;">
	<font style="font-size: large; font-weight: 20;">图片文字识别</font>
</div>
<div id="cc" class="easyui-layout" style="width: 600px; height: 400px;"
	data-options="fit: true, border:false">
	<div data-options="region:'west'" style="width: 400px;">
		<div class="ibx-uc-uimg">
			<img id="znzq_tpsb_uploadImg" alt="" href="#" title="请选择图片文件"
				class="avatar" height="78" width="78">
		</div>
	</div>
	<div data-options="region:'center'">
			<input id="znzq_wordresult_textbox"
				data-options="multiline:true" class="easyui-textbox" label="识别的文字:"
				style="width: 100%; height: 250px;">
	</div>
</div>
<script type="text/javascript">
	new verUpload({
		files : "#znzq_tpsb_uploadImg",
		name : "upload",
		method : "${pageContext.request.contextPath}/baiduAIAction!uploadImg.action",
		load_list : false,
		success : function(d) {
			var obj = jQuery.parseJSON(d);
			if (obj.success) {
				//识别成功后的处理
				$('#znzq_wordresult_textbox').textbox('setValue', obj.obj);
			}
			$.messager.show({
				title : '提示',
				msg : obj.msg
			});
		},
		size : 1024 * 4,
		ext : [ 'jpg', 'jpeg', 'png', 'gif' ]
	});
</script>