package pl.tcs.po.service;

import org.springframework.stereotype.Service;
import pl.tcs.po.model.Player;
import pl.tcs.po.model.QuestionModel;
import pl.tcs.po.model.QuizSession;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class QuizService {

    private Map<String, QuizSession> sessions = new ConcurrentHashMap<>();

    public QuizSession createSession(List<QuestionModel> questions) {
        QuizSession session = new QuizSession(questions);
        sessions.put(session.getSessionId(), session);
        return session;
    }

    public QuizSession joinSession(String sessionId, String playerName) {
        QuizSession session = sessions.get(sessionId);
        if (session != null) {
            session.addPlayer(new Player(playerName));
        }
        return session;
    }

    public QuizSession getSession(String sessionId) {
        return sessions.get(sessionId);
    }
}