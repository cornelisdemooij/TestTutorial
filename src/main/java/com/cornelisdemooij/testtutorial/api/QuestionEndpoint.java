package com.cornelisdemooij.testtutorial.api;

import com.cornelisdemooij.testtutorial.domain.Question;
import com.cornelisdemooij.testtutorial.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/question")
@Component
public class QuestionEndpoint {

    @Autowired
    private QuestionService questionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listQuestions(){
        Iterable <Question> questions = questionService.findAll();
        return Response.ok(questions).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postQuestion(Question question){
        Question result = questionService.save(question);
        return Response.accepted(result.getId()).build();
    }

}
