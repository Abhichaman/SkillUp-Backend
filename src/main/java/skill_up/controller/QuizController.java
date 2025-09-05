package skill_up.controller;


import skill_up.entity.QuizQuestion;
import skill_up.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class QuizController {

    @Autowired
    QuizService questionService;

    @GetMapping("/questions/{subject}")
    public List<QuizQuestion> getQuestion(@PathVariable String subject) {
        return questionService.getQuestionsBySubject(subject);
    }

    @PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
    public QuizQuestion saveQuestion(@RequestBody QuizQuestion question) {
        return questionService.saveQuestion(question);
    }
}
