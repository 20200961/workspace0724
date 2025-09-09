package com.myapp.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.myapp.model.vo.Asset;

public class AssetDao {
    private Properties prop = new Properties();

    public AssetDao() {
        try {
            prop.loadFromXML(new FileInputStream("resources/query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Asset selectAsset(Asset a, Connection conn) {
        Asset asset = null;
        try (PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("selectAsset"))) {
            pstmt.setString(1, a.getAssetId());
            try (ResultSet rset = pstmt.executeQuery()) {
                if (rset.next()) {
                    asset = new Asset(
                        rset.getString("asset_id"),
                        rset.getString("asset_name"),
                        rset.getDouble("current_price"),
                        rset.getDouble("volatility")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asset;
    }

    public List<Asset> selectAllAsset(Connection conn) {
        List<Asset> list = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(prop.getProperty("selectAllAsset"));
             ResultSet rset = pstmt.executeQuery()) {
            while (rset.next()) {
                list.add(new Asset(
                    rset.getString("asset_id"),
                    rset.getString("asset_name"),
                    rset.getDouble("current_price"),
                    rset.getDouble("volatility")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updatePrice(Asset asset, Connection conn) {
        String sql = prop.getProperty("updatePrice");

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, asset.getCurrentPrice());
            pstmt.setString(2, asset.getAssetId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
