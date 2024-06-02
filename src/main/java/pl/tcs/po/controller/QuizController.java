package pl.tcs.po.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tcs.po.model.QuestionModel;
import pl.tcs.po.model.QuizSession;
import pl.tcs.po.service.QuizService;
import pl.tcs.po.webClient.QuestionsClient;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuestionsClient questionsClient;

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<QuizSession> createQuizSession() {
        List<QuestionModel> questions = questionsClient.getQuestions();
        QuizSession session = quizService.createSession(questions);
        return ResponseEntity.ok(session);
    }

    @PostMapping("/join")
    public ResponseEntity<QuizSession> joinQuizSession(@RequestParam String sessionId, @RequestParam String playerName) {
        QuizSession session = quizService.joinSession(sessionId, playerName);
        return ResponseEntity.ok(session);
    }
}
