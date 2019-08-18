package me.brian.domain.entities;

import java.util.Date;

public class Balance {

    private int id;
    private int idUser;
    private double total;
    private Date date;

    public Balance() {
    }

    public Balance(int id, int idUser, double total, Date date) {
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

    public void setDate(Date date) {
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

    public Date getDate() {
        return date;
    }
}
