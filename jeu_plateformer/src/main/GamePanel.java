package src.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import src.Inputs.keyBoardInputs;
import src.Inputs.mouseInputs;

import static src.utilz.Constants.PlayerConstants.*;
import static src.utilz.Constants.Direction.*;

public class GamePanel extends JPanel{

    private mouseInputs mInputs;
    private float xDelta = 100; 
    private float yDelta = 100;
    private BufferedImage image;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 5;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;


    
    public GamePanel() {

        importImg();
        loadAnimation();
        mInputs = new mouseInputs(this);
        setPanelSize();
        addKeyListener(new keyBoardInputs(this));
        addMouseListener(new mouseInputs(this));
        addMouseMotionListener(mInputs);
    }

    private void importImg() {

        File fimg = new File("./res/satyr_Sheet.png");
        try {
            image = ImageIO.read(fimg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadAnimation() {

        animations = new BufferedImage[5][9];

        for(int j = 0; j < animations.length; ++j){
            for(int i = 0; i < animations[j].length; ++i){
                animations[j][i] = image.getSubimage(i*32, j*32, 32, 32);
            }
        }

    }

    private void setPanelSize() {

        Dimension size = new Dimension(1280,800);
        setPreferredSize(size);
    }

    public void setDirection(int dir){

        this.playerDir = dir;
        moving = true;

    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }

    private void updateAniTick() {
        
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= getSpriteAmount(playerAction)){
                aniIndex = 0;
            }
        }
    }

    private void setAnimation() {

        if(moving){
            playerAction = RUNNING;
        }
        else{
            playerAction = IDLE;
        }
    }

    private void updatePos() {
        
        if(moving){
            switch (playerDir) {
                case LEFT:
                    xDelta -=5;
                    break;
                case UP:
                    yDelta -=5;
                    break;
                case RIGHT:
                    xDelta +=5;
                    break;
                case DOWN:
                    yDelta +=5;
                    break;
            }
        }
    }

    public void updateGame(){

        updateAniTick();
        setAnimation();
        updatePos();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(animations[playerAction][aniIndex], (int)xDelta, (int)yDelta, 128, 128,null);

    }

}
