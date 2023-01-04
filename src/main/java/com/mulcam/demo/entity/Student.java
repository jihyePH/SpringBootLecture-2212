package com.mulcam.demo.entity;

import java.sql.Date;

public class Student {
	private int sid;
	private String sname;
	private String gender;
	private Date enterYear;
	private String deptName;
	
	public Student() {}
	public Student(int sid, String sname, String gender, Date enterYear, String deptName) {
		this.sid = sid;
		this.sname = sname;
		this.gender = gender;
		this.enterYear = enterYear;
		this.deptName = deptName;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getEnterYear() {
		return enterYear;
	}
	public void setEnterYear(Date enterYear) {
		this.enterYear = enterYear;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", gender=" + gender + ", enterYear=" + enterYear
				+ ", deptName=" + deptName + "]";
	}
}
