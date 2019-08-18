package r.brian.data.local.entities;

import androidx.annotation.NonNull;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import me.brian.domain.entities.Balance;
import me.brian.domain.entities.User;

public class BalanceDatabase extends RealmObject {

    @PrimaryKey
    private int id;
    private int idUser;
    private double total;
    private Date date;

    public BalanceDatabase() {
    }

    public BalanceDatabase(int id, int idUser, double total, Date date) {
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

    @NonNull
    @Override
    public String toString() {
        return String.format("id:%s, idUser:%s, total:%s, date:%s",
                getId(), getIdUser(), getTotal(), getDate());
    }

    public Balance toBalance() {
        return new Balance(
                getId(),
                getIdUser(),
                getTotal(),
                getDate()
        );
    }
}
