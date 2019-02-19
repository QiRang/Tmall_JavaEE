<%@ page language="java" import="java.util.*,cart.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的购物车</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/styles.css" rel="stylesheet" type="text/css">
	<link href="css/fore/style.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet" type="text/css"> 
	<script src="js/jquery/jquery.min.js"></script>
  </head>
  
  <body>
    <%@include file="header.jsp" %>
    <c:choose>
		<%-- 如果没有车 或 车中没有条目 则显示图片--%>
		<c:when test="${empty sessionScope.cart or fn:length(sessionScope.cart.cartItems) eq 0 }">
			<h1 style="padding: 20px 0 20px 230px;">您的购物车为空，快去商城挑选吧！</h1>
		</c:when>
		<c:otherwise>
			<div class="cartDiv">
				<div class="cartTitle pull-right">
					<span>已选商品  (不含运费)</span>
					<span style="color:rgb(196, 0, 0);">￥</span>
					<span class="cartTitlePrice">0.00 </span>
					<button class="buyCartItemsButton" disabled="disabled" style="background-color: rgb(170, 170, 170);">结 算</button>
				</div>
				<div class="cartProductList">
					<table class="cartProductTable">
						<thead>
							<tr>
								<th>								
								</th>
								<th>商品信息</th>
								<th>单价</th>
								<th>数量</th>
								<th width="120px">金额</th>
								<th class="operation">操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${sessionScope.cart.cartItems }" var="cartItem">
							<tr class="cartProductItemTR" style="background-color: rgb(255, 255, 255);">
								<td>
									<input type="checkbox" class="cartCheckBox" name="cartCheckBox" value="${cartItem.subtotal }">
									<img class="cartProductImg" src="${cartItem.product.productImg  }">
								</td>
								<td>
									<div class="cartProductLinkOutDiv">
										<a href="jsp/productDetail.jsp?productId=${cartItem.product.productId }" class="cartProductLink">${cartItem.product.productName }</a>
										<div class="cartProductLinkInnerDiv">
											<img src="img/site/creditcard.png" title="支持信用卡支付">
											<img src="img/site/7day.png" title="消费者保障服务,承诺7天退货">
											<img src="img/site/promise.png" title="消费者保障服务,承诺如实描述">
										</div>
									</div>
									
								</td>
								<td>
								<!-- 
									<span class="cartProductItemOringalPrice">￥2799.00</span>  -->
									<span style="color:rgb(196, 0, 0);font-size: 14px;">￥</span>
									<span class="cartProductItemPromotionPrice">${cartItem.product.productPrice }</span>
									
								</td>
								<td>
								
									<div class="cartProductChangeNumberDiv">
										<span class="hidden productId">${cartItem.product.productId }</span>
										<span class="hidden cartItemNum">${cartItem.count}</span>
										<span class="hidden stock">${cartItem.product.stock}</span>
										<span class="hidden userName">${sessionScope.user.userName}</span>
										<button class="numberMinus">-</button>
										<input name="cartItemNumber" class="orderItemNumberSetting" value="${cartItem.count }">
										<button class="numberPlus">+</button>
									</div>					
								
								 </td>
								<td>
									<span style="color:rgb(196, 0, 0);font-size: 14px;">￥</span>
									<span class="cartProductItemSmallSumPrice">
									${cartItem.subtotal}
									</span>
								
								</td>
								<td>
									<span class="hidden productId">${cartItem.product.productId }</span>
									<a class="deleteCartItem" href="javascript:;">删除</a>
								</td>
							</tr>		
						</c:forEach>		
						</tbody>
					</table>
				</div>
				
				<div class="cartFoot">
					<input type="checkbox" id="allCheckBox">
					<span>全选</span>
			<!-- 		<a href="#">删除</a> -->
					
					<div class="pull-right">
						<!--  <span>已选商品 <span class="cartSumNumber">0</span> 件</span> -->
						<span>合计 (不含运费): </span> 
						<span style="color:rgb(196, 0, 0);font-size: 14px;">￥</span>
						<span class="cartSumPrice">0.00</span>
						<button class="buyAllButton" disabled="disabled" style="background-color: rgb(170, 170, 170);">结  算</button>
					</div>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
    <%@include file="footer.jsp"%>	
  </body>
  <script>
  	// 计算总计
    function showTotal() {
    	var total = 0;
        var subTotal = 0;
        var number = 0;
    	//统计个数
        var count=0;   
        // 1. 获取所有的被勾选的条目复选框！循环遍历
        $("[name='cartCheckBox']").each(function (index, element) {
            var isChecked = $(this).prop("checked");
            console.log("isChecked",isChecked);
		    if(isChecked){
		        count++;
		    }
            // 如果多选框被选中
            if(isChecked == true) {
                // 2. 获取该cartItem的总价
                subTotal = parseFloat($(this).val());
                console.log("subTotal" ,subTotal);
                //4. 累加计算
                total += subTotal;
                number += 1;
                console.log("number",number);
                console.log("total",total);
                console.log("加了一次");
            }
        });
        // 5. 把总计显示在总计元素上
        $(".cartTitlePrice").text(total.toFixed(2));//toFixed(2)函数的作用是把total保留2位
        //
	   	if(count == $("[name='cartCheckBox']").size()){
	   		console.log("全红");
		    $("#allCheckBox").prop("checked",true);
		    $(".cartSumPrice").text(total.toFixed(2));
		    $(".buyAllButton").css("background","rgb(196, 0, 0)");
		    $(".buyCartItemsButton").css("background","rgb(196, 0, 0)");
		    $(".buyCartItemsButton").removeAttr("disabled");
			$(".buyAllButton").removeAttr("disabled");
		}else if(count > 0 && count < $("[name='cartCheckBox']").size()){
			console.log("一灰一红");
		    $("#allCheckBox").prop("checked",false);
		    $(".cartSumPrice").text("0.00");
		    $(".buyAllButton").css("background","rgb(170, 170, 170)");
		    $(".buyCartItemsButton").css("background","rgb(196, 0, 0)");
		    $(".buyCartItemsButton").removeAttr("disabled");
			$(".buyAllButton").attr("disabled","disabled");
		} else if(count == 0){
			console.log("全灰");
			$("#allCheckBox").prop("checked",false);
			$(".cartSumPrice").text("0.00");
			$(".buyCartItemsButton").css("background","rgb(170, 170, 170)");
			$(".buyAllButton").css("background","rgb(170, 170, 170)");
			$(".buyCartItemsButton").attr("disabled","disabled");
			$(".buyAllButton").attr("disabled","disabled");
		}
		console.log("count",count);
    }
  	$(function(){
        $("[name='cartCheckBox']").on("click", function () {
            //选择多选框后,计算价格
            showTotal();
            
        });
		$("#allCheckBox").click(function(){
	        //获取当前全选框的选中状态
			var flag=$("#allCheckBox").prop("checked");
			console.log(flag);
		    if(flag){
				//如果选中，则给下面的每个复选框都选中
				$("[name='cartCheckBox']").prop("checked",true);
				//选择多选框后,计算价格
            	showTotal();
		    }else{
		    	console.log("else");
				$("[name='cartCheckBox']").prop("checked",false);
				//选择多选框后,计算价格
            	showTotal();
		    }
	    });
	    //加减数目模块
	    $(".numberPlus").click(function(){
	    	var url="cartServlet";
	    	var num = $(this).parent().children("input").val();
            var productId =  $(this).parent().children(".productId").text();
            //var num = $("[name='cartItemNumber']").val();
            var newNum = parseInt(num) + 1;
            var stock = $(this).parent().children(".stock").text();;
            if(newNum > stock){
            	alert("库存数量有限，无法再添加");
            	return;
            }
            var userName= $(".userName").text();
            var data={"action":"numberPlus","productId":productId};
            console.log("productId",productId);
            console.log("num",num);
            console.log("newNum",newNum);
            console.log("userName",userName);
            console.log("data",data);
            var success=function(response){
            	console.log(response);
             };
             $.post(url,data,success,'json');
             window.location.reload();
	    });
	    $(".numberMinus").click(function(){
	    	var url="cartServlet";
	    	var num = $(this).parent().children("input").val();
            var productId =  $(this).parent().children(".productId").text();
            //var num = $("[name='cartItemNumber']").val();
            var newNum = parseInt(num) - 1;
            if(newNum == 0){
            	alert("不能再减了");
            	return;
            }
            var userName= $(".userName").text();
            var data={"action":"numberMinus","productId":productId};
            console.log("productId",productId);
            console.log("num",num);
            console.log("newNum",newNum);
            console.log("userName",userName);
            console.log("data",data);
            var success=function(response){
            	console.log(response);
             };
             $.post(url,data,success,'json');
             window.location.reload();
	    });
	    $(".deleteCartItem").click(function(){
	    	//
	    	if(confirm("确定删除吗？")){
	    		var url="cartServlet";
		    	var userName= $(".userName").text();
		    	var productId =  $(this).parent().children(".productId").text();
	            var data={"action":"deleteCartItem","productId":productId};
		    	var productId =  $(this).parent().children(".productId").text();
		    	var success=function(response){
	            	console.log(response);
	             };
	             $.post(url,data,success,'json');
	             window.location.reload();
	    	}
	    	
	    });
	    $(".buyCartItemsButton").click(function(){
	    	alert("生成订单");
	    });
	    $(".buyAllButton").click(function(){
	    	alert("生成订单");
	    });
  	});
  </script>
</html>
