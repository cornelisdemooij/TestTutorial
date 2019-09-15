package com.cornelisdemooij.testtutorial.api;

import com.cornelisdemooij.testtutorial.domain.Question;
import com.cornelisdemooij.testtutorial.persistence.QuestionRepository;
import com.cornelisdemooij.testtutorial.service.QuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionEndpointTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private QuestionRepository questionRepo;

    @Before
    public void setUp() {
        Question[] questionArray = {
            new Question("Does this answer your question?"),
            new Question("How about this?")
        };
        Iterable<Question> questions = Arrays.asList(questionArray);

        when(questionService.findById(questionArray[0].getId())).thenReturn(questionArray[0]);
        when(questionService.findById(questionArray[1].getId())).thenReturn(questionArray[1]);
        when(questionService.findAll()).thenReturn(questions);
    }

    @Test
    public void getQuestionsFromEndpointWithMockedService() throws Exception {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/api/question", String.class);
        String expectedResponse = "[{\"id\":0,\"text\":\"Does this answer your question?\",\"length\":31},{\"id\":0,\"text\":\"How about this?\",\"length\":15}]";

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo(expectedResponse);

        verify(questionService, never()).findById((long)0);
        verify(questionService, never()).findById((long)1);

        verify(questionService, never()).findById(anyLong());

        verify(questionService).findAll();
        verifyNoMoreInteractions(questionService);

        verifyZeroInteractions(questionRepo);
    }

}
