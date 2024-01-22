package net.mp3skater.main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();

    // BALLS
    public static ArrayList<Ball> balls = new ArrayList<>();

    // PAUSE
    private boolean exPause = false; // To see if Pause has been changed
    private boolean isPause = false;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.getColor("gray", 0x333333));
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        // Implement KeyListener:
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);

        Utils.setBalls(balls);
    }
    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        // GAME LOOP
        double drawInterval = 1_000_000_000d/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime-lastTime)/drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
    private void changePauseState() {
        isPause = !isPause;
    }
    private void update() {

        // Pause, when pause is being pressed
        if(KeyHandler.pausePressed && !exPause) changePauseState();
        exPause = KeyHandler.pausePressed;

        // Don't continue if Game paused
        if(isPause) return;

        ArrayList<Ball> temp = balls;

        // Slow balls down so they don't go on forever
        double SLOW = 0.1;
        for (Ball b : temp) {
            double speed = b.getSpeed();
            if(speed >= SLOW) {
                b.setSpeed(speed - SLOW);
            }
            else {
                b.setSpeed(0);
            }
        }

        for(Ball b : temp) {
            updateAngle(b);

            b.setX(b.getX() + (int) (b.getX() * b.getSpeed()));
            b.setY(b.getY() + (int) (b.getY() * b.getSpeed()));
        }

        balls = temp;
    }
    public void updateAngle(Ball ball) {
        // Variables for increased efficiency
        int x = ball.getX();
        int y = ball.getY();
        int r = ball.getRadius();

        if(x >= WIDTH - r) {
            ball.turnX();
            ball.setX(WIDTH-r);
        }
        else if(x <= 0) {
            ball.turnX();
            ball.setX(0);
        }

        if(y >= HEIGHT - r) {
            ball.turnY();
            ball.setY(HEIGHT-r);
        }
        if(y <= 0) {
            ball.turnY();
            ball.setY(0);
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        // BOARD
        board.draw(g2);

        // PIECES
        for(Ball b : balls) {
            b.draw(g2);
        }

        // PAUSE
        if(isPause) {
            g2.setColor(Color.getColor("purple", 0x8942c8));
            g2.setFont(new Font ("Courier New", Font.BOLD, 40));
            g2.drawString("Game Paused", 170, 315);
        }
    }
}
