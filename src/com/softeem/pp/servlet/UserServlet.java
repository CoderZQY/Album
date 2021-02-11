package com.softeem.pp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.dao.UserDAO;
import com.softeem.dao.impl.UserDAOImpl;
import com.softeem.pp.dto.User;

public class UserServlet extends HttpServlet{

	// alt+/
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			//req.setCharacterEncoding("utf-8");
			UserDAO udao = new UserDAOImpl();
			//��ȡ������
			String op = req.getParameter("operation");
			if("register".equals(op)){
				//��ȡǰ̨���е���Ϣ
				String name = req.getParameter("username");
				String pass = req.getParameter("password");
				User u = new User();
				u.setUname(name);
				u.setUpass(pass);
				boolean b = udao.register(u);
				if(b){
					//ע��ɹ�
					req.setAttribute("msg","<font color='green'>ע��ɹ�,�������û��������½</font>");
					//��ת
				}else{
					//ע��ʧ��
					req.setAttribute("msg", "ע��ʧ��,����!");
					//��ת
				}
				req.getRequestDispatcher("loginAndRegister.jsp").forward(req, resp);
			}else{
				//��½����
				//��ȡǰ̨���е���Ϣ
				String name = req.getParameter("username");
				String pass = req.getParameter("password");
				User user = udao.login(name);
				if(user != null){
					//�ж�ǰ̨������������ͺ�̨���ݿ��е������Ƿ�һ��
					if(user.getUpass().equals(pass)){
						//��½�ɹ�
						//����ǰ�û����浽session��
						req.getSession().setAttribute("user", user);
						req.getRequestDispatcher("main.jsp").forward(req, resp);
					}else{
						//�������
						req.setAttribute("msg", "�������!");
						req.getRequestDispatcher("loginAndRegister.jsp").forward(req, resp);
					}
				}else{
					//�û�������
					req.setAttribute("msg", "�û�������");
					req.getRequestDispatcher("loginAndRegister.jsp").forward(req, resp);
				}
			}
	}
}
