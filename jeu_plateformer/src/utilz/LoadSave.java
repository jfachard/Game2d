package src.utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.main.Game;

public class LoadSave {

    public static final String PLAYER_ATLAS = "satyr_Sheet.png";
    public static final String LEVEL_ATLAS = "Tileset.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";

    public static BufferedImage getSpriteAtlas(String filename){

        BufferedImage image = null;
        File fimg = new File("./res/" + filename);
        try {
            image = ImageIO.read(fimg);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public static int [][] getLevelData(){

        int [][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = getSpriteAtlas(LEVEL_ONE_DATA);

        for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if (value >= 150)
					value = 0;
				lvlData[j][i] = value;
			}
		return lvlData;
    }
}
