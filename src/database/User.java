package database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Olcha on 20.06.2016.
 */
@Entity
@Table(name = "users")
public class User {
    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    @Column(name = "id")
    private String id;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
    public String getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(String id) {
        this.id = id;
    }


}
