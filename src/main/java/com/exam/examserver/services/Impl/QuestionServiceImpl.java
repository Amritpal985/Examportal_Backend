package com.exam.examserver.services.Impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.examserver.model.exam.Question;
import com.exam.examserver.model.exam.Quiz;
import com.exam.examserver.repo.QuestionRepository;
import com.exam.examserver.services.QuestionService;


@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return this.questionRepository.save(question);
    }
    
    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return new LinkedHashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Question getQuestion(Long qid) {
        return this.questionRepository.findById(qid).get();
    }

    @Override
    public Set<Question> getQuesitonsOfQuiz(Quiz quiz) {
        return this.questionRepository.findByQuiz(quiz);
    }

    @Override
    public void deleteQuestion(Long qid) {
        this.questionRepository.deleteById(qid);
    }

    @Override
    public Question get(Long quesId) {
        return this.questionRepository.findById(quesId).get();
    }
    
}
