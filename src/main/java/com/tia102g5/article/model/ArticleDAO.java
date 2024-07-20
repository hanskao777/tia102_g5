package com.tia102g5.article.model;

import java.util.List;

public interface ArticleDAO {
	
	int insert(Article entity);

	int update(Article entity);
	
	int delete(Integer id);
	 
	Article getById(Integer id);
	
	
	List<Article> getAll();
	
//	List<Article> getByCompositeQuery(Map<String, String> map);
	
//	long getTotal();
}
