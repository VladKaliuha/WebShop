package com.epam.preproduction.kaliuha.service.category;

import com.epam.preproduction.kaliuha.entity.impl.Category;
import com.epam.preproduction.kaliuha.service.Service;

import java.util.List;
import java.util.Optional;

public interface CategoryService extends Service {
    /**
     * Return category by id
     *
     * @param id category id
     * @return suitable category
     */
    Optional<Category> getCategory(long id);

    /**
     * Return all categories
     *
     * @return categories list
     */
    List<Category> getAllCategories();
}
