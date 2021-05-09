<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- -->

<div id="cc" class="easyui-layout" data-options="border:false"
     style="width: 800px; height: 400px;">

    <div class="ibx-uc-uimg">
        <img id="admin_xxxg_uploadImg" enctype="multipart/form-data" alt=""
             href="#" style="cursor: pointer;" class="avatar"
             src="img/default_user.jpg" height="78" width="78">
    </div>
</div>
<script type="text/javascript">
    new verUpload({
        files: "#admin_xxxg_uploadImg",
        name: "upload",
        method: "${pageContext.request.contextPath}/userAction!uploadHeadIcon.action",
        load_list: false,
        success: function (d) {
            var obj = jQuery.parseJSON(d);
            if (obj.success) {
                userCenter();
            }
            $.messager.show({
                title: '提示',
                msg: obj.msg
            });
        },
        size: 1024 * 4,
        ext: ['jpg', 'jpeg', 'png', 'gif']
    });
    $(function () {
        var src = $('#admin_north_headIcon')[0].src;
        if (typeof (src) == "undefined") {
            return;
        }
        $('#admin_xxxg_uploadImg').attr('src', src);
    });
</script>
