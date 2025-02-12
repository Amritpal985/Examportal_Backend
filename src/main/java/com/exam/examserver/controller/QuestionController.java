package com.exam.examserver.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examserver.model.exam.Question;
import com.exam.examserver.model.exam.Quiz;
import com.exam.examserver.services.QuestionService;
import com.exam.examserver.services.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Question>add(@RequestBody Question question)
    {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    @PutMapping("/")
    public ResponseEntity<Question>update(@RequestBody Question question)
    {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    @GetMapping("/quiz/{qId}")
    public ResponseEntity<?>getQuestionsOfQuiz(@PathVariable("qId") Long qid)
    {
        // Quiz quiz=new Quiz();
        // quiz.setQid(qid);
        // return ResponseEntity.ok(this.questionService.getQuesitonsOfQuiz(quiz));
        Quiz quiz=this.quizService.getQuiz(qid);
        Set<Question>questions=quiz.getQuestions();
        List<Question> list=new ArrayList<>(questions);
        if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions())){
            list=list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions()+1));
        }

        list.forEach((q)->{
            q.setAnswer("");
        });

        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/quiz/all/{qId}")
    public ResponseEntity<?>getQuestionsOfQuizAdmin(@PathVariable("qId") Long qid)
    {
        Quiz quiz=new Quiz();
        quiz.setQid(qid);
        return ResponseEntity.ok(this.questionService.getQuesitonsOfQuiz(quiz));
        
    }


    // get single question
    @GetMapping("/{quesId}")
    public Question get(@PathVariable("quesId") Long quesId)
    {
        return this.questionService.getQuestion(quesId);
    }

    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") Long quesId){
        this.questionService.deleteQuestion(quesId);
    }

    // eval quiz

    @PostMapping("/eval-quiz")
    public ResponseEntity<?>evalQuiz(@RequestBody List<Question>questions){
        System.out.println(questions);
        Double marksGot=0.0;
        Integer correctAnswers=0;
        Integer attempted=0;
        for(Question q:questions){
            // System.out.println(q.getGivenAnswer());  
            Question question=this.questionService.get(q.getQuesId());
            if(question.getAnswer().equals(q.getGivenAnswer())){
                // correct answer
                correctAnswers++;
                double mark=Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
                marksGot+=mark;
            }

            if(q.getGivenAnswer()!=null){
                attempted++;
            }
        };
        Map<String,Object>map=Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
        return ResponseEntity.ok(map);
    }

     

    
    
}
