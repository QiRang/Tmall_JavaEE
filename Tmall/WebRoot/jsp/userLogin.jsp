<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userLogin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style>
		.loginBackgroundImg{margin:0 auto;display:block;}
		.form{position:absolute;float:right;right:200px;top:150px;background-color:#ffffff;height:400px;width:300px;padding:50px 20px 0 20px;}
		.form input{width:280px;height:40px;padding-left:40px;}
		.form div{padding:0 10px 20px 10px;}
		#submit{padding-right:40px;}
		.form a{font-size:12px;text-decoration:none;padding:0 5px;color:rgb(108, 108, 108);}
		.form a:hover{color:#ff0000;}
	</style>
	<script src="js/jquery/jquery.min.js"></script>
	<script>
	$(function(){
		$("*[name='submit']").click(function(){
			var name = $("*[name='name']").val();
			var password = $("*[name='password']").val();
			if(name == "" || name == null || name == undefined){
				alert("请输入用户名");
				$("*[name='name']").focus();
				return false;
			} else if(password == "" || password == null || password==undefined){
				alert("请输入密码");
				$("*[name='password']").focus();
				return false;
			} else {
				return true;
			}
		});
	});
	</script>
	
  </head>
  
  <body>
    <div class="logo">
      <img style="padding:10px 0 40px 160px;width:200px;" src="//img.alicdn.com/tfs/TB1_Gn8RXXXXXXqaFXXXXXXXXXX-380-54.png">
    </div>
    <div style="background-color:rgb(254, 199, 239);">
      <img id="loginBackgroundImg" class="loginBackgroundImg" src="https://img.alicdn.com/tfs/TB1IR2clIfpK1RjSZFOXXa6nFXa-1190-600.jpg">
    </div>
    <div class="form">
    	<div style="padding:0 10px 0 10px;">
    		<span style="font-size:18px;">密码登陆</span><br>
    		<span style="padding:10px 0 10px 40px;position:relative;">
    			<span></span>
    			<span style="width:280px;padding:2px 0; display:block;color:rgb(255,0,45);font-size:12px;">&nbsp;&nbsp;${loginResult}</span>
    		</span>
    	</div>
    	<form action="userServlet?action=login" method="post">
    		<div style="position:relative;">
    			<label style="position:absolute;float:left;left:10px;width:40px;height:40px;line-height:40px;background-color:rgb(169, 169, 169);text-align:center;">账号</label>
    			<input type="text" id="name" placeholder="&nbsp;&nbsp;会员名/邮箱/手机号" name="name">
    		</div>
    		<div style="position:relative;">
    			<label style="position:absolute;float:left;left:10px;width:40px;height:40px;line-height:40px;background-color:rgb(169, 169, 169);text-align:center;">密码</label>
    			<input type="password" name="password" id="password">
    		</div>
    		<div>
    			<input id="submit" name="submit" class="submitUserLogin" type="submit" value="登&nbsp;陆" style="background-color:rgb(255,0,54);color:#ffffff;border:0;">
    		</div>
	  </form>
	  <div style="display:flex;flex-direction:row;justify-content:flex-end;margin-top:30px;"><a href="javascript:;">忘记密码</a><a href="javascript:;">忘记会员名</a><a href="../Tmall/jsp/register.jsp">免费注册</a></div>
    </div>
  </body>
</html>
