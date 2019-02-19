<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="js/jquery/2.0.0/jquery.min.js"></script>
	<link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
	<script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>
	<link href="css/back/style.css" rel="stylesheet">
  </head>
  
  <body>
    <%@include file="header.jsp" %>
	<div class="workingArea">
		<h1 class="label label-info" >订单管理</h1>
		<br>
		<br>
		
		<div class="listDataTableDiv">
			<table class="table table-striped table-bordered table-hover1  table-condensed">
				<thead>
					<tr class="success">
						<th>ID</th>
						<th>状态</th>
						<th>金额</th>
						<th width="100px">商品数量</th>
						<th width="100px">买家名称</th>
						<th>创建时间</th>
						<th>支付时间</th>
						<th>发货时间</th>
						<th>确认收货时间</th>
						<th width="120px">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>8539</td>
						<td>待付款</td>
						<td>￥1,503.00</td>
						<td align="center">1</td>
						<td align="center">456</td>
						<td>2018-11-03 19:19:06</td>
						<td></td>
						<td></td>
						<td></td>
						<td>
							<button oid=8539 class="orderPageCheckOrderItems btn btn-primary btn-xs">查看详情</button>
						</td>
					</tr>
					<tr class="orderPageOrderItemTR"  oid=8539>
						<td colspan="10" align="center">
							<div  class="orderPageOrderItem">
								<table width="800px" align="center" class="orderPageOrderItemTable">
									<tr>
										<td align="left">
											<img width="40px" height="40px" src="img/productSingle/1302.jpg">
										</td>	
										<td>
											<a href="foreproduct?pid=149">
												<span>纳蒂兰卡1066卫浴漩冲虹吸式马桶350坑距坐便器可配智能盖座便器</span>
											</a>											
										</td>
										<td align="right">
											<span class="text-muted">1个</span>												
										</td>
										<td align="right">
											<span class="text-muted">单价：￥1503.0</span>												
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>				
				</tbody>
			</table>
		</div>
	</div>
  </body>
</html>
