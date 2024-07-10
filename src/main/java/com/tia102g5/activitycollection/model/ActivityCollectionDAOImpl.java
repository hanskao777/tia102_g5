package com.tia102g5.activitycollection.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbcinfo.JDBCInfo;

public class ActivityCollectionDAOImpl implements ActivityCollectionDAO{
	
	//新增
	public static final String INSERT = "INSERT INIO activitycollection (memberID, activityID)"
			+ "VALUES(?, ?)";
	
	//更新
	public static final String UPDATE = "UPDATE activitycollection SET memberID = ?, activityID = ? WHERE activityCollectionID = ?";
	
	//查詢 (單一)
	public static final String FIND_BY_PRIMARY_KEY = "SELECT * FROM activitycollection WHERE activityCollectionID = ?";
	
	//查詢 (複合)
	public static final String GET_ALL = "SELECT * FROM activitycollection ORDER BY activityCollectionID";
	
	// 載入驅動
	static {
		try {
			Class.forName(JDBCInfo.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//新增
	@Override
	public void insert(ActivityCollectionVO activityCollectionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			//建立連線
			con = DriverManager.getConnection(JDBCInfo.URL, JDBCInfo.USER, JDBCInfo.PASSWORD);
			pstmt = con.prepareStatement(INSERT);
			
			//設定 SQL 指令
			pstmt.setInt(1, activityCollectionVO.getMemberID());
			pstmt.setInt(2, activityCollectionVO.getActivityID());
			
			//送出 SQL 指令
			pstmt.executeUpdate();
			
			System.out.println("新增成功");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//關閉連線
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//更新
	@Override
	public void update(ActivityCollectionVO activityCollectionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			//建立連線
			con = DriverManager.getConnection(JDBCInfo.URL, JDBCInfo.USER, JDBCInfo.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			
			//設定 SQL 指令
			pstmt.setInt(1, activityCollectionVO.getMemberID());
			pstmt.setInt(2, activityCollectionVO.getActivityID());
			
			//送出 SQL 指令
			pstmt.executeUpdate();
			
			System.out.println("更新成功");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//關閉連線
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//查詢 (單一)
	@Override
	public ActivityCollectionVO findByPrimaryKey(Integer activityCollectionID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ActivityCollectionVO activityCollectionVO = null;
		
		try {
			//建立連線
			con = DriverManager.getConnection(JDBCInfo.URL, JDBCInfo.USER, JDBCInfo.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PRIMARY_KEY);
			
			//設定 SQL 指令
			pstmt.setInt(1, activityCollectionID);
			
			//送出 SQL 指令
			rs = pstmt.executeQuery();

			// 包裝查詢的 Activity 物件
			while(rs.next()) {
				activityCollectionVO = new ActivityCollectionVO();
				activityCollectionVO.setActivityCollectionID(activityCollectionID);
				activityCollectionVO.setMemberID(rs.getInt("memberID"));
				activityCollectionVO.setActivityID(rs.getInt("activityID"));
				activityCollectionVO.setActivityCollectionTime(rs.getTimestamp("activityCollectionTime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//關閉連線
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return activityCollectionVO;
	}

	//查詢 (複合)
	@Override
	public List<ActivityCollectionVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ActivityCollectionVO> activityCollectionVOList = new ArrayList<ActivityCollectionVO>();
		
		try {
			//建立連線
			con = DriverManager.getConnection(JDBCInfo.URL, JDBCInfo.USER, JDBCInfo.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			//送出 SQL 指令
			rs = pstmt.executeQuery();
			
			// 包裝查詢的 Activity 物件
			while(rs.next()) {
				ActivityCollectionVO activityCollectionVO = new ActivityCollectionVO();
				activityCollectionVO.setActivityCollectionID(rs.getInt("activityCollectionID"));
				activityCollectionVO.setMemberID(rs.getInt("memberID"));
				activityCollectionVO.setActivityID(rs.getInt("activityID"));
				activityCollectionVO.setActivityCollectionTime(rs.getTimestamp("activityCollectionTime"));
				
				activityCollectionVOList.add(activityCollectionVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//關閉連線
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return activityCollectionVOList;
	}
	
	

}
