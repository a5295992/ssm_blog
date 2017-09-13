<%@page import="com.along.common.AnRequest"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../global/global.jsp"%>
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${statics }/awesome/css/font-awesome.css">
	<script type="text/javascript" src="${statics }/easyUI/jquery.easyui.min.js"></script>
<div title="DataGrid" style="padding:5px;width: 100%;height: 100%" >
					
<table id="dg" class="easyui-datagrid" title="菜单列表" style="width:100%;height:100%"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: '${headpath }<%=AnRequest. A_MENU_QUERY%>',
				method: 'get',
				pagination:'true'
			">
		<thead>
			<tr>
						<th data-options="field:'id'" width="80" sortable="true">ID</th>
						<th data-options="field:'name',editor:'textbox'" width="100" sortable="true">名称</th>
						<th data-options="field:'href',width:'18%',
						editor:{
							type:'combogrid',
							options:{
								panelWidth: 500,
								idField: 'url',
								textField: 'url',
								method:'get',
								url:'${headpath }<%=AnRequest. A_URLENTITY_QUERY%>',
								columns: [[
								{field:'id',title:'ID',width:'20%'},
								{field:'description',title:'描述',width:'25%'},
								{field:'url',title:'访问路径',width:'55%',align:'right'},
								]],
								pagination:'true',
								fitColumns: true,
							}
						}">访问路径</th>
						<th data-options="field:'pid',width:'10%',
						formatter:formatOper,
						editor:{
							type:'combogrid',
							options:{
								panelWidth: 500,
								idField: 'id',
								textField: 'name',
								method:'get',
								url:'${headpath }<%=AnRequest. A_MENU_QUERY%>',
								columns: [[
								{field:'id',title:'ID',width:'20%'},
								{field:'name',title:'名称',width:'25%'},
								{field:'remarks',title:'备注',width:'55%',align:'right'},
								]],
								pagination:'true',
								fitColumns: true,
							}
						}">父级菜单</th>
						<th data-options="field:'iconcls',align:'right',editor:'textbox'" width="80">图标</th>
						
						<th data-options="field:'createBy'" width="150">创建人</th>
						<th data-options="field:'createDate',align:'center'" width="10%">创建日期</th>
						<th data-options="field:'updateBy',align:'center'" width="150">更新人</th>
						<th data-options="field:'updateDate',align:'center'" width="10%">更新日期</th>
						<th data-options="field:'remarks',align:'center',editor:'textbox'" width="150">备注</th>
			</tr>
		</thead>
	</table>
	<input type="hidden" value="" name="pid"/>
<!--工具栏  -->
<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">撤销</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChangesssssss()">查看更改</a>
		<span>选择模式: </span>
		<select onchange="modechoose(this.value)">
			<option value="0">单选模式</option>
			<option value="1">复选模式</option>
			<option value="2">编辑模式</option>
		</select>
		
		<input class="easyui-searchbox" data-options="prompt:'输入搜索内容',menu:'#mm',searcher:doSearch" style="width:300px"></input>
		<div id="mm">
		<div data-options="name:'name',iconCls:'icon-ok'">name</div>
		</div>
	</div>

<script type="text/javascript">
	var urlquery =headpath+ "/a/menu/query";//查询统一访问路径
	var urladd =headpath+"/a/menu/add"; //增删改 统一访问路径
	var entitysName = "menus";   //实体集合名
	//格式化数据
	function formatOper(val,row,index){
		if(row.pmenu==null){
			return "";
		}
		return row.pmenu.name;
		
	}
	
	var jsonData = {};
		var editIndex = undefined;
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'pid'});
				var name = $(ed.target).combobox('getText');
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		
		function onClickRow(index){
			if (editIndex != index){
				if (endEditing()){
					$('#dg').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					editIndex = index;
					
				} else {
					$('#dg').datagrid('selectRow', editIndex);
				}
			}
		}
		function append(){
			if (endEditing()){
				$('#dg').datagrid('appendRow',{status:'P'});
				editIndex = $('#dg').datagrid('getRows').length-1;
				$('#dg').datagrid('selectRow', editIndex)
						.datagrid('beginEdit', editIndex);
			}
		}
		function removeit(){
			var rows = $('#dg').datagrid('getSelections');
			if(rows.length>0){
				$.messager.confirm("提示","确定删除吗?",function(confirm){
					if(confirm){
						var urlEntitys = getChanges(rows,"1");
						var data ={};
						data[entitysName]=JSON.stringify(urlEntitys);
						var result = submitAjax(data,urladd);
						callbacka_(result);
						$('#dg').datagrid('reload');
					}else{
						reject();
					}
				});
			}
			/* if (editIndex == undefined){return}
			$('#dg').datagrid('cancelEdit', editIndex)
					.datagrid('deleteRow', editIndex);
			editIndex = undefined; */
		}
		
		function accept(){
			endEditing();
			var rows = $('#dg').datagrid('getChanges');
			if(rows.length>0){
				$.messager.confirm("提示","一共修改了"+rows.length+"项,确定操作吗?",function(comfirm){
					if(comfirm){
						var rows = $('#dg').datagrid('getChanges');
						var urlEntitys = getChanges(rows,null);
						var data ={};
						data[entitysName]=JSON.stringify(urlEntitys);
						
						var result = submitAjax(data,urladd);
						
						callbacka_(result);
						//刷新数据
						$('#dg').datagrid('reload');
					}else {
						reject();
					}
				});
			}
		}
		function reject(){
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
		}
		function getChanges(rows,delFlag){
			var urlEntitys = {};
			for (var int = 0; int < rows.length; int++) {
				var urlEntity = {};
				var dg = rows[int];
				urlEntity['id']=dg.id;
				if(delFlag!=null){
					urlEntity['delFlag']=delFlag;
				}
					urlEntity['name']=dg.name;
					urlEntity['href']=dg.href;
					urlEntity['pid']=dg.pid;
					urlEntity['iconcls']=dg.iconcls;
					urlEntity['remarks']=dg.remarks;
					
					/* urlEntity['createBy']=dg.createBy;
					urlEntity['createDate']=dg.createDate;
					urlEntity['updateDate']=dg.updateDate; */
				
				urlEntitys[int]=urlEntity;
			}
			console.log("urlEntitys:"+urlEntitys);
			return urlEntitys;
		}
		
		function getChangesssssss(){
			var rows = $('#dg').datagrid('getChanges');
			$.messager.alert("提示","一共修改了"+rows.length+"行数据");
		}
		
		function submitAjax(data,url){
			var result=null;
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:url,
		        data:data,// 你的formid
		        async:false,
		        error: function(request) {
		            window.confirm("抱歉！ 服务器繁忙");
		        },
		        success: function(data) {
		        	result =data;
		        }
		    });
			return result;
		}
		
		
		function callbacka_(result){
			if(result!=null){
				var resultJson = JSON.parse(result);
				var successCount = resultJson['successCount'];
				/* var successMessage = resultJson['successMessage']; */
				var failCount = resultJson['failCount'];
				var failMessage = resultJson['failMessage'];
				var cause="";
				for (var int = 0; int < failMessage.length; int++) {
					cause="<p>"+cause+failMessage[int]+"</p>";
				}
				$.messager.alert("提示信息","<p>成功:"+successCount+" 失败:"+failCount+"</p>"+cause);
			}
		}
		
		function modechoose(value){
			switch(value){
			case '0':
				$('#dg').datagrid({'singleSelect': true});
				break;
			case '1':
				$('#dg').datagrid({'onClickRow': empty});
				$('#dg').datagrid({'singleSelect': false});
				break;
			default:
					$('#dg').datagrid({'onClickRow': onClickRow});
					$('#dg').datagrid({'singleSelect': true});
				break;
			}
			
		}
		function empty(){
			
		}
		/* 查询*/
		function doSearch(value,name){
			var data = {};
			data[name]=value;
			//获取查询参数
			//执行查询
			var  msg = submitAjax({'data':JSON.stringify(data)},urlquery);
			$('#dg').datagrid('loadData',JSON.parse(msg));
		}
	</script>

