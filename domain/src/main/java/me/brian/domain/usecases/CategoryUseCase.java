package me.brian.domain.usecases;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import me.brian.domain.entities.Category;
import me.brian.domain.repositories.CategoryDatabaseRepository;

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
