import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;

//전체 경로 : DriverManager --> Connection --> Statement --> ResultSet
//				DriverManager --> Connection -->	PreparedStatement -> ResultSet

public class PreparedStatementDemo {
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.print("부서번호 : ");
		int department_num = scan.nextInt();
		System.out.print("월급 : ");
		//PreparedStatement : ? 사용. //입력받는 값 2개 = ? 2개 사용한다는  뜻.	//24행 참조.
		double salary = scan.nextDouble();
		
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection();		//2, 3step
		
		String sql = " SELECT  ename, deptno, sal, dname  " +		//JOIN ~ USIN절 쓸 때, 식별자 사용 금지 (emp.deptno X)
						" FROM   emp INNER JOIN dept USING(deptno) " +
						" WHERE deptno = ? AND sal >= ?";		//불완전한 SQL 문장.
		//4step : PreparedStatement (문장객체) 생성.
		PreparedStatement pstmt = conn.prepareStatement(sql);		//아직까지 불완전한 SQL 문장 // 여기서 처음이자 마지막 문법검사, 객체검사 진행.
		pstmt.setInt(1, department_num);			//int : 부서번호가 정수이므로 	//1 : 첫번째 ?를 department_num로 채워라.
		pstmt.setDouble(2, salary);				//Double : salary가 double형이므로.	//2 : 두번째 ? 를 salary로 채워라.
		//5step : executeQuery()를 통해 주어진 SQL문장 처리 //단, PreparedStatement사용시, 파라미터 X.
		ResultSet rs = pstmt.executeQuery();		//주의사항!! 파라미터로 sql전달 X.	(sql : 불완전한 SQL문장이므로)
		//6step : ResultSet 가상테이블 (바구니) 오픈해서 처리.
		while(rs.next()) {
			String ename = rs.getString("ename");
			int deptno = rs.getInt("deptno");
			double sal = rs.getDouble("sal");
			String dname = rs.getString("dname");
			System.out.println(ename + "\t" + deptno + "\t" + sal + "\t" + dname);
		}
		DBClose.close(conn);  		//7step
		
	}
}
