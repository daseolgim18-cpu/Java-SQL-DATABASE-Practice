import java.sql.Connection; // DB 연결 객체
import java.sql.DriverManager; // DB 연결을 만들어주는 클래스
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; // DB 관련 에러 처리

public class JdbcSelect{
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
            String sql = "" +
                        "SELECT userid, username, userpassword, userage, useremail "+
                        "FROM users " +
                        "WHERE userid=?"; // 문자열을 이어 붙일 때 단어가 붙는 걸 방지하려고 공백을 넣는 것
            
            // PreparedStatement 열기 및 값 저장
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "winter2");

            // SQL문 실행 후, ResultSet을 통해 데이터 읽기
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("userid"));
                user.setUserName(rs.getString("username"));
                user.setUserPassword(rs.getString("userpassword"));
                user.setUserAge(rs.getString("userage"));
                user.setUserEmail(rs.getString("useremail"));
                System.out.println(user);
            } else {
                System.out.println("사용자 아이디가 존재하지 않음");
            }
            rs.close();

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

