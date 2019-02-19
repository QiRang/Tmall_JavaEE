<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'editProduct.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  	<link href="css/back/style.css" rel="stylesheet">
	<script src="js/jquery/jquery.min.js"></script>
	<link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">

  </head>
  
  <body>
  	<%@include file="header.jsp" %>
   <div class="panel panel-warning editDiv">
			<div class="panel-heading">编辑商品</div>
			<div class="panel-body">
				<form  id="editForm" method="post" action="adminServlet?action=adminEditProduct">
					  <table class="editTable">
						<tbody>
							<tr>
								<td>商品名称</td>
								<td><input id="name" value=<%=request.getParameter("productName") %> name="productName" type="text" class="form-control"></td>
							</tr>
							<tr>
								<td>商品价格</td>
								<td><input id="orignalPrice" value=<%=request.getParameter("productPrice") %> name="productPrice" type="text" class="form-control"></td>
							</tr>
							<tr>
								<td>库存量</td>
								<td><input id="stock" name="productStock" value=<%=request.getParameter("stock") %> type="text" class="form-control"></td>
							</tr>
			    			<tr class="hidden">
			    				<td>商品id</td>
			    				<td>
			    					<input id="productId" type="text" value=<%=request.getParameter("productId") %> name="productId">
			    				</td>
			    			</tr>
							<tr class="submitTR">
								<td colspan="2" align="center">
									<input type="hidden" name="cid" value="83">
									<button name="editProduct" type="submit" class="btn btn-success">提交</button>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
				<script>
					const NUMBERREG = /^[0-9]*[1-9][0-9]*$/;
					const PRICEREG = /^(\d+\.\d\d)$/;
					$(function(){
					  	$("*[name='editProduct']").click(function(){
					  		console.log("lllll");
					  		var productName = $("*[name='productName']").val();
					  		var productPrice = $("*[name='productPrice']").val();
					  		var productStock = $("*[name='productStock']").val();
					  		console.log(productPrice);
							if(productName == "" || productName == null || productName == undefined){
					  			alert("请输入商品名");
					  			$("*[name='productName']").focus();
					  			return false;
					  		} else if(productPrice == "" || productPrice == null || productPrice == undefined){
					  			alert("请输入商品价格");
					  			$("*[name='productPrice']").focus();
					  			return false;
					  		} else if(!PRICEREG.test(productPrice)){
					  			console.log("哈哈哈哈哈哈哈");
								alert("请输入正确形式的价格");
								return false;
							} else if(productStock == "" || productStock == null || productStock == undefined){
					  			alert("请输入商品库存量");
					  			$("*[name='productName']").focus();
					  			return false;
					  		} else if(!NUMBERREG.test(productStock)){
								alert("库存数量需为整数");
								return false;
							} else {
								return true;
							};
					  	});
					  });
				</script>
			</div>
		</div>
  </body>
</html>
