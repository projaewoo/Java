import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

//--예를 들어, 사원번호, 이름, 급여, 근무부서를 함께 출력하되
//--급여가 3000 이상인 데이터만 출력
//등가조인 사용.
public class EquiJoinDemo {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection();		//2step : Oracle Driver Loading, 3step : DB에 Connection.
//		System.out.println(conn);		//확인 차 출력.
		
		//비표준 조인.(등가조인) (Equi join) (Inner join) (단순 조인)
//		String sql = " SELECT e.empno, e.ename, e.sal, d.deptno, d.dname, d.loc " +
//							" FROM emp e, dept d " +
//							" WHERE e.deptno = d.deptno AND e.sal >= 3000 ";		//비표준  : WHERE에 조인조건 명시. //등가 조인 : WHERE절에 = 연산자 사용.
		
		//표준 조인 (Natural Join)
//		String sql = " SELECT e.empno, e.ename, e.sal, deptno, d.dname, d.loc " +
//							" FROM emp e NATURAL JOIN dept d " +
//							" WHERE e.sal >= 3000 ";
		//Natural Join 사용 시, 공통된 칼럼(조인 조건)에는 식별자 즉, 테이블 이름이나 테이블 별칭을 사용할 수 없다.
		
		//표준 조인 (JOIN ~ USING 절)
		//JOIN 앞에 INNER명시해도 됨. //JOIN ~ USING에 해당하는 표준조인 키워드인 INNER사용.
		String sql = " SELECT e.empno, e.ename, e.sal, deptno, d.dname, d.loc " +
						" FROM emp e INNER JOIN dept d USING(deptno) " +
						" WHERE e.sal >= 3000 " ;
		
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
