package net.mp3skater.main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();

    // VARIABLES
    private static final int NUM_BALLS = 20;
    private static final double SLOW = 0.7;
    private static final double FORCE = 0.5;
    private static final double GRAV = 0.18;
    private static int grav_dir = 1;

    // OTHER
    private static boolean ex_spacePressed = false;
    private static boolean ex_nPressed = false;
    private static boolean borderActive = true;

    // BALLS
    public static ArrayList<Ball> balls = new ArrayList<>();

    // PAUSE
    private boolean exPause = false; // To see if Pause has been changed
    private boolean isPause = false;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        // Implement KeyListener:
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);

        Utils.setBalls(balls, NUM_BALLS);
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
    private void setZero() {
        ArrayList<Ball> balls1 = balls;
        for(Ball b : balls1) {
            b.setVecX(0);
            b.setVecY(0);
        }
        balls = balls1;
    }
    private void changeDir() {
        if(grav_dir >= 4) {
            grav_dir = 0;
        }
        else{
            grav_dir++;
        }
    }
    private void changePauseState() {
        isPause = !isPause;
    }
    private void update() {

        if(KeyHandler.spacePressed && !ex_spacePressed) changeDir();
        ex_spacePressed = KeyHandler.spacePressed;

        if(KeyHandler.nPressed && !ex_nPressed)
            borderActive = !borderActive;
        ex_nPressed = KeyHandler.nPressed;

        boolean ex_zeroPressed = false;
        if(KeyHandler.zeroPressed && !ex_zeroPressed) setZero();
        ex_spacePressed = KeyHandler.spacePressed;

        if(KeyHandler.pausePressed && !exPause) changePauseState(); // Pause, when pause is being pressed
        exPause = KeyHandler.pausePressed; // Update ex pause
        if(isPause) return; // Don't continue if Game paused

        ArrayList<Ball> temp = balls; // Temporary list of balls to be switched with the real ones in the end

        for (Ball b : temp) {
            // To avoid Balls from bouncing forever
            if(Math.abs(b.getVec()[0]) < 0.0000001)
                b.setVecX(0);
            if(!borderActive)
                b.addGravity(GRAV, grav_dir);
            if(b.getPos()[1] < HEIGHT - b.getSize() || b.getPos()[1] > 0)
                b.addGravity(GRAV, grav_dir);

            if(borderActive)
                updateAngle(b); // Change the angle when ball bounces off side

            // Throws balls towards the mouse
            if(mouse.pressed) {
                b.addVec(FORCE*(mouse.x - (b.getSize()/2.0) - b.getPos()[0])/(b.getSize()+500),//Math.abs(mouse.x - b.getPos()[0]),
                        FORCE*(mouse.y - (b.getSize()/2.0) - b.getPos()[1])/(b.getSize()+500)//Math.abs(mouse.y - b.getPos()[1])
                        );
            }

            b.applyVec(); // Finally, apply the movements
        }

        balls = temp;
    }
    public void updateAngle(Ball ball) {
        // Variables for increased efficiency
        ball.applyVec();
        double x = ball.getX();
        double y = ball.getY();
        int r = ball.getSize();

        if(x >= WIDTH - r) {
            ball.turnX();
            ball.setX(WIDTH-r);
            ball.slow(SLOW);
        }
        else if(x <= 0) {
            ball.turnX();
            ball.setX(0);
            ball.slow(SLOW);
        }

        if(y >= HEIGHT - r) {
            ball.turnY();
            ball.setY(HEIGHT-r);
            ball.slow(SLOW);
        }
        else if(y <= 0) {
            ball.turnY();
            ball.setY(0);
            ball.slow(SLOW);
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        // BOARD
        board.draw(g2);

        // PIECES
        for(Ball b : balls) b.draw(g2);

        // GRAV DIRECTION
        g2.setFont(new Font ("Courier New", Font.BOLD, 40));
        g2.drawString(String.valueOf(grav_dir), 25, 40);

        // GRAV DIRECTION
        g2.drawString("Border: " + (borderActive ? "active" : "not active"), 70, 40);

        // PAUSE
        if(isPause) {
            g2.setColor(Color.gray);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            g2.fillRect(0, 0, WIDTH, HEIGHT);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            g2.setColor(Color.getColor("purple", 0x8942c8));
            g2.drawString("Game Paused", WIDTH/2-132, 315);
        }
    }
}
