package com.myapp.controller;

import java.sql.Timestamp;
import java.util.List;

import com.myapp.model.vo.Asset;
import com.myapp.model.vo.TradeHistory;
import com.myapp.service.TradeHistoryService;

public class TradeHistoryController {
    private TradeHistoryService tradehistoryService = new TradeHistoryService();

    public void selectAllTradeHistory(String userId) {
        List<TradeHistory> list = tradehistoryService.selectAllTradeHistory(userId);
        for (TradeHistory th : list) {
            System.out.println(th);
        }
    }
    
    public void selectTradeHistoryByDate(String userId, String date) {
        List<TradeHistory> list = tradehistoryService.selectTradeHistoryByDate(userId, date);
        for (TradeHistory th : list) {
            System.out.println(th);
        }
    }

    
//    public void updateTradeHistory(String user_Id, String assetId, boolean tradetype, double price, double allPrice,
//			double quantity, Timestamp trade_time) {
//    	TradeHistory t = new TradeHistory(user_Id,assetId,tradetype,price,allPrice,quantity,trade_time);
//    	
//    	int result = tradehistoryService.updateTradeHistory(t);
//
//        if (result > 0) {
//            System.out.println("거래내역 저장 성공");
//        } else {
//            System.out.println("거래내역 저장 실패");
//        }
//    }
    
    
}
