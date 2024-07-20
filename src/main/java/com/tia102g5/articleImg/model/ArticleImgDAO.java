package com.tia102g5.articleImg.model;

import java.util.List;

public interface ArticleImgDAO {
	
	int insert(ArticleImg entity);

	int update(ArticleImg entity);
	
	int delete(Integer id);
	 
	ArticleImg getById(Integer id);
	
	List<ArticleImg> getAll();
	
//	List<Article> getByCompositeQuery(Map<String, String> map);
	

//	long getTotal();
}
