<%@page import="com.along.common.AnRequest"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../global/global.jsp"%>
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${statics }/easyUI/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${statics }/awesome/css/font-awesome.css">
	<script type="text/javascript" src="${statics }/easyUI/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${statics }/jquery/validate.min.js"></script>
					
		<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="个人信息" style="width:500px;padding:30px 70px 50px 70px">
		<div style="margin-bottom:20px">
			<div>姓名:</div>
			<input class="easyui-textbox" name = "name" id = "name" style="width:100%" value="${user.name }" required="required">
		</div>
		<div style="margin-bottom:20px">
			<div>登录账号:</div>
			<input class="easyui-textbox" style="width:100%" value="${user.loginName }" readonly="readonly" id="loginName" value="${user.loginName }">
		</div>
		<div style="margin-bottom:20px">
			<div>密码:<a href="#" onclick="addConfimPassoword()">修改</a></div>
			<input class="easyui-passwordbox" name="password" id = "password" style="width:100%" value="******" disabled="disabled">
		</div>
		<div style="margin-bottom:20px;display: none" id="passwComfirmDiv">
			<div>确认密码:<a href="#" onclick="compareTwicePassw()">确认</a></div>
			<input class="easyui-passwordbox" name="password2" id = "password_confirm" style="width:100%" >
		</div>
		<div style="margin-bottom:20px">
			<div>头像:</div>
			<p><img alt="" src="${headpath }${user.photo}"></p>
			<input class="easyui-filebox" name="photo"  id = "photo" data-options="prompt:'Choose another file...'" style="width:100%">
		</div>
		<div style="margin-bottom:20px">
			<div>身份:</div>
			<input class="easyui-textbox"  value="${user.role.name }" readonly="readonly" style="width:100%">
		</div>
		
		<div style="margin-bottom:20px">
			<div>邮箱:</div>
			<input class="easyui-textbox" name="email"  id = "email" value="${user.email }" style="width:100%">
		</div>
		<div style="margin-bottom:20px">
			<div>手机:</div>
			<input class="easyui-textbox" name="phone"  id = "phone" value="${user.phone }" style="width:100%">
		</div>
		<div style="margin-bottom:20px">
			<div>介绍:</div>
			<input class="easyui-textbox" name="remarks"  id = "remarks" value="${user.remarks }" style="width:100%">
		</div>
		<div>
			<a href="#" id="complet" class="easyui-linkbutton"  style="width:100%" onclick="fsu()">完成</a>
		</div>
	</div>
	
	<input type="hidden" name="id"  id = "id" value="${user.id }"/>

	<script type="text/javascript">
	var redirectIndex  = headpath+"/a/index";
	var checkIsChangePs= headpath+"/a/user/update";
	var comfirmpsUrl = headpath+"/a/user/query";
	var submitUrl = headpath +"/a/user/add";//提交的url	
	var entitysName = "users";   //提交的名字
	
	
	var params= ["#name","#password","#email","#phone","#remarks","#photo","#loginName"];//要更改的字段
	var constraint_default = {"notNull":true,"min":5,"max":20};
	var constraints= [constraint_default,
	                  constraint_default,
	                  {"email":true,"notNull":true},
	                  {"phone":true,"notNull":true},
	                  {"max":50},
	                  {"max":50},
	                  {}];//要更改的字段
	function fsu(){
	    	var user   = getForm();
			var id = $('#id').val();
			user['id']=id;
			console.log(user);
			var userss = {};   userss['1']=user;
			var data   = {};   data[entitysName]=JSON.stringify(userss);
			if(user!=null){
				$.messager.prompt('操作提示', '继续操作需要输入您的密码', function(r){
					if (r){
						var param ={}; 
						param['password']=r;
						param['loginName']=$("#loginName").val();
						if(comfirmPassword({'data':JSON.stringify(param)},comfirmpsUrl)){
							var result = submitAjax(data,submitUrl,false,"POST");
							
							callbacka_(result);
						}else{
							$.messager.alert("提示","密码错误");
						}
						
					}
				});
			}
	}
	
	//封装 为json
	function getForm(){
		var result = FormValidate.validate(params,constraints);
		if(result['success']){
			return result['entity'];
		}else{
			var  fails = result['fails'];
			var content = "";
			for (var int = 0; int < fails.length; int++) {
				for ( var fai in fails[int]) {
					content=content+fails[int][fai];
				}
			}
			if(content!=''){
				$.messager.alert("提示",content);
			}
			return null;
		}
	}
	
	function submitAjax(data,url, isAsync,method){
		var result=null;
		$.ajax({
	        cache: true,
	        type: method,
	        url:url,
	        data:data,// 你的formid
	        async:isAsync,
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
			
			setTimeout(function(){
				if(successCount>0){
					 var result1 = submitAjax("",checkIsChangePs, false,"GET");//用来检测是否更改了密码
					 
					 if(result1=="logout"){
						 $.messager.alert("提示","当前登录用户信息已过期");
						 setTimeout(function(){
							 parent.window.location.href=redirectIndex;
						 }, 3000);
					 }
				}
			}, 2000);
			
		}
	}
	
	
	function addConfimPassoword(){
		$("#complet").linkbutton({disabled:true});
		$("#password").passwordbox({disabled:false,value:""});
		$("#passwComfirmDiv").css("display","block");
		
	}
	
	function compareTwicePassw(){
		var pas =$('#password').val();
		var pas2 = $('#password_confirm').val();
		var msg = (pas==pas2)?"":"两次密码不一致";
		if(msg!=""){$.messager.alert("提示",msg);}else{
			$("#complet").linkbutton({disabled:false});
			$("#passwComfirmDiv").css("display","none");
		}
		
	}
	
	function comfirmPassword(ps,url){
		var result = submitAjax(ps,url,false,"POST");
		if(JSON.parse(result)['total']>0){
			return true;
		}else {
			return false;
		}
	}
	</script>
