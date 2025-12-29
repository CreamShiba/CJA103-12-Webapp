<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.carshop.adm.model.*"%>

<% //見com.carshop.adm.controller.EmpServlet.java第163行存入req的admVO物件 (此為從資料庫取出的admVO, 也可以是輸入格式有錯誤時的admVO物件)
   AdmVO admVO = (AdmVO) request.getAttribute("admVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>管理員資料修改 - update_adm_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>管理員資料修改 - update_adm_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="<%=request.getContextPath()%>/img/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/adm/adm.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>管理員編號:<font color=red><b>*</b></font></td>
		<td><%=admVO.getAdmno()%></td>
	</tr>
	<tr>
		<td>管理員頭像:<font color=red><b>*</b></font></td>
        <td>
       		 <div id="preview_area">
             <img id="blob_holder" 
                  src="<%=request.getContextPath()%>/adm/adm.do?action=getPhoto&admno=<%=admVO.getAdmno()%>" 
                  width="120" height="120" 
                  style="border: 1px solid #ccc; padding: 2px; border-radius: 5px; object-fit: cover;"
                  onerror="this.style.display='none'" />
           </div>
           <br>
           <span style="font-size:12px; color:gray;">若不修改圖片請勿選擇檔案</span><br>
           <input type="file" name="upfile1" id="upfile1" accept="image/png, image/jpeg"onchange="previewImage(this)">
        </td>
	</tr>
	<tr>
		<td>管理員姓名:</td>
		<td><input type="TEXT" name="admName" value="<%=admVO.getAdmName()%>" size="45"/></td>
	</tr>
	<tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="admAccount"   value="<%=admVO.getAdmAccount()%>" size="45"/></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="admPassword"   value="<%=admVO.getAdmPassword()%>" size="45"/></td>
	</tr>
	<tr>
		<td>信箱:</td>
		<td><input type="TEXT" name="admEmail"  value="<%=admVO.getAdmEmail()%>" size="45"/></td>
	</tr>
	<tr>
		<td>雇用日期:</td>
		<td><input name="hiredate" id="f_date1" type="text" ></td> 
	</tr>
	<tr>
		<td>狀態:</td>
		<td>
			<select size="1" name="admStatus">
			    <option value="1" ${(admVO.admStatus == 1)? 'selected' : '' }>正常</option>
			    <option value="0" ${(admVO.admStatus == 0)? 'selected' : '' }>離職</option>
			</select>
    </td> 
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="admno" value="<%=admVO.getAdmno()%>">
<input type="submit" value="送出修改">
<a href='<%=request.getContextPath()%>/adm/listAllAdms.jsp'><input type="button" value="返回清單" ></a>
</FORM>



</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<script src="<%=request.getContextPath()%>/js/test.js"></script>
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=admVO.getHiredate()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>