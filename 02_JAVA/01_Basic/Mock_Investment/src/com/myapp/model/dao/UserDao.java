package com.myapp.model.dao;

import static com.myapp.common.Template.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.myapp.model.vo.User;

public class UserDao {
	private Properties prop = new Properties();

	public UserDao() {
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertUser(User u,Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertUser");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,u.getUserId());
			pstmt.setString(2,u.getPassword());
			pstmt.setString(3,u.getName());
			pstmt.setDouble(4,u.getBalance());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}
	
	public boolean loginUser(User u, Connection conn) {
		boolean isLogin = false;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginUser");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,u.getUserId());
			pstmt.setString(2,u.getPassword());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				isLogin = true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return isLogin;
	}
	
	public double getBalance(User u, Connection conn) {
		double balance = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("getBalance");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,u.getUserId());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				balance = rset.getDouble("balance");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return balance;
	}
	
	public int updateBalance(User u, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateBalance");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setDouble(1,u.getBalance());
			pstmt.setString(2,u.getUserId());
			
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int addBalance(User u, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("addBalance");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setDouble(1,u.getBalance());
			pstmt.setString(2,u.getUserId());
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
}
}
