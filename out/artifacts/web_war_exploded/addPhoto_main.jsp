<%@ page language="java" import="java.util.*,com.softeem.pp.dto.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addPhoto.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <style>
	table{
		position:absolute;
		top:5%;
		left:30%;
		width:500px;
		height:400px;
		border:2px solid purple;
	}  
		.btn{
  		cursor:pointer;
  		height:40px;
  		width:70px;
  		font-size:20px;
  		font-weight: bold;
  		border:0;
  		background-color: green;
  		color:white;
  	}
  </style>
  <script>
  	function checkFile(){
  		//��ȡ������
  		var form1 = document.form1;
  		//����ļ��ؼ��е���Ϣ(·��)
  		var path = form1.file.value;
  		//�����ѡ���
  		var aid = form1.aid.value;
  		//��ô�����Ϣ��ʾ��λ��
  		var e = document.getElementById("error");
  		if(path.length<1){
  			e.innerText="��ѡ��ͼƬ·��";
  			return false;
  		}else{
  			e.innerText="";
  		}
  		if(aid==0){
  			e.innerText="��ѡ�����";
  			return false;
  	  	}else{
  	  		e.innerText="";
  	  	}
  		form1.action="PhotoUploadServlet";
  	}
  </script>
  <body bgcolor="#f5f5f5">
    <form name="form1" onsubmit="return checkFile()" method="post" enctype="multipart/form-data">
    	<table border="1">
    		<tr bgcolor="#1E90FF">
    			<th colspan="2" style="font-size:25;font-style: oblique">�ϴ���Ƭ</th>
    		</tr>
    		<tr>
    			<th colspan="2">
    				<img id="p" width="150" height="150"/>
    			</th>
    		</tr>
    		<tr>
    			<th colspan="2" id="error" style="color:red"></th>
    		</tr>
    		<tr>
    			<th colspan="2">
    				<input type="file" name="file" id="s" style="width:300px" onchange="showImg()"/>
    			</th>
    		</tr>
    		<tr>
	    		<td>ѡ�����</td>
    			<td><select name="aid">
    					<option value="0">==��ѡ��==</option>
    					<c:forEach items="${list}" var="a">
    					<option value="${a.aid}">${a.aname}</option>
    					</c:forEach>
    				</select>
    			</td>
    		</tr>
    		<tr>
    			<td>��Ƭ����</td>
    			<td>
    				<textarea rows="5" name="pdiscription" cols="30"></textarea>
    			</td>
    		</tr>
    		<tr>
    			<th colspan="2">
    				<input type="submit" value="�ϴ�" class="btn"/>
    				<input type="button" value="ȡ��" class="btn"/>
    			</th>
    		</tr>
    	</table>
    </form>
  </body>
  <script>
  	function showImg(){
		//ͼƬԤ��
		var files=document.getElementById("s");
		var picture=document.getElementById("p");
		//����ͼƬ���ض���
		var reader=new FileReader();
		//��ȡͼƬ
		reader.readAsDataURL(files.files[0]);
		//��ͼƬ�������֮���Զ�����
		reader.onload=function(e){
			//�õ�ͼƬ��·�� result
			var result=this.result;
			console.log(result);
			//������ŵ�img��ǩ��
			picture.src=result;
		}
  	}
  </script>
</html>
