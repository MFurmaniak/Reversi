package packageName;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;

public class Menu extends TilePane{ //class that contains menu and layout of it
    private Button optionsButton = new Button();
    private Button newGameButton = new Button();
    private Button exitButton = new Button();
    Menu(){
        super(Orientation.VERTICAL,0,25);
        this.setTileAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);
        newGameButton.setText("New Game");
        optionsButton.setText("Options");
        exitButton.setText("Exit");
        this.getChildren().addAll(newGameButton, optionsButton, exitButton);
    }

    public Button getExitButton() { //method returns button for use in other classes
        return exitButton;
    }

    public Button getNewGameButton() { //method returns button for use in other classes
        return newGameButton;
    }

    public Button getOptionsButton() { //method returns button for use in other classes
        return optionsButton;
    }
}
