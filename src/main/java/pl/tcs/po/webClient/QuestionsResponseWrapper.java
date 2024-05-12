package pl.tcs.po.webClient;

import pl.tcs.po.model.QuestionModel;

import java.util.List;

public class QuestionsResponseWrapper {
    private List<QuestionModel> questions;

    public List<QuestionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionModel> questions) {
        this.questions = questions;
    }

}
