package r.brian.data.local.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import me.brian.domain.entities.User;

public class UserDatabase extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private String lastname;
    private String age;
    private String username;
    private String password;

    public UserDatabase() {
    }

    public UserDatabase(int id, String name, String lastname, String age, String username, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public UserDatabase(User user) {
        this.setId(user.getId());
        this.setName(user.getName());
        this.setPassword(user.getPassword());
        this.setUsername(user.getUsername());
    }

    public User toUser() {
        User user = new User();
        user.setId(this.getId());
        user.setName(this.getName());
        user.setPassword(this.getPassword());
        user.setUsername(this.getUsername());
        user.setLastname(this.getLastname());
        user.setAge(this.getAge());
        return user;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
