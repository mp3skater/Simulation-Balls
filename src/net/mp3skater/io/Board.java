package net.mp3skater.io;

import net.mp3skater.GamePanel;

import java.awt.*;

public class Board {

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(0,0, GamePanel.WIDTH, GamePanel.HEIGHT);
    }
}