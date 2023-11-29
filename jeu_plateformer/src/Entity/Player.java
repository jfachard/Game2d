package src.Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.utilz.LoadSave;

import static src.utilz.Constants.PlayerConstants.*;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 5;
    private int playerAction = IDLE;
    private boolean moving = false , jumping = false;
    private boolean left, up, right, down;
    private float playerSpeed = 4.0f;
    
    public Player(float x, float y, int width, int height){
        super(x, y, width, height);
        loadAnimation();
    }

    public void update(){
        
        updatePos();
        updateAniTick();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex], (int)x, (int)y, width*2, height*2,null);
    }

    private void updateAniTick() {
        
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= getSpriteAmount(playerAction)){
                aniIndex = 0;
                jumping = false;
            }
        }
    }

    private void setAnimation() {

        int startAni = playerAction;

        if(moving){
            playerAction = RUNNING;
        }
        else{
            playerAction = IDLE;
        }

        if(jumping){
            playerAction = JUMP;
        }

        if(startAni != playerAction){
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {

        moving = false;

        if(left && !right){
            x -= playerSpeed;
            moving = true;
        } else if(right && !left){
            x += playerSpeed;
            moving = true;
        }
        
        if(up && !down){
            y -= playerSpeed;
            moving = true;
        } else if(down && !up){
            y += playerSpeed;
            moving = true;
        }
    }

    private void loadAnimation() {

        BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[5][9];

        for(int j = 0; j < animations.length; ++j){
            for(int i = 0; i < animations[j].length; ++i){
                animations[j][i] = image.getSubimage(i*32, j*32, 32, 32);
            }
        }

    }

    public void resetDirBoolean(){

        left = false;
        up = false;
        right = false;
        down = false;
    }

    public void setJumping(boolean jumping){

        this.jumping = jumping;
    }

    public boolean isLeft(){
        return left;
    }

    public void setLeft(boolean left){
        this.left = left;
    }

     public boolean isUp(){
        return up;
    }

    public void setUp(boolean up){
        this.up = up;
    }

     public boolean isRight(){
        return right;
    }

    public void setRight(boolean right){
        this.right = right;
    }

     public boolean isDown(){
        return down;
    }

    public void setDown(boolean down){
        this.down = down;
    }

    
}
