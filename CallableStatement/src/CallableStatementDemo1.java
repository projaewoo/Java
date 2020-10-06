import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

//emp table에서 새로운 사원의 정보를 이름, 업무, 매니저, 급여를 입력받아 등록하는 emp_input 프로시저를 생성.
//단, 부서번호는 매니저의 부서 번호와 동일하게 하고 보너스는 SALESMAN은 0을 그 외는 NULL을 입력하라.

//Oracle에서 Stored Procedure 생성.
//Java에서 Stored Procedure 호출.
public class CallableStatementDemo1 {
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.print("이름 : ");			String ename = scan.next();
		System.out.print("업무 : ");			String job = scan.next();
		System.out.print("매니저 : ");		int mgr = scan.nextInt();
		System.out.print("급여 : ");			double sal = scan.nextDouble();
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection();		//2, 3step
		//4step : Statement생성.
		String sql = "{ call emp_input(?, ?, ?, ?)}";		//파라미터 4개.	//불완전한 SQL문장.
		CallableStatement cstmt = conn.prepareCall(sql);		//여전히 불완전한 SQL문장.
		cstmt.setString(1, ename);
		cstmt.setString(2, job);
		cstmt.setInt(3, mgr);
		cstmt.setDouble(4, sal);		//비로소 완전한 SQL문장.
		//5step: SQL 실행.
		int row = cstmt.executeUpdate();		//SELECT면 executeQuery()사용.
		if(row == 1) System.out.println("입력 성공");
		else System.out.println("입력 실패");
		//6step : close
		if(cstmt != null)	cstmt.close();
		DBClose.close(conn); 		//6step.
	}
}
