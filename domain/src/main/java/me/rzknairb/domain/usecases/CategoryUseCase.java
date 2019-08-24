package me.rzknairb.domain.usecases;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.rzknairb.domain.entities.Category;
import me.rzknairb.domain.repositories.CategoryDatabaseRepository;

@Singleton
public class CategoryUseCase {

    @Inject
    CategoryDatabaseRepository categoryDatabaseRepository;

    @Inject
    CategoryUseCase() {
    }

    public Single<List<Category>> getAll() throws Exception {
        return categoryDatabaseRepository.getAllCategories();
    }

    public Single<List<Category>> createCategory(String category, String code) throws Exception{
        return categoryDatabaseRepository.createCategory(new Category(category, code));
    }
}
