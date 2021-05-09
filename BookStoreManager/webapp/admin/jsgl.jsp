<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="admin_jsgl_toobar">
    <a onclick="append();" class="easyui-linkbutton"
       data-options="iconCls:'icon-add',plain:true">添加</a> <a
        onclick="editRole();" class="easyui-linkbutton"
        data-options="iconCls:'icon-edit',plain:true">修改</a> <a
        onclick="removerole();" class="easyui-linkbutton"
        data-options="iconCls:'icon-remove',plain:true">删除</a> 角色名称: <input
        id="admin_jsgl_rolename" class="easyui-textbox"></input> <a href="#"
                                                                    class="easyui-linkbutton"
                                                                    data-options="iconCls:'icon-search',plain:true"
                                                                    onclick="searchRoleFun();">查询</a>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-cancel',plain:true" onclick="clearRoleFun();">清空</a>
</div>
<div id="admin_jsgl_addDialog" class="easyui-dialog"
     data-options="closed:true,modal:true,title:'添加用户',buttons:[{
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					$('#admin_jsgl_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/roleAction!add.action',
						onSubmit : function(param){
						addGrandMenuid1(param);
    					},						
						success : function(r) {
							var obj = jQuery.parseJSON(r);
							if (obj.success) {
								$('#admin_jsgl_datagrid').datagrid('insertRow',{
									index:0,
									row:obj.obj
								});
								$('#admin_jsgl_addDialog').dialog('close');
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
    <form id="admin_jsgl_addForm" method="post">
        <table>
            <tr>
                <th>角色名称</th>
                <td><input name="rolename" class="easyui-validatebox"
                           data-options="required:true" validType="validateSameName"/></td>
            </tr>
            <tr>
                <th>备注</th>
                <td><input name="remark"/></td>
            </tr>
            <tr>
                <th>授权菜单</th>
                <td>
                    <ul id="admin_yhgl_add_grandMenutree"></ul>
                </td>
            </tr>
        </table>
    </form>
</div>
<!-- 修改用户弹窗 -->
<div id="admin_jsgl_editDialog" class="easyui-dialog"
     data-options="closed:true,modal:true,title:'修改角色',buttons:[{
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					$('#admin_jsgl_editForm').form('submit', {
						url : '${pageContext.request.contextPath}/roleAction!edit.action',
						onSubmit : function(param){
						addGrandMenuid(param);
    					},
						success : function(r) {
							var msg='';
							var obj = jQuery.parseJSON(r);
							if (obj.obj=='0'||obj.obj=='1') {
								$('#admin_jsgl_datagrid').datagrid('load');
								$('#admin_jsgl_datagrid').datagrid('clearChecked');
								$('#admin_jsgl_editDialog').dialog('close');
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
     style="width: 500px; height: 500px;" align="center">
    <form id="admin_jsgl_editForm" method="post">
        <table>
            <tr>
                <th>角色名称</th>
                <td><input name="rolename" class="easyui-validatebox"
                           data-options="required:true"/></td>
            </tr>
            <tr>
                <th>备注</th>
                <td><input name="remark"/></td>
            </tr>
            <tr>
                <th>授权菜单</th>
                <td>
                    <ul id="admin_yhgl_edit_grandMenutree" name="grandMenus"></ul>
                </td>
            </tr>
            <tr hidden="true">
                <td><input name="roleid"/></td>
            </tr>
            <tr hidden="true">
                <td><input name="oldrolename"/></td>
            </tr>
        </table>
    </form>
</div>

<div id="admin_jsgl_layout" class="easyui-layout"
     data-options="fit:true,border:false">
    <div data-options="region:'center',border:false">
        <table id="admin_jsgl_datagrid"></table>
    </div>
</div>
<script type="text/javascript">

    function addGrandMenuid(param) {
        var nodes = $('#admin_yhgl_edit_grandMenutree').tree('getChecked');
        var id = [];
        var pid = [];
        for (var node in nodes) {
            id.push("'" + nodes[node].id + "'");
            pid.push("'" + nodes[node].pid + "'");
        }
        param.menusId = id.join(',');
        param.menusPid = pid.join(',');
    }

    function addGrandMenuid1(param) {
        var nodes = $('#admin_yhgl_add_grandMenutree').tree('getChecked');
        var id = [];
        for (var node in nodes) {
            id.push("'" + nodes[node].id + "'");
        }
        param.menusId = id.join(',');
    }

    function searchRoleFun() {
        var rolename = $('#admin_jsgl_rolename').val();
        $('#admin_jsgl_datagrid').datagrid('load', {
            'rolename': rolename
        });
    }

    function clearRoleFun() {
        $('#admin_jsgl_rolename').textbox('setText', '');
        $('#admin_jsgl_datagrid').datagrid('load', {});

    }

    //新增角色弹窗初始化
    function append() {
        $('#admin_jsgl_addForm input').val('');
        $('#admin_jsgl_addDialog').dialog('open');

        $('#admin_yhgl_add_grandMenutree').tree({
            url: '${pageContext.request.contextPath}/menuAction!getAllTreeNote.action',
            checkbox: true,
            parentField: 'pid',
            lines: true,
            cascadeCheck: true
        });

    }

    function editRole() {
        var ids = [];
        var rows = $('#admin_jsgl_datagrid').datagrid('getChecked');
        for (var i = 0; i < rows.length; i++) {
            ids.push(rows[i].id);
        }
        if (ids.length == 1) {//如果选择了修改的行,则执行修改操作
            $('#admin_jsgl_editDialog').dialog('open');
            $('#admin_jsgl_editForm input[name=rolename]').val(rows[0].rolename);
            $('#admin_jsgl_editForm input[name=oldrolename]').val(rows[0].rolename);
            $('#admin_jsgl_editForm input[name=remark]').val(rows[0].remark);
            $('#admin_jsgl_editForm input[name=roleid]').val(rows[0].roleid);
            $('#admin_jsgl_editForm input[name=rolename]').validatebox({
                required: true
            });
            $('#admin_yhgl_edit_grandMenutree').tree({
                url: '${pageContext.request.contextPath}/roleAction!getGrandMenus.action',
                checkbox: true,
                parentField: 'pid',
                lines: true,
                cascadeCheck: true,
                onBeforeLoad: function (node, param) {
                    param.roleid = rows[0].roleid;
                },
                onCheck: function (node, checked) {
                    console.log(node);
                }
            });

        } else if (ids.length >= 2) {
            $.messager.show({
                title: '提示',
                msg: "你只能同时修改一个用户!"
            });
        } else {
            $.messager.show({
                title: '提示',
                msg: "请选择一条用户记录修改!"
            });
        }

    }

    //批量删除用户
    function removerole() {
        var ids = [];
        var rows = $('#admin_jsgl_datagrid').datagrid('getChecked');
        for (var i = 0; i < rows.length; i++) {
            ids.push("'" + rows[i].roleid + "'");
        }
        if (ids.length > 0) {//如果选择了删除的行,则执行删除操作
            $.messager.confirm('确认', '您是否要删除当前选中的项目？', function (r) {
                if (r) {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/roleAction!removeRole.action',
                        data: {
                            ids: ids.join(',')
                        },
                        type: 'post',
                        dataType: "json",
                        success: function (data) {
                            if (data.success == true) {
                                $('#admin_jsgl_datagrid').datagrid('load');
                                $('#admin_jsgl_datagrid').datagrid('clearChecked');
                            }
                            $.messager.show({
                                title: '提示',
                                msg: data.msg
                            });
                        }
                    });
                }
            });

        } else {
            $.messager.show({
                title: '提示',
                msg: "请选择需要删除的用户记录!"
            });
        }

    }

    $(function () {

        $('#admin_jsgl_datagrid').datagrid({
            url: '${pageContext.request.contextPath}/roleAction!datagrid.action',
            fit: true,
            fitColumns: true,
            border: false,
            pagination: true,
            idField: 'roleid',
            striped: "true",
            pageSize: 10,
            pageList: [10, 20, 30, 40, 50],
            sortName: 'rolename',
            sortOrder: 'asc',
            /*pagePosition : 'both',*/
            checkOnSelect: false,
            selectOnCheck: false,
            frozenColumns: [[{
                field: 'roleid',
                title: '角色ID',
                width: 150,
                checkbox: true
            }]],
            columns: [[{
                field: 'rolename',
                title: '角色名称',
                sortable: true,
                width: 150
            }, {
                field: 'remark',
                title: '备注',
                width: 150
            }]],
            toolbar: "#admin_jsgl_toobar"
        });
    });
</script>