<%@ page language="java" import="java.util.*,product.ProductCategoryImpl" pageEncoding="utf-8"%>
<%@ page import="utils.Page,product.ProductCategory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>分类管理</title>
    
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
		<h1 class="label label-info" >分类管理</h1>
		<br>
		<br>
		<div class="listDataTableDiv">
			<table class="table table-striped table-bordered table-hover  table-condensed">
				<thead>
					<tr class="success">
						<th>ID</th>
						<th>图片</th>
						<th>分类名称</th>
						<th>属性管理</th>
						<th>产品管理</th>
						<th>编辑</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody>
				<%
				Page<ProductCategory> p1= null;
				int pageNum = 1;//pageNum时从页面的a标签传过来的值，代表当前页面
				try {
					p1 = (Page<ProductCategory>)session.getAttribute ("p1");
					pageNum = Integer.valueOf(request.getParameter("pageNum"));
				} catch(NumberFormatException e){
					pageNum = 1;
				} catch(NullPointerException e){
					e.printStackTrace();
				}
				int pageSize = 5;
				//完成数据库的查询，把结果放入sessiont域中
				ProductCategoryImpl productCategoryImpl = new ProductCategoryImpl();
				p1 = productCategoryImpl.findAllProductCategoryNameWithPage(pageNum, pageSize);
				session.setAttribute("p1", p1);
				 %>
					<c:if test="${sessionScope.p1.list != null}">
						<c:forEach items="${sessionScope.p1.list }" var="productCategory">
							<tr>
								<td>${productCategory.productCategoryId}</td>
								<td><img height="40px" src=""></td>
								<td>${productCategory.productCategoryName}</td>
								<td><a href="javascript:;"><span class="glyphicon glyphicon-th-list"></span></a></td>					
								<td><a href="jsp/admin/productManagement.jsp?productCategoryName=${productCategory.productCategoryName}&productCategoryId=${productCategory.productCategoryId}"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>					
								<td><a class="EditProductCategory" href="javascript:;"><span class="glyphicon glyphicon-edit"></span></a></td>
								<td><a class="deleteProductCategory" href="adminServlet?action=deleteProductCategory&productCategoryId=${productCategory.productCategoryId}"><span class="glyphicon glyphicon-trash"></span></a></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
		<div style="font-size:14px;text-align:center">
			<span>共${sessionScope.p1.totalRecord }种分类，共${sessionScope.p1.totalPage }页，当前为第${sessionScope.p1.pageNum}页</span>
		</div>
		<div class="pageDiv">
		  <ul class="pagination">
		    <%--如果当前页为第一页时，就没有上一页这个超链接显示 --%>
             <c:if test="${sessionScope.p1.pageNum == 1}">
                 <c:forEach begin="${sessionScope.p1.start}" end="${sessionScope.p1.end}" step="1" var="i">
                     <c:if test="${sessionScope.p1.pageNum == i}">
                         ${i}
                     </c:if>                
                     <c:if test="${sessionScope.p1.pageNum != i}">
                         <a href="pageServlet?action=category&pageNum=${i}">${i}</a>                                        
                     </c:if>                        
                 </c:forEach>
                 <a href="pageServlet?action=category&pageNum=${sessionScope.p1.pageNum+1}">下一页</a>
                 <a href="pageServlet?action=category&pageNum=${sessionScope.p1.totalPage}">尾页</a>                   
             </c:if>
             
             <%--如果当前页不是第一页也不是最后一页，则有上一页和下一页这个超链接显示 --%>
            <c:if test="${sessionScope.p1.pageNum > 1 && sessionScope.p1.pageNum < sessionScope.p1.totalPage}">
                 <a href="pageServlet?action=category&pageNum=${sessionScope.p1.pageNum-1}">上一页</a>
                 <c:forEach begin="${sessionScope.p1.start}" end="${sessionScope.p1.end}" step="1" var="i">  
                     <c:if test="${sessionScope.p1.pageNum == i}">
                         ${i}
                     </c:if>            
                     <c:if test="${sessionScope.p1.pageNum != i}">
                         <a href="pageServlet?action=category&pageNum=${i}">${i}</a>                                        
                     </c:if>                        
                 </c:forEach>
                 <a href="pageServlet?action=category&pageNum=${sessionScope.p1.pageNum+1}">下一页</a>
                 <a href="pageServlet?action=category&pageNum=${sessionScope.p1.totalPage}">尾页</a> 
             </c:if>
             
             <%-- 如果当前页是最后一页，则只有上一页这个超链接显示，下一页,尾页没有 --%>
             <c:if test="${sessionScope.p1.pageNum == sessionScope.p1.totalPage && sessionScope.p1.pageNum > 1}">
                 <a href="pageServlet?action=category&pageNum=${sessionScope.p1.pageNum-1}">上一页</a>
                 <c:forEach begin="${sessionScope.p1.start}" end="${sessionScope.p1.end}" step="1" var="i">
                     <c:if test="${sessionScope.p1.pageNum == i}">
                         ${i}
                     </c:if>
                     <c:if test="${sessionScope.p1.pageNum != i}">
                         <a href="pageServlet?action=category&pageNum=${i}">${i}</a>                                    
                     </c:if>                
                 </c:forEach>
             </c:if>             
		  </ul>
		</div>
		<div class="panel panel-warning addDiv" style="dispaly:block;">
		  <div class="panel-heading">新增分类</div>
		  <div class="panel-body">
		    	<form method="post" id="addForm" action="adminServlet?action=adminAddCategory">
		    		<table class="addTable">
		    			<tbody><tr>
		    				<td>分类名称</td>
		    				<td><input id="productCategoryName" name="productCategoryName" type="text" class="form-control"></td>
		    			</tr> 
		    			<tr>
		    				<td>分类图片</td>
		    				<td>
		    					<input id="categoryPic" type="file" name="filepath">
		    				</td>
		    			</tr>
		    			<tr class="submitTR">
		    				<td colspan="2" align="center">
		    					<button type="submit" name="submitAdd" class="btn btn-success">提 交</button>
		    				</td>
		    			</tr>
		    		</tbody>
		    	</table>
		    </form>
		  </div>
		</div>
		
		
	 </div>
  </body>
  <script type="text/javascript">
  $(function(){
  	$("*[name='submitAdd']").click(function(){
  		var productCategoryName = $("*[name='productCategoryName']").val();
  		if(productCategoryName == "" || productCategoryName == null || productCategoryName == undefined){
  			alert("请输入分类名");
  			$("*[name='productCategoryName']").focus();
  			return false;
  		} else {return true;}
  	});
  	$(".deleteProductCategory").click(function(){
		if(confirm("确定删除吗？")){
			window.location.href= $(".deleteProductCategory").attr("href");
			return true;
		} else {
			return false;
		}
	});
  });
  </script>
</html>
