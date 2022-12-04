package com.reagente.manager.manager.service;

import com.reagente.manager.manager.entity.Category;
import com.reagente.manager.manager.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategory() {return categoryRepository.findAll();}

    public Category getCategoryDetalhes(Integer id){
        for (Category category : getCategory()){
            if (category.getId().equals(id)){
                return category;
            }
        }
        return null;
    }
}
