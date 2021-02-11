package com.softeem.pp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.dao.PhotoDAO;
import com.softeem.dao.impl.PhotoDAOImpl;
import com.softeem.pp.dto.Photo;

public class QueryPhotoServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			//��ȡ��ǰҳ
			String cp = request.getParameter("currentPage");
			int currentPage = Integer.parseInt(cp);
			//���ղ�ѯ����
			String pname = request.getParameter("pname");
			String id = request.getParameter("aid");
			System.out.println("����ѯ�����ID��"+id);
			request.setAttribute("pname", pname);
			request.setAttribute("aid", id);
			
			int aid = Integer.parseInt(id);
			//ƴ��sql���
			String sql = "select * from tb_photo where 1=1";
			String sql2 = "select count(*) from tb_photo where 1=1";
			//�ж��û��Ƿ���д��Ƭ��
			if(pname != null && !"".equals(pname)){
				sql = sql+" and p_name like '%"+pname+"%'";
				sql2 = sql2+" and p_name like '%"+pname+"%'";
			}
			if(aid != 0){
				sql = sql+" and a_id="+aid;
				sql2 = sql2+" and a_id="+aid;
			}
			
			sql = sql+" limit ?,?";
			
			PhotoDAO pdao = new PhotoDAOImpl();
			//��ȡ���Ͻ������ҳ��
			int totalPage = pdao.getTotalPage(sql2, 8);
			
			if(currentPage<1){
				currentPage = 1;
			}
			if(currentPage>totalPage){
				currentPage = totalPage;
			}
			
			//��ȡ��ѯ���
			List<Photo> list = pdao.queryPhoto(sql, 8, currentPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
			request.getRequestDispatcher("QueryPhotoResult.jsp").forward(request, response);
	}

}
