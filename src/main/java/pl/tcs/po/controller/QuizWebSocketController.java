package pl.tcs.po.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import pl.tcs.po.model.Player;
import pl.tcs.po.model.PlayerAnswer;
import pl.tcs.po.model.QuizSession;
import pl.tcs.po.service.QuizService;

@Controller
public class QuizWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private QuizService quizService;

    @MessageMapping("/quiz/start")
    public void startQuiz(String sessionId) {
        QuizSession session = quizService.getSession(sessionId);
        if (session != null) {
            messagingTemplate.convertAndSend("/topic/questions/" + sessionId, session.getCurrentQuestion());
        }
    }

    @MessageMapping("/quiz/answer")
    public void receiveAnswer(PlayerAnswer playerAnswer) {
        QuizSession session = quizService.getSession(playerAnswer.getSessionId());
        if (session != null) {
            Player player = session.getPlayers().stream()
                    .filter(p -> p.getName().equals(playerAnswer.getPlayerName()))
                    .findFirst()
                    .orElse(null);
            if (player != null && session.getCurrentQuestion().getCorrectAnswer().equals(playerAnswer.getAnswer())) {
                player.incrementScore();
            }
            session.nextQuestion();
            messagingTemplate.convertAndSend("/topic/questions/" + session.getSessionId(), session.getCurrentQuestion());
        }
    }
}