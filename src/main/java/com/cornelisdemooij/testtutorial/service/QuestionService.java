package com.cornelisdemooij.testtutorial.service;

import com.cornelisdemooij.testtutorial.domain.Question;
import com.cornelisdemooij.testtutorial.persistence.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question save(Question question){
        return questionRepository.save(question);
    }

    public Question findById(Long id) {
        return questionRepository.findById(id).get();
    }

    public Iterable <Question> findAll(){
        return questionRepository.findAll();
    }
}
