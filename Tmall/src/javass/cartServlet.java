package javass;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import product.Product;
import product.ProductImpl;

import cart.Cart;
import cart.CartItem;

public class cartServlet extends HttpServlet {

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
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		int productId = 0;
		int num = 0;
		String action = "";
		//String userName = "";
		CartItem cartItem = null;
		Product product = null;
		ProductImpl productImpl = null;
		Cart cart =null;
		try{
			action = request.getParameter("action");
		}catch(NullPointerException e){
			e.printStackTrace();
		}
		if(action.equals("addCartItem")){
			try{
				num =  Integer.valueOf(request.getParameter("num"));
				productId = Integer.valueOf(request.getParameter("productId"));
				//userName = request.getParameter("userName");
			} catch(NullPointerException e){
				e.printStackTrace();
			}
			productImpl = new ProductImpl();
			product = productImpl.findProductById(productId);
			cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setCount(num);
			try{
				cart = (Cart) request.getSession().getAttribute("cart");
			}catch(NullPointerException e){
				cart = new Cart();
			}
			cart.add(cartItem);
			session.setAttribute("cart", cart);
			System.out.println("添加到了购物车");
		} else if(action.equals("numberPlus")){
			//
			System.out.println("numberPlusServlet");
			try{
				productId = Integer.valueOf(request.getParameter("productId"));
				num = 1;
				productImpl = new ProductImpl();
				product = productImpl.findProductById(productId);
				cartItem = new CartItem();
				cartItem.setProduct(product);
				cartItem.setCount(num);
				try{
					cart = (Cart) request.getSession().getAttribute("cart");
				}catch(NullPointerException e){
					cart = new Cart();
				}
				cart.add(cartItem);
				session.setAttribute("cart", cart);
				System.out.println("添加到了购物车");
			}catch(NullPointerException e){
				//
			}
		} else if(action.equals("numberMinus")){
			//
			System.out.println("numberMinusServlet");
			try{
				productId = Integer.valueOf(request.getParameter("productId"));
				num = -1;
				productImpl = new ProductImpl();
				product = productImpl.findProductById(productId);
				cartItem = new CartItem();
				cartItem.setProduct(product);
				cartItem.setCount(num);
				try{
					cart = (Cart) request.getSession().getAttribute("cart");
				}catch(NullPointerException e){
					cart = new Cart();
				}
				cart.add(cartItem);
				session.setAttribute("cart", cart);
				System.out.println("添加到了购物车");
			}catch(NullPointerException e){
				//
			}
		} else if(action.equals("deleteCartItem")){
			//
			System.out.println("deleteCartItem");
			try{
				productId = Integer.valueOf(request.getParameter("productId"));
				try{
					cart = (Cart) request.getSession().getAttribute("cart");
				}catch(NullPointerException e){
					cart = new Cart();
				}
				cart.delete(productId);
				session.setAttribute("cart", cart);
				System.out.println("从购物车里删除了东西");
			}catch(NullPointerException e){
				//
				e.printStackTrace();
			}
		}
		
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
