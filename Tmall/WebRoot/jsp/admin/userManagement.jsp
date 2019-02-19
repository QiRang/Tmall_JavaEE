<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 引入类 -->
<%@ page import="user.*,utils.Page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
	<link href="css/back/style.css" rel="stylesheet">
  </head>
  <body>
  	<%@include file="header.jsp" %>
	<div class="workingArea">
		<h1 class="label label-info" >用户管理</h1>
		<br>
		<br>
		<div class="listDataTableDiv">
			<table class="table table-striped table-bordered table-hover  table-condensed">
				<thead>
					<tr class="success">
						<th>用户名称</th>
						<th>密码</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${sessionScope.p2.list != null}">
						<c:forEach items="${sessionScope.p2.list }" var="user">
							<tr>
								<td>${user.userName}</td>
								<td>${user.password}</td>
							</tr>
						</c:forEach>
					</c:if>	
				</tbody>
			</table>
		</div>
		<div style="font-size:14px;text-align:center">
			<span>共${sessionScope.p2.totalRecord }个用户，共${sessionScope.p2.totalPage }页，当前为第${sessionScope.p2.pageNum}页</span>
		</div>
		<div class="pageDiv">
		  <ul class="pagination">
		    <%--如果当前页为第一页时，就没有上一页这个超链接显示 --%>
             <c:if test="${sessionScope.p2.pageNum == 1}">
                 <c:forEach begin="${sessionScope.p2.start}" end="${sessionScope.p2.end}" step="1" var="i">
                     <c:if test="${sessionScope.p2.pageNum == i}">
                         ${i}
                     </c:if>                
                     <c:if test="${sessionScope.p2.pageNum != i}">
                         <a href="pageServlet?action=user&pageNum=${i}">${i}</a>                                        
                     </c:if>                        
                 </c:forEach>
                 <a href="pageServlet?action=user&pageNum=${sessionScope.p2.pageNum+1}">下一页</a>
                 <a href="pageServlet?action=user&pageNum=${sessionScope.p2.totalPage}">尾页</a>                  
             </c:if>
             
             <%--如果当前页不是第一页也不是最后一页，则有上一页和下一页这个超链接显示 --%>
            <c:if test="${sessionScope.p2.pageNum > 1 && sessionScope.p2.pageNum < sessionScope.p2.totalPage}">
                 <a href="pageServlet?action=user&pageNum=${sessionScope.p2.pageNum-1}">上一页</a>
                 <c:forEach begin="${sessionScope.p2.start}" end="${sessionScope.p2.end}" step="1" var="i">  
                     <c:if test="${sessionScope.p2.pageNum == i}">
                         ${i}
                     </c:if>            
                     <c:if test="${sessionScope.p2.pageNum != i}">
                         <a href="pageServlet?action=user&pageNum=${i}">${i}</a>                                        
                     </c:if>                        
                 </c:forEach>
                 <a href="pageServlet?action=user&pageNum=${sessionScope.p2.pageNum+1}">下一页</a>
                 <a href="pageServlet?action=user&pageNum=${sessionScope.p2.totalPage}">尾页</a> 
             </c:if>
             
             <%-- 如果当前页是最后一页，则只有上一页这个超链接显示，下一页,尾页没有 --%>
             <c:if test="${sessionScope.p2.pageNum == sessionScope.p2.totalPage && sessionScope.p2.pageNum > 1}">
                 <a href="pageServlet?action=user&pageNum=${sessionScope.p2.pageNum-1}">上一页</a>
                 <c:forEach begin="${sessionScope.p2.start}" end="${sessionScope.p2.end}" step="1" var="i">
                     <c:if test="${sessionScope.p2.pageNum == i}">
                         ${i}
                     </c:if>
                     <c:if test="${sessionScope.p2.pageNum != i}">
                         <a href="pageServlet?action=user&pageNum=${i}">${i}</a>                                
                     </c:if>                
                 </c:forEach>
             </c:if>             
		  </ul>
		</div>
	</div>
  </body>
</html>
