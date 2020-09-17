import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//OUTER 조인.
//표준 FULL OUTER JOIN ~ ON = RIGHT JOIN + LEFT JOIN
//소속이 없는 사원, 사원이 한 명도 없는 부서 모두 출력.
public class JDBCDemo1 {
	public static void main(String[] args) throws SQLException{
		//2 step : Driver Loading
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		//2step 다른 방법 : Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//3step : DB에 Connection
		final String url = "jdbc:oracle:thin:@localhost:1521:XE";  //final : 상수 : 못바꿈.
		Properties info = new Properties();		//Map 계열.
		info.setProperty("user", "HR");
		info.setProperty("password", "hr");
		Connection conn = DriverManager.getConnection(url, info);
		
		//4step : Statement 생성.  (?에 들어갈게 없어서 Statement으로 생성)
		Statement stmt = conn.createStatement();
		
		//5step : Query실행.
		String sql = " SELECT  employee_id, first_name, e.department_id, d.department_id, department_name  " +
							" FROM  employees e FULL OUTER JOIN departments d ON e.department_id = d.department_id ";		//Query 세팅.
		ResultSet rs = stmt.executeQuery(sql);		//Query 실행.
		
		//6step : ResultSet 장바구니를 뽑아내자.
		System.out.println("카운트\t사원번호\t사원이름\t사원쪽 부서번호\t부서쪽 부서번호\t부서이름");
		int count = 0;
		while(rs.next()) {
			int employee_id = rs.getInt("employee_id");
			String first_name = rs.getString("first_name");
			int department_id1 = rs.getInt(3);				//변수이름 유사하므로 인덱스 넘버로 표기.
			int department_id2 = rs.getInt(4);
			String department_name = rs.getString("department_name");
			System.out.println(++count + " : " + employee_id + "\t" + first_name  + "\t" + department_id1 + "\t" + department_id2 + "\t" + department_name);
		}
		
		//7step : close  //역순으로 닫음.
		if(rs !=null) rs.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close();
	}
}
