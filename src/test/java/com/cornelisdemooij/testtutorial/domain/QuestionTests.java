package com.cornelisdemooij.testtutorial.domain;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class QuestionTests {

    @Test
    public void checkLength() {
        Question question = new Question();
        question.setText("Is this a question?");
        Assert.assertEquals(19, question.getLength());
    }
}
