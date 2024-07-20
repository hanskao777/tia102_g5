package com.tia102g5.articleImg.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tia102g5.articleImg.model.ArticleImgService;


@WebServlet("/articleImg/ImgDBGifReader")
@MultipartConfig
public class ImgDBGifReader extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Integer articleImgID = Integer.valueOf(req.getParameter("articleImgID"));
			ArticleImgService articleImgSvc = new ArticleImgService();
			byte[] image = articleImgSvc.getOneArticleImg(articleImgID).getArticlePic();

			if (image != null) {
				out.write(image);
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND, "請選擇圖片檔案");
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error");
		} finally {
			out.flush();
			out.close();
		}
	}

}
