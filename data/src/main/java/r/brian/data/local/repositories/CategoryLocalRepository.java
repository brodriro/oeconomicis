package r.brian.data.local.repositories;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import me.brian.domain.entities.Category;
import me.brian.domain.repositories.CategoryDatabaseRepository;
import r.brian.data.Utils;
import r.brian.data.local.entities.CategoryDatabase;

public class CategoryLocalRepository implements CategoryDatabaseRepository {

    private final RealmConfiguration realmConfiguration;

    @Inject
    public CategoryLocalRepository(RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
    }

    @Override
    public Single<List<Category>> getAllCategories() throws Exception {
        try (Realm realm = Realm.getInstance(realmConfiguration)) {
            RealmResults<CategoryDatabase> results = realm.where(CategoryDatabase.class).findAll();
            if (results == null) return null;
            return Single.just(CategoryDatabase.toCategories(results));
        }
    }

    @Override
    public Single<List<Category>> createCategory(final Category category) throws Exception {
        try (Realm realmInstance = Realm.getInstance(realmConfiguration)) {
            Number id = realmInstance.where(CategoryDatabase.class).max("id");
            int nextId = Utils.generateId(id);
            category.setId(nextId);
            realmInstance.executeTransaction(realm -> realm.insertOrUpdate(new CategoryDatabase(category)));

            return getAllCategories();
        }
    }
}
