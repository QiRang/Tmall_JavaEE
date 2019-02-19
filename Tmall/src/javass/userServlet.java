package javass;

import java.io.IOException;

import java.io.PrintWriter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.Cart;

import user.User;
import user.UserDaoImpl;
import utils.MyException;

public class userServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/** 
	 *             2018,11,7 Statement 是 Java
	 *             执行数据库操作的一个重要方法，用于在已经建立数据库连接的基础上，向数据库发送要执行的SQL语句。
	 *             Statement对象，用于执行不带参数的简单SQL语句。 1.Statement:用于执行SQL语句的对象
	 *             1).通过connection的createStatement()方法来获取
	 *             2).通过excuteUpdate(sql)可以执行SQL语句
	 *             3).传入的SQL可以是insert,update或者delete
	 *             ,但是不能是select(这个可以用Statement对象的excuteQuery(sql)得到ResultSet)
	 *             2.Connection、Statement都是应用程序和数据库服务器的连接资源，使用后一定要关闭
	 *             需要在finally中关闭Connection和Statement对象
	 *             3.关闭的顺序：先关闭后获取的,即先关闭Statement，后关闭Connection
	 *             ResultSet:结果集，封装了使用JDBC进行查询的结果
	 *             1.调用Statement对象的excuteQuery(sql)方法可以得到结果集
	 *             2.ResultSet返回的实际上就是一张数据表，有一个指针
	 *             指向数据表的第一样的前面，可以调用next()方法检测下一行是否有效，若有效则返回true
	 *             ,并且指针下移，相当于迭代器对象的hasNext()和next()的结合体
	 *             3.当指针对位到确定的一行时，可以通过调用getXxx(index)或者getXxx(columnName)
	 *             获取每一列的值，例如：getInt(1),getString("name") 4.ResultSet当然也需要进行关闭
	 *             PreparedStatement: 可批量提交 1.addBatch() 将一组参数添加到
	 *             PreparedStatement对象内部 2.executeBatch()
	 *             将一批参数提交给数据库来执行，如果全部命令执行成功，则返回更新计数组成的数组。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		User user = null;
		String userName = "";
		String loginResult = "";
		boolean isSuccess = false;
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		HttpSession session = null;
		String InputedName="";
		String InputedPassWord = "";
		String action = "";
		Cart cart = null;
		//先获取action,确定用户的操作.
		try {
			action = request.getParameter("action");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		if (action.equals("register")) {
			try{
				InputedName = request.getParameter("name").toString();
				InputedPassWord =  request.getParameter("password").toString();
			}catch(NullPointerException e){
				e.printStackTrace();
			}
			user = new User();
			user.setUserName(InputedName);
			user.setPassword(InputedPassWord);
			try{
				userDaoImpl.add(user);
				out.print("<script>alert('注册成功,正在跳转登陆页面!');window.location.href='jsp/userLogin.jsp'</script>");
			} catch (MyException ue){
				System.out.println("-------"+ue.getMessage());
				String message = ue.getMessage().toString();
				if(message.equals("会员名被占用")){
					System.out.println("进入占用的if");
					out.print("<script>alert('会员名被占用!');window.location.href='jsp/register.jsp'</script>");
				} 
			}
		} else if (action.equals("login")) {
			try{
				InputedName = request.getParameter("name").toString();
				InputedPassWord =  request.getParameter("password").toString();
			}catch(NullPointerException e){
				e.printStackTrace();
			}
			//设一个生命周期为session的user
			session = request.getSession();
			try{
				user = (User)session.getAttribute("user");
				if(user == null){ 
					user = new User();
					session.setAttribute("user", user);
					System.out.println("session里面设user对象成功!");
				}
			} catch(Exception e){
				user = new User();
				session.setAttribute("user", user);
				System.out.println("session里面设user对象成功!");
			}
			//检查user是否已登陆
			isSuccess = user.isSuccess();
			userName = user.getUserName();
			if(isSuccess && InputedName.equals(userName)){
				//System.out.println("已经登陆了");
				request.setAttribute("loginResult", InputedName+"已经登录了");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/userLogin.jsp");
				rd.forward(request, response);
			} else {
				user.setUserName(InputedName);
				user.setPassword(InputedPassWord);
				try{
					userDaoImpl.login(user);
					cart = new Cart();
					session.setAttribute("cart", cart);
					//登陆，成功，将userName传到首页
					System.out.println("*登陆成功，进入首页");
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
					rd.forward(request, response);
				} catch (MyException e){
					loginResult = "您输入的用户名不存在，或密码不般配";
					if(e.getMessage().toString().equals(loginResult)){
						request.setAttribute("loginResult", loginResult);
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/userLogin.jsp");
						rd.forward(request, response);
					}
				}
			}
			
		} else if(action.equals("buy")){
			//用户提交订单了,对订单开始处理
			out.print("<script>alert('下单成功!');window.location.href='jsp/index.jsp'</script>");
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {
		// Put your code here
		super.init(config);
		
		
	}

}
