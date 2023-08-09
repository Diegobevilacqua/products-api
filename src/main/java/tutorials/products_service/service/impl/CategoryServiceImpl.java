package tutorials.products_service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutorials.products_service.entity.Category;
import tutorials.products_service.exceptions.CategoryNotFoundException;
import tutorials.products_service.repository.CategoryRepository;
import tutorials.products_service.service.CategoryService;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        log.info("Retrieving categories...");
        List<Category> categories = categoryRepository.findAll();
        log.info("Categories retrieved successfully.");
        return categories;
    }

    @Override
    public Category getCategory(Long id) {
        log.info(String.format("Retrieving category %d...", id));
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        log.info(String.format("Product %d was retrieved successfully", category.getId()));
        return category;
    }

    @Override
    public Category saveCategory(Category category) {
        log.info(String.format("Saving category \"%s\"...", category.getDetail()));
        Category savedCategory = categoryRepository.save(category);
        log.info(String.format("Category named \"%s\" has been successfully saved.", category.getDetail()));
        return savedCategory;
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        log.info(String.format("Retrieving category named \"%s\"...", category.getDetail()));
        Category existingCategory = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        log.info(String.format("Product named \"%s\" has been successfully retrieved.", category.getDetail()));
        existingCategory.setDetail(category.getDetail());
        log.info(String.format("Saving category named \"%s\"...", existingCategory.getDetail()));
        Category savedCategory = categoryRepository.save(existingCategory);
        log.info(String.format("Category named \"%s\" has been successfully saved.", existingCategory.getDetail()));
        return savedCategory;
    }

    @Override
    public void deleteCategory(Long id) {
        log.info(String.format("Deleting category with id: %d...", id));
        categoryRepository.deleteById(id);
        log.info(String.format("Category with id: %d has been successfully deleted.", id));
    }
}
