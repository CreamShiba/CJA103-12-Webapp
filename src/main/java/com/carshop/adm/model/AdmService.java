package com.carshop.adm.model;

import java.util.List;

public class AdmService {

	private AdmDAO_interface dao;

	public AdmService() {
		dao = new AdmJDBCDAO();
	}

	public AdmVO addAdm(String admAccount, String admPassword,String admName, String admEmail,
			 java.sql.Date hiredate) {

		AdmVO admVO = new AdmVO();

		admVO.setAdmAccount(admAccount);
		admVO.setAdmPassword(admPassword);
		admVO.setAdmName(admName);
		admVO.setAdmEmail(admEmail);
		admVO.setHiredate(hiredate);
		dao.insert(admVO);

		return admVO;
	}

	public AdmVO updateAdm(Integer admno, String admAccount, String admPassword, String admName,
			String admEmail, java.sql.Date hiredate,Byte admStatus) {

		AdmVO admVO = new AdmVO();
		admVO.setAdmno(admno);
		admVO.setAdmAccount(admAccount);
		admVO.setAdmPassword(admPassword);
		admVO.setAdmName(admName);
		admVO.setAdmEmail(admEmail);
		admVO.setHiredate(hiredate);
		admVO.setAdmStatus(admStatus);
		dao.update(admVO);

		return admVO;
	}

	public void deleteAdm(Integer admno) {
		dao.delete(admno);
	}

	public AdmVO getOneAdm(Integer admno) {
		return dao.findByPK(admno);
	}

	public List<AdmVO> getAll() {
		return dao.getAll();
	}
}
