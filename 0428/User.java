public class User {
    private String userId;
    private String userName;
    private String userPassword;
    private String userAge;
    private String userEmail;

    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserId(){
        return userId;
    }

    public String getUserName(){
        return userName;
    }

    public String getUserPassword(){
        return userPassword;
    }

    public String getUserAge(){
        return userAge;
    }

    public String getUserEmail(){
        return userEmail;
    }

}
