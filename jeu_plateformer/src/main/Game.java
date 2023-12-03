package src.main;

import java.awt.Graphics;

import src.Entity.Player;
import src.Levels.LevelManagers;

public class Game implements Runnable{

    private GameWindow gw;
    private GamePanel gp;
    private Thread gameThread;
    private final int FPS_SET = 60;
    private final int UPS_SET = 60;

    private Player player;
    private LevelManagers levelManagers;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
    
    public Game(){

        initClasses();
        gp = new GamePanel(this);
        gw = new GameWindow(gp);
        gp.requestFocus();
        startGameLoop();

    }

    private void initClasses() {
        levelManagers = new LevelManagers(this);
        player = new Player(200, 200, (int)(32*SCALE), (int)(32*SCALE));
        player.loadLvlData(levelManagers.getCurrentLvel().getLevelData());
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        levelManagers.update();
        player.update();
    }

    public void render(Graphics g){
        levelManagers.draw(g);
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
