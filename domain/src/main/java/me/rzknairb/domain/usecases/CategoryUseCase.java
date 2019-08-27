package me.rzknairb.domain.usecases;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.rzknairb.domain.entities.Category;
import me.rzknairb.domain.repositories.CategoryLocalRepositoryImp;

@Singleton
public class CategoryUseCase {

    @Inject
    CategoryLocalRepositoryImp categoryLocalRepositoryImp;

    @Inject
    CategoryUseCase() {
    }

    public Single<List<Category>> getAll() throws Exception {
        return categoryLocalRepositoryImp.getAllCategories();
    }

    public Single<List<Category>> createCategory(String category, String code) throws Exception{
        return categoryLocalRepositoryImp.createCategory(new Category(category, code));
    }
}
