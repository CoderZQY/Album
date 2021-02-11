package com.softeem.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softeem.dao.AlbumDAO;
import com.softeem.dao.PhotoDAO;
import com.softeem.pp.dbutil.DBConnection;
import com.softeem.pp.dto.Album;


public class AlbumDAOImpl implements AlbumDAO{

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	/**
	 * ������
	 */
	public boolean addAlbum(Album a) {
		String sql = "insert into tb_album(a_name,a_createtime,a_discription,u_id) values(?,?,?,?)";
		try {
			//��ȡ����
			conn = DBConnection.getConn();
			//���Ԥ��������
			ps = conn.prepareStatement(sql);
			//Ԥ����
			ps.setString(1,a.getAname());
			ps.setDate(2,new Date(System.currentTimeMillis()));
			ps.setString(3, a.getAdiscription());
			ps.setInt(4, a.getUid());
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

	public Album getNowAlbum() {
		String sql = "select * from tb_album where a_id=(select max(a_id) from tb_album)";
		Album a = null;
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				//��ѯ����Ӧ�ļ�¼,�򴴽�������
				a = new Album();
				a.setAid(rs.getInt(1));
				a.setAcreateTime(rs.getDate("a_createtime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return a;
	}

	public List<Album> findAlbumByUid(int uid) {
		String sql = "select * from tb_album where u_id=?";
		//����һ���������ڴ�Ų�ѯ�����������
		List<Album> list = new ArrayList<Album>();
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			while(rs.next()){
				//���������һ����¼,˵����ѯ��һ����¼
				Album a = new Album();
				//���������Ķ�������Ը�ֵ���½���������
				a.setUid(rs.getInt("u_id"));
				a.setAcreateTime(rs.getDate("a_createtime"));
				a.setAname(rs.getString("a_name"));
				a.setAdiscription(rs.getString("a_discription"));
				a.setAid(rs.getInt("a_id"));
				//����ǰ��¼��ӵ�������
				list.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * ��ҳ��ѯ
	 */
	public List<Album> findAlbumByUid(int uid, int pageSize, int currentPage) {
		String sql = "select * from tb_album where u_id=? limit ?,?";
		List<Album> list = new ArrayList<Album>();
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ps.setInt(2, (currentPage-1)*pageSize);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			while(rs.next()){
				Album a = new Album();
				a.setAid(rs.getInt("a_id"));
				a.setAname(rs.getString("a_name"));
				a.setAdiscription(rs.getString("a_discription"));
				a.setAcreateTime(rs.getDate("a_createtime"));
				a.setUid(uid);
				//����ǰ��ѯ��������ŵ�������
				list.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return list;
	}

	/**
	 * ��ȡ��ǰ�û�������ҳ��
	 */
	public int getTotalPage(int uid,int pageSize) {
		//��ҳ��
		int totalPage = 0;
		//�ܼ�¼����
		int totalNum = 0;
		String sql = "select count(*) from tb_album where u_id=?";
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			rs = ps.executeQuery();
			if(rs.next()){
				totalNum = rs.getInt(1);
			}
			//�ж��ܼ�¼���ܷ����ÿҳ��С
			if(totalNum % pageSize == 0){
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

	/**
	 * ɾ�����
	 * ����:ԭ����,һ����,������,��ȫ��
	 */
	public boolean delAlbum(int aid) {
		//��ɾ����ǰ����е���Ƭ
		PhotoDAO pdao = new PhotoDAOImpl();
		boolean f = pdao.delPhotoByAid(aid);
		//�����Ƭɾ���ɹ�,�ż���ִ��ɾ�����
		if(f){
			String sql = "delete from tb_album where a_id=?";
			try {
				conn = DBConnection.getConn();
				ps = conn.prepareStatement(sql);
				ps.setInt(1, aid);
				int i = ps.executeUpdate();
				if(i>0){
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public Album getAlbum(int aid) {
		String sql = "select * from tb_album where a_id=?";
		Album a = null;
		try {
			conn = DBConnection.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, aid);
			rs = ps.executeQuery();
			if(rs.next()){
				a = new Album();
				a.setAname(rs.getString("a_name"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return a;
	}

}
