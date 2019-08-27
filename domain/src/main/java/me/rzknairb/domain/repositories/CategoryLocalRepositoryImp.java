package me.rzknairb.domain.repositories;

import java.util.List;

import io.reactivex.Single;
import me.rzknairb.domain.entities.Category;

public interface CategoryLocalRepositoryImp {

    Single<List<Category>> getAllCategories() throws Exception;

    Single<List<Category>> createCategory(Category category) throws Exception;
}
