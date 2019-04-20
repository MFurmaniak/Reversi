package packageName;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.util.Random;

public class NewGame extends HBox { // class for game view and handling game events
    private Label player = new Label("Player 1");
    private Button returnButton = new Button("Return");
    private GridPane board = new GridPane();
    private Piece pieces[][];
    private Field fields[][];
    private int turn;
    private boolean countTime = true;
    private Label timerLabel = new Label("0");
    private Image player1Image = new Image("packageName/White.png");
    private Image player2Image = new Image("packageName/Black.png");
    private Image availableImage = new Image("packageName/Available.png");
    private int size;
    private boolean ai;

    NewGame(int size, boolean ai, int set, int maxTime){ //creates the view of the game window and init the game
        super();
        turn = 1;
        this.size = size;
        this.ai = ai;
        TilePane leftSideBar = new TilePane(Orientation.VERTICAL,0,25);
        leftSideBar.setAlignment(Pos.CENTER_LEFT);
        board.setAlignment(Pos.CENTER_LEFT);
        if(set == 2){
            player2Image = new Image("packageName/Blue.png");
        }
        if(set == 3){
            player1Image = new Image("packageName/Blue.png");
        }
        leftSideBar.getChildren().addAll(timerLabel,player,returnButton);
        pieces = new Piece[size][size];
        fields = new Field[size][size];
        for(int i=0; i < size; i++){
            for(int j=0; j < size; j++){
                Image fieldImage = new Image("packageName/Field.png");
                fields[i][j] = new Field(fieldImage,i,j);
                    board.add(fields[i][j], i, j);
                    addFieldEvent(fields[i][j]);
            }
        }
        initBoard();
        checkFields();

        this.getChildren().addAll(leftSideBar, board);
       Timeline clock = new Timeline(new KeyFrame(Duration.millis(1000),ae->changeTime()));
        clock.setCycleCount(Timeline.INDEFINITE);
       clock.play();


    }

    public Button getReturnButton() { //returns button for use in other places
        return returnButton;
    }

    private void initBoard(){ // initialize board with pieces
        pieces[size/2][size/2] = new Piece(player1Image,size/2,size/2);
        board.add(pieces[size/2][size/2],size/2,size/2);
        fields[size/2][size/2].setPiece(1);

        pieces[size/2-1][size/2-1] = new Piece(player1Image,size/2-1,size/2-1);
        board.add(pieces[size/2-1][size/2-1],size/2-1,size/2-1);
        fields[size/2-1][size/2-1].setPiece(1);

        pieces[size/2-1][size/2] = new Piece(player2Image,size/2-1,size/2);
        board.add(pieces[size/2-1][size/2],size/2-1,size/2);
        fields[size/2-1][size/2].setPiece(2);

        pieces[size/2][size/2-1] = new Piece(player2Image,size/2-1,size/2);
        board.add(pieces[size/2][size/2-1],size/2,size/2-1);
        fields[size/2][size/2-1].setPiece(2);
    }
    private int translateDirection(int z, int k){ // changes a set of index differences into a direction number, -1 if set is wrong
        if(z == 0 && k == -1){
            return 0;
        }
        if(z == 1 && k == -1){
            return 1;
        }
        if(z == 1 && k == 0){
            return 2;
        }
        if(z == 1 && k == 1){
            return 3;
        }
        if(z == 0 && k == 1){
            return 4;
        }
        if(z == -1 && k == 1){
            return 5;
        }
        if(z == -1 && k == 0){
            return 6;
        }
        if(z == -1 && k == -1){
            return 7;
        }
        return -1;
    }
    private boolean checkDirection(int z, int k, Field field){ //checks move in one direction for one field
        int x = field.getxInt();
        int y = field.getyInt();
        if(x + z >= 0 && x + z < size && y + k >=0 && y + k < size){
           if(fields[x+z][y+k].getPiece() == 2 - turn + 1){
                while(x + z >= 0 && x + z < size && y + k >=0 && y + k < size){
                    x = x + z;
                    y = y + k;
                    if (fields[x][y].getPiece() == 0) {
                        pieces[x][y] = new Piece(availableImage, x, y);
                        addPieceEvent(pieces[x][y]);
                        board.add(pieces[x][y], x, y);
                        fields[x][y].setPiece(4);
                        fields[x][y].setMovesValue(translateDirection(z,k));
                        return true;
                    }
                    if (fields[x][y].getPiece() == 4) {
                        fields[x][y].setMovesValue(translateDirection(z,k));
                        return false;
                    }
                    if (fields[x][y].getPiece() == turn){
                        return false;
                    }

                }
           }
        }
        return false;
    }

    private int checkFields(){ //checks all moves for all fields
        int ruchy = 0;
        for(int i = 0; i<size; i++){
            for(int j = 0 ; j<size ; j++){
                if(fields[i][j].getPiece() == turn){
                    if(checkDirection(0,1,fields[i][j])){
                        ruchy++;
                    }
                    if(checkDirection(0,-1,fields[i][j])){
                        ruchy++;
                    }
                    if(checkDirection(1,0,fields[i][j])){
                        ruchy++;
                    }
                    if(checkDirection(-1,0,fields[i][j])){
                        ruchy++;
                    }
                    if(checkDirection(1,1,fields[i][j])){
                        ruchy++;
                    }
                    if(checkDirection(-1,1,fields[i][j])){
                        ruchy++;
                    }
                    if(checkDirection(1,-1,fields[i][j])){
                        ruchy++;
                    }
                    if(checkDirection(-1,-1,fields[i][j])){
                        ruchy++;
                    }
                }
            }
        }
        return ruchy;
    }
    private void swapDirection(int z, int k, Field field){ // swaps pieces for one direction and field
        int x = field.getxInt();
        int y = field.getyInt();
        int direction = translateDirection(z, k);
        if(field.getMovesValue(direction)){
            while(x - z >= 0 && x - z < size && y - k >=0 && y - k < size && fields[x-z][y-k].getPiece()!= turn){
                x = x - z;
                y = y - k;
                fields[x][y].setPiece(turn);
                board.getChildren().remove(pieces[x][y]);
                if(turn == 1){
                    pieces[x][y] = new Piece(player1Image, x, y);
                }
                else{
                    pieces[x][y] = new Piece(player2Image, x, y);
                }
                board.add(pieces[x][y],x,y);
            }
        }
    }
    private void swapPieces(Field field){ //swaps all pieces possible from one field
        swapDirection(0,-1,field);
        swapDirection(1,-1,field);
        swapDirection(1,0,field);
        swapDirection(1,1,field);
        swapDirection(0,1,field);
        swapDirection(-1,1,field);
        swapDirection(-1,0,field);
        swapDirection(-1,-1,field);

    }

    private int countPieces(int player) { //counts number of pieces for a player
        int numberOfPieces = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (fields[i][j].getPiece() == player) {
                    numberOfPieces++;
                }
            }
        }
        return numberOfPieces;
    }
    private void removeAvailable(){ //removes available moves
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fields[i][j].clearMoves();
                if (fields[i][j].getPiece() == 4) {
                    fields[i][j].setPiece(0);
                    board.getChildren().remove(pieces[i][j]);
                }
            }
        }
    }

    private void changeTurn(){ //turn change
        turn = 2 - turn + 1;
        if(turn==2){
            player.setText("Player 2");
        }
        else{
            player.setText("Player 1");
        }
    }
    private void aiPlay(int a){ // ai move pattern
        Random rand = new Random();
        int b = rand.nextInt(a)+1;
        int next;
        int x = 0;
        int y = 0;
        while(b>0){
            for(int i = 0; i<size; i++){
                for(int j = 0; j<size; j++){
                    if(fields[i][j].getPiece()==4){
                        b--;
                    }
                    if(b==0){
                        x = i;
                        y = j;
                        b = -1;
                    }
                }
            }
            pieces[x][y] = new Piece(player2Image, x ,y);
            fields[x][y].setPiece(turn);
            board.setConstraints(pieces[x][y], x, y);
            board.getChildren().add(pieces[x][y]);
            swapPieces(fields[x][y]);
            removeAvailable();
            changeTurn();
            next = checkFields();
            if(next == 0){
                removeAvailable();
                changeTurn();

                next = checkFields();
                if(turn == 2 && next != 0){
                    aiPlay(b);
                }

                if(next == 0){
                    endGame();
                }
            }
        }
    }

    private void endGame(){ //ends game, stops counter and creates a new window with information about winner
        countTime=false;
        new EndWindow(countPieces(1),countPieces(2));
    }
    public void endOfTime(){ // ends game and removes available moves
        endGame();
        removeAvailable();
    }
    private void changeTime(){ //method for counter, changes the label of timer
        if(countTime){
            timerLabel.setText(Integer.toString(Integer.parseInt(timerLabel.getText())+1));
        }

    }
    public int getTime(){ // returns time
        return Integer.parseInt(timerLabel.getText());
    }
    private void addFieldEvent(Field field){
        field.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int x = field.getxInt();
                int y = field.getyInt();
                if(fields[x][y].getPiece() == 4) {
                    fields[x][y].setPiece(turn);
                    if (turn == 2) {
                        pieces[x][y] = new Piece(player2Image, x ,y);
                    } else {
                        pieces[x][y] = new Piece(player1Image, x ,y);
                    }
                    board.setConstraints(pieces[x][y], x, y);
                    board.getChildren().add(pieces[x][y]);
                    swapPieces(fields[x][y]);
                    changeTurn();
                    removeAvailable();
                    int a = checkFields();
                    if(a == 0){
                        changeTurn();
                        removeAvailable();
                        a = checkFields();
                        if(a == 0){
                            endGame();
                        }
                    }
                    if(ai && turn == 2 && a!= 0){
                        aiPlay(a);
                    }
                }
            }
        });
    } //behaviour of the mouse button click on a field

    private void addPieceEvent(Piece piece){
        piece.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int x = piece.getxInt();
                int y = piece.getyInt();
                if(fields[x][y].getPiece() == 4) {
                    fields[x][y].setPiece(turn);
                    if (turn == 2) {
                        pieces[x][y] = new Piece(player2Image, x ,y);
                    } else {
                        pieces[x][y] = new Piece(player1Image, x ,y);
                    }
                    board.setConstraints(pieces[x][y], x, y);
                    board.getChildren().add(pieces[x][y]);
                    swapPieces(fields[x][y]);
                    changeTurn();
                    removeAvailable();
                    int a = checkFields();
                    if(a == 0){
                        changeTurn();
                        removeAvailable();
                        a = checkFields();
                        if(a == 0){
                            endGame();
                        }
                    }
                    if(ai && turn == 2 && a !=0){
                        aiPlay(a);
                    }
                    System.out.println(countPieces(1) + " , " + countPieces(2));
                }
            }
        });
    } //behaviour of mouse click on piece

}

