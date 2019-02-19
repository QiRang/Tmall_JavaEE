<%@ page language="java" import="java.util.*,product.ProductImpl,product.Product,java.text.DecimalFormat" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userBuy.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/bootstrap/3.3.6/bootstrap.min.css">
	<link href="css/styles.css" rel="stylesheet">
	<link href="css/fore/style.css" rel="stylesheet">
	<script src="js/jquery/jquery.min.js"></script>
  </head>
  <script type="text/javascript">
  $(function(){
  	$(".submitOrderButton").click(function(){
  		var address = $("*[name='address']").val();
  		var post = $("*[name='post']").val();
  		var receiver = $("*[name='receiver']").val();
  		var mobile = $("*[name='mobile']").val();
  		if(address === null || address === "" || address === undefined){
  			alert("请输入地址");
  			$("*[name='address']").focus();
  			return false;
  		} else if(post === null || post === "" || post === undefined){
  			alert("请输入邮政编码");
  			$("*[name='post']").focus();
  			return false;
  		}else if(receiver === null || receiver === "" || receiver === undefined){
  			alert("请输入收货人");
  			$("*[name='receiver']").focus();
  			return false;
  		} else if(mobile === null || mobile === "" || mobile === undefined){
  			alert("请输入手机号");
  			$("*[name='mobile']").focus();
  			return false;
  			
  		} else {return true;}
  	});
  });
  </script>
  <body>
  	<%@include file="header.jsp"%>
  	<div class="buyPageDiv">
  		<%	int productId = Integer.valueOf(request.getParameter("productId"));
  			int mount = Integer.valueOf(request.getParameter("num"));
			System.out.println(productId);
			ProductImpl productDaoImpl = new ProductImpl(); 
			Product product = productDaoImpl.findProductById(productId);
			DecimalFormat df = new DecimalFormat( "0.00");
		%>
	  	<form action="userServlet?action=buy" method="post">
			<div class="buyFlow">
				<img class="pull-left" src="img/site/simpleLogo.png">
				<img class="pull-right" src="img/site/buyflow.png">
				<div style="clear:both"></div>
			</div>
			<div class="address">
				<div class="addressTip">输入收货地址</div>
				<div>
					<table class="addressTable">
						<tbody><tr>
							<td class="firstColumn">详细地址<span class="redStar">*</span></td>
							<td><textarea name="address" placeholder="请如实填写详细收货地址"></textarea></td>
						</tr>
						<tr>
							<td>邮政编码</td>
							<td><input name="post" placeholder="如不清楚，请填写000000" type="text"></td>
						</tr>
						<tr>
							<td>收货人姓名<span class="redStar">*</span></td>
							<td><input name="receiver" placeholder="长度不超过25个字符" type="text"></td>
						</tr>
						<tr>
							<td>手机号码 <span class="redStar">*</span></td>
							<td><input name="mobile" placeholder="请输入11位手机号码" type="text"></td>
						</tr>
					</tbody></table>
					
				</div>	
			</div>
			<div class="productList">
				<div class="productListTip">确认订单信息</div>
				<table class="productListTable">
					<thead>
						<tr>
							<th colspan="2" class="productListTableFirstColumn">
								<img class="tmallbuy" src="img/site/tmallbuy.png">
								<a class="marketLink" href="javascript:;">店铺：天猫店铺</a>
								<a class="wangwanglink" href="javascript:;"> <span class="wangwangGif"></span></a>
							</th>
							<th>单价</th>
							<th>数量</th>
							<th>小计</th>
							<th>配送方式</th>
						</tr>
						<tr class="rowborder">
							<td colspan="2"></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</thead>
					<tbody class="productListTableTbody">
						<tr class="orderItemTR">
							<td class="orderItemFirstTD"><img class="orderItemImg" src="<%=product.getProductImg()%>"></td>
							<td class="orderItemProductInfo">
							<a href="jsp/productDetail.jsp?productId=<%=product.getProductId()%>" class="orderItemProductLink">
								<%=product.getProductName()%>
							</a>
								<img src="img/site/creditcard.png" title="支持信用卡支付">
								<img src="img/site/7day.png" title="消费者保障服务,承诺7天退货">
								<img src="img/site/promise.png" title="消费者保障服务,承诺如实描述">
							</td>
							<td>
							<span class="orderItemProductPrice">￥<%=product.getProductPrice()%></span>
							</td>
							<td>
							<span class="orderItemProductNumber"><%=mount%></span>
							</td>
							<td><span class="orderItemUnitSum">
							￥<%=df.format((Double.valueOf( product.getProductPrice()) *mount))%>
							</span></td>
							<td rowspan="5" class="orderItemLastTD">
							<label class="orderItemDeliveryLabel">
								<input type="radio" value="" checked="checked">
								普通配送
							</label>
							<select class="orderItemDeliverySelect">
								<option>快递 免邮费</option>
							</select>
							</td>
						</tr>				
					</tbody>
				</table>
				<div class="orderItemSumDiv">
					<div class="pull-left">
						<span class="leaveMessageText">给卖家留言:</span>
						<span>
							<img class="leaveMessageImg" src="img/site/leaveMessage.png">
						</span>
						<span class="leaveMessageTextareaSpan" style="display: none;">
							<textarea name="userMessage" class="leaveMessageTextarea"></textarea>
							<div>
								<span>还可以输入200个字符</span>
							</div>
						</span>
					</div>
					<span class="pull-right">店铺合计(含运费): ￥<%=df.format((Double.valueOf( product.getProductPrice()) *mount))%></span>
				</div>
			</div>
			<div class="orderItemTotalSumDiv">
				<div class="pull-right"> 
					<span>实付款：</span>
					<span class="orderItemTotalSumSpan">￥<%=df.format((Double.valueOf( product.getProductPrice()) *mount))%></span>
				</div>
			</div>
			<div class="submitOrderDiv">
				<button type="submit" class="submitOrderButton">提交订单</button>
			</div>
		  </form>
		</div>
    <%@include file="footer.jsp"%>
  </body>
</html>
