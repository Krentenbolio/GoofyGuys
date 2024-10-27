/*
 * Handles the timer.
 */

public class Refresh extends Thread {
    private GamePanel gp;

    public Refresh(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void run() {
        int countdown = 15;
        while (countdown > -1) {
            gp.setCountdownTime(countdown);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countdown--;
        }
        gp.stopGame();
    }
}
