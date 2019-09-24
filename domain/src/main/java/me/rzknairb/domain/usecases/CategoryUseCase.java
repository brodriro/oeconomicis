package me.rzknairb.domain.usecases;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.rzknairb.domain.entities.Category;
import me.rzknairb.domain.repositories.CategoryLocalRepository;

@Singleton
public class CategoryUseCase {

    @Inject
    CategoryLocalRepository categoryLocalRepository;

    @Inject
    CategoryUseCase() {
    }

    public Single<List<Category>> getAll() throws Exception {
        return categoryLocalRepository.getAllCategories();
    }

    public Single<List<Category>> createCategory(String category, String code) throws Exception{
        return categoryLocalRepository.createCategory(new Category(category, code));
    }
}
