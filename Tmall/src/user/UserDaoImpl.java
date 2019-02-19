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
		//获得链接对象
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql="select max(userId) from user";
		//创建语句传输对象
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
			//接收结果集
			resultSet = preparedStatement.executeQuery();
			//遍历结果集
			while(resultSet.next()) {
				//会员存在
				throw new MyException("会员名被占用") ;
			}
			//
			userId++;
			sql = "insert into user(userId,userName,password,status) values (?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, user.getUserName());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setInt(4, 0);
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
		//准备sql语句
		String sql = "update user set userName= ? , password = ? where userId = ?";
		//创建语句传输对象
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
		//准备sql语句
		String sql = "select * from user where userId = ?";
		//创建语句传输对象
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
	//准备sql语句
	String sql = "select * from user where userName = ?";
	//创建语句传输对象
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
					// 当前用户输入了错误的密码
					System.out.println("*密码错误");
					throw new MyException("密码错误");
				} else if (user.getPassword().equals(resultSet.getString(3))) {
					// 匹配成功，进入首页
					// 把状态变为1
					System.out.println("*登陆成功，进入首页");
				}
			}
		} else {
			// 当前用户不存在
			System.out.println("*会员名不存在");
			throw new MyException("该会员不存在");
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
		//准备sql语句
		String sql = "select * from user ";
		//创建语句传输对象
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//集合中只能放入user对象
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
		//准备sql语句
		System.out.println(startIndex);
		System.out.println(pageSize);
		//这里用了 limit n m  n表示开始查找位置，m表示查找多少行。注意，m须得写数值，不能写成pageSize，否则报错MySQLSyntaxErrorException
		String sql = "select * from user limit " + startIndex + ",5";
		System.out.println(sql);
		//创建语句传输对象
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//集合中只能放入user对象
		List<User> users = new ArrayList<User>();
		User user = null;
		try {
			System.out.println("开始按量查找数据");
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			System.out.println("成功获取结果集");
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
		//先获取totalRecorde
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		int totalRecord = userDaoImpl.count();
		System.out.println("总数据："+ totalRecord);
		Page<User> page = new Page<User>(pageNum, pageSize, totalRecord);
		//再获取startIndex
		int startIndex = page.getStarIndex();
		System.out.println("startIndex:" + startIndex);
		//按pageSize查数据，存入page的list中
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
				System.out.println("总有数据："+ count);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public void login(User user){
		// TODO Auto-generated method stub
		//连接数据库
		Connection connection = DBUtil.getConnection();
		//准备sql语句
		String sql = "select * from user where userName = ? and password = ?";
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String loginResult = "";
		//String backNews = "";
		try{
			String userName = user.getUserName();
			String password = user.getPassword();
			//创建结果集 resultSet			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			//接收结果集
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
				loginResult="您输入的用户名不存在，或密码不般配";
				throw new MyException(loginResult);
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		} finally{
			//关闭资源
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
	}
}
