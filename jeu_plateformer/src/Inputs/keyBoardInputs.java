package src.Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static src.utilz.Constants.Direction.*;

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
                gp.setDirection(UP);
                break;
            case KeyEvent.VK_DOWN:
                gp.setDirection(DOWN);
                break;
            case KeyEvent.VK_LEFT:
                gp.setDirection(LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                gp.setDirection(RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                gp.setMoving(false);
                break;
        }
    }
    
}
