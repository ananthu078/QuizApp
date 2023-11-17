package com.project.QuizApp.repository;

import com.project.QuizApp.model.Question;
import com.project.QuizApp.model.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuizRepository extends MongoRepository<Quiz, String> {

}
