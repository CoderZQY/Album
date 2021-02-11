package com.softeem.pp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.dao.PhotoDAO;
import com.softeem.dao.impl.PhotoDAOImpl;

public class DelPhotoServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			response.setHeader("contentType", "text/html;charset=utf-8");
			String id1 = request.getParameter("aid");
			int aid = Integer.parseInt(id1);
			//��ȡҳ��˴���������ƬID
			String id = request.getParameter("pid");
			//��String���͵�IDת��Ϊint�͵�ID
			int pid = Integer.parseInt(id);
			//��ȡ��Ƭ�����ݷ��ʶ���
			PhotoDAO pdao = new PhotoDAOImpl();
			//ִ��ɾ��
			boolean b = pdao.delPhotoByPid(pid);
			PrintWriter pw = new PrintWriter(response.getOutputStream());
			if(b){
				pw.println("<script>alert('Delete Success!');window.location.href='ShowPhotoByAlbumServlet?currentPage=1&aid="+aid+"'</script>");
				pw.flush();
			}else{
				pw.println("<script>alert('ɾ��ʧ��!');history.back()'</script>");
				pw.flush();
				
			}
	}

}
