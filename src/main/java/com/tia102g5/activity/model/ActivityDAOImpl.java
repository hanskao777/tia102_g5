package com.tia102g5.activity.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbcinfo.JDBCInfo;

public class ActivityDAOImpl implements ActivityDAO {

	// 新增
	public static final String INSERT = "INSERT INTO activity (partnerID, venueID, venueRentalID, activityName, activityContent, activityPicture, activityPostTime, activityTag, activityStatus, ticketSetStatus, sellTime) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	// 更新
	public static final String UPDATE = "UPDATE activity SET " + "partnerID = ?," + "venueID = ?,"
			+ "venueRentalID = ?," + "activityName = ?," + "activityContent = ?," + "activityPicture = ?,"
			+ "activityPostTime = ?," + "activityTag = ?," + "activityStatus = ?," + "ticketSetStatus = ?,"
			+ "sellTime = ?" + "WHERE activityID = ?";

	// 查詢 (單一)
	public static final String FIND_BY_PRIMARY_KEY = "SELECT * FROM activity WHERE activityID = ?";

	// 查詢 (複合)
	public static final String GET_ALL = "SELECT * FROM activity ORDER BY activityID";

	// 載入驅動
	static {
		try {
			Class.forName(JDBCInfo.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	// 新增
	public void insert(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 建立連線
			con = DriverManager.getConnection(JDBCInfo.URL, JDBCInfo.USER, JDBCInfo.PASSWORD);
			pstmt = con.prepareStatement(INSERT);

			// 設定 SQL 指令
			pstmt.setInt(1, activityVO.getPartnerID());
			pstmt.setInt(2, activityVO.getVenueID());
			pstmt.setInt(3, activityVO.getVenueRentalID());
			pstmt.setString(4, activityVO.getActivityName());
			pstmt.setString(5, activityVO.getActivityContent());
			pstmt.setBytes(6, activityVO.getActivityPicture());
			pstmt.setTimestamp(7, activityVO.getActivityPostTime());
			pstmt.setString(8, activityVO.getActivityTag());
			pstmt.setInt(9, activityVO.getActivityStatus());
			pstmt.setInt(10, activityVO.getTicketSetStatus());
			pstmt.setTimestamp(11, activityVO.getSellTime());

			// 送出 SQL 指令
			pstmt.executeUpdate();

			System.out.println("成功新增");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 關閉連線
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	// 更新
	public void update(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 建立連線
			con = DriverManager.getConnection(JDBCInfo.URL, JDBCInfo.USER, JDBCInfo.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			// 設定 SQL 指令
			pstmt.setInt(1, activityVO.getPartnerID());
			pstmt.setInt(2, activityVO.getVenueID());
			pstmt.setInt(3, activityVO.getVenueRentalID());
			pstmt.setString(4, activityVO.getActivityName());
			pstmt.setString(5, activityVO.getActivityContent());
			pstmt.setBytes(6, activityVO.getActivityPicture());
			pstmt.setTimestamp(7, activityVO.getActivityPostTime());
			pstmt.setString(8, activityVO.getActivityTag());
			pstmt.setInt(9, activityVO.getActivityStatus());
			pstmt.setInt(10, activityVO.getTicketSetStatus());
			pstmt.setTimestamp(11, activityVO.getSellTime());
			pstmt.setInt(12, activityVO.getActivityID());

			// 送出 SQL 指令
			pstmt.executeUpdate();

			System.out.println("成功更新");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 關閉連線
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 查詢 (單一)
	@Override
	public ActivityVO findByPrimaryKey(Integer activityID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ActivityVO activityVO = null;

		try {
			// 建立連線
			con = DriverManager.getConnection(JDBCInfo.URL, JDBCInfo.USER, JDBCInfo.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PRIMARY_KEY);

			// 設定 SQL 指令
			pstmt.setInt(1, activityID);

			// 送出 SQL 指令
			rs = pstmt.executeQuery();

			// 包裝查詢的 Activity 物件
			while (rs.next()) {
				activityVO = new ActivityVO();
				activityVO.setActivityID(activityID);
				activityVO.setPartnerID(rs.getInt("partnerID"));
				activityVO.setVenueID(rs.getInt("venueID"));
				activityVO.setVenueRentalID(rs.getInt("venueRentalID"));
				activityVO.setActivityName(rs.getString("activityName"));
				activityVO.setActivityContent(rs.getString("activityContent"));
				activityVO.setActivityPicture(rs.getBytes("activityPicture"));
				activityVO.setActivityCreateTime(rs.getTimestamp("activityCreateTime"));
				activityVO.setActivityPostTime(rs.getTimestamp("activityPostTime"));
				activityVO.setActivityTag(rs.getString("activityTag"));
				activityVO.setActivityStatus(rs.getInt("activityStatus"));
				activityVO.setTicketSetStatus(rs.getInt("ticketSetStatus"));
				activityVO.setSellTime(rs.getTimestamp("sellTime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 關閉連線
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return activityVO;
	}

	// 查詢 (複合)
	@Override
	public List<ActivityVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ActivityVO> activityVOList = new ArrayList<ActivityVO>();

		try {
			// 建立連線
			con = DriverManager.getConnection(JDBCInfo.URL, JDBCInfo.USER, JDBCInfo.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			// 送出 SQL 指令
			rs = pstmt.executeQuery();

			// 包裝查詢的 Activity 物件
			while (rs.next()) {
				ActivityVO activityVO = new ActivityVO();
				activityVO.setActivityID(rs.getInt("activityID"));
				activityVO.setPartnerID(rs.getInt("partnerID"));
				activityVO.setVenueID(rs.getInt("venueID"));
				activityVO.setVenueRentalID(rs.getInt("venueRentalID"));
				activityVO.setActivityName(rs.getString("activityName"));
				activityVO.setActivityContent(rs.getString("activityContent"));
				activityVO.setActivityPicture(rs.getBytes("activityPicture"));
				activityVO.setActivityCreateTime(rs.getTimestamp("activityCreateTime"));
				activityVO.setActivityPostTime(rs.getTimestamp("activityPostTime"));
				activityVO.setActivityTag(rs.getString("activityTag"));
				activityVO.setActivityStatus(rs.getInt("activityStatus"));
				activityVO.setTicketSetStatus(rs.getInt("ticketSetStatus"));
				activityVO.setSellTime(rs.getTimestamp("sellTime"));

				activityVOList.add(activityVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 關閉連線
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return activityVOList;
	}
	
}
