<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tia102g5.articleImg.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
ArticleImgService articleImgSvc = new ArticleImgService();
List<ArticleImg> list = articleImgSvc.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有文章圖片資料 - listAllArticleImg.jsp</title>

<style>

body {
    background-color: #E0FFFF;
    display: flex;
    justify-content: center;
}

.container {
    width: 1000px;
}
  

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

table {
    width: 100%;
    background-color: #FFFFF0;
    margin: 5px auto;
}

table, th, td {
	border: 1px solid #00A4B7;
}

th, td {
	padding: 5px;
	text-align: center;
}


img.article-img {
	max-width: 100px;
	max-height: 100px;
	width: auto;
	height: auto;
	object-fit: contain;
}
</style>

</head>
<body bgcolor='#E0FFFF'>

	<div class="container">

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有文章資料 - listAllArticleImg.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/regression.gif"
						width="60" height="50" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>文章圖片編號</th>
			<th>文章編號</th>
			<th>文章圖片</th>
			<th>圖片更新時間</th>

			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="articleImg" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${articleImg.articleImgID}</td>
				<td>${articleImg.articleID}</td>
				<td><a
					href="<%=request.getContextPath()%>/articleImg/ImgDBGifReader?articleImgID=${articleImg.articleImgID}"
					target="_blank"> <img
						src="<%=request.getContextPath()%>/articleImg/ImgDBGifReader?articleImgID=${articleImg.articleImgID}"
						alt="文章圖片" width="article-img" height="70">
				</a></td>
				<td><fmt:formatDate value="${articleImg.articleImgCreateTime}"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/articleImg/articleImg.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="articleImgID" value="${articleImg.articleImgID}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/articleImg/articleImg.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="articleImgID" value="${articleImg.articleImgID}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
 	</div>
</body>
</html>