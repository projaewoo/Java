import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

//사원 수 몇명인지 알아오는 StoredProcedure (OUT모드) 호출.
//OUT형 파라미터를 어떻게 처리할 것인가.
public class CallableStatementDemo3 {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection();		//2, 3step.
		String sql = "{ call sp_emp_count(?)}";		//물음표 : OUT용.
		CallableStatement cstmt = conn.prepareCall(sql);		//4step : Statement 종류 선택. (PreparedStatement? CallableStatement?)
		//OUT용 파라미터 : set...() 메소드 X!!   registerOutParameter()메소드 사용!!
		cstmt.registerOutParameter(1, Types.NUMERIC);		//Types.NUMBER = Oracle의 NUMBER
		cstmt.execute();	//5step : 실행. 	OUT모드 : execute()메소드 사용!
		int count = cstmt.getInt(1);		//첫번째 물음표를 int로 가져옴.
		System.out.println("이 회사의 사원 수는 " + count + "명 입니다.");
		
		//6step : close.
		if(cstmt != null) cstmt.close();
		DBClose.close(conn);		//6step.
	}
}