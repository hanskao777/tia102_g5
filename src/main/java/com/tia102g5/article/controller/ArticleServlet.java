package com.tia102g5.article.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g5.article.model.Article;
import com.tia102g5.article.model.ArticleService;

public class ArticleServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		
/************************************* 用文章編號查詢 ******************************************/		
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("articleID");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入文章編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/article/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer articleID = null;
			try {
				articleID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("文章編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/article/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ArticleService artSvc = new ArticleService();
			Article article = artSvc.getOneArticle(articleID);
			if (article == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/article/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("article", article); // 資料庫取出的article物件,存入req
			String url = "/article/listOneArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneArticle.jsp
			successView.forward(req, res);
		}

		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllArticle.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數� ****************************************/
			Integer articleID = Integer.valueOf(req.getParameter("articleID"));

			/*************************** 2.開始查詢資料 ****************************************/
			ArticleService articleSvc = new ArticleService();
			Article article = articleSvc.getOneArticle(articleID);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("article", article); // 資料庫取出的article物件,存入req
			String url = "/article/update_article_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_article_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_article_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer articleID = Integer.valueOf(req.getParameter("articleID").trim());

			String articleTitle = req.getParameter("articleTitle");
			String articleTitleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
			if (articleTitle == null || articleTitle.trim().length() == 0) {
				errorMsgs.add("文章標題: 請勿空白");
			} else if (!articleTitle.trim().matches(articleTitleReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("文章標題: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
			}

			Integer memberID = null;
			try {
				memberID = Integer.valueOf(req.getParameter("memberID").trim());
			} catch (NumberFormatException e) {
				memberID = 0;
				errorMsgs.add("會員編號請填數字.");
			}

			String articleContent = req.getParameter("articleContent");
			String articleContentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,500}$";
			if (articleContent == null || articleContent.trim().length() == 0) {
				errorMsgs.add("文章內容: 請勿空白");
			} else if (!articleContent.trim().matches(articleContentReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("文章內容: 只能是中、英文字母、數字和_ , 且長度必需在2到500之間");
			}

			Integer boardID = null;
			try {
				boardID = Integer.valueOf(req.getParameter("boardID").trim());
			} catch (NumberFormatException e) {
				boardID = 0;
				errorMsgs.add("各版編號請填數字.");
			}

			Integer articleStatus = null;
			try {
				articleStatus = Integer.valueOf(req.getParameter("articleStatus").trim());
			} catch (NumberFormatException e) {
				articleStatus = 0;
				errorMsgs.add("文章狀態請填數字,0為不顯示,1為顯示");
			}

//			Date articleCreateTime = null;
//			try {
//				articleCreateTime = java.sql.Date.valueOf(req.getParameter("articleCreateTime").trim());
//			} catch (IllegalArgumentException e) {
//				articleCreateTime = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");
//			}

			Article article = new Article();
			article.setArticleID(articleID);
			article.setArticleTitle(articleTitle);
			article.setMemberID(memberID);
			article.setArticleContent(articleContent);
			article.setBoardID(boardID);
			article.setArticleStatus(articleStatus);
//			article.setArticleCreateTime(articleCreateTime);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("article", article); // 含有輸入格式錯誤的article物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/article/update_article_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			
			/****************************2.開始修改資料 ****************************************/
			ArticleService articleSvc = new ArticleService();
			article = articleSvc.updateArticle(articleID, articleTitle, memberID, articleContent, boardID, articleStatus);

			/****************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("article", article); // 資料庫update成功後,正確的的article物件,存入req
			String url = "/article/listOneArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneArticle.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addArticle.jsp的請求  

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/************************** 1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String articleTitle = req.getParameter("articleTitle");
			String articleTitleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
			if (articleTitle == null || articleTitle.trim().length() == 0) {
				errorMsgs.add("文章標題: 請勿空白");
			} else if (!articleTitle.trim().matches(articleTitleReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("文章標題: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
			}
			Integer memberID = null;
			try {
				memberID = Integer.valueOf(req.getParameter("memberID").trim());
			} catch (NumberFormatException e) {
				memberID = 0;
				errorMsgs.add("會員編號請填數字.");
			}

			String articleContent = req.getParameter("articleContent");
			String articleContentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,500}$";
			if (articleContent == null || articleContent.trim().length() == 0) {
				errorMsgs.add("文章內容: 請勿空白");
			} else if (!articleContent.trim().matches(articleContentReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("文章內容: 只能是中、英文字母、數字和_ , 且長度必需在2到500之間");
			}

			Integer boardID = null;
			try {
				boardID = Integer.valueOf(req.getParameter("boardID").trim());
			} catch (NumberFormatException e) {
				boardID = 0;
				errorMsgs.add("各版編號請填數字.");
			}

			Integer articleStatus = null;
			try {
				articleStatus = Integer.valueOf(req.getParameter("articleStatus").trim());
			} catch (NumberFormatException e) {
				articleStatus = 0;
				errorMsgs.add("文章狀態請填數字,0為不顯示,1為顯示");
			}

//			Date articleCreateTime = null;
//			try {
//				articleCreateTime = java.sql.Date.valueOf(req.getParameter("articleCreateTime").trim());
//			} catch (IllegalArgumentException e) {
//				articleCreateTime = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");
//			}

			Article article = new Article();
			article.setArticleTitle(articleTitle);
			article.setMemberID(memberID);
			article.setArticleContent(articleContent);
			article.setBoardID(boardID);
			article.setArticleStatus(articleStatus);
//			article.setArticleCreateTime(articleCreateTime);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("article", article); // 含有輸入格式錯誤的article物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/addArticle.jsp");
				failureView.forward(req, res);
				return;
			}

			/******************************* 2.開始新增資料***************************************/
			ArticleService articleSvc = new ArticleService();
			article = articleSvc.addArticle(articleTitle, memberID, articleContent, boardID, articleStatus);

			/******************************* 3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/article/listAllArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllArticle.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // listAllArticle.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/**************************** 1.接收請求參數 ***************************************/
			Integer articleID = Integer.valueOf(req.getParameter("articleID"));

			/**************************** 2.開始刪除資料 ***************************************/
			ArticleService articleSvc = new ArticleService();
			articleSvc.deleteArticle(articleID);

			/**************************** 3.刪除完成,準備轉交(Send the Success view)************/
			String url = "/article/listAllArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
