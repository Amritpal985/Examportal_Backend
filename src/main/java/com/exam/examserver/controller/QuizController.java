package com.exam.examserver.controller;

import java.util.List;

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

import com.exam.examserver.model.exam.Category;
import com.exam.examserver.model.exam.Quiz;
import com.exam.examserver.services.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody Quiz quiz)
    {
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    // update quiz
    @PutMapping("/")
    public ResponseEntity<?>update(@RequestBody Quiz quiz){
        return ResponseEntity.ok((this.quizService.updateQuiz(quiz)));
    }

    // @GetMapping("/")
    // pubilc ResponseEntity<?>quizzes(){
    //     return ResponseEntity.ok(this.quizService.getQuizzes());
    // }
    

    @GetMapping("/")
    public ResponseEntity<?> quizzes(){
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    @GetMapping("/{qId}")
    public Quiz quiz(@PathVariable("qId") Long qid)
    {
        return this.quizService.getQuiz(qid);
    }

    @DeleteMapping("/{qId}")
    public void delete(@PathVariable("qId") Long qid)
    {
        this.quizService.deleteQuiz(qid);
    }

    @GetMapping("/category/{cid}")
    public List<Quiz> getQuizzesOfCategory(@PathVariable("cid")Long cid){

        Category category=new Category();
        category.setCid(cid);
        return this.quizService.getQuizzesOfCategory(category);
    }

    @GetMapping("/active")
    public List<Quiz> getActiveQuizzes(){
        return this.quizService.getActiveQuizzes();
    }

    @GetMapping("/active/category/{cid}")
    public List<Quiz> getActiveQuizzesOfCatgegory(@PathVariable("cid") Long cid){
        Category category=new Category();
        category.setCid(cid);
        return this.quizService.getActiveQuizzesOfCategory(category);
    }




    
}
