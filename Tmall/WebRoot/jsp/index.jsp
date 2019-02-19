<%@ page language="java" import="java.util.*,product.*" pageEncoding="utf-8"%>
<%@ page import="java.text.DecimalFormat" %>
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
    
    <title>模仿天猫官网</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/styles.css" rel="stylesheet">		
	<script>
		window.onload=function(){
			function createChilds(obj){
				for(var i=0;i<obj.num;i++){
					var child=document.createElement(obj.tag);
					child.className=obj.className;
					obj.father.appendChild(child);
				}
			}
			function changeBacImg(div,arr){
			    createChilds({
					num:arr.length,
					tag:'li',
					father:document.querySelector(".showUl"),
				});
				var num=0;
				var aLi=document.querySelector(".showUl").getElementsByTagName("li");
		    	var timer=setInterval(function(){
		    		num++;
			    	if(num>=aLi.length){
			    		num=0;
			    	}
			    	control(num);
		    	},4000);
			    for(var i=0;i<aLi.length;i++){
			  	  aLi[i].index=i;
			  	  aLi[i].onclick=function(){
			  		num=this.index;
			  		control(num);
			      };
			    };
			    function control(count){
			  	  div.style.backgroundImage=arr[count];
			  	  for(var i=0;i<aLi.length;i++){
			  		aLi[i].style.backgroundColor="rgb(165, 160, 153)";
			    	};
			    	aLi[count].style.backgroundColor="#ffffff";
			   	  };
			}
			
			var showPictures=["url('//img.alicdn.com/imgextra/i1/21/O1CN011C1emiw7FI58D9Y_!!21-0-luban.jpg_q100.jpg_.webp')",
			"url('https://img.alicdn.com/simba/img/TB1IciTja6qK1RjSZFmSut0PFXa.jpg')",
			"url('//img.alicdn.com/imgextra/i4/113/O1CN01DPIoQ61ChnE9CSWRM_!!113-0-luban.jpg_q100.jpg_.webp')",
			"url('https://aecpm.alicdn.com/simba/img/TB1W4nPJFXXXXbSXpXXSutbFXXX.jpg')",
			"url('//img.alicdn.com/imgextra/i3/110/O1CN01Vw9j2n1CgQ2TmEYwM_!!110-0-luban.jpg_q100.jpg_.webp')"];
			document.getElementById("subNav").style.backgroundImage=showPictures[0];
			changeBacImg(document.getElementById("subNav"),showPictures);
		}
	</script>
  </head>
  <body>
    <!-- header -->
    <%@include file="header.jsp" %>
    <!-- search -->
    <div id="search">
      <img class="searchLogo" src="https://img.alicdn.com/tfs/TB1S_8liMHqK1RjSZJnXXbNLpXa-290-130.gif">
      <div class="searchWrap">
        <form class="seacherForm" action="#">
          	<input class="searchItems" type="text" name="searchItems" placeholder="&nbsp;&nbsp;请输入关键字">
       </form>
       <button type="submit" class="searchSubmit">搜索</button>
      </div>
    </div>
    <!-- items -->
    <div id="content" style="position:relative;">
    	<img class="contentBac" src="img/redBac.jpg" width="100%">
    	<div style="z-index:999;">
    		<div id="mainNav">
	    		<div style="height:50px;width:200px;line-height:50px;text-align:center;background-color:rgb(255, 0, 54);color:#ffffff;">商品分类</div>
	    		<div class="mainNavTitleDiv">
	    			<a href="//chaoshi.tmall.com?acm=lb-zebra-148799-667861.1003.4.2429983&amp;scm=1003.4.lb-zebra-148799-667861.OTHER_14561837688591_2429983"  aria-label="天猫超市" >
						<img alt="" src="//img.alicdn.com/tfs/TB1ztBlaMMPMeJjy1XbXXcwxVXa-200-60.png" style="width:100px;"></img>
						<div class="hover-pic"></div>
					</a>
					<a href="//www.tmall.hk/?acm=lb-zebra-148799-667861.1003.4.2429983&amp;scm=1003.4.lb-zebra-148799-667861.OTHER_14561833841102_2429983"  aria-label="天猫国际" >
						<img alt="" src="//img.alicdn.com/tfs/TB1t5ObaBxRMKJjy0FdXXaifFXa-200-60.png" style="width:100px;"></img>
						<div class="hover-pic"></div>
					</a>
					<a href="//vip.tmall.com/vip/index.htm?acm=lb-zebra-148799-667861.1003.4.2429983&amp;scm=1003.4.lb-zebra-148799-667861.OTHER_14561845383563_2429983" >天猫会员
						<div class="hover-pic"></div>
					</a>
					<a href="//3c.tmall.com/?acm=lb-zebra-148799-667861.1003.4.2429983&amp;scm=1003.4.lb-zebra-148799-667861.OTHER_14561822298635_2429983" >电器城
						<div class="hover-pic"></div>
					</a>
					<a href="//miao.tmall.com/?acm=lb-zebra-148799-667861.1003.4.2429983&amp;scm=1003.4.lb-zebra-148799-667861.OTHER_14561818451146_2429983" >喵鲜生
						<div class="hover-pic"></div>
					</a>
					<a href="//yao.tmall.com/?acm=lb-zebra-148799-667861.1003.4.2429983&amp;scm=1003.4.lb-zebra-148799-667861.OTHER_14561829993617_2429983" >医药馆
						<div class="hover-pic"></div>
					</a>
					<a href="//wt.tmall.com/?acm=lb-zebra-148799-667861.1003.4.2429983&amp;scm=1003.4.lb-zebra-148799-667861.OTHER_14561826146128_2429983" >营业厅
						<div class="hover-pic"></div>
					</a>
					<a href="//www.tmall.com/wow/mlh/act/timei?acm=lb-zebra-148799-667861.1003.4.2429983&amp;scm=1003.4.lb-zebra-148799-667861.OTHER_14561806908679_2429983" >魅力惠
						<div class="hover-pic"></div>
					</a>
					
					<a href="//www.alitrip.com/?acm=lb-zebra-148799-667861.1003.4.2429983&amp;scm=1003.4.lb-zebra-148799-667861.OTHER_145618030611810_2429983" >飞猪旅行
						<div class="hover-pic"></div>
					</a>
					
					<a href="//suning.tmall.com?acm=lb-zebra-148799-667861.1003.4.2429983&amp;scm=1003.4.lb-zebra-148799-667861.OTHER_11_2429983" >	苏宁易购
						<div class="hover-pic"></div>
					</a>
	    		</div>   		
	    	</div>
	    	<div>
	    	    <div id="subNav">
	    	    	<ul class="showUl"></ul>
	    	    	<div id="allCategory">
	   	    		<%
	   	    		ProductCategoryImpl productCategoryImpl = new ProductCategoryImpl();
					List<ProductCategory> productCategorys = productCategoryImpl.load();
					session.setAttribute("productCategorys", productCategorys);//session范围的productCategorys
					%>
					<c:if test="${sessionScope.productCategorys != null}">
						<c:forEach items="${sessionScope.productCategorys}" var="productCategory">
							<div class="eachCategory"><span><a href="javascript:;">${productCategory.productCategoryName }</a></span></div>
						</c:forEach>
					</c:if>
					</div>
	    	    </div>    		
	    	</div>
		    <div style="width:10px;;height:200px;"></div>
	    	<c:if test="${sessionScope.productCategorys != null}">
				<c:forEach items="${sessionScope.productCategorys}" var="productCategory">
					<div class="productArea">
						<div class="eachCategoryProducts">
							<div class="left-mark"></div>
							<span class="categoryTitle">${productCategory.productCategoryName }</span><br>
								<c:forEach items="${productCategory.products}" var="product"  begin="0" end="4" step="1">
									<div class="productItem">
										<a href="jsp/productDetail.jsp?productCategoryId=${product.productCategoryId}&productId=${product.productId}">
											<img width="100px" height="40px" src="${product.productImg}">
										</a>
										<a><span class="productItemName">${product.productName }<br/></span></a>
										<!-- <a><span class="productItemStore">某某旗舰店</span></a>  -->
										<span class="productPrice">￥${product.productPrice}</span>
									</div>
								</c:forEach>
							<div style="clear:both"></div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
    </div>
    <!-- footer -->
    <%@include file="footer.jsp"%>														
  </body>
</html>
