package com.project.QuizApp.service;

import com.project.QuizApp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.project.QuizApp.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionrepository;
    public ResponseEntity<List<Question>> getAllQuestions(){
        return new ResponseEntity<>(questionrepository.findAll(),HttpStatus.OK);
        }

    public ResponseEntity<String> enterQuestion(Question question){
        questionrepository.insert(question);
        return new ResponseEntity<>("Success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category){
        try{
        return new ResponseEntity<>(questionrepository.findByCategory(category),HttpStatus.OK);
         } catch (Exception e){
             e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> enterAllQuestions(List<Question> questions) {
        try {
            questionrepository.saveAll(questions);
            return new ResponseEntity<>("Success",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("BAD REQUEST",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> updateQuestion(String id,Question upatedQuestion) {
        Question existingQuestion= questionrepository.findById(id).get();
        existingQuestion.setQuestionTitle(upatedQuestion.getQuestionTitle());
        existingQuestion.setOption1(upatedQuestion.getOption1());
        existingQuestion.setOption2(upatedQuestion.getOption2());
        existingQuestion.setOption3(upatedQuestion.getOption3());
        existingQuestion.setOption4(upatedQuestion.getOption4());
        existingQuestion.setRightAnswer(upatedQuestion.getRightAnswer());
        existingQuestion.setDifficultyLevel(upatedQuestion.getDifficultyLevel());
        existingQuestion.setCategory(upatedQuestion.getCategory());
        return new ResponseEntity<>(questionrepository.save(existingQuestion),HttpStatus.OK);

    }
}
