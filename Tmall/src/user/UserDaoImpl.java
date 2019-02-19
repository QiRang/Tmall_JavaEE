package user;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.*;

import utils.DBUtil;
import utils.MyException;
import utils.Page;


public class UserDaoImpl implements UserDao {
	
	@Override
	public void add(User user) {
		//������Ӷ���
		Connection connection = DBUtil.getConnection();
		//׼��sql���
		String sql="select max(userId) from user";
		//������䴫�����
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int userId=0;
		try {
			preparedStatement=connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				System.out.println("111:::::"+resultSet);
				userId++;
			}
			sql = "select * from user where userName = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUserName());
			//���ս����
			resultSet = preparedStatement.executeQuery();
			//���������
			while(resultSet.next()) {
				//��Ա����
				throw new MyException("��Ա����ռ��") ;
			}
			//
			userId++;
			sql = "insert into user(userId,userName,password,status) values (?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, user.getUserName());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setInt(4, 0);
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
	
	@Override
	public void delete(int userId) {
		Connection connection = DBUtil.getConnection();
		String sql = "delete from user where userId = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	
	}
	
	@Override
	public void update(User user) {
		Connection connection = DBUtil.getConnection();
		//׼��sql���
		String sql = "update user set userName= ? , password = ? where userId = ?";
		//������䴫�����
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setInt(3, user.getUserId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}
	
	@Override
	public User load(int userId) {
		Connection connection = DBUtil.getConnection();
		//׼��sql���
		String sql = "select * from user where userId = ?";
		//������䴫�����
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				user = new User();
				user.setUserId(userId);
				user.setUserName(resultSet.getString("userName"));
				user.setPassword(resultSet.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return user;
	}
	
	@Override
	public User load(String userName) {
	// TODO Auto-generated method stub
	Connection connection = DBUtil.getConnection();
	//׼��sql���
	String sql = "select * from user where userName = ?";
	//������䴫�����
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	User user = null;
	try {
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, userName);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			resultSet.previous();
			while (resultSet.next()) {
				if (userName.equals(resultSet.getString(3))) {
					// ��ǰ�û������˴��������
					System.out.println("*�������");
					throw new MyException("�������");
				} else if (user.getPassword().equals(resultSet.getString(3))) {
					// ƥ��ɹ���������ҳ
					// ��״̬��Ϊ1
					System.out.println("*��½�ɹ���������ҳ");
				}
			}
		} else {
			// ��ǰ�û�������
			System.out.println("*��Ա��������");
			throw new MyException("�û�Ա������");
		}
		while(resultSet.next()) {
			user = new User();
			user.setUserName(userName);
			user.setUserId(resultSet.getInt("userId"));
			user.setPassword(resultSet.getString("password"));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		DBUtil.close(resultSet);
		DBUtil.close(preparedStatement);
		DBUtil.close(connection);
	}
	return user;
	}
	
	@Override
	public List<User> load() {
		Connection connection = DBUtil.getConnection();
		//׼��sql���
		String sql = "select * from user ";
		//������䴫�����
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//������ֻ�ܷ���user����
		List<User> users = new ArrayList<User>();
		User user = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				user = new User();
				user.setUserId(resultSet.getInt("userId"));
				user.setUserName(resultSet.getString("userName"));
				user.setPassword(resultSet.getString("password"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return users;
	}
	public List<User> load(int startIndex, int pageSize) {
		Connection connection = DBUtil.getConnection();
		//׼��sql���
		System.out.println(startIndex);
		System.out.println(pageSize);
		//�������� limit n m  n��ʾ��ʼ����λ�ã�m��ʾ���Ҷ����С�ע�⣬m���д��ֵ������д��pageSize�����򱨴�MySQLSyntaxErrorException
		String sql = "select * from user limit " + startIndex + ",5";
		System.out.println(sql);
		//������䴫�����
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//������ֻ�ܷ���user����
		List<User> users = new ArrayList<User>();
		User user = null;
		try {
			System.out.println("��ʼ������������");
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			System.out.println("�ɹ���ȡ�����");
			while(resultSet.next()) {
				System.out.println("111" + resultSet.getString("userName"));
				user = new User();
				user.setUserId(resultSet.getInt("userId"));
				user.setUserName(resultSet.getString("userName"));
				user.setPassword(resultSet.getString("password"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return users;
	}
	//
	public Page<User> findAllUserWithPage(int pageNum, int pageSize){
		//�Ȼ�ȡtotalRecorde
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		int totalRecord = userDaoImpl.count();
		System.out.println("�����ݣ�"+ totalRecord);
		Page<User> page = new Page<User>(pageNum, pageSize, totalRecord);
		//�ٻ�ȡstartIndex
		int startIndex = page.getStarIndex();
		System.out.println("startIndex:" + startIndex);
		//��pageSize�����ݣ�����page��list��
		List<User> list = userDaoImpl.load(startIndex, pageSize);
		page.setList(list);
		return page;
	}
	public int count(){
		Connection connection = DBUtil.getConnection();
		String sql = "select count(*) from user";
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
	public void login(User user){
		// TODO Auto-generated method stub
		//�������ݿ�
		Connection connection = DBUtil.getConnection();
		//׼��sql���
		String sql = "select * from user where userName = ? and password = ?";
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String loginResult = "";
		//String backNews = "";
		try{
			String userName = user.getUserName();
			String password = user.getPassword();
			//��������� resultSet			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			//���ս����
			resultSet = preparedStatement.executeQuery();
			boolean  m = resultSet.next();
			if (m==true) {
				//resultSet.previous();
				//System.out.println(resultSet.getString(2)+resultSet.getString(3) +"ejvfnfvnfvnfv");
				user.setSuccess(true);
				user.setUserName(userName);
				user.setPassword(password);
			} else {
				user.setSuccess(false);
				user.setUserName(userName);
				user.setPassword(password);
				loginResult="��������û��������ڣ������벻����";
				throw new MyException(loginResult);
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		} finally{
			//�ر���Դ
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}
}
