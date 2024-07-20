package com.tia102g5.articleImg.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.tia102g5.articleImg.model.ArticleImg;
import com.tia102g5.articleImg.model.ArticleImgService;

@MultipartConfig
public class ArticleImgServlet extends HttpServlet  {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("articleImgID");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入文章圖片編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/articleImg/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer articleImgID = null;
			try {
				articleImgID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("文章圖片編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/articleImg/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ArticleImgService artImgSvc = new ArticleImgService();
			ArticleImg articleImg = artImgSvc.getOneArticleImg(articleImgID);
			if (articleImg == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/articleImg/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("articleImg", articleImg); // 資料庫取出的article物件,存入req
			String url = "/articleImg/listOneArticleImg.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneArticle.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllArticle.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數� ****************************************/
			Integer articleImgID = Integer.valueOf(req.getParameter("articleImgID"));

			/*************************** 2.開始查詢資料 ****************************************/
			ArticleImgService articleImgSvc = new ArticleImgService();
			ArticleImg articleImg = articleImgSvc.getOneArticleImg(articleImgID);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("articleImg", articleImg); // 資料庫取出的article物件,存入req
			String url = "/articleImg/update_articleImg_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_article_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_article_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer articleImgID = Integer.valueOf(req.getParameter("articleImgID").trim());
			
			Integer articleID = Integer.valueOf(req.getParameter("articleID").trim());

			
			Part filePart = req.getPart("articlePic");
			if (filePart == null || filePart.getSize() == 0) {
				errorMsgs.add("請選擇圖片文件");
			} else {
				String fileName = filePart.getSubmittedFileName();
				String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				long fileSize = filePart.getSize();
				long maxSize = 1024 * 1024 * 5; // 5MB

				// 檢查文件類型和大小
				if (!fileExtension.matches("jpg|jpeg|png|gif")) {
					errorMsgs.add("圖片格式無效，僅支持jpg、jpeg、png、gif格式");
				}
				if (fileSize > maxSize) {
					errorMsgs.add("圖片大小超過5MB限制");
				}

			}
			
			// 將圖片數據讀取為字節數組
			byte[] articlePic = null;
			try (InputStream inputStream = filePart.getInputStream();
				ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = inputStream.read(buf)) != -1) {
					outStream.write(buf, 0, len);
				}
				articlePic = outStream.toByteArray();
			} catch (IOException e) {
				errorMsgs.add("圖片讀取失敗");
			}

			ArticleImg articleImg = new ArticleImg();
			articleImg.setArticleImgID(articleImgID);
			articleImg.setArticleID(articleID);
			articleImg.setArticlePic(articlePic);


			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("articleImg", articleImg); // 含有輸入格式錯誤的article物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/articleImg/update_articleImg_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/**************************** 2.開始修改資料 ****************************************/
			ArticleImgService articleImgSvc = new ArticleImgService();
			articleImg = articleImgSvc.updateArticleImg(articleImgID, articleID, articlePic);

			/**************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("articleImg", articleImg); // 資料庫update成功後,正確的的article物件,存入req
			String url = "/articleImg/listOneArticleImg.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneArticle.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addArticle.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/************************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer articleID = null;
			try {
				articleID = Integer.valueOf(req.getParameter("articleID").trim());
			} catch (NumberFormatException e) {
				articleID = 0;
				errorMsgs.add("文章編號請填數字.");
			}

			Part filePart = req.getPart("articlePic");
			if (filePart == null || filePart.getSize() == 0) {
				errorMsgs.add("請選擇圖片文件");
			} else {
				String fileName = filePart.getSubmittedFileName();
				String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				long fileSize = filePart.getSize();
				long maxSize = 1024 * 1024 * 5; // 5MB

				// 檢查文件類型和大小
				if (!fileExtension.matches("jpg|jpeg|png|gif")) {
					errorMsgs.add("圖片格式無效，僅支持jpg、jpeg、png格式");
				}
				if (fileSize > maxSize) {
					errorMsgs.add("圖片大小超過5MB限制");
				}
			}		
			
			// 將圖片數據讀取為字節數組
			byte[] articlePic = null;
			try (InputStream inputStream = filePart.getInputStream();
				ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = inputStream.read(buf)) != -1) {
					outStream.write(buf, 0, len);
				}
				articlePic = outStream.toByteArray();
			} catch (IOException e) {
				errorMsgs.add("圖片讀取失敗");
			}
			
			

			ArticleImg articleImg = new ArticleImg();
			articleImg.setArticleID(articleID);
			articleImg.setArticlePic(articlePic);


			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("articleImg", articleImg); // 含有輸入格式錯誤的article物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/articleImg/addArticleImg.jsp");
				failureView.forward(req, res);
				return;
			}

			/******************************** 2.開始新增資料***************************************/
			ArticleImgService articleImgSvc = new ArticleImgService();
			articleImg = articleImgSvc.addArticleImg(articleID, articlePic);

			/******************************* 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/articleImg/listAllArticleImg.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllArticle.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // listAllArticle.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/**************************** 1.接收請求參數 ***************************************/
			Integer articleImgID = Integer.valueOf(req.getParameter("articleImgID"));

			/**************************** 2.開始刪除資料 ***************************************/
			ArticleImgService articleImgSvc = new ArticleImgService();
			articleImgSvc.deleteArticle(articleImgID);

			/**************************** 3.刪除完成,準備轉交(Send the Success view) ************/
			String url = "/articleImg/listAllArticleImg.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}

}
