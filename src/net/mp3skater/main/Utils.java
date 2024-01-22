package net.mp3skater.main;

import java.util.ArrayList;

public class Utils {

    public static Ball randomBall() {
        // Returns a ball with random parameters within the set boundaries
        double dirX = Math.random()*2-1;
        return new Ball((int) (Math.random()*460+70),
                (int) (Math.random()*460+70),
                plusOrMinus(Math.abs(dirX)),
                plusOrMinus(1 - Math.abs(dirX)),
                (int) (Math.random()*70+30),
                Math.random()*10+20,
                randomColor());
    }
    public static double plusOrMinus(double init) {
        // Returns 50/50 positive or negative value
        return Math.random()>0.5 ? init : -init;
    }
    public static BallColor randomColor() {
        int r = (int) (Math.random()*8);

        return switch (r) {
            case 0 -> BallColor.MAGENTA;
            case 2 -> BallColor.YELLOW;
            case 3 -> BallColor.GREEN;
            case 4 -> BallColor.RED;
            case 5 -> BallColor.ORANGE;
            case 7 -> BallColor.PINK;
            case 8 -> BallColor.CYAN;
            default -> BallColor.BLUE;
        };
    }
    public static void setBalls(ArrayList<Ball> balls) {
        for(int i = 0; i < 12; i++)
            balls.add(randomBall());
    }
}
