package javass;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.Product;
import product.ProductCategory;
import product.ProductCategoryImpl;
import product.ProductImpl;
import utils.MyException;
import utils.Page;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



public class adminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";
    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    private final String adminname = "admin"
;
	/**
	 * Destruction of the servlet. <br>
	 */
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
		PrintWriter out = response.getWriter();;
		String action = "";
		String InputedProductCategoryName = "";
		String InputedProductName = "";
		String InputedProductPrice ="";
		String InputedProductStock ="";
		String InputedAdminName = "";
		String InputedAdminPassword = "";
				
		
		String filePathF = "";
		ProductCategory productCategory = null;
		Product product = null;
		ProductCategoryImpl productCategoryImpl = null;
		ProductImpl productImpl = null;
		int productCategoryId = 0;
		int productId = 0;
		try{
			action = request.getParameter("action");
		} catch(NullPointerException e){
			e.printStackTrace();
		}
		System.out.println("action:" + action);
		if(action.equals("adminLogin")){
			System.out.println("进入分类管理servlet");
			try{
				InputedAdminName = request.getParameter("adminName");
				InputedAdminPassword = request.getParameter("adminPassword");
			}catch(NullPointerException e){
				e.printStackTrace();
			}
			int pageNum = 0;
			try {
				pageNum = Integer.valueOf(request.getParameter("pageNum"));
			} catch(NumberFormatException e){
				pageNum = 1;
			}
			int pageSize = 5;
			System.out.println("当前页：" + pageNum);
			request.setAttribute("pageSize", 5);
			//完成数据库的查询，把结果放入request域中
			productCategoryImpl = new ProductCategoryImpl();
			Page<ProductCategory> p = productCategoryImpl.findAllProductCategoryNameWithPage(pageNum, pageSize);
			request.setAttribute("p", p);
			if(InputedAdminName.equals("admin") && InputedAdminPassword.equals("admin")){
				System.out.println("开始跳转");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/admin/index.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("loginResult", "您输入的用户名不存在，或密码不般配");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/admin/adminLogin.jsp");
				rd.forward(request, response);
			}
		} else if(action.equals("adminAddCategory")){
			//admin添加商品分类
			try{
				InputedProductCategoryName = request.getParameter("productCategoryName").toString();
			} catch (NullPointerException e){
				e.printStackTrace();
			}
			System.out.println(InputedProductCategoryName);
			productCategory = new ProductCategory();
			productCategory.setProductCategoryName(InputedProductCategoryName);
			productCategoryImpl = new ProductCategoryImpl();
			try{
				productCategoryImpl.add(productCategory);
				System.out.println("添加了分类");
				out.print("<script>alert('添加完成');window.location.href='../Tmall/jsp/admin/index.jsp'</script>");
			} catch (MyException ue){
				System.out.println("-------"+ue.getMessage());
				String message = ue.getMessage().toString();
				if(message.equals("分类名已存在")){
					System.out.println("进入占用的if");
					out.print("<script>alert('分类名已存在!');window.location.href='../Tmall/jsp/admin/index.jsp'</script>");
				} 
			}
		} else if(action.equals("deleteProductCategory")){
			productCategory = new ProductCategory();
			productCategoryImpl = new ProductCategoryImpl();
			try{
				productCategoryId =  Integer.valueOf(request.getParameter("productCategoryId"));
			} catch (NullPointerException e){
				productCategoryId = 0;
			}
			System.out.println("productCategoryId"+ productCategoryId);
			productCategoryImpl.delete(productCategoryId);
			out.print("<script>alert('删除成功');window.location.href='../Tmall/jsp/admin/index.jsp'</script>");
		} else if(action.equals("adminAddProduct")){
			System.out.println("adminAddProduct");
//			// 检测是否为多媒体上传
//	        if (!ServletFileUpload.isMultipartContent(request)) {
//	            // 如果不是则停止
//	            PrintWriter writer = response.getWriter();
//	            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
//	            writer.flush();
//	            return;
//	        }
	        // 配置上传参数
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
	        factory.setSizeThreshold(MEMORY_THRESHOLD);
	        // 设置临时存储目录
	        //factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	        factory.setRepository(new File(System.getProperty("catalina.home")));
	  
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        upload.setHeaderEncoding("UTF-8");//解决http报头乱码，即中文文件名乱码
	        upload.setFileSizeMax(MAX_FILE_SIZE);// 设置最大文件上传值
	        upload.setSizeMax(MAX_REQUEST_SIZE); // 设置最大请求值 (包含文件和表单数据)
	        // 构造临时路径来存储上传的文件
	        // 这个路径相对当前应用的目录
	        String uploadPath = getServletContext().getRealPath("./") + UPLOAD_DIRECTORY;
	        System.out.println("uploadPath" + uploadPath);
	        // 如果目录不存在则创建
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdir();
	        }
	        String[] addProduct = new String[100];
	  
	        try {
	            // 解析请求的内容提取文件数据
	            @SuppressWarnings("unchecked")
	            List<FileItem> formItems = upload.parseRequest(request);
	            if (formItems != null && formItems.size() > 0) {
	            	int i = 0;
	                // 迭代表单数据
	                for (FileItem item : formItems) {
	                    // 处理不在表单中的字段
	                    if (!item.isFormField()) {
	                        String fileName = new File(item.getName()).getName();
	                        String filePath = uploadPath + File.separator + fileName;
	                        File storeFile = new File(filePath);
	                        // 保存文件到硬盘
	                        item.write(storeFile);
	                        filePathF = "upload" + File.separator + fileName;
	                        System.out.println(filePathF);
	                    } else {
	                    	//普通表单域
	                    	String value = item.getString("UTF-8");//解决表单输入输乱码。
	                    	addProduct[i] = value;
	                    	System.out.println("value:" + value);
	                    	System.out.println(addProduct[i]);
	                    	i++;
	                    }
	                }
	            }
    			product = new Product();
    			product.setProductName(addProduct[0]);
    			product.setProductPrice(addProduct[1]);
    			product.setStock(addProduct[2]);
    			product.setProductCategoryId(addProduct[3]);
    			product.setProductImg(filePathF);
    			productImpl = new ProductImpl();
    			if(productImpl.add(product)){
    				System.out.println("添加商品陈功");
        			out.print("<script>alert('添加商品成功');window.location.href='../Tmall/jsp/admin/productManagement.jsp'</script>");
    			} else {
    				out.print("<script>alert('添加商品失败');window.location.href='../Tmall/jsp/admin/productManagement.jsp'</script>");
    			}
	        } catch (Exception ex) {
	            request.setAttribute("message",
	                    "错误信息: " + ex.getMessage());
	            // 跳转到 message.jsp
		        getServletContext().getRequestDispatcher("/jsp/admin/uploadFile.jsp").forward(
		                request, response);
	        }
		} else if(action.equals("deleteProduct")){
			product = new Product();
			productImpl = new ProductImpl();
			try{
				productId =  Integer.valueOf(request.getParameter("productId"));
			} catch (NullPointerException e){
				productCategoryId = 0;
			}
			System.out.println("productCategoryId"+ productCategoryId);
			if(productImpl.delete(productId)){
				out.print("<script>alert('删除成功');window.location.href='../Tmall/jsp/admin/productManagement.jsp'</script>");
			} else {
				out.print("<script>alert('删除失败');window.location.href='../Tmall/jsp/admin/productManagement.jsp'</script>");
			}
			
		} else if(action.equals("adminEditProduct")) {
			System.out.println("adminEditProduct");
			try{
				System.out.println("kkkkkkkkkkkkkkkkkkkkkkkk");
				InputedProductName = request.getParameter("productName");
				InputedProductPrice = request.getParameter("productPrice");
				InputedProductStock = request.getParameter("productStock");
				//String productIdd = request.getParameter("productId").toString();
				productId = Integer.valueOf(request.getParameter("productId"));
				//System.out.println("productIdd" + productIdd);
				//productId = 1;
			} catch(NullPointerException e){
				e.printStackTrace();
			}
			System.out.println("productId" + productId);
			product = new Product();
			product.setProductName(InputedProductName);
			product.setProductPrice(InputedProductPrice);
			product.setStock(InputedProductStock);
			product.setProductId(productId);
			productImpl = new ProductImpl();
			if(productImpl.update(product)){
				out.print("<script>alert('更新成功');window.location.href='../Tmall/jsp/admin/productManagement.jsp'</script>");
			} else {
				out.print("<script>alert('更新失败');window.location.href='../Tmall/jsp/admin/productManagement.jsp'</script>");
			}
		
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
