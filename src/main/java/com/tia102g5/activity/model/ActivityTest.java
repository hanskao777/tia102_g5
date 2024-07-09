package com.tia102g5.activity.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ActivityTest {

	private static Integer activityID;
	private static Integer partnerID;
	private static Integer venueID;
	private static Integer venueRentalID;
	private static String activityName;
	private static String activityContent;

	private static String activityPictureURL;
	private static byte[] activityPicture;

	private static String activityPostTimeInput;
	private static Timestamp activityPostTime;

	private static String activityTag;
	private static Integer activityStatus;
	private static Integer ticketSetStatus;

	private static String sellTimeInput;
	private static Timestamp sellTime;

	private static ActivityVO activityVO;
	private static List<ActivityVO> activityVOList = new ArrayList<ActivityVO>();
	private static ActivityDAO dao = new ActivityDAOImpl();

	private static Scanner sc = new Scanner(System.in);

	// 測試主程式
	public static void main(String[] args) {
		String function;

		Again: 
		while (true) {
			System.out.println("請輸入測試功能 (1)INSERT (2)UPDATE (3)FIND_BY_PRIMARY_KEY (4)GET_ALL (5)結束程式");
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
		System.out.println("請輸入廠商 ID: ");
		partnerID = sc.nextInt();

		System.out.println("請輸入場館 ID: ");
		venueID = sc.nextInt();

		System.out.println("請輸入申請資料 ID: ");
		venueRentalID = sc.nextInt();

		System.out.println("請輸入名稱: ");
		activityName = sc.next();

		System.out.println("請輸入內容: ");
		activityContent = sc.next();

		System.out.println("請輸入圖片位址: ");
		activityPictureURL = sc.next();

		System.out.println("請輸入排程時間 eg. 2024/07/04: ");
		activityPostTimeInput = sc.next();

		System.out.println("請輸入類型標籤: ");
		activityTag = sc.next();

		System.out.println("請輸入設定狀態: ");
		activityStatus = sc.nextInt();

		System.out.println("請輸入票券設定狀態: ");
		ticketSetStatus = sc.nextInt();

		System.out.println("請輸入起售日: ");
		sellTimeInput = sc.next();

		// 設定 activityPicture 資料
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(activityPictureURL);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedInputStream bis = new BufferedInputStream(fis);
		try {
			activityPicture = new byte[bis.available()];
			bis.read(activityPicture);

			// 轉換輸入日期
			activityPostTime = dateFormat(activityPostTimeInput);
			sellTime = dateFormat(sellTimeInput);

			// 建立 ActivityVO 物件
			activityVO = new ActivityVO(null, partnerID, venueID, venueRentalID, activityName, activityContent,
					activityPicture, null, activityPostTime, activityTag, activityStatus, ticketSetStatus, sellTime);

			// 存入 DB
			dao.insert(activityVO);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 關閉輸入流
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 更新
	public static void update() {
		// 使用者輸入資料
		System.out.println("請輸入更新活動 ID");
		activityID = sc.nextInt();

		System.out.println("請輸入廠商 ID: ");
		partnerID = sc.nextInt();

		System.out.println("請輸入場館 ID: ");
		venueID = sc.nextInt();

		System.out.println("請輸入申請資料 ID: ");
		venueRentalID = sc.nextInt();

		System.out.println("請輸入名稱: ");
		activityName = sc.next();

		System.out.println("請輸入內容: ");
		activityContent = sc.next();

		System.out.println("請輸入圖片位址: ");
		activityPictureURL = sc.next();

		System.out.println("請輸入排程時間 eg. 2024/07/04: ");
		activityPostTimeInput = sc.next();

		System.out.println("請輸入類型標籤: ");
		activityTag = sc.next();

		System.out.println("請輸入設定狀態: ");
		activityStatus = sc.nextInt();

		System.out.println("請輸入票券設定狀態: ");
		ticketSetStatus = sc.nextInt();

		System.out.println("請輸入起售日: ");
		sellTimeInput = sc.next();

		// 設定 activityPicture 資料
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(activityPictureURL);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedInputStream bis = new BufferedInputStream(fis);
		try {
			activityPicture = new byte[bis.available()];
			bis.read(activityPicture);

			// 轉換輸入日期
			activityPostTime = dateFormat(activityPostTimeInput);
			sellTime = dateFormat(sellTimeInput);

			// 建立 ActivityVO 物件
			activityVO = new ActivityVO(activityID, partnerID, venueID, venueRentalID, activityName, activityContent,
					activityPicture, null, activityPostTime, activityTag, activityStatus, ticketSetStatus, sellTime);

			// 存入 DB
			dao.update(activityVO);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 關閉輸入流
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 查詢 (單一)
	public static void findByPrimaryKey() {
		// 使用者輸入資料
		System.out.println("請輸入活動 ID");
		activityID = sc.nextInt();

		// 從 DB 搜尋資料
		activityVO = dao.findByPrimaryKey(activityID);
		
		System.out.println(activityVO);
	}

	// 查詢 (複合)
	public static void getAll() {
		// 從 DB 搜尋資料
		activityVOList = dao.getAll();

		// 取得迭代器
		Iterator<ActivityVO> it = activityVOList.iterator();
		System.out.println("\n活動 ID : ");
		// 依照ID順序取出 ActivityVO 物件並顯示活動 ID
		while (it.hasNext()) {
			System.out.println(it.next().getActivityID());
		}
	}

	// 格式化輸入日期資料
	public static Timestamp dateFormat(String inputDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date date = null;
		try {
			date = format.parse(inputDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp timestamp = new Timestamp(date.getTime());

		return timestamp;
	}

}
