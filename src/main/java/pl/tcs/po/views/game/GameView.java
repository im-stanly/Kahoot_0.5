package pl.tcs.po.views.game;

import pl.tcs.po.controller.QuestionController;
import pl.tcs.po.model.Answers;
import pl.tcs.po.model.QuestionModel;
import pl.tcs.po.views.MainLayout;
import pl.tcs.po.webClient.QuestionsClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
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
        questionGroup.setItems(question.getAnswers().getAnswerA(), question.getAnswers().getAnswerB(),
                question.getAnswers().getAnswerC(), question.getAnswers().getAnswerD(),
                question.getAnswers().getAnswerE(), question.getAnswers().getAnswerF());
        submitButton = new Button("Submit");
        submitButton.addClickListener(e -> {
            if (questionGroup.getValue() != null) {
                Notification.show("You selected: " + questionGroup.getValue());
            }
        });
        submitButton.addClickShortcut(Key.ENTER);

        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, questionGroup, submitButton);

        add(questionGroup, submitButton);
    }

}
