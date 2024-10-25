public class Refresh extends Thread {
    private GamePanel gp;

    public Refresh(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void run() {
        for (int i = 300; i > 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        gp.stopGame();
    }
}
