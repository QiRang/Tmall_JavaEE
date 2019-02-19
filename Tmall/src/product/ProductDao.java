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
	public Product findProductById(int productId);//通过id查找商品
	public List<Product> findProductByCategory(int productCategoryId);//通过分类查找一定数量的产品
	public Page<Product> findAllProductWithPage(int pageNum, int pageSize);
	public Page<Product> findAllProductWithPage(int pageNum, int pageSize, String productCategoryId);
	public List<Product> load(int productCategoryId);//查找分类下的所有产品
	public List<Product> load(int startIndex, int pageSize, String productCategoryId);//按数量读取一定量的分类,存在list中
}
