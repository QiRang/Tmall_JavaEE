package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DBUtil;
import utils.MyException;
import utils.Page;

public class ProductCategoryImpl  implements ProductCategoryDao{
	//增加分类
	public void add(ProductCategory productCategory){
		//获得链接对象
		Connection connection = DBUtil.getConnection();
		String sql ="";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//int productCategoryId=0;
		try {
			sql = "select * from productcategory where productCategoryName = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productCategory.getProductCategoryName());
			//接收结果集
			resultSet = preparedStatement.executeQuery();
			//遍历结果集
			while(resultSet.next()) {
				//分类名存在
				throw new MyException("分类名已存在") ;
			}
			//
			//productCategoryId++;
			sql = "insert into productcategory(productCategoryName) values (?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productCategory.getProductCategoryName());
			System.out.println("分类名："+productCategory.getProductCategoryName());
			//preparedStatement.setString(2, productCategory.getProductCategoryName());
			preparedStatement.addBatch();//将一组参数添加到此 PreparedStatement 对象的批处理命令中
			preparedStatement.executeBatch();// 将一批参数提交给数据库来执行，如果全部命令执行成功，则返回更新计数组成的数组。
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭资源
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	
	}
	//读取所有分类,存在list中
	public List<ProductCategory> load() {
		//System.out.println("dsghjjjjjjj");
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql = "select * from productCategory ";
		//创建语句传输对象
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		ProductImpl productImpl = new ProductImpl();
		List<ProductCategory> productCategorys = new ArrayList<ProductCategory>();
		ProductCategory productCategory = null;
		int productCategoryId = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				productCategory = new ProductCategory();
				productCategoryId = resultSet.getInt("productCategoryId");
				List<Product> list = productImpl.findProductByCategory(productCategoryId);
				System.out.println("list"+list);
				productCategory.setProducts(list);
				productCategory.setProductCategoryId(resultSet.getInt("productCategoryId"));
				productCategory.setProductCategoryName(resultSet.getString("productCategoryName"));
				productCategorys.add(productCategory);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return productCategorys;
	}
	//按数量读取一定量的分类,存在list中
	public List<ProductCategory> load(int startIndex, int pageSize) {
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		System.out.println(startIndex);
		System.out.println(pageSize);
		//这里用了 limit n m  n表示开始查找位置，m表示查找多少行。注意，m须得写数值，不能写成pageSize，否则报错MySQLSyntaxErrorException
		String sql = "select * from productCategory limit " + startIndex + ",5";
		System.out.println(sql);
		//创建语句传输对象
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//集合中只能放入user对象
		List<ProductCategory> productCategorys = new ArrayList<ProductCategory>();
		ProductCategory productCategory = null;
		try {
			System.out.println("开始按量查找数据");
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			System.out.println("成功获取结果集");
			ProductImpl productImpl = new ProductImpl();
			while(resultSet.next()) {
				productCategory = new ProductCategory();
				List<Product> list = productImpl.findProductByCategory(resultSet.getInt("productCategoryId"));
				productCategory.setProducts(list);
				productCategory.setProductCategoryId(resultSet.getInt("productCategoryId"));
				productCategory.setProductCategoryName(resultSet.getString("productCategoryName"));
				productCategorys.add(productCategory);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return productCategorys;
	}
	//按数量读取一定量的分类存在Page类的list中
	public Page<ProductCategory> findAllProductCategoryNameWithPage(int pageNum, int pageSize){
		ProductCategoryImpl productCategoryImpl = new ProductCategoryImpl();
		//先获取totalRecorde
		int totalRecord = productCategoryImpl.count();
		System.out.println("总数据："+ totalRecord);
		Page<ProductCategory> page = new Page<ProductCategory>(pageNum, pageSize, totalRecord);
		//再获取startIndex
		int startIndex = page.getStarIndex();
		System.out.println("startIndex:" + startIndex);
		//按pageSize查数据，存入page的list中
		List<ProductCategory> list = productCategoryImpl.load(startIndex, pageSize);
		page.setList(list);
		System.out.println("list:" + list);
		return page;
	}
	public int count(){
		Connection connection = DBUtil.getConnection();
		String sql = "select count(*) from productCategory";
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
	public void delete(int productCategoryId) {
		// TODO Auto-generated method stub
		Connection connection = DBUtil.getConnection();
		String sql = "delete from productCategory where productCategoryId = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, productCategoryId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}
	
}
