package entities;

import java.util.Objects;

/**
 *
 * @author aymen
 */
public class User {
    private int id ;
    private String username ;
    private String email ;
    private String password ;
    private String confirmPassword ;
    private String numTel;
    private String cinPasseport;
    private String activationToken;
    private String resetToken;
    private String roles;

    public User(String username, String email, String password, String numTel, String cinPasseport, String roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.cinPasseport = cinPasseport;
        this.roles = roles;
    }
    
    public User(int id, String username, String email, String password, String numTel, String cinPasseport, String roles) {
        this.id=id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.cinPasseport = cinPasseport;
        this.roles = roles;
    }

    public User(int id, String username, String email, String password, String numTel, String cinPasseport) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.cinPasseport = cinPasseport;
    }

    public User(int id,  String email, String username, String numTel, String cinPasseport) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.numTel = numTel;
        this.cinPasseport = cinPasseport;
    }
    
    
    
    public User(int id, String username, String email, String password, String confirmPassword, String numTel, String cinPasseport, String activationToken, String resetToken, String roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.numTel = numTel;
        this.cinPasseport = cinPasseport;
        this.activationToken = activationToken;
        this.resetToken = resetToken;
        this.roles = roles;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getCinPasseport() {
        return cinPasseport;
    }

    public void setCinPasseport(String cinPasseport) {
        this.cinPasseport = cinPasseport;
    }

    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", numTel='" + numTel + '\'' +
                ", cinPasseport='" + cinPasseport + '\'' +
                ", activationToken='" + activationToken + '\'' +
                ", resetToken='" + resetToken + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(confirmPassword, user.confirmPassword) && Objects.equals(numTel, user.numTel) && Objects.equals(cinPasseport, user.cinPasseport) && Objects.equals(activationToken, user.activationToken) && Objects.equals(resetToken, user.resetToken) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, confirmPassword, numTel, cinPasseport, activationToken, resetToken, roles);
    }  
}
