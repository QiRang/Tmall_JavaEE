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
	 *             2018,11,7 Statement �� Java
	 *             ִ�����ݿ������һ����Ҫ�������������Ѿ��������ݿ����ӵĻ����ϣ������ݿⷢ��Ҫִ�е�SQL��䡣
	 *             Statement��������ִ�в��������ļ�SQL��䡣 1.Statement:����ִ��SQL���Ķ���
	 *             1).ͨ��connection��createStatement()��������ȡ
	 *             2).ͨ��excuteUpdate(sql)����ִ��SQL���
	 *             3).�����SQL������insert,update����delete
	 *             ,���ǲ�����select(���������Statement�����excuteQuery(sql)�õ�ResultSet)
	 *             2.Connection��Statement����Ӧ�ó�������ݿ��������������Դ��ʹ�ú�һ��Ҫ�ر�
	 *             ��Ҫ��finally�йر�Connection��Statement����
	 *             3.�رյ�˳���ȹرպ��ȡ��,���ȹر�Statement����ر�Connection
	 *             ResultSet:���������װ��ʹ��JDBC���в�ѯ�Ľ��
	 *             1.����Statement�����excuteQuery(sql)�������Եõ������
	 *             2.ResultSet���ص�ʵ���Ͼ���һ�����ݱ���һ��ָ��
	 *             ָ�����ݱ�ĵ�һ����ǰ�棬���Ե���next()���������һ���Ƿ���Ч������Ч�򷵻�true
	 *             ,����ָ�����ƣ��൱�ڵ����������hasNext()��next()�Ľ����
	 *             3.��ָ���λ��ȷ����һ��ʱ������ͨ������getXxx(index)����getXxx(columnName)
	 *             ��ȡÿһ�е�ֵ�����磺getInt(1),getString("name") 4.ResultSet��ȻҲ��Ҫ���йر�
	 *             PreparedStatement: �������ύ 1.addBatch() ��һ�������ӵ�
	 *             PreparedStatement�����ڲ� 2.executeBatch()
	 *             ��һ�������ύ�����ݿ���ִ�У����ȫ������ִ�гɹ����򷵻ظ��¼�����ɵ����顣
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
		//�Ȼ�ȡaction,ȷ���û��Ĳ���.
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
				out.print("<script>alert('ע��ɹ�,������ת��½ҳ��!');window.location.href='jsp/userLogin.jsp'</script>");
			} catch (MyException ue){
				System.out.println("-------"+ue.getMessage());
				String message = ue.getMessage().toString();
				if(message.equals("��Ա����ռ��")){
					System.out.println("����ռ�õ�if");
					out.print("<script>alert('��Ա����ռ��!');window.location.href='jsp/register.jsp'</script>");
				} 
			}
		} else if (action.equals("login")) {
			try{
				InputedName = request.getParameter("name").toString();
				InputedPassWord =  request.getParameter("password").toString();
			}catch(NullPointerException e){
				e.printStackTrace();
			}
			//��һ����������Ϊsession��user
			session = request.getSession();
			try{
				user = (User)session.getAttribute("user");
				if(user == null){ 
					user = new User();
					session.setAttribute("user", user);
					System.out.println("session������user����ɹ�!");
				}
			} catch(Exception e){
				user = new User();
				session.setAttribute("user", user);
				System.out.println("session������user����ɹ�!");
			}
			//���user�Ƿ��ѵ�½
			isSuccess = user.isSuccess();
			userName = user.getUserName();
			if(isSuccess && InputedName.equals(userName)){
				//System.out.println("�Ѿ���½��");
				request.setAttribute("loginResult", InputedName+"�Ѿ���¼��");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/userLogin.jsp");
				rd.forward(request, response);
			} else {
				user.setUserName(InputedName);
				user.setPassword(InputedPassWord);
				try{
					userDaoImpl.login(user);
					cart = new Cart();
					session.setAttribute("cart", cart);
					//��½���ɹ�����userName������ҳ
					System.out.println("*��½�ɹ���������ҳ");
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
					rd.forward(request, response);
				} catch (MyException e){
					loginResult = "��������û��������ڣ������벻����";
					if(e.getMessage().toString().equals(loginResult)){
						request.setAttribute("loginResult", loginResult);
						RequestDispatcher rd = request.getRequestDispatcher("/jsp/userLogin.jsp");
						rd.forward(request, response);
					}
				}
			}
			
		} else if(action.equals("buy")){
			//�û��ύ������,�Զ�����ʼ����
			out.print("<script>alert('�µ��ɹ�!');window.location.href='jsp/index.jsp'</script>");
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
