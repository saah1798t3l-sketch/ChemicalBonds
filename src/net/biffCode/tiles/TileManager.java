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
        mapTiles = new int[screen.totalColumns][screen.totalRows];
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
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/resources/Tiles/brick01.png"));
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
            while (col < screen.totalColumns && row < screen.totalRows){
                String line = reader.readLine();
                while(col < screen.totalColumns){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTiles[col][row] = num;
                    col++;
                }
                if (col == screen.totalColumns){
                    col = 0;
                    row++;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2d){
        int worldColumn = 0;
        int worldRow = 0;

        while (worldColumn < screen.totalColumns && worldRow < screen.totalRows){
            int x = worldColumn*screen.tileSize - screen.player.worldX;
            int y = worldRow*screen.tileSize - screen.player.worldY;
            g2d.drawImage(tiles[mapTiles[worldColumn][worldRow]].image,x,y,screen.tileSize, screen.tileSize, null);
            worldColumn++;
            if (worldColumn == screen.totalColumns){
                worldColumn = 0;
                worldRow++;
            }
        }

    }


}
