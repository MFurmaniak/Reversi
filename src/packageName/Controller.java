package packageName;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Controller { //class that contains buttons behavior
    NewGame newGame = new NewGame(8,false,1, 0);
    Button returnNewGame = newGame.getReturnButton();
    public Controller(/*NewGame newGame,*/StackPane stackPane, Options options, Menu menu, Stage primaryStage){
        Button exitButton = menu.getExitButton();
        exitButton.setOnAction(new EventHandler<ActionEvent>() { //exit button behaviour
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        Button optionsButton = menu.getOptionsButton();
        optionsButton.setOnAction(new EventHandler<ActionEvent>() { //enter options button behaviour
            @Override
            public void handle(ActionEvent event) {
                menu.setVisible(false);
                options.setVisible(true);
            }
        });
        Button optionsReturnButton = options.getReturnButton();
        optionsReturnButton.setOnAction(new EventHandler<ActionEvent>() { //return from options button behaviour
            @Override
            public void handle(ActionEvent event) {
                menu.setVisible(true);
                options.setVisible(false);
                System.out.println(options.getSizeOfBoard());
            }
        });
        Button newGameButton = menu.getNewGameButton();
        newGameButton.setOnAction(new EventHandler<ActionEvent>() { //new game button behaviour
            @Override
            public void handle(ActionEvent event) {
                newGame = new  NewGame(options.getSizeOfBoard(),options.isAiCheckBoxSelected(),options.whichSet(),options.getMaxTime());
                returnNewGame = newGame.getReturnButton();
                Timer clockTimer = new Timer(options.getMaxTime(),newGame);
                clockTimer.start();
                setReturnNewGameAction(returnNewGame, menu, stackPane, primaryStage);
                stackPane.getChildren().add(newGame);
                menu.setVisible(false);
                newGame.setVisible(true);
                primaryStage.setHeight(32*options.getSizeOfBoard()+100);
                primaryStage.setWidth(32*options.getSizeOfBoard()+100);

            }
        });
    }
    private void setReturnNewGameAction(Button returnNewGame, Menu menu, StackPane stackPane, Stage primaryStage){
        returnNewGame.setOnAction(new EventHandler<ActionEvent>() { //return from new game button behaviour
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setHeight(300);
                primaryStage.setWidth(300);
                newGame.setVisible(false);
                stackPane.getChildren().remove(newGame);
                menu.setVisible(true);
            }
        });
    }
}

