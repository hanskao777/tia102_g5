package com.tia102g5.article.model;


import org.hibernate.Session;

import com.util.HibernateUtil;



public class TestArticle {
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
//=====================新增========================//
			
//			Article arti = new Article();
//			arti.setArticleTitle("Hibernate測試時間2");
//			arti.setMemberID(2);
//			arti.setArticleContent("測試時間2");
//			arti.setBoardID(3);
//			arti.setArticleStatus(1);
//			
//			session.save(arti);
//			
			
//======================刪除=======================//
			
//			Article arti = session.get(Article.class, 3);
//			if (arti != null)
//				session.delete(arti);			
			
		
//======================更新=======================//
						
//			Article arti = new Article();
//			arti.setArticleID(10);
//			arti.setArticleTitle("更新測試測試2");
//			arti.setMemberID(3);
//			arti.setArticleContent("這是測試更新資料的內文2");
//			arti.setBoardID(1);
//			arti.setArticleStatus(1);		
//			
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			Date date = sdf.parse("2023-01-01");
//			arti.setArticleCreateTime(date);
//				
//			session.saveOrUpdate(arti);			
			
			
//			Article arti = session.get(Article.class, 10);
//			if (arti != null)
//				arti.setArticleTitle("測試更新2");
//		
			
//======================查詢=======================//
			
			Article arti1 = session.get(Article.class, 1);
			System.out.println(arti1);
//			
//			Article arti2 = session.load(Article.class, 2);
//			System.out.println(arti2);		
		
				
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}
		
		
	}

}
