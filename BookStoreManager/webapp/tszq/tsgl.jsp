<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="tszq_sjgl_toobar">
	<a onclick="editBook();" class="easyui-linkbutton"
		data-options="iconCls:'icon-edit',plain:true">修改</a> <a
		onclick="removeBook();" class="easyui-linkbutton"
		data-options="iconCls:'icon-remove',plain:true">删除</a>图书名称: <input
		id="tszq_sjgl_bookname" class="easyui-textbox"></input> <a href="#"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-search',plain:true"
		onclick="searchBookFun();">查询</a> <a href="#"
		class="easyui-linkbutton"
		data-options="iconCls:'icon-cancel',plain:true"
		onclick="clearBookFun();">清空</a>
		<a href="#"
		class="easyui-linkbutton" style="float: right;"
		data-options="plain:true"
		onclick="toexcel();">导出</a>
</div>
<div id="tszq_sjgl_addDialog" class="easyui-dialog"
	data-options="closed:true,modal:true,title:'添加用户',buttons:[{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					$('#tszq_sjgl_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/bookAction!add.action',					
						success : function(r) {
							var obj = jQuery.parseJSON(r);
							if (obj.success) {
								$('#tszq_sjgl_datagrid').datagrid('load', {});
								$('#tszq_sjgl_addDialog').dialog('close');
							}
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
						}
					});
				}
			}]"
	style="width: 500px; height: 500px;" align="center">
	<form id="tszq_sjgl_addForm" method="post"
		enctype="multipart/form-data">
		<h3>图书信息</h3>
		<div id="tszq_sjgl_toobar" style="margin-bottom: 15px;">
			图书ISBN: <input id="tszq_sjgl_searchbookisbn" class="easyui-textbox"></input>
			<a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true"
				onclick="insertBookFun();">插入</a>
		</div>

		<div style="margin-bottom: 10px">
			<label class="textbox-label textbox-label-before"
				style="text-align: left; height: 30px; line-height: 30px; display: block; float: left; margin-left: 73px;">图书封面:</label>
			<img id="tszq_sjgl_image1" name="image" alt="" href="#"
				style="cursor: pointer; margin-right: 100px;" class="avatar"
				src="img/default_user.jpg" height="78" width="78">
		</div>

		<div style="margin-bottom: 10px">
			<input id="tszq_sjgl_name1" name="name" class="easyui-textbox"
				required="true" label="图书名称:" style="width: 70%">
		</div>
		<div style="margin-bottom: 10px">
			<input id="tszq_sjgl_author1" name="author" class="easyui-textbox"
				required="true" label="作者:" style="width: 70%">
		</div>

		<div style="margin-bottom: 10px">
			<input id="tszq_sjgl_introduction1" name="introduction"
				data-options="multiline:true" class="easyui-textbox" label="介绍:"
				style="width: 70%; height: 60px;">
		</div>
		<div style="margin-bottom: 10px">
			<input id="tszq_sjgl_clc1" class="easyui-combobox" name="clc"
				label="图书分类:" style="width: 70%"
				data-options="valueField:'id',textField:'text',url:'bookAction!getComboboxItem.action'">
		</div>
		<div style="margin-bottom: 10px">
			<input id="tszq_sjgl_title1" name="title" class="easyui-textbox"
				label="类别:" style="width: 70%">
		</div>
		<div style="margin-bottom: 10px">
			<input id="tszq_sjgl_folio1" name="folio" class="easyui-numberbox"
				label="页数:" data-options="min:0" style="width: 70%">
		</div>
		<div style="margin-bottom: 10px">
			<input id="tszq_sjgl_price1" label="价格:" type="text" name="price"
				class="easyui-numberbox" data-options="min:0,precision:2"
				style="width: 70%">
		</div>

		<div style="margin-bottom: 10px">
			<input id="tszq_sjgl_publisher1" name="publisher"
				class="easyui-textbox" label="出版社:" style="width: 70%">
		</div>
		<div style="margin-bottom: 10px">
			<input id="tszq_sjgl_publishingAddress1" name="publishingAddress"
				class="easyui-textbox" label="出版地址:" style="width: 70%">
		</div>
		<div style="margin-bottom: 10px">
			<input id="tszq_sjgl_publishingTime1" name="publishingTime"
				class="easyui-numberbox" label="出版时间:" data-options="min:0"
				style="width: 70%">
		</div>
		<div style="margin-bottom: 10px;">
			<input id="tszq_sjgl_code1" label="图书编码:" name="code"
				class="easyui-textbox" style="width: 70%">
		</div>
		<div style="margin-bottom: 10px;">
			<input id="tszq_sjgl_isbn1" label="ISBN:" name="Isbn"
				class="easyui-textbox" style="width: 70%">
		</div>
		<div style="margin-bottom: 10px; display: none;">
			<input id="tszq_sjgl_image2" name="image" class="easyui-textbox"
				style="width: 70%">
		</div>
	</form>
</div>
<!-- 修改用户弹窗 -->
<div id="tszq_sjgl_editDialog" class="easyui-dialog"
	data-options="closed:true,modal:true,title:'修改图书信息',buttons:[{
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					$('#tszq_sjgl_editForm').form('submit', {
						url : '${pageContext.request.contextPath}/bookAction!edit.action',
						success : function(r) {
							var obj = jQuery.parseJSON(r);
							if (obj.success) {
								$('#tszq_sjgl_datagrid').datagrid('load');
								$('#tszq_sjgl_datagrid').datagrid('clearChecked');
								$('#tszq_sjgl_editDialog').dialog('close');
							}
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
						}
					});
				}
			}]"
	style="width: 500px; height: 550px;" align="center">
	<form id="tszq_sjgl_editForm" method="post">
		<h3>图书信息</h3>
		<div style="margin-bottom: 10px">
			<input id="tszq_sjgl_name" name="name" class="easyui-textbox"
				required="true" label="图书名称:" style="width: 70%">
		</div>
		<div style="margin-bottom: 10px">
			<input id="tszq_sjgl_author" name="author" class="easyui-textbox"
				required="true" label="作者:" style="width: 70%">
		</div>

		<div style="margin-bottom: 10px">
			<input id="tszq_sjgl_introduction" name="introduction"
				data-options="multiline:true" class="easyui-textbox" label="介绍:"
				style="width: 70%; height: 60px;">
		</div>
		<div style="margin-bottom: 10px">
			<input id="tszq_sjgl_folio" name="folio" class="easyui-numberbox"
				label="页数:" data-options="min:0" style="width: 70%">
		</div>
		<div style="margin-bottom: 10px">
			<input id="tszq_sjgl_price" label="价格:" type="text" name="price"
				class="easyui-textbox" data-options="min:0,precision:2"
				style="width: 70%">
		</div>

		<div style="margin-bottom: 10px">
			<input id="tszq_sjgl_publisher" name="publisher"
				class="easyui-textbox" label="出版社:" style="width: 70%">
		</div>
		<div style="margin-bottom: 10px">
			<input id="tszq_sjgl_publishingTime" name="publishingTime"
				class="easyui-numberbox" label="出版时间:" data-options="min:0"
				style="width: 70%">
		</div>
		<div style="margin-bottom: 10px; display: none;">
			<input id="tszq_sjgl_code" name="code" class="easyui-textbox"
				style="width: 70%">
		</div>
		<div style="margin-bottom: 10px; display: none;">
			<input id="tszq_sjgl_isbn" name="Isbn" class="easyui-textbox"
				style="width: 70%">
		</div>

	</form>
</div>

<div id="tszq_sjgl_layout" class="easyui-layout"
	data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<table id="tszq_sjgl_datagrid"></table>
	</div>
</div>
<script type="text/javascript">
	//初始化图书封面上传控件
	new verUpload({
		files : "#tszq_sjgl_image1",
		name : "uploadbookImg",
		method : "${pageContext.request.contextPath}/bookAction!uploadBookIcon.action",
		load_list : false,
		success : function(d) {
			var obj = jQuery.parseJSON(d);
			if (obj.success) {
				$("#tszq_sjgl_image2").textbox("setValue", obj.obj)
			}
			$.messager.show({
				title : '提示',
				msg : obj.msg
			});
		},
		fail : function(d) {
			console.error(d);
		},
		size : 1024 * 4,
		ext : [ 'jpg', 'jpeg', 'png', 'gif' ]
	});

	function insertBookFun() {
		var isbn = $('#tszq_sjgl_searchbookisbn').val();
		if (isbn != "") {
			$.ajax({
				url : '${pageContext.request.contextPath}/bookAction!getBookInfobyIsbn.action',
				data : {
					Isbn : isbn
				},
				type : 'post',
				dataType : "json",
				success : function(data) {
					if (data.success == true) {
						$('#tszq_sjgl_code1').textbox('setValue', data.obj.code);
						$('#tszq_sjgl_isbn1').textbox('setValue', data.obj.isbn);
						$('#tszq_sjgl_title1').textbox('setValue', data.obj.title);
						$('#tszq_sjgl_name1').textbox('setValue', data.obj.name);
						$('#tszq_sjgl_clc1').combobox('setValue', data.obj.clc);
						$('#tszq_sjgl_author1').textbox('setValue', data.obj.author);
						$('#tszq_sjgl_introduction1').textbox('setValue', data.obj.introduction);
						$('#tszq_sjgl_publisher1').textbox('setValue', data.obj.publisher);
						$('#tszq_sjgl_publishingAddress1').textbox('setValue', data.obj.publishingAddress);
						$('#tszq_sjgl_publishingTime1').numberbox('setValue', data.obj.publishingTime);
						$('#tszq_sjgl_folio1').numberbox('setValue', data.obj.folio);
						$('#tszq_sjgl_price1').numberbox('setValue', data.obj.price);
						$("#tszq_sjgl_image1").attr("src", data.obj.image);
					}
					$.messager.show({
						title : '提示',
						msg : data.msg
					});
				}
			});
		} else {
			$.messager.show({
				title : '提示',
				msg : "请输入图书的ISBN号."
			});
		}
	}

	function searchBookFun() {
		var bookname = $('#tszq_sjgl_bookname').val();
		$('#tszq_sjgl_datagrid').datagrid('load', {
			'bookname' : bookname
		});
	}
	function clearBookFun() {
		$('#tszq_sjgl_bookname').textbox('setValue', '');
		$('#tszq_sjgl_datagrid').datagrid('load', {});

	}

	//新增角色弹窗初始化
	function append() {
		$('#tszq_sjgl_code1').textbox('setValue', "");
		$('#tszq_sjgl_isbn1').textbox('setValue', "");
		$('#tszq_sjgl_title1').textbox('setValue', "");
		$('#tszq_sjgl_name1').textbox('setValue', "");
		$('#tszq_sjgl_clc1').combobox('setValue', "");
		$('#tszq_sjgl_author1').textbox('setValue', "");
		$('#tszq_sjgl_introduction1').textbox('setValue', "");
		$('#tszq_sjgl_publisher1').textbox('setValue', "");
		$('#tszq_sjgl_publishingAddress1').textbox('setValue', "");
		$('#tszq_sjgl_publishingTime1').numberbox('setValue', "");
		$('#tszq_sjgl_folio1').numberbox('setValue', "");
		$('#tszq_sjgl_price1').numberbox('setValue', "");
		$("#tszq_sjgl_image1").attr("src", "img/default_user.jpg");
		$('#tszq_sjgl_addDialog').dialog('open');

	}
	function editBook() {
		var ids = [];
		var rows = $('#tszq_sjgl_datagrid').datagrid('getChecked');
		for (var i = 0; i < rows.length; i++) {
			ids.push(rows[i].id);
		}
		if (ids.length == 1) {//如果选择了修改的行,则执行修改操作
			$('#tszq_sjgl_editDialog').dialog('open');
			$('#tszq_sjgl_code').textbox('setValue', rows[0].code);
			$('#tszq_sjgl_isbn').textbox('setValue', rows[0].isbn);
			$('#tszq_sjgl_name').textbox('setValue', rows[0].name);
			$('#tszq_sjgl_author').textbox('setValue', rows[0].author);
			$('#tszq_sjgl_introduction').textbox('setValue', rows[0].introduction);
			$('#tszq_sjgl_publisher').textbox('setValue', rows[0].publisher);
			$('#tszq_sjgl_publishingTime').numberbox('setValue', rows[0].publishingTime);
			$('#tszq_sjgl_folio').numberbox('setValue', rows[0].folio);
			$('#tszq_sjgl_price').numberbox('setValue', rows[0].price);
		} else if (ids.length >= 2) {
			$.messager.show({
				title : '提示',
				msg : "你只能同时修改一条记录!"
			});
		} else {
			$.messager.show({
				title : '提示',
				msg : "请选择一条记录修改!"
			});
		}

	}

	//批量删除图书
	function removeBook() {
		var ids = [];
		var rows = $('#tszq_sjgl_datagrid').datagrid('getChecked');
		for (var i = 0; i < rows.length; i++) {
			ids.push("'" + rows[i].isbn + "'");
		}
		if (ids.length > 0) {//如果选择了删除的行,则执行删除操作
			$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					$.ajax({
						url : '${pageContext.request.contextPath}/bookAction!removeBook.action',
						data : {
							ids : ids.join(',')
						},
						type : 'post',
						dataType : "json",
						success : function(data) {
							if (data.success == true) {
								$('#tszq_sjgl_datagrid').datagrid('load');
								$('#tszq_sjgl_datagrid').datagrid('clearChecked');
							}
							$.messager.show({
								title : '提示',
								msg : data.msg
							});
						},
						error : function(xhr, textStatus) {
							//先把cookie失效
							//重新刷新页面
						}
					});
				}
			});

		} else {
			$.messager.show({
				title : '提示',
				msg : "请选择需要删除的用户记录!"
			});
		}

	}
    function toexcel() {
    	// export with customized rows
    	$('#tszq_sjgl_datagrid').datagrid('toExcel', {
    	    filename: 'datagrid.xls',
    	    worksheet: 'Worksheet'
    	});
	}
	
	$(function() {
		$('#tszq_sjgl_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/bookAction!datagrid.action',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'isbn',
			striped : "true",
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'name',
			sortOrder : 'asc',
			checkOnSelect : false,
			selectOnCheck : false,
			frozenColumns : [ [ {
				field : 'isbn',
				title : 'ISBN',
				width : 150,
				checkbox : true
			}, {
				field : 'name',
				title : '图书名称',
				sortable : true,
				width : 150

			}, {
				field : 'image',
				title : '图书封面',
				fixed : true,
				width : 150,
				formatter : function(value, row, index) {
					var text = "<img  href=\"#\" style=\"cursor: pointer;\" class=\"avatar\" height=\"50\" width=\"50\" src="+value+">";
					return text;
				}
			} ] ],
			columns : [ [ {
				field : 'author',
				title : '作者',
				width : 150
			}, {
				field : 'introduction',
				title : '图书介绍',
				width : 150,
				formatter : function(value, row, index) {
					var text = '<span title=\"'+value+'\">' + value + '</span>';
					return text;
				}
			}, {
				field : 'folio',
				title : '页数',
				width : 150
			}, {
				field : 'price',
				title : '价格',
				width : 150
			}, {
				field : 'publisher',
				title : '出版社',
				width : 150
			}, {
				field : 'publishingTime',
				title : '出版时间',
				width : 150
			} ] ],
			toolbar : "#tszq_sjgl_toobar"
		});
	});
</script>