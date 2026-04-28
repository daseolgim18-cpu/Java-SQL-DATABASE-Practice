import java.sql.CallableStatement;
import java.sql.Connection; // DB 연결 객체
import java.sql.DriverManager; // DB 연결을 만들어주는 클래스
import java.sql.SQLException; // DB 관련 에러 처리
import java.sql.Types;


public class JdbcFunctionCall2{
    public static void main(String[] args) {
        Connection conn = null; // 연결이 안 된 상태
        try {
            // JDBC Driver 등록
            Class.forName("oracle.jdbc.OracleDriver");

            // 연결하기(내 컴퓨터(localhost)에 있는 오라클 XE DB에 system 계정으로 접속)
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", 
                "system", 
                "1234"
            );

            //매개변수화된 호출문 작성과 CallableStatement 얻기
            String sql = "{call user_create(?, ?, ?, ?, ?, ?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            
            // ?값 지정 및 리턴 타입 지정
            cstmt.setString(1, "summer");
            cstmt.setString(2, "한여름");
            cstmt.setString(3, "12345");
            cstmt.setInt(4, 26);
            cstmt.setString(5, "summer@eaxmple.com");
            cstmt.registerOutParameter(6, Types.INTEGER);

            // 함수 실행 및 리턴값 얻기
            cstmt.execute();
            int rows = cstmt.getInt(6);
            System.out.println("저장된 행의 수: " + rows);

            //CallableStatement 닫기
            cstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    // 연결 끊기
                    conn.close();
                    System.out.println("연결 끊기");
            } catch (SQLException e) {}
            }
        }
    }    
}

