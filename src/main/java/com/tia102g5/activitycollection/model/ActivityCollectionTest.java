package com.tia102g5.activitycollection.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ActivityCollectionTest {

	private static Integer activityCollectionID;
	private static Integer memberID;
	private static Integer activityID;
	
	private static ActivityCollectionVO activityCollectionVO;
	private static List<ActivityCollectionVO> activityCollectionVOList = new ArrayList<ActivityCollectionVO>();
	private static ActivityCollectionDAOImpl dao = new ActivityCollectionDAOImpl();
	
	private static Scanner sc = new Scanner(System.in);
	
	// 測試主程式
	public static void main(String[] args) {
		String function;

		Again: while (true) {
			System.out.println("請輸入測試功能 (1)INSERT (2)UPDATE (3)FIND_BY_PRIMARY_KEY (4)GET_ALL");
			function = sc.next();

			switch (function) {
			case "1":
				// 新增
				insert();
				break;
			case "2":
				// 更新
				update();
				break;
			case "3":
				// 查詢 (單一)
				findByPrimaryKey();
				break;
			case "4":
				// 查詢 (複合)
				getAll();
			case "5":
				// 結束程式
				break Again;
			default:
				// 輸入錯誤
				System.out.println("輸入錯誤");
			}
		}

		// 關閉輸入流
		sc.close();
	}

	// 新增
	public static void insert() {
		// 使用者輸入資料
		System.out.println("請輸入會員 ID: ");
		memberID = sc.nextInt();

		System.out.println("請輸入活動 ID: ");
		activityID = sc.nextInt();
		
		// 建立 ActivityCollectionVO 物件
		activityCollectionVO = new ActivityCollectionVO(null, memberID, activityID, null);

		// 存入 DB
		dao.insert(activityCollectionVO);
	}

	// 更新
	public static void update() {
		// 使用者輸入資料
		System.out.println("請輸入更新活動收藏 ID");
		activityCollectionID = sc.nextInt();

		System.out.println("請輸入會員 ID: ");
		memberID = sc.nextInt();

		System.out.println("請輸入活動 ID: ");
		activityID= sc.nextInt();

		// 建立 ActivityVO 物件
		activityCollectionVO = new ActivityCollectionVO(activityCollectionID, memberID, activityID, null);

		// 存入 DB
		dao.update(activityCollectionVO);
	}

	// 查詢 (單一)
	public static void findByPrimaryKey() {
		// 使用者輸入資料
		System.out.println("請輸入活動收藏 ID");
		activityID = sc.nextInt();

		// 從 DB 搜尋資料
		activityCollectionVO = dao.findByPrimaryKey(activityCollectionID);
		
		System.out.println(activityCollectionVO);
	}

	// 查詢 (複合)
	public static void getAll() {
		// 從 DB 搜尋資料
		activityCollectionVOList = dao.getAll();

		// 取得迭代器
		Iterator<ActivityCollectionVO> it = activityCollectionVOList.iterator();
		System.out.println("\n活動收藏 ID : ");
		// 依照ID順序取出 ActivityVO 物件並顯示活動收藏 ID
		while (it.hasNext()) {
			System.out.println(it.next().getActivityCollectionID());
		}
	}
	
}
