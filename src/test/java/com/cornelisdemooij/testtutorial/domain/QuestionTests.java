package com.cornelisdemooij.testtutorial.domain;

import org.junit.Assert;
import org.junit.Test;

public class QuestionTests {

    @Test
    public void checkThatEmptyConstructorWorks() {
        Question question = new Question();
        Assert.assertEquals(0, question.getId());
        Assert.assertEquals("", question.getText());
        Assert.assertEquals(0, question.getLength());
    }

    @Test
    public void checkThatStringConstructorWorks() {
        String questionText = "Gaat het nog regenen vandaag?";
        Question question = new Question(questionText);
        Assert.assertEquals(0, question.getId());
        Assert.assertEquals(questionText, question.getText());
        Assert.assertEquals(questionText.length(), question.getLength());
    }

    @Test
    public void checkThatSettersWork() {
        Question question = new Question();
        question.setId(5);
        question.setText("test string");
        Assert.assertEquals(5, question.getId());
        Assert.assertEquals("test string", question.getText());
        Assert.assertEquals(11, question.getLength());
    }
}
