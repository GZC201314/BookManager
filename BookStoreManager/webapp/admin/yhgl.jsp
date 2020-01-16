<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="admin_yhgl_layout" class="easyui-layout"
	data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询条件',border:false"
		style="height: 100px;">
		<form id="admin_yhgl_searchForm">
			<div>
				创建时间: <input id="startcreatetime" name="startcreatetime"></input> --
				<input id="endcreatetime" name="endcreatetime"></input> 
				最后修改时间: <input id="startmodifytime" name="startmodifytime"></input> -- 
				<input id="endmodifytime" name="endmodifytime"></input>
				<input id="username" name="username" class="easyui-input"></input>
				<a href="#" class="easyui-linkbutton"
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
<table id="admin_yhgl_datagrid">
</table>
<script type="text/javascript">
	function searchFun() {
		$('#admin_yhgl_datagrid').datagrid('load',
				serializeObject($('#admin_yhgl_searchForm')));
	}
	$(function() {
		//初始化查询创建用户时间
		$('#startcreatetime').datebox().datebox('calendar').calendar(
				{
					validator : function(date) {
						var now = new Date();
						var d1 = new Date(now.getFullYear(), now.getMonth(),
								now.getDate());
						return d1 >= date;
					}
				});
		$('#endcreatetime').datebox().datebox('calendar').calendar(
				{
					validator : function(date) {
						var now = new Date();
						var startTime = $('#startcreatetime').val();
						if (typeof (startTime) != undefined) {
							var d1 = new Date(now.getFullYear(),
									now.getMonth(), now.getDate());
							return d1 >= date && startTime <= date;
						}
						return false;
					}
				});
		//初始化查询最后修改用户时间
		$('#startmodifytime').datebox().datebox('calendar').calendar(
				{
					validator : function(date) {
						var now = new Date();
						var d1 = new Date(now.getFullYear(), now.getMonth(),
								now.getDate());
						return d1 >= date;
					}
				});
		$('#endmodifytime').datebox().datebox('calendar').calendar(
				{
					validator : function(date) {
						var now = new Date();
						var startTime = $('#startmodifytime').val();
						if (typeof (startTime) != undefined) {
							var d1 = new Date(now.getFullYear(),
									now.getMonth(), now.getDate());
							return d1 >= date && startTime <= date;
						}
						return false;
					}
				});
		$('#admin_yhgl_datagrid')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/userAction!datagrid.action',
							fit : true,
							fitColumns : true,
							border : false,
							pagination : true,
							idField : 'id',
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
									remove();
								}
							}, '-', {
								text : '修改',
								iconCls : 'icon-edit',
								handler : function() {
								}
							}, '-' ]
						});
	});
</script>