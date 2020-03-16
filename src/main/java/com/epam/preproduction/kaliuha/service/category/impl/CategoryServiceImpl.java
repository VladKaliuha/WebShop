package com.epam.preproduction.kaliuha.service.category.impl;

import com.epam.preproduction.kaliuha.dao.category.CategoryDao;
import com.epam.preproduction.kaliuha.entity.impl.Category;
import com.epam.preproduction.kaliuha.service.category.CategoryService;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Optional<Category> getCategory(long id) {
        return categoryDao.getCategory(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }
}
