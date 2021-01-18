function searchFun() {
	$('#admin_yhgl_datagrid').datagrid('load',
			serializeObject($('#admin_yhgl_searchForm')));
}

$(function() {
	$('#startcreatetime').datebox().datebox('calendar').calendar({
		validator : function(date) {
			var now = new Date();
			var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
			var d2 = new Date(now.getFullYear(), now.getMonth(), now.getDate()
							+ 10);
			return d1 <= date && date <= d2;
		}
	});
});

$(function() {
	// 初始化查询创建时间

	$('#admin_yhgl_datagrid').datagrid({
				url : '${pageContext.request.contextPath}/userAction!datagrid.action',
				fit : true,
				fitColumns : true,
				border : false,
				pagination : true,
				idField : 'id',
				pageSize : 10,
				pageList : [10, 20, 30, 40, 50],
				sortName : 'name',
				sortOrder : 'asc',
				/* pagePosition : 'both', */
				checkOnSelect : false,
				selectOnCheck : false,
				frozenColumns : [[{
							field : 'id',
							title : '编号',
							width : 150,
							checkbox : true
						}, {
							field : 'name',
							title : '登录名称',
							width : 150,
							sortable : true
						}]],
				columns : [[{
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
						}]],
				toolbar : [{
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
						}, '-']
			});
});