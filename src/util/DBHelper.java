package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
	
	// ���ݿ�����
	private static final String driver = "com.mysql.jdbc.Driver";
	// �������ݿ�  shopping ��һ������ Ҫ��mysql ���Ƚ���
	private static final String url = "jdbc:mysql://localhost:3306/shopping?useUnicode=true&characterEncoding=utf-8"; 
	private static final String username = "root";
	private static final String password = "111111";
	private static Connection conn;
	
	// ��̬����鸺���������
	static {
		try{
			Class.forName(driver);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	/*
	 * ���͵ĵ���ģʽ �������Ӷ���
	 * ���Ǵ���һ�� conn �����������ֱ�ӷ����������ǲ����� ���½�һ��
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
