<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!-- 新增用户弹窗 -->
<div id="admin_yhgl_addDialog" class="easyui-dialog"
	data-options="closed:true,modal:true,title:'添加用户',buttons:[{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					$('#admin_yhgl_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/userAction!add.action',
						success : function(r) {
							var obj = jQuery.parseJSON(r);
							if (obj.success) {
								$('#admin_yhgl_datagrid').datagrid('insertRow',{
									index:0,
									row:obj.obj
								});
								$('#admin_yhgl_addDialog').dialog('close');
							}
							$.messager.show({
								title : '提示',
								msg : obj.msg
							});
						}
					});
				}
			}]"
	style="width: 500px; height: 200px;" align="center">
	<form id="admin_yhgl_addForm" method="post">
		<table>
			<tr>
				<th>登录名称</th>
				<td><input name="name" class="easyui-validatebox"
					data-options="required:true" validType="validateSameName" /></td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="pwd" type="password"
					class="easyui-validatebox" data-options="required:true"></td>
			</tr>
			<tr>
				<th>角色</th>
				<td><input id="roleid1" name="roleid" /></td>
			</tr>

		</table>
	</form>
</div>
<!-- 修改用户弹窗 -->
<div id="admin_yhgl_editDialog" class="easyui-dialog"
	data-options="closed:true,modal:true,title:'修改用户',buttons:[{
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					$('#admin_yhgl_editForm').form('submit', {
						url : '${pageContext.request.contextPath}/userAction!edit.action',
						success : function(r) {
							var msg='';
							var obj = jQuery.parseJSON(r);
							if (obj.obj=='0'||obj.obj=='1') {
								$('#admin_yhgl_datagrid').datagrid('load');
								$('#admin_yhgl_datagrid').datagrid('clearChecked');
								$('#admin_yhgl_editDialog').dialog('close');
								msg='修改用户成功.';
							}else if(obj.obj=='2'){
								msg='修改的用户名已存在,请换一个.';
							}
							$.messager.show({
								title : '提示',
								msg : msg
							});
						}
					});
				}
			}]"
	style="width: 500px; height: 200px;" align="center">
	<form id="admin_yhgl_editForm" method="post">
		<table>
			<tr>
				<th>登录名称</th>
				<td><input name="name" class="easyui-validatebox"
					data-options="required:true" /></td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="pwd" type="password" /></td>
			</tr>
			<tr>
				<th>角色</th>
				<td><input id="roleid" name="roleid" /></td>
			</tr>
			<tr hidden="true">
				<td><input name="oldname" /></td>
			</tr>

		</table>
	</form>
	<label style="color: red;">如果你不想修改密码,请置空. </label>
</div>

<div id="admin_yhgl_layout" class="easyui-layout"
	data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询条件',border:false"
		style="height: 100px;">
		<form id="admin_yhgl_searchForm" style="margin-top: 25px;">
			<div>
				创建时间: <input id="startcreatetime" name="startcreatetime"></input> --
				<input id="endcreatetime" name="endcreatetime"></input> 最后修改时间: <input
					id="startmodifytime" name="startmodifytime"></input> -- <input
					id="endmodifytime" name="endmodifytime"></input> 登录名称: <input
					id="username" name="username"></input> <a href="#"
					class="easyui-linkbutton"
					data-options="iconCls:'icon-search',plain:true"
					onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-cancel',plain:true"
					onclick="clearFun();">清空</a>
			</div>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="admin_yhgl_datagrid"></table>
	</div>
</div>
<script type="text/javascript">

	function searchFun() {
		$('#admin_yhgl_datagrid').datagrid('load', serializeObject($('#admin_yhgl_searchForm')));
	}
	function clearFun() {
		$('#startcreatetime').datebox('setValue', '');
		$('#endcreatetime').datebox('setValue', '');
		$('#startmodifytime').datebox('setValue', '');
		$('#endmodifytime').datebox('setValue', '');
		$('#username').val('');
		$('#admin_yhgl_datagrid').datagrid('load');

	}

	$(function() {

		$('#roleid').combobox({
			url : '${pageContext.request.contextPath}/roleAction!getComboboxItem.action',
			valueField : 'id',
			textField : 'text'
		});
		$('#roleid1').combobox({
			url : '${pageContext.request.contextPath}/roleAction!getComboboxItem.action',
			valueField : 'id',
			textField : 'text'
		});
		//新增用户弹窗初始化
		function append() {
			$('#admin_yhgl_addForm input').val('');
			$('#admin_yhgl_addDialog').dialog('open');
		}
		//初始化查询创建用户时间
		$('#startcreatetime').datebox().datebox('calendar').calendar({
			validator : function(date) {
				var now = new Date();
				var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
				return d1 >= date;
			}
		});
		$('#endcreatetime').datebox().datebox('calendar').calendar({
			validator : function(date) {
				var now = new Date();
				var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
				return d1 >= date;
			}
		});
		//初始化查询最后修改用户时间
		$('#startmodifytime').datebox().datebox('calendar').calendar({
			validator : function(date) {
				var now = new Date();
				var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
				return d1 >= date;
			}
		});
		$('#endmodifytime').datebox().datebox('calendar').calendar({
			validator : function(date) {
				var now = new Date();
				var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
				return d1 >= date;
			}
		});

		//修改用户
		function editUser() {
			var ids = [];
			var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');
			for (var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			if (ids.length == 1) {//如果选择了修改的行,则执行修改操作
				$('#admin_yhgl_editDialog').dialog('open');
				$('#admin_yhgl_editForm input[name=name]').val(rows[0].name);
				$('#admin_yhgl_editForm input[name=oldname]').val(rows[0].name);
				$('#admin_yhgl_editForm input[name=pwd]').val('');
				$('#admin_yhgl_editForm input[name=name]').validatebox({
					required : true
				});
				$('#roleid').combobox('setValue', rows[0].roleid);

			} else if (ids.length >= 2) {
				$.messager.show({
					title : '提示',
					msg : "你只能同时修改一个用户!"
				});
			} else {
				$.messager.show({
					title : '提示',
					msg : "请选择一条用户记录修改!"
				});
			}
		}
		//批量删除用户
		function removeuser() {
			var ids = [];
			var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');
			for (var i = 0; i < rows.length; i++) {
				ids.push("'" + rows[i].id + "'");
			}
			if (ids.length > 0) {//如果选择了删除的行,则执行删除操作
				$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
					if (r) {
						$.ajax({
							url : '${pageContext.request.contextPath}/userAction!removeUser.action',
							data : {
								ids : ids.join(',')
							},
							type : 'post',
							dataType : "json",
							success : function(data) {
								if (data.success == true) {
									$('#admin_yhgl_datagrid').datagrid('load');
									$('#admin_yhgl_datagrid').datagrid('clearChecked');
								}
								$.messager.show({
									title : '提示',
									msg : data.msg
								});
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
		$('#admin_yhgl_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/userAction!datagrid.action',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			striped : "true",
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'name',
			sortOrder : 'asc',
			/*pagePosition : 'both',*/
			checkOnSelect : false,
			selectOnCheck : false,
			frozenColumns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				checkbox : true
			}, {
				field : 'name',
				title : '登录名称',
				width : 150,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'pwd',
				title : '密码',
				width : 150,
				formatter : function(value, row, index) {
					return '******';
				}
			}, {
				field : 'roleid',
				title : '角色',
				width : 150,
				formatter : function(value, row, index) {
					var text = "";
					switch (value) {
					case '0':
						text = "超级管理员";
						break;
					case '1':
						text = "管理员";
						break;
					case '2':
						text = "operater";
						break;
					case '3':
						text = "seller";
						break;
					default:
						text = "other";
						break;
					}
					return text;
				}
			}, {
				field : 'createdatetime',
				title : '创建时间',
				width : 150,
				sortable : true
			}, {
				field : 'lastmodifytime',
				title : '最后修改时间',
				width : 150,
				sortable : true
			} ] ],
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					append();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					removeuser();
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					editUser();
				}
			}, '-' ]
		});
	});
</script>