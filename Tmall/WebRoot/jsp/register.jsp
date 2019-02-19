<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>天猫注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<script type="text/javascript">
		function registerCheck(){
			if(document.registerForm.name.value === ""){
				alert("请输入会员名");
				document.registerForm.name.focus();
				return false;
			} else if(document.registerForm.password.value === ""){
				alert("请输入密码");
				document.registerForm.password.focus();
				return false;
			} else if(document.registerForm.repeatPassword.value === ""){
				alert("请输入确认密码");
				document.registerForm.repeatPassword.focus();
				return false;
			} else if(document.registerForm.repeatPassword.value!=document.registerForm.password.value){
				alert("两次密码不一样");
				document.registerForm.repeatPassword.focus();
				return false;
			} else {
				return true;
			}			
		}
	</script>	
  </head>
  	
  <body>
  	<!-- header -->
    <%@include file="header.jsp" %>
    <div>
	    <form action="userServlet?action=register" method="post"  class="registerForm" id="registerForm" name="registerForm" onsubmit="return registerCheck()">
			<div class="registerDiv">
				<div class="registerErrorMessageDiv">
					<div class="alert alert-danger" role="alert">
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
					  	<span class="errorMessage"></span>
					</div>		
				</div>
				<table class="registerTable" align="center">
					<tr>
						<td  class="registerTip registerTableLeftTD">设置会员名</td>
						<td></td>
					</tr>
					<tr>
						<td class="registerTableLeftTD">登陆名</td>
						<td  class="registerTableRightTD">
							<input id="name" name="name" placeholder="会员名一旦设置成功，无法修改" >
							<!-- <span id="spanF">*请勿输入特殊字符</span>  -->
							</td>
					</tr>
					<tr>
						<td  class="registerTip registerTableLeftTD">设置登陆密码</td>
						<td  class="registerTableRightTD">登陆时验证，保护账号信息</td>
					</tr>		
					<tr>
						<td class="registerTableLeftTD">登陆密码</td>
						<td class="registerTableRightTD">
							<input id="password" name="password" type="password"  placeholder="设置你的登陆密码" >
						<!-- <span id="spanS">*请勿输入特殊字符</span>  -->
							</td>
					</tr>
					<tr>
						<td class="registerTableLeftTD">密码确认</td>
						<td class="registerTableRightTD">
							<input id="repeatPassword" name="repeatPassword" type="password"   placeholder="请再次输入你的密码" >
						<!-- <span id="spanT">*与登陆密码保持一致</span>  -->
						</td>
					</tr>
							
					<tr>
						<td colspan="2" class="registerButtonTD">
							<button id="submit" type="submit">提   交</button>
						</td>
					</tr>				
				</table>
			</div>
		 </form>
		</div>
	 <!-- footer -->
    <%@include file="footer.jsp"%>
  </body>
</html>
