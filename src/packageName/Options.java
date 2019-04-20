package packageName;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;


public class Options extends TilePane { //class for options, contains layout of options menu
    private Button returnButton = new Button();
    private CheckBox aiCheckBox = new CheckBox("Game with A.I.");
    private NumberTextField sizeTextField = new NumberTextField();
    private Label sizeLabel = new Label("Size of board");
    private ToggleGroup group = new ToggleGroup();
    private Label timeLable = new Label("Max game time");
    private NumberTextField timeTextField = new NumberTextField();
    private RadioButton set1Button = new RadioButton("set 1 ");
    private RadioButton set2Button = new RadioButton("set 2 ");
    private RadioButton set3Button = new RadioButton("set 3 ");
    Options(){
        super(Orientation.VERTICAL,0,5);
        this.setTileAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);
        returnButton.setText("Return");
        sizeTextField.setPromptText("size of board");
        sizeTextField.setText("8");
        timeTextField.setText("0");
        set1Button.setToggleGroup(group);
        set2Button.setToggleGroup(group);
        set3Button.setToggleGroup(group);
        set1Button.setSelected(true);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(set1Button, set2Button, set3Button);
        this.getChildren().addAll(sizeLabel, sizeTextField, timeLable, timeTextField, aiCheckBox, hBox, returnButton);
    }

    public Button getReturnButton() { //method returns button for use in other classes
        return returnButton;
    }


    public boolean isAiCheckBoxSelected(){ //method returns state of aiCheckBox
        System.out.println(sizeTextField.getWidth());
        return aiCheckBox.isSelected();
    }


    public int getSizeOfBoard(){ //method returns size of board from sizetextfield as integer
        if(sizeTextField.getText().isEmpty()){
            sizeTextField.setText("4");
        }
        if(Integer.parseInt(sizeTextField.getText())<4){
            sizeTextField.setText("4");
        }
        if(Integer.parseInt(sizeTextField.getText())>16){
            sizeTextField.setText("16");
        }
        return Integer.parseInt(sizeTextField.getText());
    }


    public int getMaxTime(){ //method returns number of seconds after the game will end as integer
        if(!timeTextField.getText().isEmpty()){
            return Integer.parseInt(timeTextField.getText());
        }
        return 0;

    }


    public int whichSet(){ //method returns number of selected pieces set
        if(set1Button.isSelected()){
            return 1;
        }
        if(set2Button.isSelected()){
            return 2;
        }
        if(set3Button.isSelected()){
            return 3;
        }
        return -1;
    }
}
