<%@page import="com.along.common.AnRequest"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../global/global.jsp"%>
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${statics }/awesome/css/font-awesome.css">
	<script type="text/javascript" src="${statics }/easyUI/jquery.easyui.min.js"></script>
<div title="DataGrid" style="padding:5px;width: 100%;height: 100%" >
					
<table class="easyui-datagrid"
		style="width:100%;height:100%"
		url="${headpath }<%=AnRequest. A_MENU_QUERY%>"
		title="菜单管理" iconCls="icon-search"
		rownumbers="true" pagination="true"
		toolbar="#tb">
			<thead>
				<tr>
						<th data-options="field:'id'" width="80" sortable="true">ID</th>
						<th data-options="field:'name'" width="100" sortable="true">名称</th>
						<th data-options="field:'href',align:'right'" width="80">访问地址</th>
						<th data-options="field:'pid',align:'right'" width="80">父级ID</th>
						
						
						<th data-options="field:'createBy'" width="150">创建人</th>
						<th data-options="field:'createDate',align:'center'" width="150">创建日期</th>
						<th data-options="field:'updateBy',align:'center'" width="150">更新人</th>
						<th data-options="field:'updateDate',align:'center'" width="150">更新日期</th>
						<th data-options="field:'remarks',align:'center'" width="150">备注</th>
						<th data-options="field:'delFlag',align:'center'" width="150">删除标记</th>
				</tr>
			</thead>
</table>
<!--工具栏  -->
	<div id="tb">
	<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRole()">添加</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateRole()">修改</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveRoles()">保存</a>
	<a href="#" class="easyui-linkbutton" iconCls="fa fa-trash" plain="true" onclick="deleRole()">删除</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="allPermissions()">权限列表</a>
    </div>
</div>