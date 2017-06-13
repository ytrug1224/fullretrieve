package com.searchengin.full.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.searchengin.full.config.InitParams;
import com.searchengin.util.StringUtils;

public class DataBaseUtils {
	private static String URL;
	private static String USER;
	private static String PASSWORD;
	
	static{
		URL = StringUtils.getConfigParam(InitParams.DATABASE_URL, "", InitParams.SEARCH_PROPERTIES);
		USER = StringUtils.getConfigParam(InitParams.DATABASE_USER, "", InitParams.SEARCH_PROPERTIES);
		PASSWORD = StringUtils.getConfigParam(InitParams.DATABASE_PASSWORD, "", InitParams.SEARCH_PROPERTIES);
	}

	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn,PreparedStatement pstmt,Statement stmt,ResultSet rs){
		try {
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
