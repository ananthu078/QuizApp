package com.project.QuizApp.controller;


import com.project.QuizApp.model.Question;
import com.project.QuizApp.model.QuestionWrapper;
import com.project.QuizApp.model.Response;
import com.project.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
 @PostMapping ("create/{category}/{numQ}/{title}")
 public ResponseEntity<String> createQuiz(@PathVariable String category, @PathVariable Integer numQ, @PathVariable String title){
        return quizService.createQuiz(category, numQ,title);
    }

    @GetMapping("get/{title}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable String title){
            return quizService.getQuizQuestions(title);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable String id, @RequestBody List<Response> responses){
        return quizService.marks(id,responses);
    }
}


