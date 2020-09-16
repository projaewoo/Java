import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

public class CrossJoinDemo {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection();		//2step : Oracle Driver Loading, 3step : DB에 Connection.
//		System.out.println(conn);		//확인 차 출력.
		
		//비표준 (Cartesian Product)
//		String sql = " SELECT e.empno, e.ename, e.sal, d.deptno, d.dname, d.loc " +
//							" FROM emp e, dept d ";
		
		//표준 조인 (Cross Join)
		String sql = " SELECT e.empno, e.ename, e.sal, d.deptno, d.dname, d.loc " +
							" FROM emp e CROSS JOIN dept d ";
		
		Statement stmt = conn.createStatement();		//4step : Statement 생성.
		ResultSet rs = stmt.executeQuery(sql);	//5step : executeQuery()를 통해 주어진 SQL문장 실행.
		//6step : ResultSet이라는 가상테이블(바구니)를 처리.
		int count = 0;
		while(rs.next()) {
			int empno = rs.getInt(1);
			String ename = rs.getString(2);
			double sal = rs.getDouble(3);
			int deptno = rs.getInt(4);
			String dname = rs.getString(5);
			String loc = rs.getString(6);
			System.out.println(++count + "번째 줄 :  " + empno + "\t" + ename + "\t" + sal + "\t" + deptno + "\t" + dname + "\t" + loc);
		}
		DBClose.close(conn);  //7step
		
	}
}
