<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tia102g5.article.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
Article article = (Article) request.getAttribute("article"); //ArticleServlet.java(Concroller), 存入req的article物件
%>

<html>
<head>
<title>文章資料 - listOneArticle.jsp</title>

<style>
  table#table-1 {
	background-color: white;
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
    border: 1px solid #00A4B7;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='#E0FFFF'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>文章資料 - listOneArticle.jsp</h3>
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
	</tr>
	<tr>
		<td><%=article.getArticleID()%></td>
		<td><%=article.getArticleTitle()%></td>
		<td><%=article.getMemberID()%></td>
		<td><%=article.getArticleContent()%></td>
		<td><%=article.getBoardID()%></td>
		<td><%=article.getArticleStatus()%></td>
	</tr>
</table>

</body>
</html>