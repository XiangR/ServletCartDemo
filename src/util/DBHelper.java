package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
	
	// 数据库驱动
	private static final String driver = "com.mysql.jdbc.Driver";
	// 连接数据库  shopping 是一个库名 要在mysql 中先建立
	private static final String url = "jdbc:mysql://localhost:3306/shopping?useUnicode=true&characterEncoding=utf-8"; 
	private static final String username = "root";
	private static final String password = "111111";
	private static Connection conn;
	
	// 静态代码块负责加载驱动
	static {
		try{
			Class.forName(driver);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	/*
	 * 典型的单例模式 返回连接对象
	 * 我们创建一个 conn 若是其存在则直接返回它，若是不存在 则新建一个
	 */
	public static Connection getConnection() throws SQLException {
		if(conn == null) {
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		} else {
			return conn;
		}
	}
	
	public static void main(String[] args) {
		
		try {
			Connection conn = DBHelper.getConnection();
			Connection conn1 = DBHelper.getConnection();
			System.out.println(conn.toString());
			System.out.println(conn1.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
