package com.softeem.pp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.dao.UserDAO;
import com.softeem.dao.impl.UserDAOImpl;
import com.softeem.pp.dto.User;

public class UpdatePassWordServlet extends HttpServlet {

	//�ݹ�:��ӵݹ�;ֱ�ӵݹ�
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			//��ȡǰ̨ҳ�洫����̨��ԭʼ����
			String oldpass = request.getParameter("oldpass");
			//��õ�ǰ��½�û�����ʵ����
			User user = (User)request.getSession().getAttribute("user");
			if(oldpass.equals(user.getUpass())){
				//����ƥ����
				//��ȡǰ̨ҳ�洫������������
				String newpass = request.getParameter("newpass");
				UserDAO udao = new UserDAOImpl();
				boolean b = udao.updatePass(user.getUid(), newpass);
				if(b){
					//�����޸ĳɹ�
					//��session�е��û��������,ͬʱ���½��û���Ϣ���õ�session��
					user.setUpass(newpass);
					request.getSession().setAttribute("user", user);
					request.setAttribute("success", "�����޸ĳɹ�");
					request.getRequestDispatcher("updatePassword.jsp").forward(request, response);
				}else{
					//ԭʼ�������
					request.setAttribute("error", "�����޸�ʧ��");
					request.getRequestDispatcher("updatePassword.jsp").forward(request, response);
				}
			}else{
				//ԭʼ�������
				request.setAttribute("error", "ԭʼ���벻��ȷ");
				request.getRequestDispatcher("updatePassword.jsp").forward(request, response);
			}
	}

}
