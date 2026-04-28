import java.sql.Connection; // DB 연결 객체
import java.sql.DriverManager; // DB 연결을 만들어주는 클래스
import java.sql.PreparedStatement;
import java.sql.SQLException; // DB 관련 에러 처리

public class JabcDelete2{
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

            //매개변수화된 SQL문 작성
            String sql ="DELETE FROM boards WHERE btitle=?";
            
            // PreparedStatement 열기 및 값 저장
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "눈오는 날");

            //SQL문 실행
            int rows = pstmt.executeUpdate();
            System.out.println("삭제된 행 수: " + rows);
            
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
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

