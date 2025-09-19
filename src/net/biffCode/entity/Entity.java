package net.biffCode.entity;


import java.awt.image.BufferedImage;

public class Entity {
    int x, y;
    int speed;
    public BufferedImage spriteSheet;
    public String direction;
    public boolean spriteVersion = true;
    public int spriteVersionCount = 0;
}
