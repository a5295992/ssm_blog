<%@page import="com.along.common.AnRequest"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../global/global.jsp"%>
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${statics }/awesome/css/font-awesome.css">
	<script type="text/javascript" src="${statics }/easyUI/jquery.easyui.min.js"></script>
<div title="DataGrid" style="padding:5px;width: 100%;height: 100%" >
					
<%-- <table id='dg' class="easyui-datagrid"
		style="width:100%;height:100%"
		url="${headpath }<%=AnRequest. A_URLENTITY_QUERY%>"
		title="路径管理" 
		iconCls="icon-search"
		rownumbers="true"
		pagination="true"
		toolbar="#tb"
		singleSelect='true'
		onClickRow="onClickRow">
			<thead>
				<tr>
						<th data-options="field:'id'" width="80" sortable="true">ID</th>
						<th data-options="field:'name',editor:'textbox'" width="100" sortable="true">名称</th>
						<th data-options="field:'title',editor:'textbox',align:'right'" width="80">标题</th>
						<th data-options="field:'url',editor:'textbox',align:'right'" width="80">全称</th>
						<th data-options="field:'methodType'" width="150">请求方法</th>
						<th data-options="field:'description',editor:'textbox',align:'center'" width="150">描述</th>
						<th data-options="field:'fileName',editor:'textbox',align:'center'" width="150">文件名</th>
						<th data-options="field:'fileType',align:'center'" width="150">文件类型</th>
						<th data-options="field:'filePath',align:'center'" width="150">文件路径</th>
						
						<th data-options="field:'listprice',width:80,align:'right',editor:{type:'numberbox',options:{precision:1}}">List Price</th>
						<th data-options="field:'unitcost',width:80,align:'right',editor:'numberbox'">Unit Cost</th>
						<th data-options="field:'attr1',width:250,editor:'textbox'">Attribute</th>
						<th data-options="field:'status',width:60,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">Status</th> 
				</tr>
			</thead>
</table> --%>
<!--工具栏  -->
	
		<table id="dg" class="easyui-datagrid" title="博客列表" style="width:100%;height:100%"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				url: '${headpath }<%=AnRequest. A_BLOG_QUERY%>',
				method: 'get',
				pagination:'true'
			">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id'" width="80" sortable="true">ID</th>
				<th data-options="field:'description',editor:'textbox',align:'center'" width="20%" >标题</th>
				<th data-options="field:'category',editor:'textbox',align:'center'" width="15%">分类</th>
				<th data-options="field:'createBy',width:60,align:'center',editor:{type:'checkbox',options:{on:'GET',off:'POST'}}">用户名</th>
				<th data-options="field:'status',width:60,align:'center',editor:{type:'checkbox',options:{on:'可用',off:'不可用'}}">状态</th>
				<th data-options="field:'remarks',editor:'textbox',align:'center'" width="15%">备注</th>
				<th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">访问</th>  
			</tr>
		</thead>
	</table>
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
	var urlquery =headpath+ "/a/blog/query";//查询统一访问路径
	var urladd =headpath+"/a/blog/add"; //增删改 统一访问路径
	var entitysName = "blogs";   //实体集合名
	
	function formatOper(val,row,index){  
	    return '<a href="#" onclick="testUrl('+index+')">测试</a>';  
	}  
	
	var jsonData = {};
		var editIndex = undefined;
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				
				/* var editors = $('#dg').datagrid('getEditors',editIndex);
				for (var int = 0; int < editors.length; int++) {
					console.info("thid value"+editors[int].target.val());
				} */
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
					urlEntity['title']=dg.title;
					urlEntity['url']=dg.url;
					urlEntity['catagory_id']=dg.methodType;
					urlEntity['description']=dg.description;
				
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
		
		function submitAjax(data,url,type,async){
			var result=null;
			$.ajax({
		        cache: true,
		        type: type,
		        url:url,
		        data:data,// 你的formid
		        async:async,
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
				var failCount   = resultJson['failCount'];
				var failMessage = resultJson['failMessage'];
				var addCount    = resultJson['addCount'];
				var deleCount   = resultJson['deleCount'];
				var queryCount  = resultJson['queryCount'];
				var updateCount = resultJson['updateCount'];
				var cause="";
				for (var int = 0; int < failMessage.length; int++) {
					cause="<p>"+cause+failMessage[int]+"</p>";
				}
				$.messager.alert("提示信息","<p>成功:"+successCount+
						                    " 失败:"+failCount+
						                    "添加:"+ addCount+
						                    "删除:"+ deleCount+
						                    "更新:"+ updateCount+
						                    "查询:"+ queryCount+
						                    "</p>"+cause);
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
</div>