package src.Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import src.main.Game;
import src.utilz.LoadSave;

public class LevelManagers {

    private Game game;
    private BufferedImage [] levelSprite;
    private Level levelOne;
    
    public LevelManagers(Game game){
        this.game = game;
    //  levelSprite = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        importOustsideSprite();
        levelOne = new Level(LoadSave.getLevelData());
    }

    private void importOustsideSprite() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[150];
        for(int j = 0 ; j < 10; ++j){
            for(int i = 0; i < 15; ++i){
                int index = j*15 + i;
                levelSprite[index] = img.getSubimage(i*32, j*32, 32, 32);
            }
        }

    }

    public void draw(Graphics g){
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
			for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
				int index = levelOne.getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
			}
    }

    public void update(){

    }
}
