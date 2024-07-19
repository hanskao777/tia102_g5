<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title>Tia102G5 Article: Home</title>

<style>
  table#table-1 {
	width: 800px;
	background-color: #AFEEEE;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px #00808C;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: Mint;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: FF8099;
    display: inline;
  }
</style>

</head>
<body bgcolor='#FDF5E6'>

<table id="table-1">
   <tr><td><h3>Tia102G5 Article: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Tia102G5 Article: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllArticle.jsp'>List</a> all Article.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="/tia102g5/article/article.do" >
        <b>輸入文章編號 (如1):</b>
        <input type="text" name="articleID">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="articleSvc" scope="page" class="com.tia102g5.article.model.ArticleService" />
   
  <li>
     <FORM METHOD="post" ACTION="/tia102g5/article/article.do" >
       <b>選擇文章編號:</b>
       <select size="1" name="articleID">
         <c:forEach var="article" items="${articleSvc.all}" > 
          <option value="${article.articleID}">${article.articleID}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="/tia102g5/article/article.do" >
       <b>選擇文章標題:</b>
       <select size="1" name="articleID">
         <c:forEach var="article" items="${articleSvc.all}" > 
          <option value="${article.articleID}">${article.articleTitle}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>文章管理</h3>

<ul>
  <li><a href='addArticle.jsp'>Add</a> a new Article.</li>
</ul>

</body>
</html>