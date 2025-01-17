package com.triangle.quizapp.service;

import com.triangle.quizapp.dao.QuestionDao;
import com.triangle.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
//service where all calculation or method done here
@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
        return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
    }catch (
            Exception e
        ){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
        return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
    }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.BAD_REQUEST);
    }
   public ResponseEntity<String> addQuestion(Question question){
        questionDao.save(question);
        return new ResponseEntity<>("Success",HttpStatus.OK);

   }

    public ResponseEntity<String> deleteQuestion(int id) {
        questionDao.deleteById(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
}
