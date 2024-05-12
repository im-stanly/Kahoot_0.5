package pl.tcs.po.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tcs.po.model.QuestionModel;
import pl.tcs.po.webClient.QuestionsClient;

import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    QuestionsClient questionsClient;

    @GetMapping("/getQuestion")
    public QuestionModel getQuestion(){
        return questionsClient.getQuestions().get(0);
    }

    @GetMapping("/getQuestions")
    public List<QuestionModel> getQuestions(){
        return questionsClient.getQuestions();
    }
}
