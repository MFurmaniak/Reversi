package packageName;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    /*main class for application, creates menu, controller and options objects for and add them to view */
    @Override
    public void start(Stage primaryStage) throws Exception{

        StackPane mainStackPane = new StackPane();
        primaryStage.setTitle("Reversi");
        primaryStage.setScene(new Scene(mainStackPane, 300,300));
        primaryStage.setResizable(false);

        BackgroundImage backgroundImage;
        backgroundImage = new BackgroundImage(new Image("packageName/Background.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        Menu menu = new Menu();
        Options options = new Options();
        menu.setBackground(new Background(backgroundImage));
        options.setBackground(new Background(backgroundImage));

        mainStackPane.getChildren().addAll(menu,options);
        primaryStage.show();
        options.setVisible(false);
        Controller controller = new Controller(mainStackPane, options, menu, primaryStage);


    }


    public static void main(String[] args) {
        launch(args);
    }
}
