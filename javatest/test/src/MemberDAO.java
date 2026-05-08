import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberDAO {

    public void insertMember(int id, String pw, String name) {

        String sql =
                "INSERT INTO TBL_MEMBER(ID, PW, NAME, REG_DATE) "
              + "VALUES (?, ?, ?, SYSDATE)";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, id);
            pstmt.setString(2, pw);
            pstmt.setString(3, name);

            int rows = pstmt.executeUpdate();

            System.out.println("회원가입 성공");
            System.out.println("삽입된 행 수 : " + rows);

        } catch (SQLException e) {

            if(e.getErrorCode() == 1) {
                System.out.println("이미 사용중인 아이디입니다.");
            } else {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteMember(int id, String pw) {

        String sql =
                "DELETE FROM TBL_MEMBER "
              + "WHERE ID = ? AND PW = ?";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, id);
            pstmt.setString(2, pw);

            int rows = pstmt.executeUpdate();

            if(rows > 0) {
                System.out.println("회원탈퇴 성공");
                System.out.println("트리거 실행 완료");
            } else {
                System.out.println("아이디 또는 비밀번호 불일치");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}