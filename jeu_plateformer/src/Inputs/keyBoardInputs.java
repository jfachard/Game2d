package src.Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import src.main.GamePanel;

public class keyBoardInputs implements KeyListener{

    private GamePanel gp;

    public keyBoardInputs(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
        
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            
            case KeyEvent.VK_UP:
                gp.getGame().getPlayer().setUp(true);
                break;
            case KeyEvent.VK_DOWN:
                gp.getGame().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_LEFT:
                gp.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
                gp.getGame().getPlayer().setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                gp.getGame().getPlayer().setJumping(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            
            case KeyEvent.VK_UP:
                gp.getGame().getPlayer().setUp(false);
                break;
            case KeyEvent.VK_DOWN:
                gp.getGame().getPlayer().setDown(false);
                break;
            case KeyEvent.VK_LEFT:
                gp.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_RIGHT:
                gp.getGame().getPlayer().setRight(false);
                break;
        }
    }
    
}
