package net.biffCode.entity;

import net.biffCode.GameScreen;
import net.biffCode.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Player extends Entity{
    GameScreen screen;
    KeyHandler keyHandler;
    String name;

    public Player(GameScreen GS, KeyHandler KH){
        screen = GS;
        keyHandler = KH;
        this.setDefault();
        this.loadSpriteSheet();
        direction= "forward";
    }
    public void setDefault(){
        x = 100;
        y =100;
        speed = 4;
    }
    public void update(){
        if (keyHandler.up){
            y-=speed;
            direction = "back";
        }
        else if (keyHandler.down){
            y+=speed;
            direction = "forward";
        }
        else if (keyHandler.left){
            x-=speed;
            direction = "left";
        }
        else if (keyHandler.right){
            x+=speed;
            direction = "right";
        }
    }
    public void draw(Graphics2D g2d){
        //g2d.setColor(Color.white);
        //g2d.fillRect(x,y,screen.tileSize,screen.tileSize);
        switch (direction){
            case "forward": g2d.drawImage(spriteSheet.getSubimage(0,0,32,32),x,y,64,64, null);break;
            case "back": g2d.drawImage(spriteSheet.getSubimage(0,64,32,32),x,y,64,64, null);break;
            case "left": g2d.drawImage(spriteSheet.getSubimage(0,128,32,32),x,y,64,64, null);break;
            case "right": g2d.drawImage(spriteSheet.getSubimage(0,96,32,32),x,y,64,64, null);break;
        }
       }
    public void loadSpriteSheet(){
        try {
            spriteSheet = ImageIO.read(getClass().getResource("/resources/player/SpriteSheet.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
