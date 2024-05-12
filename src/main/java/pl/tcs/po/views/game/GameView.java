package pl.tcs.po.views.game;

import pl.tcs.po.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Kahoot v 0.5")
@Route(value = "/game", layout = MainLayout.class)
@RouteAlias(value = "/game", layout = MainLayout.class)
public class GameView extends HorizontalLayout {

    private TextField questionText;
    private Button submitButton;

    public GameView() {
        // questionText = new TextField("Question");

        RadioButtonGroup<String> questionGroup = new RadioButtonGroup<>();
        questionGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        questionGroup.setLabel("Question text");
        questionGroup.setItems("Option1", "Option2", "Option3");
        submitButton = new Button("Submit");
        submitButton.addClickListener(e -> {
            Notification.show("Placeholder");
        });
        submitButton.addClickShortcut(Key.ENTER);

        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, questionGroup, submitButton);

        add(questionGroup, submitButton);
    }

}
