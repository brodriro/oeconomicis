package me.brian.domain.entities;

public class Balance {

    private int id;
    private int idUser;
    private double total;
    private String date;

    public Balance() {
    }

    public Balance(int id, int idUser, double total, String date) {
        this.id = id;
        this.idUser = idUser;
        this.total = total;
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public double getTotal() {
        return total;
    }

    public String getDate() {
        return date;
    }
}
