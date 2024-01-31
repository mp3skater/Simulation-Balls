package net.mp3skater.main;

import java.util.ArrayList;

public class Utils {

    public static Ball randomBall() {
        // Returns a ball with random parameters within the set boundaries
        return new Ball(Math.random()*460+70,
                Math.random()*460+70,
                Math.random()*8+2,
                Math.random()*8+2,
                (int) (Math.random()*70+30),
                randomColor());
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
    public static void setBalls(ArrayList<Ball> balls, int numb_balls) {
        for(int i = 0; i < numb_balls; i++) {
            balls.add(randomBall());
        }
    }
}
