import java.sql.CallableStatement;
import java.sql.Connection; // DB 연결 객체
import java.sql.DriverManager; // DB 연결을 만들어주는 클래스
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; // DB 관련 에러 처리
import java.sql.Types;

public class JdbcFunctionCall{
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
            String sql = "{? = call user_login(?, ?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            
            // ?값 지정 및 리턴 타입 지정
            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.setString(2, "winter2");
            cstmt.setString(3, "12345");

            // 함수 실행 및 리턴값 얻기
            cstmt.execute();
            int result = cstmt.getInt(1);

            //CallableStatement 닫기
            cstmt.close();

            // 로그인 결과 (Switch Expressions 이용)
            String message = switch(result) {
                case 0 -> "로그인 성공";
                case 1 -> "비밀번호가 틀림";
                default -> "아이디가 존재하지 않음";
            };
            System.out.println(message);

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

