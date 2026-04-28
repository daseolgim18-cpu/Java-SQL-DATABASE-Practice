import java.sql.Connection; // DB 연결 객체
import java.sql.DriverManager; // DB 연결을 만들어주는 클래스
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; // DB 관련 에러 처리
import java.sql.Blob;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.InputStream;

public class JdbcSelect2{
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
                        "SELECT bno, btitle, bcontent, bwriter, bdate, bfilename, bfiledata "+
                        "FROM boards " +
                        "WHERE bwriter=?"; // 문자열을 이어 붙일 때 단어가 붙는 걸 방지하려고 공백을 넣는 것
            
            // PreparedStatement 열기 및 값 저장
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "winter");

            // SQL문 실행 후, ResultSet을 통해 데이터 읽기
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                Boards board = new Boards();
                board.setBno(rs.getInt("bno"));
                board.setBtitle(rs.getString("btitle"));
                board.setBcontent(rs.getString("bcontent"));
                board.setBwriter(rs.getString("bwriter"));
                board.setBdate(rs.getDate("bdate"));
                board.setBfilename(rs.getString("bfilename"));
                board.setBfiledata(rs.getBlob("bfiledata"));

                // 콘솔에 출력
                System.out.println(board.getBno() + " " + 
                                    board.getBtitle() + " " + 
                                    board.getBcontent() + " " + 
                                    board.getBwriter() + " " +
                                    board.getBdate() + " " +
                                    board.getBfilename());

                // 파일로 저장
                Blob blob = board.getBfiledata();
                if(blob != null) {
                    InputStream is = blob.getBinaryStream();
                    OutputStream os = new FileOutputStream("C:/Temp/"+ board.getBfilename());
                    is.transferTo(os);
                    os.flush();
                    os.close();
                    is.close();
                }
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

