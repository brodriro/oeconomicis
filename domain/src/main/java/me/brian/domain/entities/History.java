package me.brian.domain.entities;

public class History {

    private int id;
    private int idUser;
    private int idCategory;
    private int amount;
    private String category_name;
    private String date_time;
    private String description;

    public History() {
    }

    public History(int id, int idUser, int idCategory, int amount, String category_name, String date_time, String description) {
        this.id = id;
        this.idUser = idUser;
        this.idCategory = idCategory;
        this.amount = amount;
        this.category_name = category_name;
        this.date_time = date_time;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public int getAmount() {
        return amount;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getDate_time() {
        return date_time;
    }

    public String getDescription() {
        return description;
    }
}