package com.exam.examserver.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.examserver.model.exam.Category;
import com.exam.examserver.model.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz,Long>{

    public List<Quiz> findByCategory(Category category);

    public List<Quiz> findByActive(boolean active);

    public List<Quiz> findByCategoryAndActive(Category category, boolean active);
    
}
