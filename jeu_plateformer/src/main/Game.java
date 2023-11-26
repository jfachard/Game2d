package src.main;

import java.awt.Graphics;

import src.Entity.Player;

public class Game implements Runnable{

    private GameWindow gw;
    private GamePanel gp;
    private Thread gameThread;
    private final int FPS_SET = 60;
    private final int UPS_SET = 60;

    private Player player;
    
    public Game(){

        initClasses();
        gp = new GamePanel(this);
        gw = new GameWindow(gp);
        gp.requestFocus();
        startGameLoop();

    }

    private void initClasses() {
        player = new Player(200, 200);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        player.update();
    }

    public void render(Graphics g){
        player.render(g);
    }

    @Override
    public void run() {

        double timePerFrames = 1000000000D/FPS_SET;
        double timePerUpdates = 1000000000D/UPS_SET;
        
        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastChecked = 0;

        double deltaU = 0;
        double deltaF = 0;
        while(true){

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdates;
            deltaF += (currentTime - previousTime) / timePerFrames;
            previousTime = currentTime;
            
            if(deltaU >= 1){ 
                update();
                updates++;
                deltaU--;
            }

            if(deltaF >=1){

                gp.repaint();
                frames ++;
                deltaF--;
            }

            if(System.currentTimeMillis() - lastChecked >=1000){
                lastChecked = System.currentTimeMillis();
                System.out.println(frames + " FPS" + " | " + updates + " UPS");
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost(){
        player.resetDirBoolean();
    }

    public Player getPlayer(){
        return player;
    }
}
