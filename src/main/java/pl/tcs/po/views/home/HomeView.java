package pl.tcs.po.views.home;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import pl.tcs.po.views.MainLayout;
import pl.tcs.po.views.game.GameView;

@PageTitle("Kahoot v 0.5")
@Route(value = "/", layout = MainLayout.class)
@RouteAlias(value = "/", layout = MainLayout.class)
public class HomeView extends VerticalLayout {

    private String welcomeText = "Welcome to Kahoot v 0.5";
    private Button playButton;

    public HomeView() {
        playButton = new Button("Play game");
        playButton.addClickListener(e -> playButton.getUI().ifPresent(ui -> ui.navigate(
                GameView.class)));
        add(welcomeText);
        add(playButton);
    }

}
