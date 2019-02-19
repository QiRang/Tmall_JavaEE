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
	//���ӷ���
	public void add(ProductCategory productCategory){
		//������Ӷ���
		Connection connection = DBUtil.getConnection();
		String sql ="";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//int productCategoryId=0;
		try {
			sql = "select * from productcategory where productCategoryName = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productCategory.getProductCategoryName());
			//���ս����
			resultSet = preparedStatement.executeQuery();
			//���������
			while(resultSet.next()) {
				//����������
				throw new MyException("�������Ѵ���") ;
			}
			//
			//productCategoryId++;
			sql = "insert into productcategory(productCategoryName) values (?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, productCategory.getProductCategoryName());
			System.out.println("��������"+productCategory.getProductCategoryName());
			//preparedStatement.setString(2, productCategory.getProductCategoryName());
			preparedStatement.addBatch();//��һ�������ӵ��� PreparedStatement �����������������
			preparedStatement.executeBatch();// ��һ�������ύ�����ݿ���ִ�У����ȫ������ִ�гɹ����򷵻ظ��¼�����ɵ����顣
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�ر���Դ
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	
	}
	//��ȡ���з���,����list��
	public List<ProductCategory> load() {
		//System.out.println("dsghjjjjjjj");
		Connection connection = DBUtil.getConnection();
		//׼��sql���
		String sql = "select * from productCategory ";
		//������䴫�����
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
	//��������ȡһ�����ķ���,����list��
	public List<ProductCategory> load(int startIndex, int pageSize) {
		Connection connection = DBUtil.getConnection();
		//׼��sql���
		System.out.println(startIndex);
		System.out.println(pageSize);
		//�������� limit n m  n��ʾ��ʼ����λ�ã�m��ʾ���Ҷ����С�ע�⣬m���д��ֵ������д��pageSize�����򱨴�MySQLSyntaxErrorException
		String sql = "select * from productCategory limit " + startIndex + ",5";
		System.out.println(sql);
		//������䴫�����
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//������ֻ�ܷ���user����
		List<ProductCategory> productCategorys = new ArrayList<ProductCategory>();
		ProductCategory productCategory = null;
		try {
			System.out.println("��ʼ������������");
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			System.out.println("�ɹ���ȡ�����");
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
	//��������ȡһ�����ķ������Page���list��
	public Page<ProductCategory> findAllProductCategoryNameWithPage(int pageNum, int pageSize){
		ProductCategoryImpl productCategoryImpl = new ProductCategoryImpl();
		//�Ȼ�ȡtotalRecorde
		int totalRecord = productCategoryImpl.count();
		System.out.println("�����ݣ�"+ totalRecord);
		Page<ProductCategory> page = new Page<ProductCategory>(pageNum, pageSize, totalRecord);
		//�ٻ�ȡstartIndex
		int startIndex = page.getStarIndex();
		System.out.println("startIndex:" + startIndex);
		//��pageSize�����ݣ�����page��list��
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
				System.out.println("�������ݣ�"+ count);
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
