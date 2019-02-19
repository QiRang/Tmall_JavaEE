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
    // �ϴ��ļ��洢Ŀ¼
    private static final String UPLOAD_DIRECTORY = "upload";
    // �ϴ�����
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
			System.out.println("����������servlet");
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
			System.out.println("��ǰҳ��" + pageNum);
			request.setAttribute("pageSize", 5);
			//������ݿ�Ĳ�ѯ���ѽ������request����
			productCategoryImpl = new ProductCategoryImpl();
			Page<ProductCategory> p = productCategoryImpl.findAllProductCategoryNameWithPage(pageNum, pageSize);
			request.setAttribute("p", p);
			if(InputedAdminName.equals("admin") && InputedAdminPassword.equals("admin")){
				System.out.println("��ʼ��ת");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/admin/index.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("loginResult", "��������û��������ڣ������벻����");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/admin/adminLogin.jsp");
				rd.forward(request, response);
			}
		} else if(action.equals("adminAddCategory")){
			//admin�����Ʒ����
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
				System.out.println("����˷���");
				out.print("<script>alert('������');window.location.href='../Tmall/jsp/admin/index.jsp'</script>");
			} catch (MyException ue){
				System.out.println("-------"+ue.getMessage());
				String message = ue.getMessage().toString();
				if(message.equals("�������Ѵ���")){
					System.out.println("����ռ�õ�if");
					out.print("<script>alert('�������Ѵ���!');window.location.href='../Tmall/jsp/admin/index.jsp'</script>");
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
			out.print("<script>alert('ɾ���ɹ�');window.location.href='../Tmall/jsp/admin/index.jsp'</script>");
		} else if(action.equals("adminAddProduct")){
			System.out.println("adminAddProduct");
//			// ����Ƿ�Ϊ��ý���ϴ�
//	        if (!ServletFileUpload.isMultipartContent(request)) {
//	            // ���������ֹͣ
//	            PrintWriter writer = response.getWriter();
//	            writer.println("Error: ��������� enctype=multipart/form-data");
//	            writer.flush();
//	            return;
//	        }
	        // �����ϴ�����
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        // �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
	        factory.setSizeThreshold(MEMORY_THRESHOLD);
	        // ������ʱ�洢Ŀ¼
	        //factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	        factory.setRepository(new File(System.getProperty("catalina.home")));
	  
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        upload.setHeaderEncoding("UTF-8");//���http��ͷ���룬�������ļ�������
	        upload.setFileSizeMax(MAX_FILE_SIZE);// ��������ļ��ϴ�ֵ
	        upload.setSizeMax(MAX_REQUEST_SIZE); // �����������ֵ (�����ļ��ͱ�����)
	        // ������ʱ·�����洢�ϴ����ļ�
	        // ���·����Ե�ǰӦ�õ�Ŀ¼
	        String uploadPath = getServletContext().getRealPath("./") + UPLOAD_DIRECTORY;
	        System.out.println("uploadPath" + uploadPath);
	        // ���Ŀ¼�������򴴽�
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdir();
	        }
	        String[] addProduct = new String[100];
	  
	        try {
	            // ���������������ȡ�ļ�����
	            @SuppressWarnings("unchecked")
	            List<FileItem> formItems = upload.parseRequest(request);
	            if (formItems != null && formItems.size() > 0) {
	            	int i = 0;
	                // ����������
	                for (FileItem item : formItems) {
	                    // �����ڱ��е��ֶ�
	                    if (!item.isFormField()) {
	                        String fileName = new File(item.getName()).getName();
	                        String filePath = uploadPath + File.separator + fileName;
	                        File storeFile = new File(filePath);
	                        // �����ļ���Ӳ��
	                        item.write(storeFile);
	                        filePathF = "upload" + File.separator + fileName;
	                        System.out.println(filePathF);
	                    } else {
	                    	//��ͨ����
	                    	String value = item.getString("UTF-8");//��������������롣
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
    				System.out.println("�����Ʒ�¹�");
        			out.print("<script>alert('�����Ʒ�ɹ�');window.location.href='../Tmall/jsp/admin/productManagement.jsp'</script>");
    			} else {
    				out.print("<script>alert('�����Ʒʧ��');window.location.href='../Tmall/jsp/admin/productManagement.jsp'</script>");
    			}
	        } catch (Exception ex) {
	            request.setAttribute("message",
	                    "������Ϣ: " + ex.getMessage());
	            // ��ת�� message.jsp
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
				out.print("<script>alert('ɾ���ɹ�');window.location.href='../Tmall/jsp/admin/productManagement.jsp'</script>");
			} else {
				out.print("<script>alert('ɾ��ʧ��');window.location.href='../Tmall/jsp/admin/productManagement.jsp'</script>");
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
				out.print("<script>alert('���³ɹ�');window.location.href='../Tmall/jsp/admin/productManagement.jsp'</script>");
			} else {
				out.print("<script>alert('����ʧ��');window.location.href='../Tmall/jsp/admin/productManagement.jsp'</script>");
			}
		
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
