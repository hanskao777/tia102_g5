package com.tia102g5.ticket.model;

import java.util.List;

import com.tia102g5.bookticket.model.BookTicketVO;

public interface TicketDAO {
	
	void insert(TicketVO ticketVO); //新增
    void update(TicketVO ticketVO); //修改
    //void delete(Integer ticketID); //刪除
    TicketVO findByPrimaryKey(Integer ticketID); //查詢 (單一)
    List<TicketVO> getAll(); //查詢 (複合)
    
}
