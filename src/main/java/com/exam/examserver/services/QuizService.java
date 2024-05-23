package com.exam.examserver.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.examserver.model.exam.Category;
import com.exam.examserver.model.exam.Quiz;

@Service
public interface QuizService {
    public Quiz addQuiz(Quiz quiz);
    public Quiz updateQuiz(Quiz quiz);
    public Set<Quiz>getQuizzes();
    public Quiz getQuiz(Long qid);
    public void deleteQuiz(Long qid);
    public List<Quiz> getQuizzesOfCategory(Category category);
    public List<Quiz>getActiveQuizzes();
    public List<Quiz>getActiveQuizzesOfCategory(Category category);
}
