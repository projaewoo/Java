package com.example.libs;  // <--Component

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Close만 전문적으로 하는 클래스. // <--Component
public class DBClose {
	public static void close(Connection conn) { 		//utility클래스는 보통 static으로 접근.
		try {
			if(conn != null) conn.close();
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	//@OverLoading
	//Connection, Statement 다룰때의 close.
	public static void close(Connection conn, Statement stmt) { 		//utility클래스는 보통 static으로 접근.
		try {
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	//@OverLoading
	//Connection, Statement, ResultSet 다룰때의 close.
	public static void close(Connection conn, Statement stmt, ResultSet rs) { 		//utility클래스는 보통 static으로 접근.
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
}

