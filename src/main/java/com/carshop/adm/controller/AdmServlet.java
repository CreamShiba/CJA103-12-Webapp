package com.carshop.adm.controller;

import java.io.*;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.carshop.adm.model.*;


public class AdmServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("admno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer admno = null;
				try {
					admno = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AdmService admSvc = new AdmService();
				AdmVO admVO = admSvc.getOneAdm(admno);
				if (admVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("admVO", admVO); // 資料庫取出的empVO物件,存入req
				String url = "listOneAdm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer admno = Integer.valueOf(req.getParameter("admno"));
				
				/***************************2.開始查詢資料****************************************/
				AdmService admSvc = new AdmService();
				AdmVO admVO = admSvc.getOneAdm(admno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("admVO", admVO);         // 資料庫取出的admVO物件,存入req
				String url = "/adm/update_adm_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
Integer admno = Integer.valueOf(req.getParameter("admno").trim());
				
String admName = req.getParameter("admName");
				String admNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (admName == null || admName.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!admName.trim().matches(admNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
String admEmail = req.getParameter("admEmail").trim();
				String admEmailReg = ".*[\u4e00-\u9fa5].*";
				if (admEmail.trim().matches(admEmailReg)) {
					errorMsgs.add("信箱不能包含中文");
				} else if(admEmail == null || admEmail.trim().length() == 0) {
					errorMsgs.add("信箱請勿空白");
				}
				
				java.sql.Date hiredate = null;
				try {
hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
				} catch (IllegalArgumentException e) {
					hiredate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

Byte admStatus =  Byte.valueOf(req.getParameter("admStatus").trim());


				String admAccount = null;
admAccount = String.valueOf(req.getParameter("admAccount").trim());
				String admAccountReg = ".*[\u4e00-\u9fa5].*";
				if (admAccount.trim().matches(admAccountReg)) {
					errorMsgs.add("帳號不能包含中文");
				} else if(admAccount == null || admAccount.trim().length() <= 7) {
					errorMsgs.add("帳號請勿空白或長度小於8");
				}

				String admPassword = null;
admPassword = String.valueOf(req.getParameter("admPassword").trim());
				String admPasswordReg = ".*[\u4e00-\u9fa5].*";
				if (admPassword.trim().matches(admPasswordReg)) {
					errorMsgs.add("密碼不能包含中文");
				} else if(admPassword == null || admPassword.trim().length() <= 7 ) {
					errorMsgs.add("密碼請勿空白或長度小於8");
				}

				AdmVO admVO = new AdmVO();
				admVO.setAdmno(admno);
				admVO.setAdmName(admName);
				admVO.setAdmEmail(admEmail);
				admVO.setHiredate(hiredate);
				admVO.setAdmStatus(admStatus);
				admVO.setAdmAccount(admAccount);
				admVO.setAdmPassword(admPassword);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("admVO", admVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/adm/update_adm_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				AdmService admSvc = new AdmService();
				admVO = admSvc.updateAdm(admno, admAccount, admPassword, admName, admEmail, hiredate, admStatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("admVO", admVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/adm/listOneAdm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
String admName = req.getParameter("admName");
				String admNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (admName == null || admName.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!admName.trim().matches(admNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
String admEmail = req.getParameter("admEmail").trim();
				String admEmailReg = ".*[\u4e00-\u9fa5].*";
				if (admEmail.trim().matches(admEmailReg)) {
					errorMsgs.add("信箱不能包含中文");
				} else if(admEmail == null || admEmail.trim().length() == 0) {
					errorMsgs.add("信箱請勿空白");
				}
				
				java.sql.Date hiredate = null;
				try {
					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
				} catch (IllegalArgumentException e) {
					hiredate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				

				String admAccount = null;
admAccount = String.valueOf(req.getParameter("admAccount").trim());
				String admAccountReg = ".*[\u4e00-\u9fa5].*";
				if (admAccount.trim().matches(admAccountReg)) {
					errorMsgs.add("帳號不能包含中文");
				} else if(admAccount == null || admAccount.trim().length() <= 7) {
					errorMsgs.add("帳號請勿空白或長度小於8");
				}
				
				String admPassword = null;
admPassword = String.valueOf(req.getParameter("admPassword").trim());
				String admPasswordReg = ".*[\u4e00-\u9fa5].*";
				if (admPassword.trim().matches(admPasswordReg)) {
					errorMsgs.add("密碼不能包含中文");
				} else if(admPassword == null || admPassword.trim().length() <= 7 ) {
					errorMsgs.add("密碼請勿空白或長度小於8");
				}
				
				AdmVO admVO = new AdmVO();
				admVO.setAdmAccount(admAccount);
				admVO.setAdmPassword(admPassword);
				admVO.setAdmName(admName);
				admVO.setAdmEmail(admEmail);
				admVO.setHiredate(hiredate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("admVO", admVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/adm/addAdm.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AdmService admSvc = new AdmService();
				admVO = admSvc.addAdm(admAccount, admPassword, admName, admEmail, hiredate);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/adm/listAllAdms.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer admno = Integer.valueOf(req.getParameter("admno"));
				
				/***************************2.開始刪除資料***************************************/
				AdmService admSvc = new AdmService();
				admSvc.deleteAdm(admno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/adm/listAllAdms.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
