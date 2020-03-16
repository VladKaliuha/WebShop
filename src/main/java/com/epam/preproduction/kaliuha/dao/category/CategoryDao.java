package com.epam.preproduction.kaliuha.dao.category;

import com.epam.preproduction.kaliuha.dao.Dao;
import com.epam.preproduction.kaliuha.entity.impl.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao extends Dao {

    Optional<Category> getCategory(long id);

    List<Category> getAllCategories();
}
