import java.util.Scanner;

public class MainEx {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MemberDAO dao = new MemberDAO();

        while(true){
            System.out.println("===== 회원가입 =====");

            System.out.print("아이디 입력 : ");
            int id = sc.nextInt();

            System.out.print("비밀번호 입력 : ");
            String pw = sc.next();

            System.out.print("이름 입력 : ");
            String name = sc.next();

            dao.insertMember(id, pw, name);

            System.out.println();

            System.out.print("작업을 계속 하시겠습니까? : ");
            String option = sc.next();

            if(option.equals("no")){
                break;
            }
            else if(option.equals("yes"))
                continue;
            
        }

        while(true){
            System.out.println("===== 회원탈퇴 =====");

            System.out.print("삭제할 아이디 입력 : ");
            int deleteId = sc.nextInt();
    
            System.out.print("비밀번호 입력 : ");
            String deletePw = sc.next();
    
            dao.deleteMember(deleteId, deletePw);

            System.out.print("작업을 계속 하시겠습니까? : ");
            String option1 = sc.next();

            if(option1.equals("no")){
                break;
            }
            else if(option1.equals("yes"))
                continue;
        }
       
        sc.close();
    }
    
}