package com.project.QuizApp.controller;

import com.project.QuizApp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.QuizApp.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping(value="/questions")
public class QuestionController {
    @Autowired
    QuestionService questionservice;


    @GetMapping(value="/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionservice.getAllQuestions();
    }
    @PostMapping("enterQuestion")
    public ResponseEntity<String> enterQuestion(@RequestBody Question question){
        return questionservice.enterQuestion(question);
    }

    @GetMapping(value="category/{category}")
        public ResponseEntity<List<Question>> getquestionsByCategory(@PathVariable String category){
        return questionservice.getQuestionsByCategory(category);
    }

    @PostMapping(value = "enterAllQuestions")
    public ResponseEntity<String> enterAllQuestion(@RequestBody List<Question> questions){
        return questionservice.enterAllQuestions(questions);
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable String id,@RequestBody Question upatedQuestion){
            return questionservice.updateQuestion(id,upatedQuestion);
    }
}
