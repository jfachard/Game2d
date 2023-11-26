package src.main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import src.Inputs.keyBoardInputs;
import src.Inputs.mouseInputs;



public class GamePanel extends JPanel{

    private mouseInputs mInputs;
    private Game game;

    public GamePanel(Game game) {

        this.game = game;

        mInputs = new mouseInputs(this);
        setPanelSize();
        addKeyListener(new keyBoardInputs(this));
        addMouseListener(mInputs);
        addMouseMotionListener(mInputs);
    }

    
    private void setPanelSize() {

        Dimension size = new Dimension(1280,800);
        setPreferredSize(size);
    }

    public void updateGame(){
  
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame(){
        return game;
    }
}
