package com.cornelisdemooij.testtutorial;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class Controller {

    @RequestMapping("/")
    public String index() {
        return "You have reached the TesttutorialApplication. Try /api/question.";
    }
}
