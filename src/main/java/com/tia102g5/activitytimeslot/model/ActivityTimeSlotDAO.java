package com.tia102g5.activitytimeslot.model;

import java.util.List;

import com.tia102g5.activitycollection.model.ActivityCollectionVO;

public interface ActivityTimeSlotDAO {
	
	void insert(ActivityTimeSlotVO activityTimeSlotVO); //新增
    void update(ActivityTimeSlotVO activityTimeSlotVO); //修改
    //void delete(Integer activityTimeSlotID); //刪除
    ActivityTimeSlotVO findByPrimaryKey(Integer activityTimeSlotID); //查詢 (單一)
    List<ActivityTimeSlotVO> getAll(); //查詢 (複合)

}
