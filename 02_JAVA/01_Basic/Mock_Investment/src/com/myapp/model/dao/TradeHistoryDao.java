package com.myapp.model.dao;

import static com.myapp.common.Template.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.myapp.model.vo.TradeHistory;

public class TradeHistoryDao {
    private Properties prop = new Properties();

    public TradeHistoryDao() {
        try {
            prop.loadFromXML(new FileInputStream("resources/query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<TradeHistory> selectAllTradeHistory(TradeHistory t, Connection conn) {
        List<TradeHistory> tradehistoryList = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("selectAllTradeHistory"))) {
            pstmt.setString(1, t.getUser_id());
            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    int tradeTypeInt = rset.getInt("trade_type");   // DB에서 0/1 읽기
                    boolean tradeType = (tradeTypeInt == 1);       

                    tradehistoryList.add(new TradeHistory(
                        rset.getString("user_id"),
                        rset.getString("asset_id"),
                        tradeType,                                   
                        rset.getDouble("price"),
                        rset.getDouble("all_price"),                 
                        rset.getDouble("quantity"),
                        rset.getTimestamp("trade_time")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tradehistoryList;
    }
    
    public List<TradeHistory> selectTradeHistoryByDate(String userId, String date, Connection conn) {
        List<TradeHistory> list = new ArrayList<>();
        String sql = "SELECT * FROM TRADE_HISTORY WHERE user_id = ? AND TO_CHAR(trade_time, 'YYYY-MM-DD') = ? ORDER BY trade_time";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setString(2, date); // date는 "2025-09-05" 같은 형식

            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    int tradeTypeInt = rset.getInt("trade_type");
                    boolean tradeType = (tradeTypeInt == 1);

                    TradeHistory th = new TradeHistory(
                        rset.getString("user_id"),
                        rset.getString("asset_id"),
                        tradeType,
                        rset.getDouble("price"),
                        rset.getDouble("all_price"),
                        rset.getDouble("quantity"),
                        rset.getTimestamp("trade_time")
                    );
                    list.add(th);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }



    
    public int updateTradeHistory(TradeHistory t, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateTradeHistory");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,t.getUser_id());
			pstmt.setString(2,t.getAssetId());
			pstmt.setBoolean(3,t.isTradetype());
			pstmt.setDouble(4,t.getPrice());
			pstmt.setDouble(5,t.getAllPrice());
			pstmt.setDouble(6,t.getQuantity());
			pstmt.setTimestamp(7,t.getTrade_time());
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
