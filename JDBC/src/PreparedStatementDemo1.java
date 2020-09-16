import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.example.libs.DBClose;
import com.example.libs.DBConnection;


public class PreparedStatementDemo1 {
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.print("찾으려는 사원의 이름 : ");
		String name = scan.next();		//scan.next.toUpper() : 자바에서 대문자로 변환해줌.
		
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection();		//2, 3step
		
		//이름이 ~~~인 사원의 부서명을 출력.
		String sql = " SELECT ename, dname " +
							" FROM emp JOIN dept ON emp.deptno = dept.deptno " +
							" WHERE ename = UPPER(?)";		//불완전한 SQL문장.		//오라클에서 대문자로 변환. //12행 자바에서 대문자로 변환 안했으므로
		//4step : PreparedStatement 생성.
		PreparedStatement pstmt = conn.prepareStatement(sql);		//아직까지 불완전한 SQL문장. But, 처음이자 마지막으로 문법검사, 객체검사 진행.
		//5step : 완전한 SQL문장으로 만들어서  실행.
		pstmt.setString(1, name);		//1 : 첫번째 ?에 name값을 넣음.	//완전한 SQL문장 됨.
		ResultSet rs = pstmt.executeQuery();		//주의!! 파라미터에 sql넣지 않음.
		//6step : ResultSet 가상테이블을 오픈해서 처리.
		boolean flag = rs.next();			//이름이 하나여서 루프 돌 필요 없음.	(동명이인이  없어서)
			//찾았다면 flag = true ,rs에 무언가 담김 //못찾았다면 flag = false
		if(flag) {	//찾았다면 
			String ename = rs.getString("ename");		//찾았다면  꺼내서 자바의 변수에 담아.
			String dname = rs.getString("dname");
			System.out.println(ename + "\t" + dname);  		//찾았다면 꺼내서 자바 변수에 담아서 출력.
		}else {		//못찾았다면 
			System.out.println("찾으시는 사원을 못 찾았습니다. 다시 실행해주세요. ");
		}
		
		DBClose.close(conn, pstmt, rs); 			//7 step	//PreparedStatement -> Statement이므로 자식인 PreparedStatement도 올 수 있음.
	}
}
