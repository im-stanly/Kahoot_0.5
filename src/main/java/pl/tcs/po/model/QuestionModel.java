package pl.tcs.po.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class QuestionModel {
    private int id;
    private String question;
    private String description;
    private Answers answers;
    @JsonProperty("multiple_correct_answers")
    private boolean multipleCorrectAnswers;
    @JsonProperty("correct_answers")
    private CorrectAnswers correctAnswers;
    @JsonProperty("correct_answer")
    private String correctAnswer;
    private String explanation;
    private String tip;
    private List<Tag> tags;
    private String category;
    private String difficulty;
}
