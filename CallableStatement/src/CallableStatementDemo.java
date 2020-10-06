import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

//사번을 받아 삭제하는 프로시저 호출.	//실제 DB에 저장되어 있는 Stored Procedure를 Java에서 호출함.

//CallableStatement : 특정 DB에서만 사용 가능 (PreparedStatement(부모 클래스)만 사용하므로 PreparedStatement지원 안하는 DB에서 사용 불가)
																//Statement(할머니) - PreparedStatement(아빠) - CallableStatement(자손)		//따라서 CallableStatement : Statement, PreparedStatement 메소드까지 사용 가능.
//이번에는 자바 쪽에서 커밋하는 상황.
public class CallableStatementDemo {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제하려는 사원의 번호를 입력해주세요 : ");
		int empno = scan.nextInt();
		
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection();			//2, 3step.
		CallableStatement cstmt = null;
		try{
			conn.setAutoCommit(false); 		//false : 자동커밋 X.
			String sql = "{ call sp_emp_delete(?) }";		//sp_emp_delete이름을 가진 Stored Procedure를 호출.
			cstmt = conn.prepareCall(sql);		//아직까지 불완전한 SQL문장.
			cstmt.setInt(1, empno);		//비로소 완전한 SQL문장.		//입력받은 empno을 첫번째 ?에 넣음.
			int row = cstmt.executeUpdate();		//SELECT만 executeQuery()메소드 사용.
			if(row == 1)	{
				System.out.println("삭제 성공");
				conn.commit();			//Exception 발생시킴.  //성공하면 여기서 커밋.
			}
			else {
				throw new SQLException("삭제 실패");	//SQLException 던짐.
			}
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
			try {
				conn.rollback();				//SQLException 발생했을 때, rollback. //Exception 발생하므로 다시 try-catch 사용.
			} catch (SQLException e) {}
		}finally {			//Exception여부 상관없이 진행해야할 문장들.  //무조건 진행해야할 문장들.
			try {
				if(cstmt != null) cstmt.close();			//Exception 발생.
				DBClose.close(conn);
			}catch (SQLException e) {}
			
		}
		
		DBClose.close(conn);		//6step.
	}
}
