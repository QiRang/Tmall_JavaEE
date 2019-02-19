<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="user.User"%>
<header style="height:50px;background-color:rgb(242, 242, 242);padding:0px 230px 0px 230px;font-size: 14px;">
      <nav style="display:flex;flex-direction:row;justify-content:space-between;align-items:center;height:50px;">
        <div>
        	<%  User user;
        		String userName = "";
        		try{
        			user = (User)session.getAttribute("user");
        			userName = user.getUserName();
        		}catch(NullPointerException e){
        			//
        		}
		     %>
		    <a href="../Tmall/jsp/index.jsp" style="">天猫首页</a>
            <span style="padding:0 10px;color:rgb(165, 153, 176);">喵，欢迎<span class="userName" style="color:rgb(255,0,54);"><%=userName%></span>来到天猫</span>
            <a href="../Tmall/jsp/userLogin.jsp" style="padding:0 10px;">登录</a>
            <a href="../Tmall/jsp/register.jsp" style="padding:0 10px;">免费注册</a>
        </div>
        <div>
          	<span><a href="" style="padding:10px;">我的淘宝</a></span>
			<span><a href="../Tmall/jsp/shoppingCart.jsp" style="padding:10px;">购物车</a></span>
			<span><a href="" style="padding:0 10px;">收藏夹</a></span>
			<span><a href="" style="padding:0 10px;">手机版</a></span>
			<span><a href="" style="padding:0 10px;">淘宝网</a></span>
			<span><a href="" style="padding:0 10px;">商家支持</a></span>
			<span><a href="" style="padding:0 10px;">网站导航</a></span>
        </div>
      </nav>
</header>