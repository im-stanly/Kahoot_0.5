package pl.tcs.po.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuizSession {
    private String sessionId;
    private List<Player> players;
    private List<QuestionModel> questions;
    private int currentQuestionIndex;

    public QuizSession(List<QuestionModel> questions) {
        this.sessionId = UUID.randomUUID().toString();
        this.players = new ArrayList<>();
        this.questions = questions;
        this.currentQuestionIndex = 0;
    }

    public String getSessionId() {
        return sessionId;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public QuestionModel getCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }
        return null;
    }

    public void nextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            currentQuestionIndex++;
        }
    }
}

