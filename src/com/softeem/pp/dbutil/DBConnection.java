package com.softeem.pp.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * java�������ݿ�������(java database connectivity):
 * 1.��������
 * 2.��ȡ����
 * 3.���ִ������
 * 4.ִ��
 * 5.�������
 * 6.�ͷ���Դ(�ر���Դ)
 * @author ������
 */
public class DBConnection {

	private static final String CLASSDRIVER="com.mysql.cj.jdbc.Driver";
	private static final String URL="jdbc:mysql://127.0.0.1:3306/pp?serverTimezone=UTC";
	private static final String USERNAME="root";
	private static final String PASSWORD="qingyang1234";
	
	//1.��������
	static{
		//DriverManager.registerDriver(new Driver());
		//System.setProperty("jdbc.Driver","com.mysql.jdbc.Driver");
		try {
			Class.forName(CLASSDRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//2.��ȡ����
	public static Connection getConn(){
		Connection conn = null;
		//shift+alt+xת��д shift+alt+yתСд
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	//6.�ͷ���Դ
	public static void close(ResultSet rs,PreparedStatement ps,Connection conn){
		
		try {
			if(rs != null){
				rs.close();
			}
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
