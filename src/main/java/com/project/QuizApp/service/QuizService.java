package com.project.QuizApp.service;

import com.project.QuizApp.model.Question;
import com.project.QuizApp.model.QuestionWrapper;
import com.project.QuizApp.model.Quiz;
import com.project.QuizApp.model.Response;
import com.project.QuizApp.repository.QuestionRepository;
import com.project.QuizApp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
  @Autowired
  QuizRepository quizRepository;
  @Autowired
  QuestionRepository questionRepository;
    @Autowired
    MongoTemplate mongoTemplate;

  public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
      try {
          Quiz quiz = new Quiz();
          quiz.setTitle(title);
          Query query = new Query();
          query.addCriteria(Criteria.where("category").is(category));
          query.limit(numQ);
          List<Question> updatedQuestions = mongoTemplate.find(query, Question.class);
          quiz.setQuestions(updatedQuestions);
          quizRepository.save(quiz);
          return new ResponseEntity<>("Success", HttpStatus.OK);
      } catch (Exception e){
          return new ResponseEntity<>("INTERNAL SEVER ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }


  public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(String title){
        Quiz quiz=quizRepository.findByTitle(title);
        List<Question> questionFromDB= quiz.getQuestions();
       List <QuestionWrapper> questionForUSer=new ArrayList<>();
       for(Question q: questionFromDB){
              QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
              questionForUSer.add(qw);
       }
       return new ResponseEntity<>(questionForUSer,HttpStatus.OK);
  }

    public ResponseEntity<Integer> marks(String id, List<Response> responses) {
      Quiz quiz= quizRepository.findById(id).get();
      List<Question> questions= quiz.getQuestions();
        int i=0;
        int right=0;
        for(Response r:responses){
            if(r.getResponse().equals(questions.get(i).getRightAnswer())) right++;

            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);

    }
}