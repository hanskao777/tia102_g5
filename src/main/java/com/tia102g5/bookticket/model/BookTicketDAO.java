package com.tia102g5.bookticket.model;

import java.util.List;

import com.tia102g5.activitytimeslot.model.ActivityTimeSlotVO;

public interface BookTicketDAO {

	void insert(BookTicketVO bookTicketVO); //新增
    void update(BookTicketVO bookTicketVO); //修改
    //void delete(Integer bookTicketID); //刪除
    BookTicketVO findByPrimaryKey(Integer bookTicketID); //查詢 (單一)
    List<BookTicketVO> getAll(); //查詢 (複合)
    
}
