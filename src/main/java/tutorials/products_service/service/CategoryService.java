package tutorials.products_service.service;


import tutorials.products_service.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    Category saveCategory(Category category);

    Category getCategory(Long id);

    Category updateCategory(Long id, Category category);

    void deleteCategory(Long id);
}

