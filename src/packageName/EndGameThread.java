package packageName;
//class for new javafx thread that ends the game
public class EndGameThread extends Thread{
    private NewGame newGame;
    EndGameThread(NewGame newGame){
        this.newGame = newGame;
    }
    public void run(){
        newGame.endOfTime();
    }
}
