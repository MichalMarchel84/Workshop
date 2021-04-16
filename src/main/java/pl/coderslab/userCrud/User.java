package pl.coderslab.userCrud;

public class User {

    private int id;
    private String email;
    private String userName;
    private String password;

    public User() {
    }

    public User(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = DbUtil.hashPasswd(password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s", id, email, userName, password);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User user = (User) o;
            return this.id == user.id;
        } else return false;
    }

    public boolean deepEquals(User user) {
        boolean res = true;
        res &= this.id == user.id;
        res &= this.email.equals(user.email);
        res &= this.userName.equals(user.userName);
        res &= this.password.equals(user.password);
        return res;
    }
}
