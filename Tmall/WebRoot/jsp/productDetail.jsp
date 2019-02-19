<%@ page language="java" import="java.util.*,product.Product,product.ProductImpl,product.ProductCategory,user.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商品详情页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/bootstrap/3.3.6/bootstrap.min.css">
	<link href="css/styles.css" rel="stylesheet">
	<link href="css/fore/style.css" rel="stylesheet">
	<script src="js/jquery/jquery.min.js"></script>
  </head>
  <body>
    <%@include file="header.jsp" %> 
    <div class="productPageDiv">
		<!-- 商品展示与购买 -->
		<div class="imgAndInfo">
			<!-- 商品展示 -->
			<div class="imgInimgAndInfo">
			<%	int productId = Integer.valueOf(request.getParameter("productId"));
				List<ProductCategory> productCategorys = (List<ProductCategory>)session.getAttribute("productCategorys");
				System.out.println(productId);
				ProductImpl productImpl = new ProductImpl();
				Product product = productImpl.findProductById(productId);
			%>
				<img src="<%=product.getProductImg()%>" class="bigImg">
				<div class="smallImageDiv">
						<img src="<%=product.getProductImg()%>" class="smallImage">
						<img src="<%=product.getProductImg()%>" class="smallImage">
						<img src="<%=product.getProductImg()%>" class="smallImage">
						<img src="<%=product.getProductImg()%>" class="smallImage">
						<img src="<%=product.getProductImg()%>" class="smallImage">
				</div>
			</div>
			<!-- 商品购买 -->
			<div class="infoInimgAndInfo">
				<div class="productTitle"><%=product.getProductName() %></div>
				<div class="productSubTitle">
					<!-- 高档户型客厅 精湛雕花工艺 实木框架 经典款式 -->
				</div>
				<div class="productPrice">
					<div class="juhuasuan">
						<span class="juhuasuanBig"><!-- 聚划算 --></span>
						<span><!--此商品即将参加聚划算，--><span class="juhuasuanTime"><!--1天19小时--></span><!--后开始--></span>
					</div>
					<div class="productPriceDiv">
						<div class="gouwujuanDiv"><img height="16px" src="img/site/gouwujuan.png">
						<span> 全天猫实物商品通用</span>
						</div>
						<div class="originalDiv">
							<span class="originalPriceDesc">价格</span>
							<span class="originalPriceYuan">¥</span>
							<span class="originalPrice"></span>
						</div>
						<div class="promotionDiv">
							<span class="promotionPriceDesc">促销价</span>
							<span class="promotionPriceYuan promotionPrice">¥</span>
							<span class="promotionPrice">
								<%=product.getProductPrice()%>
							</span>				
						</div>
					</div>
				</div>
				<div class="productSaleAndReviewNumber">
					<div>销量 <span class="redColor boldWord">0</span></div>	
					<div>累计评价 <span class="redColor boldWord">0</span></div>
				</div>
				<div class="productNumber">
					<span>数量</span>
					<span>
						<span class="productNumberSettingSpan">
						<input id="productNumberSetting" class="productNumberSetting" type="text" value="0">
						</span>
						<span class="arrow">
							<a href="javascript:;" class="increaseNumber" id="increaseNumber">
								<span class="updown"><img src="img/site/increase.png"></span>
							</a>
							<span class="updownMiddle"> </span>
							<a href="javascript:;" class="decreaseNumber" id="decreaseNumber">
								<span class="updown"><img src="img/site/decrease.png"></span>
							</a>
						</span>
					件</span>
					<span> 库存<%=product.getStock()%>件</span>
				</div>
				<div class="serviceCommitment">
					<span class="serviceCommitmentDesc">服务承诺</span>
					<span class="serviceCommitmentLink">
						<a href="#nowhere">正品保证</a>
						<a href="#nowhere">极速退款</a>
						<a href="#nowhere">赠运费险</a>
						<a href="#nowhere">七天无理由退换</a>
					</span>
				</div>
				<div class="buyDiv">
					<a class="buyLink" href="jsp/userBuy.jsp?productId=<%=product.getProductId()%>"  onclick="return false;"><button class="buyButton">立即购买</button></a>
					<a href="javascript:;" class="addCartLink"><button class="addCartButton"><span class="glyphicon glyphicon-shopping-cart"></span>加入购物车</button></a>
				</div>
			</div>
			<div style="clear:both"></div>
		</div>
		<!-- 商品详情与商品评价-->
		<div class="productReviewDiv">
		<!-- 商品评价 -->
			<div class="productReviewTopPart">
				<a href="javascript:;" class="productReviewTopPartSelectedLink">商品详情</a>
				<a href="javascript:;" class="selected">累计评价 <span class="productReviewTopReviewLinkNumber">1</span> </a>
			</div>
			<div class="productReviewContentPart">
				<div class="productReviewItem">
					<div class="productReviewItemDesc">
						<div class="productReviewItemContent">
							哈哈哈哈啊哈哈
						</div>
						<div class="productReviewItemDate">2018-11-15</div>
					</div>
					<div class="productReviewItemUserInfo">
						f**a<span class="userInfoGrayPart">（匿名）</span>
					</div>
					<div style="clear:both"></div>
				</div>
			</div>
		</div>
		<div class="productDetailDiv">
		<!-- 商品详情 -->
			<div class="productDetailTopPart">
				<a href="javascript:;" class="productDetailTopPartSelectedLink selected">商品详情</a>
				<a href="javascript:;" class="productDetailTopReviewLink">累计评价 <span class="productDetailTopReviewLinkNumber">1</span> </a>
			</div>
			<div class="productParamterPart">
				<div class="productParamter">产品参数：</div>
				<div class="productParamterList">
					<span>安装说明详情:  无安装说明 </span>
				</div>
				<div style="clear:both"></div>
			</div>
			<div class="productDetailImagesPart">
				<img src="<%=product.getProductImg()%>">
				<img src="<%=product.getProductImg()%>">
				<img src="<%=product.getProductImg()%>">
				<img src="<%=product.getProductImg()%>">
				<img src="<%=product.getProductImg()%>">
				<img src="<%=product.getProductImg()%>">
			</div>
		</div>
	</div>
    <%@include file="footer.jsp"%>
  </body>
  <script type="text/javascript">
			$(function(){
				var stock = <%= product.getStock()%>;
				$(".productNumberSetting").keyup(function(){
					var num= $(".productNumberSetting").val();
					num = parseInt(num);
					if(isNaN(num))
						num= 1;
					if(num<0)
						num = 0;
					if(num>stock)
						num = stock;
					$(".productNumberSetting").val(num);
				});
				
				$(".increaseNumber").click(function(){
					var num= $(".productNumberSetting").val();
					num++;
					if(num>stock)
						num = stock;
					$(".productNumberSetting").val(num);
				});
				$(".decreaseNumber").click(function(){
					var num= $(".productNumberSetting").val();
					--num;
					if(num<0)
						num=0;
					$(".productNumberSetting").val(num);
				});
				$(".productReviewTopPartSelectedLink").click(function(){
					console.log("000000");
					$(".productReviewDiv").css("display","none");
					$(".productDetailDiv").css("display","block");
					//$("productDetailDiv").attr("class","selected");
					console.log("1111");
				});
				$(".productDetailTopReviewLink").click(function(){
					console.log("000000dfff");
					$(".productDetailDiv").css("display","none");
					$(".productReviewDiv").css("display","block");
					//$("productReviewDiv").attr("class","selected");
					//console.log("1111fdfdd");
				});
				$(".buyLink").click(function(){
					//直接购买
					var loginFlag = "${sessionScope.user.success}";
					console.log("success"+ loginFlag);
					if(loginFlag == "true"){
	            		var num = $(".productNumberSetting").val();
	            		if(num<=0){
			             	$(".buyLink").attr("disabled","disabled");
			             	alert("请选择购物数量");
			             } else {
			             	//
			             	$(".buyLink").removeAttr("disabled");
			              	console.log(num);
	            			window.location.href= $(".buyLink").attr("href")+"&num="+num;  
			             }
	            	}
	            	else{
						alert("请先登陆!");        		
	            	}
				});
				$(".addCartButton").click(function(){
					//加入购物车
					var loginFlag = "${sessionScope.user.success}";
					console.log("success"+ loginFlag);
					if(loginFlag == "true"){
	            		//加入购物车代码
						var url="cartServlet";
			            var productId = <%= request.getParameter("productId")%>;
			            var num = $(".productNumberSetting").val();
			            var userName= $(".userName").text();
			            var data={"action":"addCartItem","productId":productId,"num":num,"userName":userName};
			            console.log("productId",productId);
			            console.log("num",num);
			            console.log("userName",userName);
			            console.log("data",data);
			            var success=function(response){

			                if(response.errno==0){
			                    alert(response.errmsg);
			                }
			             };
			             if(num<=0){
			             	$(".addCartButton").attr("disabled","disabled");
			             	alert("请选择购物数量");
			             } else {
			             	//
			             	$(".addCartButton").removeAttr("disabled");
			             	$.post(url,data,success,'json');
			             	$(".addCartButton").css("background","rgb(170, 170, 170)");
			            	$(".addCartButton").attr("disabled","disabled");
			                alert("成功加入购物车");
			             }
	            	}
	            	else{
						alert("请先登陆!");        		
	            	}
				});
			});
		</script>
</html>
