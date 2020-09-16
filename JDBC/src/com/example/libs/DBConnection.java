package com.example.libs; // <--Component

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//Connection 전문적으로 다루는 클래스. // <--Component
public class DBConnection {
	private Properties info;		//멤버변수.
	public DBConnection() {		//생성자 : 멤버변수 초기화.
		this.info = new Properties();
		File file = new File("/Users/projaewoo/Documents/JavaFile/0914/dbinfo.properties");		//properties파일을 열어서 Properties객체와 매칭해줌.
		try {
			this.info.load(new FileInputStream(file));	
		}catch(FileNotFoundException e) {
			System.out.println(e.toString());
		}
		catch(IOException e) {
			System.out.println(e.toString());
		}
	}
	
	public Connection getConnection() {
		//2 step : 드라이브 로딩.
		try {
			Class.forName(this.info.getProperty("DBDRIVER"));   //this.info.getProperty("DBDRIVER") = oracle.jdbc.driver.OracleDriver
		} catch (ClassNotFoundException e) {
			System.out.println("Oracle Driver Loading Failure");
		}   
		//3 step : Connection 생성.
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(this.info.getProperty("DBURL"), 
																	this.info.getProperty("DBUSER"), 
																	this.info.getProperty("DBPASSWD"));
		} catch (SQLException e) {
			System.out.println("Connection Failure");
		}
		return conn;
	}
}
