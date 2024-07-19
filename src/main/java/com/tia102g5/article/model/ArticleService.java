package com.tia102g5.article.model;

import java.util.List;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.util.HibernateUtil;


@Transactional
public class ArticleService {

	private ArticleDAO dao;

	public ArticleService() {
		dao = new ArticleDAOImpl();
	}

	public Article addArticle(String articleTitle, Integer memberID, String articleContent, Integer boardID,
			Integer articleStatus) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			Article article = new Article();
			article.setArticleTitle(articleTitle);
			article.setMemberID(memberID);
			article.setArticleContent(articleContent);
			article.setBoardID(boardID);
			article.setArticleStatus(articleStatus);
//		article.setArticleCreateTime(articleCreateTime);

			dao.insert(article);

			session.getTransaction().commit();
			return article;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

	public Article updateArticle(Integer articleID, String articleTitle, Integer memberID, String articleContent,
			Integer boardID, Integer articleStatus) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			Article article = new Article();
			article.setArticleID(articleID);
			article.setArticleTitle(articleTitle);
			article.setMemberID(memberID);
			article.setArticleContent(articleContent);
			article.setBoardID(boardID);
			article.setArticleStatus(articleStatus);
//		article.setArticleCreateTime(articleCreateTime);

			dao.update(article);
			
			session.getTransaction().commit();
			return article;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

	public void deleteArticle(Integer articleID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			dao.delete(articleID);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return;
		}
	}

	public List<Article> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Article> list = dao.getAll();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

	public Article getOneArticle(Integer articleID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			Article article = dao.getById(articleID);
			session.getTransaction().commit();
			return article;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

//	public Set<Member> getMemberByMemberID(Integer memberID) {
//		return dao.getMemberByMemberID(memberID);
//	}
}
