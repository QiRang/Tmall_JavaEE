package utils;

import java.sql.*;
import java.util.Properties;


public class DBUtil {
	public static Connection getConnection() {
		String sqlDriver = "com.mysql.jdbc.Driver";
		String sqlUrl = "jdbc:mysql://localhost:3306/tmall";
		String sqlUser = "admin";
		String sqlPassword = "admin";
		Properties properties = new Properties();
		properties.setProperty("user", sqlUser);
		properties.setProperty("password", sqlPassword);
		properties.setProperty("useSSL", "false");
		properties.setProperty("useUnicode", "true");
		properties.setProperty("characterEncoding", "utf-8");
		try {
			// 1 加载驱动
			Class.forName(sqlDriver).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Connection connection = null;
		try {
			// 2 创建链接对象connection
			connection = DriverManager.getConnection(sqlUrl, properties);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	// 关闭资源的方法
	public static void close(Connection connection) {
		try {
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close(PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void close(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
