<%@page import="com.along.common.AnRequest"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../global/global.jsp"%>
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${statics }/awesome/css/font-awesome.css">
	<script type="text/javascript" src="${statics }/easyUI/jquery.easyui.min.js"></script>
	<script>
	window.UEDITOR_HOME_URL=headpath+"/statics/ueditor/";
	</script>		
<head>
    <title>博客编辑</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" charset="utf-8" src="${headpath }/statics/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${headpath }/statics/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${headpath }/statics/ueditor/lang/zh-cn/zh-cn.js"></script>

    <style type="text/css">
        div{
            width:100%;
        }
    </style>
</head>
<body>
	
	<form action="${headpath }<%=AnRequest. A_ARTICLE_ADD%>" method="POST">
	<div class="easyui-layout" style="width:100%;height:100%;">
		<div data-options="region:'west',split:true" title="选项" style="width:180px;">
			
			
			<div style="margin:10px">
				<span>输入标题:</span>
				<input type="text" class="easyui-textbox" name="name" style="width: 150px;" />
			</div>
			
			<div style="margin:10px">
				<span>博客名称:</span>
				<input type="text" class="easyui-textbox" name="blogName" style="width: 150px;" readonly="readonly" value="${blog.name}"/>
				<input type="text" class="easyui-textbox" name="blogId" style="width: 150px;" readonly="readonly" value="${blog.id}"/>
			</div>
			<div style="margin:10px">
				<span>选择分类:</span>
				<select id="selectId" class="easyui-combogrid" style="width:150px" data-options="
						panelWidth: 300,
						idField: 'id',
						textField: 'name',
						url: '${headpath }<%=AnRequest. A_ARTICLE_CATEGORY_QUERY%>',
						method: 'get',
						columns: [[
							{field:'name',title:'分类',width:60}
						]],
						fitColumns: true,
						pagination:true
					">
				</select>
			</div>
			<div style="margin:10px">
				是否公开:<input type="checkbox" name="open" value="0">
			</div>
			
			<div style="margin:10px">
				<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 150px;background: #ACBFDE" data-options="plain:true" onclick="append()">提交</a>
			</div>
			
		</div>
		<div data-options="region:'center',iconCls:'icon-ok'" title="编辑器">
		<!--editorValue  -->
			<script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
			</div>
			
		</div>
	
	<script type="text/javascript">
	 var ue = UE.getEditor('editor');
	 var entitysName ="articles";
	 var urladd =headpath+"/a/article/add"; //增删改 统一访问路径
		function append(){
			console.log("提交数据");
			var articles = getChanges();
			var data ={};
			data[entitysName]=JSON.stringify(articles);
			
			var result = submitAjax(data,urladd);
			
			if(result!=null){
				var resultJson = JSON.parse(result);
				var successCount = resultJson['successCount'];
				if(successCount>0){
					$.messager.alert("提示:","发布成功");
					
				}else{
					$.messager.alert("提示:","发布失败");
				}
			}
		}
		
		function getContent() {
		    return UE.getEditor('editor').getContent();
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
		
		function getChanges(){
			
			var urlEntitys = {};
			var name =$("input[name='name']").val();
			var category_id =getValue();
			var content = getContent();
			var blogId  =$("input[name='blogId']").val();
			var urlEntity = {};
				urlEntity['name']=name;
				urlEntity['category_id']=category_id;
				urlEntity['content']=content;
				urlEntity['blogId']=blogId;
				urlEntitys['1']=urlEntity;
			console.log("articles:"+urlEntitys);
			return urlEntitys;
		}
		
		function getValue(){
			var val = $('#selectId').combogrid('getValue');
			return val;
		}
	</script>

<!-- <div id="btns">
    <div>
        <button onclick="getAllHtml()">获得整个html的内容</button>
        <button onclick="getContent()">获得内容</button>
        <button onclick="setContent()">写入内容</button>
        <button onclick="setContent(true)">追加内容</button>
        <button onclick="getContentTxt()">获得纯文本</button>
        <button onclick="getPlainTxt()">获得带格式的纯文本</button>
        <button onclick="hasContent()">判断是否有内容</button>
        <button onclick="setFocus()">使编辑器获得焦点</button>
        <button onmousedown="isFocus(event)">编辑器是否获得焦点</button>
        <button onmousedown="setblur(event)" >编辑器失去焦点</button>

    </div>
    <div>
        <button onclick="getText()">获得当前选中的文本</button>
        <button onclick="insertHtml()">插入给定的内容</button>
        <button id="enable" onclick="setEnabled()">可以编辑</button>
        <button onclick="setDisabled()">不可编辑</button>
        <button onclick=" UE.getEditor('editor').setHide()">隐藏编辑器</button>
        <button onclick=" UE.getEditor('editor').setShow()">显示编辑器</button>
        <button onclick=" UE.getEditor('editor').setHeight(300)">设置高度为300默认关闭了自动长高</button>
    </div>

    <div>
        <button onclick="getLocalData()" >获取草稿箱内容</button>
        <button onclick="clearLocalData()" >清空草稿箱</button>
    </div>

</div>
<div>
    <button onclick="createEditor()">
    创建编辑器</button>
    <button onclick="deleteEditor()">
    删除编辑器</button>
</div>

<script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');


    function isFocus(e){
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e){
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }
    function insertHtml() {
        var value = prompt('插入html代码', '');
        UE.getEditor('editor').execCommand('insertHtml', value)
    }
    function createEditor() {
        enableBtn();
        UE.getEditor('editor');
    }
    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
        UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function getLocalData () {
        alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
    }

    function clearLocalData () {
        UE.getEditor('editor').execCommand( "clearlocaldata" );
        alert("已清空草稿箱")
    }
</script>
 -->
 
 </form>
</body>
