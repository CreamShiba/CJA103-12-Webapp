<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.carshop.adm.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  AdmVO admVO = (AdmVO) request.getAttribute("AdmVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>管理員資料 - listOneAdm.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>管理員資料 - listOneAdm.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/adm/select_page.jsp"><img src="<%=request.getContextPath()%>/img/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>管理員頭像</th>
		<th>管理員編號</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>管理員姓名</th>
		<th>信箱</th>
		<th>入職日期</th>
		<th>狀態</th>
	</tr>
	<tr>
			<td>
			<img src="${pageContext.request.contextPath}/adm/adm.do?action=getPhoto&admno=${admVO.admno}" 
			 width="120" height="120"
			 class="emp-img"
			 onerror="this.style.display='none'"> 
		 	</td>
			<td>${admVO.admno}</td>
			<td>${admVO.admAccount}</td>
			<td>${admVO.admPassword}</td>
			<td>${admVO.admName}</td>
			<td>${admVO.admEmail}</td>
			<td>${admVO.hiredate}</td>
			<td>${admVO.admStatus == 1 ? "正常" : "離職"}</td> 
	</tr>
</table>
<a href='<%=request.getContextPath()%>/adm/listAllAdms.jsp'><input type="button" value="返回清單" ></a>
</body>
</html>