package com.cornelisdemooij.testtutorial.service;

import com.cornelisdemooij.testtutorial.domain.Question;
import com.cornelisdemooij.testtutorial.persistence.QuestionRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class QuestionServiceTests {

    @TestConfiguration
    static class testConfig {
        @Bean
        public QuestionService questionService() {
            return new QuestionService();
        }
    }

    @Autowired
    private QuestionService questionService;

    @MockBean
    private QuestionRepository questionRepository;

    @Before
    public void setUp() {
        Question question1 = new Question("Is this question 1?");
        question1.setId(0);
        when(questionRepository.findById(question1.getId()))
                .thenReturn(java.util.Optional.of(question1));
        when(questionRepository.save(question1))
                .thenReturn(question1);
    }

    @Test
    public void checkThatFindByIdWorks() {
        long id = 0;
        Question question = questionService.findById(id);
        Assert.assertEquals("Is this question 1?", question.getText());
    }
}
