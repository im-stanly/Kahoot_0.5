package pl.tcs.po.model;

import lombok.Data;

@Data
public class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public void incrementScore() {
        this.score++;
    }
}

