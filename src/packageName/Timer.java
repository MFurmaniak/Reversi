package packageName;


import javafx.application.Platform;
// class to start the new thread to check if the game time is higher than maximum time
// if so starts another thread  which ends the game
public class Timer extends Thread {
    private int maxTime;
    private NewGame newGame;
    private int time;

    public Timer(int maxTime, NewGame newGame) {
        this.maxTime = maxTime;
        this.newGame = newGame;
    }

    public void run() {
        boolean running = true;
        if(maxTime == 0)
        {
            running = false;
        }
        while(running){
            long sec = 800;
            try {
                Thread.sleep(sec);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time = newGame.getTime();
            System.out.println(time);
            if(time >= maxTime && maxTime != 0){
                System.out.println(time);
                Platform.runLater(new EndGameThread(newGame));
                running=false;
            }
        }
    }

}
