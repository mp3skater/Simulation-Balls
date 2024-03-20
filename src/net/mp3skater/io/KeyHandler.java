package net.mp3skater.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public static boolean pausePressed;
    public static boolean spacePressed;
    public static boolean zeroPressed;
    public static boolean nPressed;

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_ESCAPE) {
            pausePressed = true;
        }
        if(keyCode == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        if(keyCode == KeyEvent.VK_0) {
            zeroPressed = true;
        }
        if(keyCode == KeyEvent.VK_N) {
            nPressed = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_ESCAPE) {
            pausePressed = false;
        }
        if(keyCode == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if(keyCode == KeyEvent.VK_0) {
            zeroPressed = false;
        }
        if(keyCode == KeyEvent.VK_N) {
            nPressed = false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
}
