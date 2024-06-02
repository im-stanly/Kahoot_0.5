package pl.tcs.po.model;

import lombok.Data;

@Data
public class PlayerAnswer {
    private String sessionId;
    private String playerName;
    private String answer;

    public PlayerAnswer(String sessionId, String playerName, String answer) {
        this.sessionId = sessionId;
        this.playerName = playerName;
        this.answer = answer;
    }
}

