package product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.List;
import utils.DBUtil;
import utils.MyException;
import utils.Page;

public class ProductImpl implements ProductDao{
	public boolean add(Product product) {
		System.out.println("开始插入");
		// TODO Auto-generated method stub
		//获得链接对象
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql = "insert into product(productName,productPrice,productCategoryId,productStock,productImg) values (?,?,?,?,?)";
		//创建语句传输对象
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setString(2, product.getProductPrice());
			preparedStatement.setString(3, product.getProductCategoryId());
			preparedStatement.setString(4, product.getStock());
			preparedStatement.setString(5, product.getProductImg());
			preparedStatement.addBatch();//将一组参数添加到此 PreparedStatement 对象的批处理命令中
			preparedStatement.executeBatch();// 将一批参数提交给数据库来执行，如果全部命令执行成功，则返回更新计数组成的数组。
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
			//e.printStackTrace();
		}finally {
			//关闭资源
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}

	public boolean delete(int productId) {
		// TODO Auto-generated method stub
		Connection connection = DBUtil.getConnection();
		String sql = "delete from product where productId = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
			//e.printStackTrace();
		}finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		
	}

	public boolean update(Product product) {
		// TODO Auto-generated method stub
		//获得链接对象
		Connection connection = DBUtil.getConnection();
		String sql ="";
		PreparedStatement preparedStatement = null;
		int  resultSet = 0;
		boolean flag = false;
		//int productCategoryId=0;
		try {
			System.out.println("开始更新");
			sql = "update product set productName = ?, productPrice = ?, productStock = ? where productId = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setObject(1, product.getProductName());
			preparedStatement.setObject(2, product.getProductPrice());
			preparedStatement.setObject(3, product.getStock());
			preparedStatement.setObject(4, product.getProductId());
			//preparedStatepreparedStatementment.setObject(1, product.getProductName());
			//接收结果集
			System.out.println("sql:" + sql);
			System.out.println(preparedStatement);
			resultSet = preparedStatement.executeUpdate();
			System.out.println("resultSet" + resultSet);
			if(resultSet > 0){
				flag = true;
				System.out.println("更新完成");
			}
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
			//e.printStackTrace();
		}finally {
			//关闭资源
			//DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		
	}
	public List<Product> findProductByCategory(int productCategoryId) {
		// TODO Auto-generated method stub
		Connection connection = (Connection) DBUtil.getConnection();
		//只返回6行数据
		String sql = "select * from product where productCategoryId = ? limit 5";
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Product product = null;
		List<Product> products = new ArrayList<Product>();
		//设置价格的格式
		try{
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setInt(1, productCategoryId);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
					product = new Product();
					product.setProductId(resultSet.getInt("productId"));
					product.setProductName(resultSet.getString("productName"));
					product.setProductImg(resultSet.getString("productImg"));
					product.setProductPrice(resultSet.getString("productPrice"));
					product.setProductStoreId(resultSet.getString("productStoreId"));
					product.setProductCategoryId(resultSet.getString("productCategoryId"));
					product.setStock(resultSet.getString("productStock"));
					products.add(product);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public Product findProductById(int productId) {
		// TODO Auto-generated method stub
		Connection connection = (Connection) DBUtil.getConnection();
		//准备sql语句
		String sql = "select * from product where productId = ?";
		//创建语句传输对象
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Product product = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				product = new Product();
				product.setProductId(productId);
				product.setProductName(resultSet.getString("productName"));
				product.setProductImg(resultSet.getString("productImg"));
				product.setProductPrice(resultSet.getString("productPrice"));
				product.setProductStoreId(resultSet.getString("productStoreId"));
				product.setProductCategoryId(resultSet.getString("productCategoryId"));
				product.setStock(resultSet.getString("productStock"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return product;
	}
	//按分类查询该分类下的商品，存在ProductCategory类的list中。
	public List<Product> load(int productCategoryId) {
		// TODO Auto-generated method stub
		System.out.println(productCategoryId);
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql = "select * from product";
		//创建语句传输对象
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		List<Product> products = new ArrayList<Product>();
		Product product = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				product = new Product();
				product.setProductId(resultSet.getInt("productId"));
				product.setProductName(resultSet.getString("productName"));
				product.setProductPrice(resultSet.getString("productPrice"));
				product.setProductImg(resultSet.getString("productImg"));
				product.setStock(resultSet.getString("productStock"));
				products.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return products;
	}
	//按数量读取一定量的分类,存在list中
		public List<Product> load(int startIndex, int pageSize, String productCategoryId) {
			Connection connection = DBUtil.getConnection();
			//准备sql语句
			System.out.println(startIndex);
			System.out.println(pageSize);
			//这里用了 limit n m  n表示开始查找位置，m表示查找多少行。注意，m须得写数值，不能写成pageSize，否则报错MySQLSyntaxErrorException
			String sql = "select * from product where productCategoryId = ? limit " + startIndex + ",5";
			System.out.println(sql);
			//创建语句传输对象
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			//集合中只能放入user对象
			List<Product> products = new ArrayList<Product>();
			Product product = null;
			try {
				System.out.println("开始按量查找数据");
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, productCategoryId);
				resultSet = preparedStatement.executeQuery();
				System.out.println("成功获取结果集");
				//ProductImpl productImpl = new ProductImpl();
				while(resultSet.next()) {
					product = new Product();
					//List<Product> list = productImpl.findProductByCategory(resultSet.getInt("productId"));
					product.setProductId(resultSet.getInt("productId"));
					product.setProductName(resultSet.getString("productName"));
					product.setProductPrice(resultSet.getString("productPrice"));
					product.setProductImg(resultSet.getString("productImg"));
					product.setStock(resultSet.getString("productStock"));
					products.add(product);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBUtil.close(resultSet);
				DBUtil.close(preparedStatement);
				DBUtil.close(connection);
			}
			return products;
		}
	@Override
	public Page<Product> findAllProductWithPage(int pageNum, int pageSize) {
		ProductImpl productImpl = new ProductImpl();
		//先获取totalRecorde
		int totalRecord = productImpl.count();
		System.out.println("总数据："+ totalRecord);
		Page<Product> page = new Page<Product>(pageNum, pageSize, totalRecord);
		//再获取startIndex
		int startIndex = page.getStarIndex();
		System.out.println("startIndex:" + startIndex);
		//按pageSize查数据，存入page的list中
		List<Product> list = productImpl.load(startIndex, pageSize, "1");
		page.setList(list);
		System.out.println("list:" + list);
		return page;
	}
	public int count(){
		Connection connection = DBUtil.getConnection();
		String sql = "select count(*) from product";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		try{
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				count = resultSet.getInt(1);
				System.out.println("总有数据："+ count);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Page<Product> findAllProductWithPage(int pageNum, int pageSize,
			String productCategoryId) {
		// TODO Auto-generated method stub
		ProductImpl productImpl = new ProductImpl();
		//先获取totalRecorde
		int totalRecord = productImpl.count(productCategoryId);
		System.out.println("总数据："+ totalRecord);
		Page<Product> page = new Page<Product>(pageNum, pageSize, totalRecord);
		//再获取startIndex
		int startIndex = page.getStarIndex();
		System.out.println("startIndex:" + startIndex);
		//按pageSize查数据，存入page的list中
		List<Product> list = productImpl.load(startIndex, pageSize, productCategoryId);
		page.setList(list);
		System.out.println("list:" + list);
		return page;
	}

	@Override
	public int count(String productCategoryId) {
		// TODO Auto-generated method stub
		Connection connection = DBUtil.getConnection();
		String sql = "select count(*) from product where productCategoryId = ?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int count = 0;
		try{
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productCategoryId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				count = resultSet.getInt(1);
				System.out.println("总有数据："+ count);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}	
}
