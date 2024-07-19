<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tia102g5.article.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ArticleService articleSvc = new ArticleService();
    List<Article> list = articleSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有文章資料 - listAllArticle.jsp</title>

<style>3v0u4
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
	width: 800px;
	background-color: #FFFFF0;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #00A4B7;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='#E0FFFF'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有文章資料 - listAllArticle.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/regression.gif" width="60" height="50" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>文章編號</th>
		<th>文章標題</th>
		<th>會員編號</th>
		<th>文章內容</th>
		<th>各版編號</th>
		<th>文章狀態</th>
		<%--  <th>文章新增時間</th>  --%>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="article" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${article.articleID}</td>
			<td>${article.articleTitle}</td>
			<td>${article.memberID}</td>
			<td>${article.articleContent}</td>
			<td>${article.boardID}</td>
			<td>${article.articleStatus}</td> 
			<%-- <td>${article.articleCreateTime}</td>   需要列出嗎? --%>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="articleID"  value="${article.articleID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="articleID"  value="${article.articleID}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>