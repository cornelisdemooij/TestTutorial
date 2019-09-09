package com.cornelisdemooij.testtutorial.persistence;

import com.cornelisdemooij.testtutorial.TesttutorialApplication;
import com.cornelisdemooij.testtutorial.domain.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TesttutorialApplication.class)
public class QuestionRepositoryTests {

    @Autowired
    private QuestionRepository questionRepo;

    @Test
    public void saveAndGetQuestionFromEmbeddedDB() {
        Question question = questionRepo.save(new Question("test"));
        Question foundQuestion = questionRepo.findById(question.getId()).get();

        assertNotNull(foundQuestion);
        assertThat(foundQuestion.getText()).isEqualTo(question.getText());
    }

    @Test
    public void saveAndGetMultipleQuestionsFromEmbeddedDB() {
        questionRepo.deleteAll();
        Question question1 = questionRepo.save(new Question("Is this the first question?"));
        Question question2 = questionRepo.save(new Question("Could it be the second question?"));
        //Question question3 = questionRepo.save(new Question("What? A third question?"));

        Iterable<Question> foundQuestions = questionRepo.findAll();

        Question[] foundQuestionArray = new Question[2];
        int count = 0;
        for (Question question : foundQuestions) {
            foundQuestionArray[count] = question;
            count++;
        }
        assertThat(count).isEqualTo(2);
        assertThat(foundQuestionArray[0].getText()).isEqualTo(question1.getText());
        assertThat(foundQuestionArray[1].getText()).isEqualTo(question2.getText());
    }
}
