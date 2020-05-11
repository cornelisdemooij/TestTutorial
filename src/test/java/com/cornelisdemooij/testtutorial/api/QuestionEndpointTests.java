package com.cornelisdemooij.testtutorial.api;

import com.cornelisdemooij.testtutorial.domain.Question;
import com.cornelisdemooij.testtutorial.persistence.QuestionRepository;
import com.cornelisdemooij.testtutorial.service.QuestionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionEndpointTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private QuestionRepository questionRepository;

    @Before
    public void setUp() {
        Question[] questionArray = {
                new Question("Gaat Ajax vanavond winnen?"),
                new Question("Gaat het vanavond regenen?")
        };
        questionArray[1].setId((long)1);
        Iterable<Question> questions = Arrays.asList(questionArray);

        when(questionService.findById(questionArray[0].getId())).thenReturn(questionArray[0]);
        when(questionService.findById(questionArray[1].getId())).thenReturn(questionArray[1]);
        when(questionService.findAll()).thenReturn(questions);
    }

    @Test
    public void getQuestionsFromEndpointWithMockedService() {
        ResponseEntity<String> entity = restTemplate.getForEntity("/api/question", String.class);

        Assert.assertEquals(200, entity.getStatusCodeValue());
        Assert.assertEquals("[{\"id\":0,\"text\":\"Gaat Ajax vanavond winnen?\",\"length\":26},{\"id\":1,\"text\":\"Gaat het vanavond regenen?\",\"length\":26}]",
                            entity.getBody());

        verify(questionService).findAll();
        verify(questionService, never()).findById(anyLong());

        verifyZeroInteractions(questionRepository);
    }
}
