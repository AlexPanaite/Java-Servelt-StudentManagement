package project.Login;

public class Login {
    private String username;
    private String pass;
    private String confpass;
    private String role;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setConfpass(String confpass) {
        this.confpass = confpass;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public String getConfpass() {
        return confpass;
    }

    public String getRole() {
        return role;
    }
}
