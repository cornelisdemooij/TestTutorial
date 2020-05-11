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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

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
        Question question2 = new Question("Is this question 2?");
        question2.setId(3);

        List<Question> questionList = new ArrayList<Question>();
        questionList.add(question1);
        questionList.add(question2);

        when(questionRepository.findById(question1.getId()))
                .thenReturn(java.util.Optional.of(question1));
        when(questionRepository.save(question1))
                .thenReturn(question1);
        when(questionRepository.findAll())
                .thenReturn(questionList);
    }

    @Test
    public void checkThatFindByIdWorks() {
        long id = 0;
        Question question = questionService.findById(id);
        Assert.assertEquals("Is this question 1?", question.getText());
    }

    @Test
    public void checkThatFindAllWorks() {
        Iterable<Question> questions = questionService.findAll();
        int expectedNumberOfQuestions = 2;
        int count = 0;
        for (Question question : questions) {
            count++;
        }
        Assert.assertEquals(expectedNumberOfQuestions, count);

        verify(questionRepository).findAll();
        verifyNoMoreInteractions(questionRepository);
    }
}
