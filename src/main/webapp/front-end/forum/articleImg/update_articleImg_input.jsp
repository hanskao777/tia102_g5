<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tia102g5.articleImg.model.*"%>

<%
//見com.article.controller.ArticleServlet.java第163行存入req的article物件 (此為從資料庫取出的article, 也可以是輸入格式有錯誤時的article物件)
ArticleImg articleImg = (ArticleImg) request.getAttribute("articleImg");
%>
<%-- 
--<%= article==null %>--${article.articleID}-- <!-- line 100 -->
--%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>文章圖片修改 - update_articleImg_input.jsp</title>

<style>
table#table-1 {
	background-color: #FFFDD0;
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
	background-color: #E0FFFF;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #00A4B7;
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
				<h3>文章圖片修改 - update_articleImg_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/regression.gif"
						width="60" height="50" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>圖片修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="articleImg.do" name="form1"	enctype="multipart/form-data">
		<table>
			<tr>
				<td>文章圖片編號:<font color=red><b>*</b></font></td>
				<td><%=articleImg.getArticleImgID()%></td>
			</tr>
			
			<tr>
				<td>文章編號:</td>
				<td><%=articleImg.getArticleID()%></td>
			</tr>

			<tr>
				<td>文章圖片:</td>
				<td><input type="file" name="articlePic" size="45" /></td>
			</tr>
			

			
		</table>
		<br> 
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" name="articleImgID" value="<%=articleImg.getArticleImgID()%>"> 
		<input type="hidden" name="articleID" value="<%=articleImg.getArticleID()%>"> 
		<input type="submit" value="送出修改">
	</FORM>
</body>



</html>