package com.triangle.quizapp.service;

import com.triangle.quizapp.dao.QuestionDao;
import com.triangle.quizapp.dao.QuizDao;
import com.triangle.quizapp.model.Question;
import com.triangle.quizapp.model.QuestionWrapper;
import com.triangle.quizapp.model.Quiz;
import com.triangle.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDua;
    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question>questions=questionDao.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDua.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id){
        Optional<Quiz> quiz =quizDua.findById(id);
        List<Question> questionsFromDB =quiz.get().getQuestions();
        List<QuestionWrapper> questionWrappers =new ArrayList<>();
        for(Question q : questionsFromDB) {
        QuestionWrapper qw =new QuestionWrapper(q.getId(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4(),q.getQuestionTitle());
        questionWrappers.add(qw);
        }
        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    };

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz =quizDua.findById(id).get();
        List<Question> questions=quiz.getQuestions();
int right=0;
int i=0;
        for(Response response :responses){
           if(response.getResponse().equals(questions.get(i).getRightAnswer()))
               right++;
               i++;

        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }

}
