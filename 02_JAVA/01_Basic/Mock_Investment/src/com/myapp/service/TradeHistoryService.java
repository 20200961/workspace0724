package com.myapp.service;

import static com.myapp.common.Template.close;
import static com.myapp.common.Template.commit;
import static com.myapp.common.Template.getConnection;
import static com.myapp.common.Template.rollback;

import java.sql.Connection;
import java.util.List;

import com.myapp.model.dao.TradeHistoryDao;
import com.myapp.model.vo.TradeHistory;

public class TradeHistoryService {
    private TradeHistoryDao tDao = new TradeHistoryDao();

    public TradeHistoryService() {}
    public TradeHistoryService(TradeHistoryDao tDao) {
        this.tDao = tDao;
    }

    public List<TradeHistory> selectTradeHistoryByDate(String userId, String date) {
        Connection conn = getConnection();
        List<TradeHistory> list = tDao.selectTradeHistoryByDate(userId, date, conn);
        close(conn);
        return list;
    }


    public List<TradeHistory> selectAllTradeHistory(String userId) {
        Connection conn = getConnection();
        List<TradeHistory> tradehistoryList = tDao.selectAllTradeHistory(new TradeHistory(userId), conn);
        close(conn);
        return tradehistoryList;
    }
    
    public int updateTradeHistory(TradeHistory t) {
    	Connection conn = getConnection();
    	int result = tDao.updateTradeHistory(t, conn);
        if(result > 0) commit(conn); else rollback(conn);
        close(conn);
        return result;
    }
}
