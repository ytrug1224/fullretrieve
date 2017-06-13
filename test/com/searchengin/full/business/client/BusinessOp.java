package com.searchengin.full.business.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.searchengin.full.index.IndexCommonServer;
import com.searchengin.full.util.DataBaseUtils;

import junit.framework.TestCase;

public class BusinessOp extends TestCase{

	public void test1(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DataBaseUtils.getConnection();
			String bSql = "insert into t_user (username,age) values (?,?)";
			pstmt = conn.prepareStatement(bSql);
			pstmt.setString(1, "searchenginwang");
			pstmt.setInt(2, 22);
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataBaseUtils.closeConnection(conn, pstmt, stmt, rs);
		}
		
		IndexCommonServer ics = new IndexCommonServer();
		ics.addIndexTable(5, "user", "add");
		
		/*try {
			conn = DataBaseUtils.getConnection();
			String sql = "insert into t_index (businessId,TYPE,flag) values (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 5);
			pstmt.setString(2, "user");
			pstmt.setInt(3, 0);
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataBaseUtils.closeConnection(conn, pstmt, stmt, rs);
		}*/
	}
	
}
