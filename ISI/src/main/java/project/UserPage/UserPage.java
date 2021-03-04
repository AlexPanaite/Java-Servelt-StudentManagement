package project.UserPage;

public class UserPage {
    private int id;
    private String username;
    private String pass;
    private String confpass;
    private String date;
    private String role;

    public UserPage(int id, String username, String pass, String confpass, String date, String role) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.confpass = confpass;
        this.date = date;
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setConfpass(String confpass) {
        this.confpass = confpass;
    }

    public String getConfpass() {
        return confpass;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public String getDate() {
        return date;
    }

    public String getRole() {
        return role;
    }
}
