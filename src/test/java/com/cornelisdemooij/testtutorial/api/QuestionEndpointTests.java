package com.cornelisdemooij.testtutorial.api;

import com.cornelisdemooij.testtutorial.domain.Question;
import com.cornelisdemooij.testtutorial.service.QuestionService;
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
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionEndpointTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private QuestionService questionService;

    @Test
    public void getQuestionsFromEndpointWithMockedService() throws Exception {
        Question[] questionArray = { new Question("Does this answer your question?") };
        Iterable<Question> questions = Arrays.asList(questionArray);

        when(questionService.findAll()).thenReturn(questions);

        ResponseEntity<String> entity = this.restTemplate.getForEntity("/api/question", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("[{\"id\":0,\"text\":\"Does this answer your question?\",\"length\":31}]");
    }

}
