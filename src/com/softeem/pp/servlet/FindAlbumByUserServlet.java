package com.softeem.pp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.dao.AlbumDAO;
import com.softeem.dao.impl.AlbumDAOImpl;
import com.softeem.pp.dto.Album;
import com.softeem.pp.dto.User;

public class FindAlbumByUserServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			//��ȡ��ǰ�û��Ĳ���
			String op = request.getParameter("op");
		
			//��ȡ��ǰ��½�û�
			User user = (User)request.getSession().getAttribute("user");
			//��õ�ǰ��½�û���ID
			int uid = user.getUid();
			AlbumDAO adao = new AlbumDAOImpl();
			List<Album> list = null;
			if("showAlbum".equals(op)){
				
				//����ǰ̨ҳ�洫������ҳ��
				String cp = request.getParameter("currentPage");
				int currentPage = Integer.parseInt(cp);
				//�����ȡ���ĵ�ǰҳ����С��1��Ϊ��ǰҳǿ�и�ֵΪ1
				if(currentPage<1){
					currentPage=1;
				}
				int totalPage = adao.getTotalPage(uid,8);
				//�жϵ�ǰҳ�Ƿ������ҳ��
				if(currentPage>totalPage){
					currentPage = totalPage;
				}
				//��ѯ��ǰ�û������е����(��ҳ/����)
				list = adao.findAlbumByUid(uid, 8, currentPage);
				//����ҳ�����ݵ��¸�ҳ����
				request.setAttribute("totalPage", totalPage);
				//����ǰҳ���ݵ��¸�ҳ����
				request.setAttribute("currentPage", currentPage);
				//����ѯ������������ŵ�request��Χ��
				request.setAttribute("list", list);
				//ҳ����ת
				request.getRequestDispatcher("showAlbum.jsp").forward(request, response);
			
			}else if("query".equals(op)){
				//��ѯ��Ƭʱ��Ҫʹ�õ���������Ἧ��
				list = adao.findAlbumByUid(uid);
				//����ѯ������������ŵ�request��Χ��
				request.setAttribute("list", list);
				//ҳ����ת
				request.getRequestDispatcher("QueryPhoto.jsp").forward(request, response);
			}else{
				//�����Ƭʱ��Ҫ��õ������������
				
				list = adao.findAlbumByUid(uid);
				//����ѯ������������ŵ�request��Χ��
				request.setAttribute("list", list);
				//ҳ����ת
				request.getRequestDispatcher("addPhoto_main.jsp").forward(request, response);
			}
		}
		
}
