package com.softeem.pp.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.dao.AlbumDAO;
import com.softeem.dao.impl.AlbumDAOImpl;
import com.softeem.pp.dto.Album;
import com.softeem.pp.dto.User;

public class AddAlbumServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			AlbumDAO adao = new AlbumDAOImpl();
			//��ȡǰ̨������������Ϣ
			String aname = request.getParameter("aname");//�����
			String adis = request.getParameter("adiscription");//�������
			//��ȡ���������û�
			User user = (User)request.getSession().getAttribute("user");
			int uid = user.getUid();//�û�id
			//�������
			Album a = new Album();
			//Ϊ�½�����ḳ������ֵ
			a.setAname(aname);
			a.setAdiscription(adis);
			a.setUid(uid);
			//ִ��������
			boolean b = adao.addAlbum(a);
			//��ѯ������������Ϣ
			Album a2 = adao.getNowAlbum();
			a2.setAname(aname);
			a2.setAdiscription(adis);
			a2.setUid(uid);
			if(b){
				//��ᴴ���ɹ�
				request.setAttribute("a", a2);
				request.getRequestDispatcher("createSuccess.jsp").forward(request, response);
			}else{
				//ʧ��
				request.setAttribute("msg", "����ʧ��,����");
				request.getRequestDispatcher("addAlbum.jsp").forward(request, response);
			}
	}

}
