<%@ page language="java" import="java.util.*,product.*,utils.Page" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'productManagement.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
	<link href="css/back/style.css" rel="stylesheet">
	<script src="js/jquery/jquery.min.js"></script>
  </head>
  <body>
  <%@include file="header.jsp" %>
    <div class="workingArea">
		<ol class="breadcrumb">
		<%
		String productCategoryName = "";
		String productCategoryId = "";
		try{
			productCategoryName = request.getParameter("productCategoryName");
			productCategoryId = request.getParameter("productCategoryId");
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		if(productCategoryName == null){
			try{
				productCategoryName = session.getAttribute("productCategoryName").toString();
			}catch(NullPointerException e){
				e.printStackTrace();
			}
		}
		if(productCategoryId == null){
			try{
				productCategoryId = session.getAttribute("productCategoryId").toString();
			}catch(NullPointerException e){
				e.printStackTrace();
			}
		}
		System.out.println(productCategoryName + "productCategoryNameproductCategoryNameproductCategoryNameproductCategoryName");
		System.out.println(productCategoryId + "productCategoryIdproductCategoryIdproductCategoryIdproductCategoryId");
		session.setAttribute ("productCategoryName",productCategoryName);
		session.setAttribute ("productCategoryId",productCategoryId); %>
		  <li><a href="jsp/admin/index.jsp">全部分类</a></li>
		  <li><a href="javascript:;">${sessionScope.productCategoryName}</a></li>
		  <li class="active">产品管理</li>
		</ol>
		<div class="listDataTableDiv">
			<table class="table table-striped table-bordered table-hover  table-condensed">
				<thead>
					<tr class="success">
						<th>ID</th>
						<th>图片</th>
						<th>产品名称</th>
						<th width="53px">价格</th>
						<th width="80px">库存</th>
						<th width="80px;">图片管理</th>
						<th width="80px">设置属性</th>
						<th width="42px">编辑</th>
						<th width="42px">删除</th>
					</tr>
				</thead>
				<tbody>
					<%
					Page<Product> p4 = null;
					int pageNum = 1;
					try{
						p4 = (Page<Product>)session.getAttribute("p4");
						pageNum =  Integer.valueOf(request.getParameter("pageNum"));
					} catch(NumberFormatException e){
						pageNum =1;
					} catch(NullPointerException e){
						e.printStackTrace();
					}
					int pageSize =5;
					ProductImpl productImpl = new ProductImpl();
					p4 = productImpl.findAllProductWithPage(pageNum, pageSize, productCategoryId);
					session.setAttribute("p4", p4);
					System.out.println("设置了p4");
					
					 %>
					<c:if test="${sessionScope.p4.list != null}">
						<c:forEach items="${sessionScope.p4.list }" var="product">
						<tr>
							<td>${product.productId}</td>
							<td>
								<img width="40px" height="40px" src="${product.productImg}">
							</td>
							<td>${product.productName}</td>
							<!-- <td>å±å¤§å½±é¢ é«é12æ ¸ å®åæºè½</td>
							<td>4499.01</td>  -->
							<td>${product.productPrice}</td>
							<td>${product.stock}</td>
							<td><a href="javascript:;"><span class="glyphicon glyphicon-picture"></span></a></td>
							<td><a href="javascript:;"><span class="glyphicon glyphicon-th-list"></span></a></td>
							
							<td><a href="jsp/admin/editProduct.jsp?productId=${product.productId}&productName=${product.productName}&productPrice=${product.productPrice}&stock=${product.stock}">
								<span class="glyphicon glyphicon-edit"></span>
							</a></td>
							<td><a class="deleteProduct" href="adminServlet?action=deleteProduct&productId=${product.productId}" onclick="return false;"><span class="glyphicon glyphicon-trash"></span></a></td>
						</tr>	
						</c:forEach>
					</c:if>			
				</tbody>
			</table>
		</div>
		<div style="font-size:14px;text-align:center">
			<span>共有${sessionScope.p4.totalRecord }个产品,共有 ${sessionScope.p4.totalPage }页，当前第${sessionScope.p4.pageNum}页</span>
		</div>
		<div class="pageDiv">
		  <ul class="pagination">
		  <%--如果当前页为第一页时，就没有上一页这个超链接显示 --%>
             <c:if test="${sessionScope.p4.pageNum == 1}">
                 <c:forEach begin="${sessionScope.p4.start}" end="${sessionScope.p4.end}" step="1" var="i">
                     <c:if test="${sessionScope.p4.pageNum == i}">
                         ${i}
                     </c:if>                
                     <c:if test="${sessionScope.p4.pageNum != i}">
                         <a href="pageServlet?action=product&pageNum=${i}">${i}</a>                                        
                     </c:if>                        
                 </c:forEach>
                 <a href="pageServlet?action=product&pageNum=${sessionScope.p4.pageNum+1}">下一页</a>
                 <a href="pageServlet?action=product&pageNum=${sessionScope.p4.totalPage}">尾页</a>                   
             </c:if>
             
             <%--如果当前页不是第一页也不是最后一页，则有上一页和下一页这个超链接显示 --%>
            <c:if test="${sessionScope.p4.pageNum > 1 && sessionScope.p4.pageNum < sessionScope.p4.totalPage}">
                 <a href="pageServlet?action=product&pageNum=${sessionScope.p4.pageNum-1}">上一页</a>
                 <c:forEach begin="${sessionScope.p4.start}" end="${sessionScope.p4.end}" step="1" var="i">  
                     <c:if test="${sessionScope.p4.pageNum == i}">
                         ${i}
                     </c:if>            
                     <c:if test="${sessionScope.p4.pageNum != i}">
                         <a href="pageServlet?action=product&pageNum=${i}">${i}</a>                                        
                     </c:if>                        
                 </c:forEach>
                 <a href="pageServlet?action=product&pageNum=${sessionScope.p4.pageNum+1}">下一页</a>
                 <a href="pageServlet?action=product&pageNum=${sessionScope.p4.totalPage}">尾页</a> 
             </c:if>
             
             <%-- 如果当前页是最后一页，则只有上一页这个超链接显示，下一页,尾页没有 --%>
             <c:if test="${sessionScope.p4.pageNum == sessionScope.p4.totalPage && sessionScope.p4.pageNum > 1}">
                 <a href="pageServlet?action=product&pageNum=${sessionScope.p4.pageNum-1}">上一页</a>
                 <c:forEach begin="${sessionScope.p4.start}" end="${sessionScope.p4.end}" step="1" var="i">
                     <c:if test="${sessionScope.p4.pageNum == i}">
                         ${i}
                     </c:if>
                     <c:if test="${sessionScope.p4.pageNum != i}">
                         <a href="pageServlet?action=product&pageNum=${i}">${i}</a>                                    
                     </c:if>                
                 </c:forEach>
             </c:if> 
		  </ul>
		</div>
		<div class="panel panel-warning addDiv">
			<div class="panel-heading">新增产品</div>
			<div class="panel-body">
				<form method="post" enctype="multipart/form-data" action="adminServlet?action=adminAddProduct&productCategoryId=${sessionScope.productCategoryId}" id="addForm" >
					  <table class="addTable">
						<tbody>
							<tr>
								<td>商品名称</td>
								<td><input id="name" name="productName" type="text" class="form-control"></td>
							</tr>
							<!-- 
							<tr>
								<td>äº§åå°æ é¢</td>
								<td><input id="subTitle" name="subTitle" type="text" class="form-control"></td>
							</tr>
							 -->
							<tr>
								<td>商品价格</td>
								<td><input id="orignalPrice" name="productPrice" type="text" class="form-control"></td>
							</tr>
							<!-- 
							<tr>
								<td>ä¼æ ä»·æ ¼</td>
								<td><input id="promotePrice" value="19.98" name="promotePrice" type="text" class="form-control"></td>
							</tr>
							 -->
							<tr>
								<td>库存量</td>
								<td><input id="stock" name="productStock" type="text" class="form-control"></td>
							</tr>
							<tr>
			    				<td>商品图片</td>
			    				<td>
			    					<input id="categoryPic" type="file" name="productNamFilePath" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg">
			    				</td>
			    			</tr>
			    			<tr class="hidden">
			    				<td>商品id</td>
			    				<td>
			    					<input id="categoryId" type="text" name="productCategoryId" value="${sessionScope.productCategoryId}">
			    				</td>
			    			</tr>
							<tr class="submitTR">
								<td colspan="2" align="center">
									<input type="hidden" name="cid" value="83">
									<button name="addProduct" type="submit" class="btn btn-success">提交</button>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
				<script>
					const NUMBERREG = /^[0-9]*[1-9][0-9]*$/;
					const PRICEREG = /^(\d+\.\d\d)$/;
					
					$(function(){
					  	$("*[name='addProduct']").click(function(){
					  	
					  		var productName = $("*[name='productName']").val();
					  		var productPrice = $("*[name='productPrice']").val();
					  		var productStock = $("*[name='productStock']").val();
							var fileInput = $("*[name='productNamFilePath']").val();
							console.log(fileInput);
							if(productName == "" || productName == null || productName == undefined){
					  			alert("请输入商品名");
					  			$("*[name='productName']").focus();
					  			return false;
					  		} else if(productPrice == "" || productPrice == null || productPrice == undefined){
					  			alert("请输入商品价格");
					  			$("*[name='productPrice']").focus();
					  			return false;
					  		} else if(!PRICEREG.test(productPrice)){
								alert("请输入正确形式的价格");
								return false;
							} else if(productStock == "" || productStock == null || productStock == undefined){
					  			alert("请输入商品库存量");
					  			$("*[name='productName']").focus();
					  			return false;
					  		} else if(!NUMBERREG.test(productStock)){
								alert("库存数量需为整数");
								return false;
							} else if(fileInput == "" || fileInput == null || fileInput == undefined){
								alert("请选择产品图片");
								console.log("ä¸è·³è½¬");
								return false;	 
							} else {
								return true;
							};
					  	});
					  	//
					  	$(".deleteProduct").click(function(){
							if(confirm("确定删除吗")){
								window.location.href= $(".deleteProduct").attr("href");
								return true;
								
							} else {
								return false;
							}
						});
					  });
				</script>
			</div>
		</div>
	</div>
  </body>
</html>
