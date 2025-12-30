<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>CarShop Adm: Home</title>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@400;500;700&display=swap" rel="stylesheet">

<style>
    /* 全域設定 */
    body {
        font-family: 'Noto Sans TC', sans-serif, "Microsoft JhengHei";
        background-color: #f4f7f6; /* 淺灰背景 */
        margin: 0;
        padding: 0;
        color: #333;
        display: flex;
        justify-content: center;
        min-height: 100vh;
    }

    /* 內容容器 */
    .container {
        width: 100%;
        max-width: 800px;
        background-color: #fff;
        margin: 40px 20px;
        padding: 40px;
        box-shadow: 0 4px 15px rgba(0,0,0,0.1); /* 陰影效果 */
        border-radius: 10px;
    }

    /* 標題區塊 */
    .header {
        text-align: center;
        border-bottom: 2px solid #eee;
        padding-bottom: 20px;
        margin-bottom: 30px;
    }
    
    .header h3 {
        color: #2c3e50;
        font-size: 28px;
        margin: 0;
    }

    .header span {
        color: #7f8c8d;
        font-size: 14px;
        letter-spacing: 1px;
    }

    /* 區塊標題 */
    h3.section-title {
        color: #34495e;
        border-left: 5px solid #3498db; /* 左側藍條 */
        padding-left: 10px;
        margin-top: 30px;
        margin-bottom: 15px;
        font-size: 18px;
    }

    /* 錯誤訊息 */
    .error-box {
        background-color: #fce4e4;
        border: 1px solid #fcc2c3;
        color: #cc0033;
        padding: 15px;
        border-radius: 5px;
        margin-bottom: 20px;
    }
    .error-box ul {
        margin: 0;
        padding-left: 20px;
    }

    /* 列表與表單樣式 */
    ul.action-list {
        list-style: none;
        padding: 0;
    }

    ul.action-list li {
        background: #f9f9f9;
        border: 1px solid #eee;
        margin-bottom: 10px;
        padding: 15px;
        border-radius: 6px;
        display: flex;
        align-items: center;
        flex-wrap: wrap;
        transition: 0.2s;
    }

    ul.action-list li:hover {
        background: #f0f4f8; /* 滑鼠經過變色 */
        border-color: #dbeaf9;
    }

    /* 表單元件 */
    form {
        display: flex;
        align-items: center;
        width: 100%;
        margin: 0;
    }

    b {
        margin-right: 10px;
        color: #555;
        font-weight: 500;
        min-width: 140px; /* 讓標籤對齊 */
    }

    input[type="text"], select {
        padding: 8px 12px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 14px;
        flex: 1; /* 填滿剩餘空間 */
        margin-right: 10px;
    }

    /* 按鈕樣式 */
    input[type="submit"], .btn-link {
        background-color: #3498db;
        color: white;
        border: none;
        padding: 8px 16px;
        border-radius: 4px;
        cursor: pointer;
        font-size: 14px;
        transition: background 0.3s;
        text-decoration: none;
    }

    input[type="submit"]:hover, .btn-link:hover {
        background-color: #2980b9;
    }

    /* 連結按鈕化 */
    a.action-link {
        color: #3498db;
        font-weight: bold;
        text-decoration: none;
    }
    a.action-link:hover {
        text-decoration: underline;
    }

    /* 針對只有連結的 li */
    .link-item {
        justify-content: space-between;
    }
</style>

</head>
<body>

<div class="container">

    <div class="header">
        <h3>CarShop Admin System</h3>
        <span>( MVC Architecture )</span>
    </div>

    <p style="text-align: center; color: #666;">管理員後台首頁</p>

    <c:if test="${not empty errorMsgs}">
        <div class="error-box">
            <font style="font-weight:bold;">請修正以下錯誤:</font>
            <ul>
                <c:forEach var="message" items="${errorMsgs}">
                    <li>${message}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <h3 class="section-title">資料查詢</h3>
    
    <ul class="action-list">
        <li class="link-item">
            <b>查詢所有管理員</b>
            <a href='<%=request.getContextPath()%>/adm/listAllAdms.jsp' class="btn-link">查看清單</a>
        </li>

        <li>
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adm/adm.do">
                <b>輸入管理員編號:</b>
                <input type="text" name="admno" placeholder="例如: 10">
                <input type="hidden" name="action" value="getOne_For_Display">
                <input type="submit" value="查詢">
            </FORM>
        </li>

        <jsp:useBean id="admSvc" scope="page" class="com.carshop.adm.model.AdmService" />
        
        <li>
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adm/adm.do">
                <b>選擇管理員編號:</b>
                <select size="1" name="admno">
                    <c:forEach var="admVO" items="${admSvc.all}"> 
                        <option value="${admVO.admno}">${admVO.admno}
                    </c:forEach>   
                </select>
                <input type="hidden" name="action" value="getOne_For_Display">
                <input type="submit" value="查詢">
            </FORM>
        </li>
        
        <li>
            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adm/adm.do">
                <b>選擇管理員姓名:</b>
                <select size="1" name="admno">
                    <c:forEach var="admVO" items="${admSvc.all}"> 
                        <option value="${admVO.admno}">${admVO.admName}
                    </c:forEach>   
                </select>
                <input type="hidden" name="action" value="getOne_For_Display">
                <input type="submit" value="查詢">
            </FORM>
        </li>
    </ul>

    <h3 class="section-title">管理員維護</h3>
    <ul class="action-list">
        <li class="link-item">
            <b>新增管理員</b>
            <a href='<%=request.getContextPath()%>/adm/addAdm.jsp' class="btn-link" style="background-color: #27ae60;">+ 新增資料</a>
        </li>
    </ul>

</div>

</body>
</html>