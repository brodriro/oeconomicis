package r.brian.data.local.entities;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import me.rzknairb.domain.entities.History;

public class HistoryDatabase extends RealmObject {

    @PrimaryKey
    private int id;
    private int idUser;
    private int idCategory;
    private double amount;
    private String category_name;
    private Date date_time;
    private String description;

    public HistoryDatabase() {
    }

    public HistoryDatabase(int id, int idUser, int idCategory, double amount, String category_name, Date date_time, String description) {
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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setDate_time(Date date_time) {
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

    public double getAmount() {
        return amount;
    }

    public String getCategory_name() {
        return category_name;
    }

    public Date getDate_time() {
        return date_time;
    }

    public String getDescription() {
        return description;
    }

    public History toHistory() {
        return new History(
                getId(), getIdUser(), getIdCategory(), getAmount(), getCategory_name(), getDate_time(), getDescription()
        );
    }
    public static List<History> toHistores(List<HistoryDatabase> list){
        List<History> mList = new ArrayList<>();

        for (HistoryDatabase row : list) mList.add(row.toHistory());

        return mList;
    }

    @NonNull
    @Override
    public String toString() {

        return String.format("Id:%s, IdUser:%s, Amount:%s, Date:%s, Category:%s",
                getId(), getIdUser(), getAmount(), getDate_time(), getCategory_name());
    }
}
