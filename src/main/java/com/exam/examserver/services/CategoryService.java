package com.exam.examserver.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.examserver.model.exam.Category;

@Service
public interface CategoryService {
    public Category addCategory(Category category);
    public Category updateCategory(Category category);
    public Set<Category> getCategories();
    public Category getCategory(Long cId);
    public void deleteCategory(Long cId);
}
