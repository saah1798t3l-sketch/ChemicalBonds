package net.biffCode.tiles;

import net.biffCode.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GameScreen screen;
    Tile[] tiles;
    int[][] mapTiles;

    public TileManager(GameScreen screen){
        this.screen = screen;
        tiles = new Tile[10];
        mapTiles = new int[screen.columns][screen.rows];
        getTileImage();
        loadMap("/resources/maps/map1.txt");

    }
    public void getTileImage(){
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/Tiles/brick00.png"));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/Tiles/grass00.png"));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/Tiles/grass01.png"));

        } catch(IOException IOE){
            IOE.printStackTrace();
        }
    }
    public void loadMap(String map){
        try{
            InputStream inputStream = getClass().getResourceAsStream(map);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            int col = 0;
            int row = 0;
            while (col < screen.columns && row < screen.rows){
                String line = reader.readLine();
                while(col < screen.columns){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTiles[col][row] = num;
                    col++;
                }
                if (col == screen.columns){
                    col = 0;
                    row++;
                }
            }

        }catch (Exception e){

        }
    }
    public void draw(Graphics2D g2d){
        int column = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (column < screen.columns && row < screen.rows){
            g2d.drawImage(tiles[mapTiles[column][row]].image,x,y,screen.tileSize, screen.tileSize, null);
            column++;
            x+=screen.tileSize;
            if (column == screen.columns){
                x = 0;
                column = 0;
                y+=screen.tileSize;
                row++;
            }
        }

    }


}
