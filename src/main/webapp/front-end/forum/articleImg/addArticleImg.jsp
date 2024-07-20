<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.tia102g5.articleImg.model.*"%>

<%
//見com.articleImg.controller.ArticleImgServlet.java第238行存入req的articleImg物件 (此為輸入格式有錯誤時的article物件)
ArticleImg articleImg = (ArticleImg) request.getAttribute("articleImg");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>文章圖片新增 - addArticleImg.jsp</title>

<style>
table#table-1 {
	background-color: #FFFFF0;
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
	background-color: ##E0FFFF;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid Peacock Blue;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='#E0FFFF'>

	<table id="table-1">
		<tr>
			<td>
				<h3>文章圖片新增 - addArticleImg.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/dukelupe.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

 <jsp:useBean id="articleImgSvc" scope="page" class="com.tia102g5.articleImg.model.ArticleImgService" />
 
	<FORM METHOD="post" ACTION="/TIA102G5_TEST/articleImg/articleImg.do"
		name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>文章編號:</td>
				<td>
					<%-- <td><input type="TEXT" name="articleID" value="<%= (articleImg==null)? "1" : articleImg.getArticleID()%>" size="45"/></td> --%>
					<select name="articleID">
						<c:forEach var="articleImg" items="${articleImgSvc.all}">
							<option value="${articleImg.articleImgID}">${articleImg.articleID}</option>
						</c:forEach>
				</select>
				</td>

			</tr>
			<tr>
				<td>文章圖片:</td>
				<td><input type="file" name="articlePic" size="45" /></td>

			</tr>

			<%--等所有Table建好再關聯修改
	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" />
	
	<tr>
		<td>部門:<font color=red><b>*</b></font></td>
		<td><select size="1" name="deptno">
			<c:forEach var="deptVO" items="${deptSvc.all}">
				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname}
			</c:forEach>
		</select></td>
	</tr>
	--%>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>

</body>


</html>