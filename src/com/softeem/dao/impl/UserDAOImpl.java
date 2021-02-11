package com.softeem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softeem.dao.UserDAO;
import com.softeem.pp.dbutil.DBConnection;
import com.softeem.pp.dto.User;

public class UserDAOImpl implements UserDAO {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * �û���½
	 */
	public User login(String username) {
		String sql = "select * from tb_user where u_name=?";
		User user = null;
		try {
			//��ȡ����
			conn = DBConnection.getConn();
			//���Ԥ��������
			ps = conn.prepareStatement(sql);
			//Ԥ����
			ps.setString(1, username);
			//ִ��
			rs = ps.executeQuery();
			if(rs.next()){
				user = new User();
				user.setUid(rs.getInt("u_id"));
				user.setUname(rs.getString("u_name"));
				user.setUpass(rs.getString("u_pass"));
				user.setUflag(rs.getInt("u_flag"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Ԥ����
		
		
		return user;
	}

	/**
	 * �û�ע��
	 */
	public boolean register(User user) {
		String sql = "insert into tb_user(u_name,u_pass) values(?,?)";
		try {
			//��ȡ����
			conn = DBConnection.getConn();
			//���Ԥ��������
			ps = conn.prepareStatement(sql);
			//Ԥ����
			ps.setString(1, user.getUname());
			ps.setString(2, user.getUpass());
			//ִ��
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(null, ps, conn);
		}
		return false;
	}

	public boolean updatePass(int uid, String upass) {
		String sql = "update tb_user set u_pass=? where u_id=?";
		try {
			//��ȡ����
			conn = DBConnection.getConn();
			//���Ԥ��������
			ps = conn.prepareStatement(sql);
			//Ԥ����
			ps.setString(1, upass);
			ps.setInt(2, uid);
			//ִ��
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(rs, ps, conn);
		}
		return false;
	}

	/**
	 * ��ѯ�������û�
	 */
	public List<User> findAllUser(int pageSize,int currentPage) {
		String sql = "select * from tb_user limit ?,?";
		List<User> list = new ArrayList<User>();
		
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (currentPage-1)*pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while(rs.next()){
				User u = new User();
				u.setUid(rs.getInt("u_id"));
				u.setUname(rs.getString("u_name"));
				u.setUflag(rs.getInt("u_flag"));
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	public int getTotalPage(int pageSize) {
		String sql = "select count(*) from tb_user";
		int totalNum = 0;
		int totalPage = 0;
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				totalNum = rs.getInt(1);
			}
			if(totalNum%pageSize == 0){
				totalPage = totalNum/pageSize;
			}else{
				totalPage = totalNum/pageSize+1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalPage;
	}
}
