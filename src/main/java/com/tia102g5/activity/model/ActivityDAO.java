package com.tia102g5.activity.model;

import java.util.List;

public interface ActivityDAO {
	
	void insert(ActivityVO activityVO); //新增
    void update(ActivityVO activityVO); //修改
    //void delete(Integer activityID); //刪除
    ActivityVO findByPrimaryKey(Integer activityID); //查詢 (單一)
    List<ActivityVO> getAll(); //查詢 (複合)

}
