package net.biffCode.entity;

import net.biffCode.GameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Player extends Entity{
    GameScreen screen;
    public PlayerMovementListner keyListner;

    public Player(GameScreen GS){
        screen = GS;
        this.setDefault();
        this.loadSpriteSheet();
        direction= "face fore";
        this.setKeyListner();
    }
    public void setDefault(){
        x = 100;
        y =100;
        speed = 4;
    }

    public void setKeyListner() {
        this.keyListner = new PlayerMovementListner(this);
    }

    public void update(){
        if (this.keyListner.up ){
            y-=speed;
            direction = "back";
        }
        else if (this.keyListner.down){
            y+=speed;
            direction = "forward";
        }
        else if (this.keyListner.west){
            x-=speed;
            direction = "left";
        }
        else if (this.keyListner.east){
            x+=speed;
            direction = "right";
        }
    }
    public void draw(Graphics2D g2d){
        switch (direction){
            case "forward": g2d.drawImage(spriteSheet.getSubimage((spriteVersion)? 32:64,0,screen.OGtileSize,screen.OGtileSize),x,y,64,64, null); break;
            case "back": g2d.drawImage(spriteSheet.getSubimage((spriteVersion)? 32:64,32,screen.OGtileSize,screen.OGtileSize),x,y,64,64, null);break;
            case "left": g2d.drawImage(spriteSheet.getSubimage((spriteVersion)? 32:64,64,screen.OGtileSize,screen.OGtileSize),x,y,64,64, null);break;
            case "right": g2d.drawImage(spriteSheet.getSubimage((spriteVersion)? 32:64,96,screen.OGtileSize,screen.OGtileSize),x,y,64,64, null);break;
            case "face fore": g2d.drawImage(spriteSheet.getSubimage(0,0,screen.OGtileSize, screen.OGtileSize), x, y, 64, 64, null);break;
            case "face back": g2d.drawImage(spriteSheet.getSubimage(0,32,screen.OGtileSize, screen.OGtileSize), x, y, 64, 64, null);break;
            case "face west": g2d.drawImage(spriteSheet.getSubimage(0,64,screen.OGtileSize, screen.OGtileSize), x, y, 64, 64, null);break;
            case "face east": g2d.drawImage(spriteSheet.getSubimage(0,96,screen.OGtileSize, screen.OGtileSize), x, y, 64, 64, null);break;
        }
        if (spriteVersionCount == 10){
            spriteVersionCount = 0;
            spriteVersion = !spriteVersion;
        } else{
            spriteVersionCount++;
        }
       }
    public void loadSpriteSheet(){
        try {
            spriteSheet = ImageIO.read(getClass().getResource("/resources/entity/player.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class PlayerMovementListner implements KeyListener {
        public boolean up, down, east, west;
        public int direction;
        Player player;
        public PlayerMovementListner(Player player){
            super();
            this.player = player;
        }
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){

                case KeyEvent.VK_W, KeyEvent.VK_UP:
                    up = true;
                    break;

                case KeyEvent.VK_A, KeyEvent.VK_LEFT:
                    west = true;
                    break;

                case KeyEvent.VK_S, KeyEvent.VK_DOWN:
                    down = true;
                    break;

                case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
                    east = true;
                    break;
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_W, KeyEvent.VK_UP: up = false; player.direction = "face back"; break;
                case KeyEvent.VK_A, KeyEvent.VK_LEFT: west = false; player.direction = "face west"; break;
                case KeyEvent.VK_S, KeyEvent.VK_DOWN: down = false;player.direction = "face fore"; break;
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT: east = false; player.direction = "face east"; break;
            }
        }
    }
}
