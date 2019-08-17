package me.brian.domain.entities;

public class Category {
    private int id;
    private String name;
    private String code;

    public Category() {
    }

    public Category(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
    public Category(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return getName();
    }
}
