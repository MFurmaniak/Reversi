package packageName;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

//class with end game window view

public class EndWindow extends GridPane {
    EndWindow(int scoreWhite, int scoreBlack){
        this.setAlignment(Pos.CENTER);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(new Label("Player 1: " + scoreWhite + "   "));
        hBox.getChildren().add(new Label("Player 2: " + scoreBlack));
        this.add(hBox,0,0);
        Label winner = new Label();
        if(scoreBlack>scoreWhite){
            winner.setText("Player 2 won");
        }
        else{
            if(scoreWhite>scoreBlack){
                winner.setText("Player 1 won");
            }
            else{
                winner.setText("Draw");
            }
        }
        hBox.setAlignment(Pos.CENTER);
        this.add(winner, 0,1);
        Button button = new Button("Return");
        Stage stage = new Stage();
        stage.setScene(new Scene(this));
        button.setAlignment(Pos.CENTER);
        this.add(button,0,2);
        stage.show();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        }); // behaviour of the return button in end game window
    }
}
