package com.tia102g5.coupon.model;

import java.util.List;

import com.tia102g5.bookticket.model.BookTicketVO;

public interface CouponDAO {

	void insert(CouponVO CouponVO); //新增
    void update(CouponVO CouponVO); //修改
    //void delete(Integer CouponID); //刪除
    CouponVO findByPrimaryKey(Integer CouponID); //查詢 (單一)
    List<CouponVO> getAll(); //查詢 (複合)
    
}
