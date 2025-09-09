package com.myapp.service;

import com.myapp.model.dao.UserDao;
import com.myapp.model.vo.User;
import static com.myapp.common.Template.*;

import java.sql.Connection;

public class UserService {
    private UserDao uDao = new UserDao();

    public int insertUser(User u) {
        Connection conn = getConnection();
        int result = uDao.insertUser(u, conn);
        if(result > 0) commit(conn); else rollback(conn);
        close(conn);
        return result;
    }

    public boolean loginUser(User u) {
        Connection conn = getConnection();
        boolean isLogin = uDao.loginUser(u, conn);
        close(conn);
        return isLogin;
    }

    public double getBalance(User u) {
        Connection conn = getConnection();
        double balance = uDao.getBalance(u, conn);
        close(conn);
        return balance;
    }

    public int updateBalance(User u) {
        Connection conn = getConnection();
        int result = uDao.updateBalance(u, conn);
        if(result > 0) commit(conn); else rollback(conn);
        close(conn);
        return result;
    }
    
    public int addBalance(User u) {
    	Connection conn = getConnection();
    	int result = uDao.addBalance(u, conn);
    	if(result>0) commit(conn); else rollback(conn);
    	close(conn);
    	return result;
    }
}
