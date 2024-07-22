package com.tia102g5.activitycollection.model;

import java.util.List;

public class ActivityCollectionService {

	private static ActivityCollectionDAO dao = null;
	
	//建構子產生 dao 物件
	public ActivityCollectionService() {
		dao = new ActivityCollectionDAOImpl();
	}
	
	// 新增
	public ActivityCollectionVO insert(Integer memberID, Integer activityID) {
		// 建立 ActivityCollectionVO 物件
		ActivityCollectionVO activityCollectionVO = new ActivityCollectionVO();

		activityCollectionVO.setMemberID(memberID);
		activityCollectionVO.setActivityID(activityID);
		
		// 存入 DB
		dao.insert(activityCollectionVO);
		
		return activityCollectionVO;
	}

	// 更新
	public ActivityCollectionVO update(Integer activityCollectionID, Integer memberID, Integer activityID) {

		// 建立 ActivityVO 物件
		ActivityCollectionVO activityCollectionVO = new ActivityCollectionVO();

		activityCollectionVO.setActivityCollectionID(activityCollectionID);
		activityCollectionVO.setMemberID(memberID);
		activityCollectionVO.setActivityID(activityID);
		
		// 存入 DB
		dao.update(activityCollectionVO);
		
		return findByPrimaryKey(activityCollectionID);
	}

	// 查詢 (單一)
	public ActivityCollectionVO findByPrimaryKey(Integer activityCollectionID) {
		// 從 DB 搜尋資料
		return dao.findByPrimaryKey(activityCollectionID);
	}

	// 查詢 (複合)
	public List<ActivityCollectionVO> getAll() {
		// 從 DB 搜尋資料
		return dao.getAll();
	}
	
}
