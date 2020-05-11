package com.cornelisdemooij.testtutorial.persistence;

import com.cornelisdemooij.testtutorial.TesttutorialApplication;
import com.cornelisdemooij.testtutorial.domain.Question;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TesttutorialApplication.class)
public class QuestionRepositoryTests {

    @Autowired
    private QuestionRepository questionRepo;

    @Test
    public void checkIfRepositorySavesQuestion() {
        Question question = new Question("Is dit een vraag?");
        Question savedQuestion = questionRepo.save(question);
        question.setText("Is dit een andere vraag?");

        Assert.assertNotNull(savedQuestion);
        Assert.assertEquals(question, savedQuestion);
    }

    @Test
    public void checkIfQuestionExistsInDatabase() {
        Question question = new Question("Is dit een vraag?");
        questionRepo.save(question);
        Question foundQuestion = questionRepo.findById(question.getId()).get();

        Assert.assertEquals(question.getId(), foundQuestion.getId());
        Assert.assertEquals(question.getText(), foundQuestion.getText());
    }
}
