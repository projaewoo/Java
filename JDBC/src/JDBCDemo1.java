import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.example.libs.DBClose;

import oracle.jdbc.OracleDriver;

public class JDBCDemo {
	public static void main(String[] args) throws SQLException {
		DriverManager.registerDriver(new OracleDriver());
		Properties info = new Properties();
		info.setProperty("user", "HR");
		info.setProperty("password", "hr");
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, info);
			System.out.println(conn);
		} catch (SQLException e) {
			System.out.println("Connection Failuer");
		}
		
		
//		DBConnection dbconn = new DBConnection();
//		Connection conn = dbconn.getConnection();		//2, 3step.
//		System.out.println(conn);  //확인 차 출력.
		
		String sql = "SELECT employee_id, first_name, hire_date, e.department_id, department_name, city, state_province, country_name " +
							" FROM employees e INNER JOIN departments d ON e.department_id = d.department_id " +
							" INNER JOIN locations lo ON d.location_id = lo.location_id " +
							" INNER JOIN countries co ON lo.country_id = co.country_id " +
							" WHERE e.department_id IN (?, ?, ?, ?) ";	//불완전한 SQL문장.
		PreparedStatement pstmt = conn.prepareStatement(sql);		//4step : PreparedStatement 생성 // 아직도 불완전한 SQL문 //여기서 처음이자 마지막으로 문법검사, 객체검사 수행.
		pstmt.setInt(1, 10);	//setInt : 부서번호 숫자이므로. //1 : 첫번째 ?에 //10: 첫번째 deptno에 10을 넣음.
		pstmt.setInt(2, 20);	pstmt.setInt(3, 30); 	pstmt.setInt(4, 40);		//비로소 완전한 SQL문장.
		ResultSet rs = pstmt.executeQuery();	//5step	//주의!! 파라미터로 SQL넣지말자. //22행 진행했으므로.
		//6step : ResultSet가상테이블 처리.
		int count = 0;
		while(rs.next()) {				//커서 첫번째 줄 맨 앞에 있음 -> 다음 두번째 줄 맨 앞에 있음 -> 다음 세번째 줄 맨 앞 -> ... -> 마지막 줄 맨 앞. -> 다음 줄 없으면 끝.
			int employee_id = rs.getInt("employee_id");					//DB에서 데이터 타입 : NUMBER(6, 0)이므로 int.
			String department_name = rs.getString("department_name");		//DB에서 VARCHAR2이므로 String
			String city = rs.getString("city");
			String country_name = rs.getString("country_name");
			System.out.println(++count + "번째 줄 : " + employee_id + "\t" + department_name + "\t" + city + "\t" + country_name);
		}
		DBClose.close(conn, pstmt, rs);  //7step.
		
	}
}
