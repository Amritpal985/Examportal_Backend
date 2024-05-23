package com.exam.examserver.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.examserver.model.exam.Question;
import com.exam.examserver.model.exam.Quiz;

@Service
public interface QuestionService {
    public Question addQuestion(Question question);
    public Question updateQuestion(Question question);
    public Set<Question>getQuestions();
    public Question getQuestion(Long qid);
    public Set<Question>getQuesitonsOfQuiz(Quiz quiz);
    public void deleteQuestion(Long qid);
    public Question get(Long quesId);

}
