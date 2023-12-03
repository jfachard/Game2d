package src.Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.main.Game;
import src.utilz.LoadSave;

import static src.utilz.Constants.PlayerConstants.*;
import static src.utilz.HelpMethods.*;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 5;
    private int playerAction = IDLE;
    private boolean moving = false , jumping = false;
    private boolean left, up, right, down;
    private float playerSpeed = 4.0f;
    private int [][] lvlData;
    private float xDrawOffset = 15 * Game.SCALE;
    private float yDrawOffset = 8 * Game.SCALE; 
    
    public Player(float x, float y, int width, int height){
        super(x, y, width, height);
        loadAnimation();
        initHitbox(x, y, 18*Game.SCALE, 19*Game.SCALE);
    }

    public void update(){
        
        updatePos();
        updateAniTick();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex], (int)(hitbox.x - xDrawOffset), (int)(hitbox.y - yDrawOffset), width*2, height*2,null);
        drawHitbox(g);
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
        if(!left && !right && !up && !down){
            return;
        }

        float xSpeed = 0, ySpeed = 0;

        if(left && !right){
            xSpeed = -playerSpeed;
        } else if(right && !left){
            xSpeed = playerSpeed;
        }
        
        if(up && !down){
            ySpeed = -playerSpeed;
        } else if(down && !up){
            ySpeed = playerSpeed;
        }

        if(CanMoveHeare(hitbox.x+xSpeed, hitbox.y+ySpeed, hitbox.width, hitbox.height, lvlData)){
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
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

    public void loadLvlData(int[][] lvlData){
        this.lvlData = lvlData;
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
