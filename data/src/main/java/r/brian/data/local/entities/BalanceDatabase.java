package r.brian.data.local.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BalanceDatabase extends RealmObject {

    @PrimaryKey
    private int id;
    private int idUser;
    private double total;
    private String date;

    public BalanceDatabase() {
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
