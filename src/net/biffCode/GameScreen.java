package net.biffCode;

import net.biffCode.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel implements Runnable{
    final int OGtileSize = 32; //16*16 tile sizes
    final int scale = 2; //scales tilesize
    public final int tileSize = OGtileSize*scale;
    final int columns = 16;
    final int rows = 12;
    final int screenWidth = columns*tileSize;
    final int screenHeight = rows* tileSize;
    Thread gameThread;
    int FPS = 60;
    Player player = new Player(this);

    public GameScreen(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(player.keyListner);
        this.setFocusable(true);
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        //gameLoop
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null){
            //Update info (e.g. pos)
            //Update info (e.g. H)
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta > 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        player.draw(g2d);
        g2d.dispose();
    }
}
