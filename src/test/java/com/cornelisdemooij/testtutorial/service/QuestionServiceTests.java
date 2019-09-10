package com.cornelisdemooij.testtutorial.service;

import com.cornelisdemooij.testtutorial.domain.Question;
import com.cornelisdemooij.testtutorial.persistence.QuestionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
        Question question1 = new Question("Is this a mocked test question?");
        question1.setId(0);
        when(questionRepository.findById(question1.getId()))
                .thenReturn(java.util.Optional.of((question1)));
        Question question2 = new Question("Could this be another mocked test question?");
        question2.setId(1);
        when(questionRepository.findById(question2.getId()))
                .thenReturn(java.util.Optional.of((question2)));

        // Make the save method of our mocked question repository return the question that it was given:
        /*when(questionRepository.save(any(Question.class))).thenAnswer(new Answer<Question>() {
            @Override
            public Question answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (Question) args[0];
            }
        });*/
        when(questionRepository.save(any(Question.class))).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    public void mockedQuestionShouldBeFoundById() {
        long id = 0;
        Question question = questionService.findById(id);
        assertThat(question.getText()).isEqualTo("Is this a mocked test question?");
    }

    @Test
    public void multipleMockedQuestionsShouldBeFound() {
        long[] ids = {0, 1};
        String[] texts = {
                "Is this a mocked test question?",
                "Could this be another mocked test question?"
        };
        Iterable<Question> questions = questionService.findAll();
        int count = 0;
        for (Question question : questions) {
            assertThat(question.getId()).isEqualTo(ids[count]);
            assertThat(question.getText()).isEqualTo(texts[count]);
            count++;
        }
    }

    @Test
    public void saveQuestionShouldReturnSameQuestion() {
        Question question = new Question("Is this the saved question?");
        Question returnedQuestion = questionService.save(question);
        assertThat(returnedQuestion).isEqualTo(question);
    }
}
