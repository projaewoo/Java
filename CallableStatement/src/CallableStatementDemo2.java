import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

//사번을 입력받아 사원이름과 봉급을 검색.
//OUT형 파라미터를 어떻게 처리할 것인가.
public class CallableStatementDemo2 {
	public static void main(String[] args) throws SQLException {
		DBConnection dbconn = new DBConnection();
		Connection conn = dbconn.getConnection();		//2, 3step.
		String sql = "{ call sp_emp_select(?, ?, ?) }";		//첫번째 파라미터 : 입력할 값	//두번째, 세번째 파라미터 : 나오는 값.	//아직까지 불완전한 SQL문장.
		CallableStatement cstmt = conn.prepareCall(sql);		//여전히 불완전한 SQL문장.
		//주의!!! IN모드일때만 set...()메소드 사용!!
		cstmt.setInt(1, 7788);		//첫번째 ? : empno : NUMBER : int.	//IN모드이므로 set...()메소드 사용.
		//OUT모드일때는 registerOutParameter() 사용!!
		cstmt.registerOutParameter(2, Types.VARCHAR); 		//두번째 ? : ename : VARCHAR타입.
		cstmt.registerOutParameter(3, Types.NUMERIC);			//세번째 ? : sal	  : NUMBER타입.		//비로소 완전한 SQL문장.
		//5step : Statement 실행.
		cstmt.execute();		//주의!! OUT모드 : executeUpdate() or executeQuery()가 아님!!
		String ename = cstmt.getString(2);		//나오는건 값을 가져와야함.	//두번째 물음표를 string으로 가져옴.
		Double sal = cstmt.getDouble(3);		//나오는건 값을 가져와야함.	//세번째 물음표를 Double로 가져옴.
		System.out.println("사원번호 : 7788");
		System.out.println("사원명 : " + ename);
		System.out.println("봉급 : " + sal);
		
		//6step : close.
		if(cstmt != null) cstmt.close();
		DBClose.close(conn);		//6step.
	}
}