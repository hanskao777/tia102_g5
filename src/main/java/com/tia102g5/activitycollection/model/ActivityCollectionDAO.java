package com.tia102g5.activitycollection.model;

import java.util.List;

public interface ActivityCollectionDAO {
	
	void insert(ActivityCollectionVO activityCollectionVO); //新增
    void update(ActivityCollectionVO activityCollectionVO); //修改
    void delete(Integer activityCollectionID); //刪除
    ActivityCollectionVO findByPrimaryKey(Integer activityCollectionID); //查詢 (單一)
    List<ActivityCollectionVO> getAll(); //查詢 (複合)

}
