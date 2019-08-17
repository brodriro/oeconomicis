package me.brian.domain.repositories;

import java.util.List;

import io.reactivex.Single;
import me.brian.domain.entities.Category;

public interface CategoryDatabaseRepository {

    Single<List<Category>> getAllCategories() throws Exception;

    Single<List<Category>> createCategory(Category category) throws Exception;
}
