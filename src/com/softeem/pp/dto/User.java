package com.softeem.pp.dto;

//����Ҫ��:30%ע��;70%����
public class User {
	
	private int uid;//�û�ID
	private String uname;//�û���
	private String upass;//����
	private int uflag;//��ʶλ(��ʶ��ǰ�û��ǹ���Ա������ͨ�û�)

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public int getUflag() {
		return uflag;
	}
	public void setUflag(int uflag) {
		this.uflag = uflag;
	}
	
}
