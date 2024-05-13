package pl.tcs.po.views.game;

import pl.tcs.po.controller.QuestionController;
import pl.tcs.po.model.Answers;
import pl.tcs.po.model.CorrectAnswers;
import pl.tcs.po.model.QuestionModel;
import pl.tcs.po.views.MainLayout;
import pl.tcs.po.webClient.QuestionsClient;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import jakarta.annotation.PostConstruct;

@PageTitle("Kahoot v 0.5")
@Route(value = "/game", layout = MainLayout.class)
@RouteAlias(value = "/game", layout = MainLayout.class)
public class GameView extends HorizontalLayout {

    private Button submitButton;
    private QuestionModel question;

    @Autowired
    QuestionsClient questionsClient;

    public GameView() {
    }

    @PostConstruct
    void init() {
        question = questionsClient.getQuestions().get(0);

        CheckboxGroup<String> questionGroup = new CheckboxGroup<>();
        questionGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        questionGroup.setLabel(question.getQuestion());

        Answers answers = question.getAnswers();
        List<String> answerLabels = new ArrayList<>();
        if (answers.getAnswerA() != null)
            answerLabels.add(answers.getAnswerA());
        if (answers.getAnswerB() != null)
            answerLabels.add(answers.getAnswerB());
        if (answers.getAnswerC() != null)
            answerLabels.add(answers.getAnswerC());
        if (answers.getAnswerD() != null)
            answerLabels.add(answers.getAnswerD());
        if (answers.getAnswerE() != null)
            answerLabels.add(answers.getAnswerE());
        if (answers.getAnswerF() != null)
            answerLabels.add(answers.getAnswerF());

        CorrectAnswers correctAnswers = question.getCorrectAnswers();

        questionGroup.setItems(answerLabels);
        submitButton = new Button("Submit");
        submitButton.addClickListener(e -> {
            if (questionGroup.getValue() != null) {
                boolean isCorrect = true;
                List<String> selectedAnswers = new ArrayList<>(questionGroup.getValue());
                if(correctAnswers.isAnswerACorrect() != selectedAnswers.contains(answers.getAnswerA()))
                    isCorrect = false;
                if(correctAnswers.isAnswerBCorrect() != selectedAnswers.contains(answers.getAnswerB()))
                    isCorrect = false;
                if(correctAnswers.isAnswerCCorrect() != selectedAnswers.contains(answers.getAnswerC()))
                    isCorrect = false;
                if(correctAnswers.isAnswerDCorrect() != selectedAnswers.contains(answers.getAnswerD()))
                    isCorrect = false;
                if(correctAnswers.isAnswerECorrect() != selectedAnswers.contains(answers.getAnswerE()))
                    isCorrect = false;
                if(correctAnswers.isAnswerFCorrect() != selectedAnswers.contains(answers.getAnswerF()))
                    isCorrect = false;
                if(isCorrect)
                    Notification.show("Correct!");
                else
                    Notification.show("Incorrect!");
            }
        });
        submitButton.addClickShortcut(Key.ENTER);

        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, questionGroup, submitButton);

        add(questionGroup, submitButton);
    }

}
