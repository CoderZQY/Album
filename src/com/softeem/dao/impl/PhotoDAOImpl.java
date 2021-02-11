package com.softeem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softeem.dao.PhotoDAO;
import com.softeem.pp.dbutil.DBConnection;
import com.softeem.pp.dto.Photo;

public class PhotoDAOImpl implements PhotoDAO {

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/**
	 * ͼƬ���
	 */
	public boolean addPhoto(Photo p) {
		String sql = "insert into tb_photo(p_name,p_uploadtime,p_discription,a_id) values(?,?,?,?)";
		try {
			//��ȡ����
			conn = DBConnection.getConn();
			//���Ԥ��������
			ps = conn.prepareStatement(sql);
			//Ԥ����
			ps.setString(1, p.getPname());
			ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			ps.setString(3, p.getPdiscription());
			ps.setInt(4, p.getAid());
			//ִ��
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * ɾ����Ƭ
	 */
	public boolean delPhotoByAid(int aid) {
		String sql = "delete from tb_photo where a_id=?";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, aid);
			int i = ps.executeUpdate();
			if(i>=0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<Photo> findByAid(int aid,int pageSize,int currentPage) {
		String sql = "select * from tb_photo where a_id=? limit ?,?";
		List<Photo> list = new ArrayList<Photo>();
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, aid);
			ps.setInt(2, (currentPage-1)*pageSize);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			while(rs.next()){
				Photo p = new Photo();
				p.setAid(aid);
				p.setPdiscription(rs.getString("p_discription"));
				p.setPid(rs.getInt("p_id"));
				p.setPname(rs.getString("p_name"));
				p.setPuploadTime(rs.getDate("p_uploadtime"));
				//����������ÿһ�������ŵ�������
				list.add(p);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * ������ƬIDɾ����Ƭ
	 */
	public boolean delPhotoByPid(int pid) {
		
		String sql = "delete from tb_photo where p_id=?";
		conn = DBConnection.getConn();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * ���ָ�������Ƭ����ҳ��
	 */
	public int getTotalPage(int aid, int pageSize) {
		
		int totalPage = 0;
		int totalNum = getTotalNum(aid);
	
		if(totalNum % pageSize == 0 ){
			totalPage = totalNum/pageSize;
		}else{
			totalPage = totalNum/pageSize+1;
		}
		return totalPage;
	}

	/**
	 * ��ѯ������Ƭ
	 */
	public Photo findByPid(int pid) {
		String sql = "select * from tb_photo where p_id=?";
		Photo p = null;
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pid);
			rs = ps.executeQuery();
			if(rs.next()){
				p = new Photo();
				p.setAid(rs.getInt("a_id"));
				p.setPdiscription(rs.getString("p_discription"));
				p.setPname(rs.getString("p_name"));
				p.setPuploadTime(rs.getDate("p_uploadtime"));
				p.setPid(pid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(rs, ps, conn);
		}
		
		return p;
	}

	/**
	 * �����Ƭ����
	 */
	public int getTotalNum(int aid) {
		String sql = "select count(*) from tb_photo where a_id=?";
		int totalNum = 0;
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, aid);
			rs = ps.executeQuery();
			if(rs.next()){
				totalNum = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalNum;
	}

	public List<Photo> findAll(int aid) {
		String sql = "select * from tb_photo where a_id=?";
		List<Photo> list = new ArrayList<Photo>();
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, aid);
			rs = ps.executeQuery();
			while(rs.next()){
				Photo p = new Photo();
				p.setAid(aid);
				p.setPdiscription(rs.getString("p_discription"));
				p.setPid(rs.getInt("p_id"));
				p.setPname(rs.getString("p_name"));
				p.setPuploadTime(rs.getDate("p_uploadtime"));
				//����������ÿһ�������ŵ�������
				list.add(p);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * ��Ƭ��ѯ
	 */
	public List<Photo> queryPhoto(String sql, int pageSize, int currentPage) {
		
		List<Photo> photos = new ArrayList<Photo>();
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (currentPage-1)*pageSize);
			ps.setInt(2, pageSize);
			rs = ps.executeQuery();
			while(rs.next()){
				Photo p = new Photo();
				p.setAid(rs.getInt("a_id"));
				p.setPdiscription(rs.getString("p_discription"));
				p.setPid(rs.getInt("p_id"));
				p.setPname(rs.getString("p_name"));
				p.setPuploadTime(rs.getDate("p_uploadtime"));
				//����������ÿһ�������ŵ�������
				photos.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return photos;
	}

	public int getTotalPage(String sql, int pageSize) {
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
