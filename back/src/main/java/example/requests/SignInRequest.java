package example.requests;

public class SignInRequest {

    private String login;
    private String password;

    public SignInRequest() {
    }

    public SignInRequest(String usernameOrEmail, String password) {
        this.login = usernameOrEmail;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
