package com.softeem.pp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.softeem.dao.PhotoDAO;
import com.softeem.dao.impl.PhotoDAOImpl;
import com.softeem.pp.dto.Photo;

public class PhotoUploadServlet extends HttpServlet {

	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			//��ȡͼƬ�ϴ�����������ָ��Ŀ¼
			String rootPath = this.getInitParameter("rootpath");
			try {
				//����SmartUpload����
				SmartUpload su = new SmartUpload();
				//��ʼ���ϴ�����
				su.initialize(this, request, response);
				//���������ϴ����ļ����ֵ
				su.setMaxFileSize(1024*1024*10);//���10M
				//���������ϴ��ļ�������
				su.setAllowedFilesList("jpg,png,bmp,gif,emf");
				//��ʼ�ϴ�
				su.upload();
				//��ȡ�����ļ�
				Files files = su.getFiles();
				//��ȡ�����ļ�
				File file = files.getFile(0);
				//��ȡ�ļ���
				String filename = file.getFileName();//ͼƬ����
				//�����ļ�(�����������ļ����Ƶ�����������ļ���Ŀ¼��)
				file.saveAs(rootPath+filename);
				
				//��ȡwebԪ��
				Request req = su.getRequest();
				String id = req.getParameter("aid");
				int aid = Integer.parseInt(id);//��ȡ���ID
				String adis = req.getParameter("pdiscription");//��ȡ���������Ϣ
				//�½�ͼƬ����
				Photo p = new Photo();
				p.setAid(aid);
				p.setPname(filename);
				p.setPdiscription(adis);
				
				PhotoDAO pdao = new PhotoDAOImpl();
				boolean f = pdao.addPhoto(p);
				
				if(f){
					//�ϴ��ɹ�
					request.getRequestDispatcher("ShowPhotoByAlbumServlet?aid="+aid+"&currentPage=1").forward(request, response);
				}else{
					//ʧ��
				}
				
				
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
