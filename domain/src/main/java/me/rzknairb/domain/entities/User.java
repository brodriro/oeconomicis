package me.rzknairb.domain.entities;

public class User {
    private int id;
    private String name;
    private String lastname;
    private String age;
    private String username;
    private String password;

    public User() {
    }

    public User( String name, String lastname, String age, String username, String password) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.username = username;
        this.password = password;
    }
    public User(int id, String name, String lastname, String age, String username, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return String.format("ID:%s, FullName:%s, Username:%s, Age:%s, Password:%s",
                getId(), getName() + " " +getLastname(),getUsername(), getAge(), getPassword());
    }
}
