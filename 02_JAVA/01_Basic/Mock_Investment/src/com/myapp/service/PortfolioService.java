package com.myapp.service;

import com.myapp.model.dao.PortfolioDao;
import com.myapp.model.vo.Portfolio;

import java.sql.Connection;
import java.util.ArrayList;
import static com.myapp.common.Template.*;

public class PortfolioService {
    private PortfolioDao portfolioDao = new PortfolioDao();

    public ArrayList<Portfolio> getPortfolio(String userId) {
        Connection conn = getConnection();
        ArrayList<Portfolio> list = portfolioDao.selectPortfolio(userId, conn);
        close(conn);
        return list;
    }

    public Portfolio getPortfolioByAsset(String userId, String assetId) {
        ArrayList<Portfolio> list = getPortfolio(userId);
        return list.stream()
                   .filter(p -> p.getAssetId().equals(assetId))
                   .findFirst().orElse(null);
    }

    public int insertPortfolio(Portfolio p) {
        Connection conn = getConnection();
        int result = portfolioDao.insertPortfolio(p, conn);
        if(result > 0) commit(conn); else rollback(conn);
        close(conn);
        return result;
    }

    public int updatePortfolio(Portfolio p) {
        Connection conn = getConnection();
        int result = portfolioDao.updatePortfolio(p, conn);
        if(result > 0) commit(conn); else rollback(conn);
        close(conn);
        return result;
    }

    public int deletePortfolio(Portfolio p) {
        Connection conn = getConnection();
        int result = portfolioDao.deletePortfolio(p, conn);
        if(result > 0) commit(conn); else rollback(conn);
        close(conn);
        return result;
    }
}
