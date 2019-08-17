package r.brian.data.local.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class HistoryDatabase extends RealmObject {

    @PrimaryKey
    private int id;
    private int idUser;
    private int idCategory;
    private int amount;
    private String category_name;
    private String date_time;
    private String description;

    public HistoryDatabase() {
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
