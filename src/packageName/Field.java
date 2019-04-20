package packageName;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//class for every field on a game board
//contains info about piece that is on, position in grid pane, look of the field,
// and information about direction of possible moves
public class Field extends ImageView {
    private int xInt;
    private int piece;
    private int yInt;
    private boolean[] moves = new boolean[8];

    public Field(Image image, int xInt, int yInt){
        super(image);
        this.xInt = xInt;
        this.yInt = yInt;
        this.piece = 0;
        for(int i = 0 ; i<8; i++){
            moves[i] = false;
        }
    }

    public int getPiece() { //returns piece number as int
        return piece;
    }

    public void setPiece(int piece) { //changes the piece that is on
        this.piece = piece;
    }


    public int getxInt() { //returns column number of the index as int
        return xInt;
    }

    public int getyInt() { //returns row number of the index as int
        return yInt;
    }
    public boolean getMovesValue(int direction){ // returns true if there is a move in given direction
        return moves[direction];
    }
    public void setMovesValue(int direction){ // sets true for the given direction
        moves[direction] = true;
    }
    public void clearMoves(){ //clears all moves
        for(int i = 0 ; i<8; i++){
            moves[i] = false;
        }
    }

}
