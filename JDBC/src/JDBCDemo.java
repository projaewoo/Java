//1step : import하자.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.driver.OracleDriver;		//이러한 클래스를 import.

//전체 경로 : DriverManager --> Connection --> Statement --> ResultSet
//				DriverManager --> Connection -->	PrepatedStatement -> ResultSet

public class JDBCDemo {
	public static void main(String[] args) {
		//2. Driver Loading 방법 1 (이 방법은 dbinfo.properties 사용가능)
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			System.out.println("Oracle Driver Loading Success");
//		} catch (ClassNotFoundException e) {
//			System.out.println("Oracle Driver Loading Failure");
//		}
		//2. Driver Loading 방법 2 (단, 이 방법으로는 dbinfo.properties 사용 못함)
		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("Oracle Driver Loading Success");
		} catch (SQLException e) {
			System.out.println("Oracle Driver Loading Failure");
		}
		
		//3step : DB에 Connection.
		Properties info = new Properties();
		info.setProperty("user", "scott");
		info.setProperty("password", "tiger");
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, info);		//(String url, Properties info)	//연결정보를 Connection에게 넘겨줌. //Connection :interface여서 new 못씀. //따라서 Connection만들려면 DriverManager.getConnection(url, properties)으로..
			System.out.println(conn);
		} catch (SQLException e) {
			System.out.println("Connection Failure");
		}
		
		//4step : 문장객체 (Statement) 생성.
//		Statement stmt = null;
//		ResultSet rs = null;
//		try {
//			stmt = conn.createStatement();		//Statement : 인터페이스이므로 이 방법으로 생성.
//			//5step : executeQuery()를 통해 주어진 SQL문장 실행.
//			//사원의 이름을 입력받아서 그 사원의 사번, 이름, 소속 부서이름, 위치, 부서번호 출력.
//			String name = "JAMES";
//			String sql = "SELECT empno, ename, dname, loc, deptno " +		//NATURAL JOIN : 식별자 사용 X (emp.deptno X)
//							" FROM emp NATURAL JOIN dept " +
//							" WHERE ename = '" + name + "' " ;
//			rs = stmt.executeQuery(sql);		//5step.
//			//6step : ResultSet 가상테이블을 오픈.
//			while(rs.next()) {
//				int empno = rs.getInt("empno");
//				String ename = rs.getString("ename");
//				String dname = rs.getString("dname");
//				String loc = rs.getString("loc");
//				int deptno = rs.getInt("deptno");
//				System.out.println(empno + "\t" + ename + "\t" + dname + "\t" + loc + "\t" + deptno);
//			}
		
		//4step : Making a PreparedStatement Object (이번에는 Statement생성이 아닌 PreparedStatement 생성)
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String name = "SCOTT";
			String sql = "SELECT empno, ename, dname, loc, deptno " +		//NATURAL JOIN : 식별자 사용 X (emp.deptno X)
							" FROM emp NATURAL JOIN dept " +
							" WHERE ename = ? ";			//불완전한 SQL문장.
			pstmt = conn.prepareStatement(sql);		//PreparedStatement 생성.	//sql : 아직까지 불완전한 SQL문장.	//처음이자 마지막의 문법검사, 객체검사 진행.
			pstmt.setString(1, name); 		//첫번째 ?에 name(="SCOTT") 넣어라.		//완전한 SQL 문장.
			rs = pstmt.executeQuery();		//74, 75행에서 sql 진행됨. //따라서 여기서 parameter에 sql넣어서 또 실행되게 하면 안됨.
			while(rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				int deptno = rs.getInt("deptno");
				System.out.println(empno + "\t" + ename + "\t" + dname + "\t" + loc + "\t" + deptno);
			}
		} catch (SQLException e) {
			System.out.println("Connection Failure");
		}
	}
}