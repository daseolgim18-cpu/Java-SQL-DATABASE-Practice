import java.sql.Connection; // DB 연결 객체
import java.sql.DriverManager; // DB 연결을 만들어주는 클래스
import java.sql.PreparedStatement;
import java.sql.SQLException; // DB 관련 에러 처리
import java.io.FileInputStream;

public class JdbcUpdate1{
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
            String sql = new StringBuilder()
                        .append("UPDATE boards SET ")
                        .append("btitle=?, ")
                        .append("bcontent=?, ")
                        .append("bfilename=?, ")
                        .append("bfiledata=? ")
                        .append("WHERE bno=?")
                        .toString();
            
            // PreparedStatement 열기 및 값 저장
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "눈사람");
            pstmt.setString(2, "눈으로 만든 사람");
            pstmt.setString(3, "city.jpg");
            pstmt.setBlob(4, new FileInputStream("src/city.jpg"));
            pstmt.setInt(5, 1); // "WHERE bno = 1 조건으로 업데이트

            //SQL문 실행
            int rows = pstmt.executeUpdate();
            System.out.println("수정된 행 수: " + rows);

            // PreparedStatement 닫기
            pstmt.close();
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

