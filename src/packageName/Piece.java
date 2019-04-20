package packageName;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// class for a piece contains image and position on board
public class Piece extends ImageView {
    private int xInt;
    private int yInt;
    Piece(Image image, int x, int y){
        super(image);
        this.xInt = x;
        this.yInt = y;
    }

    public int getxInt() { ///returns column index of the piece as int
        return xInt;
    }

    public int getyInt() { //returns row index of the piece as int
        return yInt;
    }
}
