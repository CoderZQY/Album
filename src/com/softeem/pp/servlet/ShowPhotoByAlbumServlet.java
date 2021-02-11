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

public class ShowPhotoByAlbumServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			//��ȡ��ǰ�����
			String id = request.getParameter("aid");
			int aid = Integer.parseInt(id);
			//��ȡ��ǰҳ
			String cp = request.getParameter("currentPage");
			int currentPage = Integer.parseInt(cp);
			//�жϵ�ǰҳ�Ƿ�С��1
			if(currentPage<1){
				currentPage=1;
			}
			//�����Ƭ�����ݷ��ʶ���
			PhotoDAO pdao = new PhotoDAOImpl();
			int totalPage = pdao.getTotalPage(aid, 8);
			//�жϵ�ǰҳ�Ƿ������ҳ��
			if(currentPage>totalPage){
				currentPage = totalPage;
			}
			//���ò�ѯ��Ƭ�ķ���
			List<Photo> list = pdao.findByAid(aid, 8, currentPage);
			
			request.setAttribute("aid",aid);
			if(list.size()<1){
				request.getRequestDispatcher("noresults.jsp").forward(request, response);
			}else{
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("currentPage", currentPage);
				//����ѯ����ͼƬ�ļ������õ��¸�ҳ����
				request.setAttribute("list", list);
				//ҳ����ת
				request.getRequestDispatcher("showPhotoByAlbum.jsp").forward(request, response);
			}
	}

}
