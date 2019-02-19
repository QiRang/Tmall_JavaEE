package javass;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import product.Product;
import product.ProductCategory;
import product.ProductCategoryImpl;
import product.ProductImpl;

import user.User;
import user.UserDaoImpl;
import utils.Page;


public class pageServlet extends HttpServlet {
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = " ";
		HttpSession session = request.getSession();
		try{
			action = request.getParameter("action");
			if(action.equals("user")){//用户管理
				int pageNum = 1;//pageNum时从页面的a标签传过来的值，代表当前页面
				try {
					pageNum = Integer.valueOf(request.getParameter("pageNum"));
				} catch(NumberFormatException e){
					pageNum = 1;
				}
				//完成数据库的查询，把结果放入session域中
				UserDaoImpl userDaoImpl = new UserDaoImpl();
				Page<User> p2 = userDaoImpl.findAllUserWithPage(pageNum, 5);
				session.setAttribute("p2", p2);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/admin/userManagement.jsp");
				rd.forward(request, response);
			} else if(action.equals("category")){//分类管理
				int pageNum = 1;//pageNum时从页面的a标签传过来的值，代表当前页面
				try {
					pageNum = Integer.valueOf(request.getParameter("pageNum"));
				} catch(NumberFormatException e){
					pageNum = 1;
				}
				int pageSize = 5;
				//完成数据库的查询，把结果放入sessiont域中
				ProductCategoryImpl productCategoryImpl = new ProductCategoryImpl();
				Page<ProductCategory> p1 = productCategoryImpl.findAllProductCategoryNameWithPage(pageNum, pageSize);
				p1.setPageNum(pageNum);
				session.setAttribute("p1", p1);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/admin/index.jsp");
				rd.forward(request, response);
			} else if(action.equals("product")){
				//商品管理
				int pageNum = 1;//pageNum时从页面的a标签传过来的值，代表当前页面
				try {
					pageNum = Integer.valueOf(request.getParameter("pageNum"));
				} catch(NumberFormatException e){
					pageNum = 1;
				}
				int pageSize = 5;
				//完成数据库的查询，把结果放入sessiont域中
				ProductImpl productImpl = new ProductImpl();
				Page<Product> p4 = productImpl.findAllProductWithPage(pageNum, pageSize);
				p4.setPageNum(pageNum);
				session.setAttribute("p4", p4);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/admin/productManagement.jsp");
				rd.forward(request, response);
			} else if(action.equals("order")){
				//订单管理
			}
		}catch(NullPointerException e){e.printStackTrace();}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
