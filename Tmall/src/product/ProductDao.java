package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DBUtil;
import utils.Page;


public interface ProductDao {
	public boolean add(Product product);
	public boolean delete(int productId);
	public boolean update(Product product);
	public int count();
	public int count(String productCategoryId);
	public Product findProductById(int productId);//ͨ��id������Ʒ
	public List<Product> findProductByCategory(int productCategoryId);//ͨ���������һ�������Ĳ�Ʒ
	public Page<Product> findAllProductWithPage(int pageNum, int pageSize);
	public Page<Product> findAllProductWithPage(int pageNum, int pageSize, String productCategoryId);
	public List<Product> load(int productCategoryId);//���ҷ����µ����в�Ʒ
	public List<Product> load(int startIndex, int pageSize, String productCategoryId);//��������ȡһ�����ķ���,����list��
}
