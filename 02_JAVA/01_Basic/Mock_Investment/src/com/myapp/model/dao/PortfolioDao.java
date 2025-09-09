package com.myapp.model.dao;

import static com.myapp.common.Template.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.Connection;

import com.myapp.model.vo.Portfolio;

public class PortfolioDao {
    private Properties prop = new Properties();

    public PortfolioDao() {
        try {
            prop.loadFromXML(new FileInputStream("resources/query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Portfolio> selectPortfolio(String userId, Connection conn) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        ArrayList<Portfolio> list = new ArrayList<>();

        String sql = prop.getProperty("selectPortfolio"); 

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rset = pstmt.executeQuery();

            while(rset.next()) {
                Portfolio p = new Portfolio(
                    rset.getString("user_id"),
                    rset.getString("asset_id"),
                    rset.getDouble("quantity"),
                    rset.getDouble("avg_price")
                );
                list.add(p);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }

        return list;
    }
    
    public int insertPortfolio(Portfolio p, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;

        String sql = prop.getProperty("insertPortfolio");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, p.getUserId());
            pstmt.setString(2, p.getAssetId());
            pstmt.setDouble(3, p.getQuantity());
            pstmt.setDouble(4, p.getAvgPrice());

            result = pstmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }

        return result;
    }
    
    public int updatePortfolio(Portfolio p, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("updatePortfolio");
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, p.getQuantity());
            pstmt.setDouble(2, p.getAvgPrice());
            pstmt.setString(3, p.getUserId());
            pstmt.setString(4, p.getAssetId());
            result = pstmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }
    
    public int deletePortfolio(Portfolio p, Connection conn) {
        int result = 0;
        String sql = prop.getProperty("deletePortfolio");
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.getUserId());
            pstmt.setString(2, p.getAssetId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
