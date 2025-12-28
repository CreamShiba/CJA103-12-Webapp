package com.carshop.adm.model;

import java.sql.Date;

public class AdmVO implements java.io.Serializable {
	private Integer admno;
	private String admAccount;
	private String admPassword;
	private String admName;
	private String admEmail;
	private Byte admStatus;
	private Date hiredate;
	private Byte[] admImage;

	public AdmVO() {
		super();
	}

	public Integer getAdmno() {
		return admno;
	}

	public void setAdmno(Integer admno) {
		this.admno = admno;
	}

	public String getAdmAccount() {
		return admAccount;
	}

	public void setAdmAccount(String admAccount) {
		this.admAccount = admAccount;
	}

	public String getAdmPassword() {
		return admPassword;
	}

	public void setAdmPassword(String admPassword) {
		this.admPassword = admPassword;
	}

	public String getAdmName() {
		return admName;
	}

	public void setAdmName(String admName) {
		this.admName = admName;
	}

	public String getAdmEmail() {
		return admEmail;
	}

	public void setAdmEmail(String admEmail) {
		this.admEmail = admEmail;
	}

	public Byte getAdmStatus() {
		return admStatus;
	}

	public void setAdmStatus(Byte admStatus) {
		this.admStatus = admStatus;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public Byte[] getAdmImage() {
		return admImage;
	}

	public void setAdmImage(Byte[] admImage) {
		this.admImage = admImage;
	}

}
