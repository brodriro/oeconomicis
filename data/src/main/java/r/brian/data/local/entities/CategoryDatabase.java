package r.brian.data.local.entities;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import me.brian.domain.entities.Category;

public class CategoryDatabase extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private String code;

    public CategoryDatabase() {
    }

    public CategoryDatabase(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
    }

    public CategoryDatabase(int id, String name, String code) {
        this.id = id;
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

    public Category toCategory() {
        return new Category(
                getId(),
                getName(),
                getCode()
        );
    }

    public static List<Category> toCategories(List<CategoryDatabase> dbCategories) {
        List<Category> list = new ArrayList<>();
        for (CategoryDatabase row : dbCategories) list.add(row.toCategory());

        return list;
    }
}
