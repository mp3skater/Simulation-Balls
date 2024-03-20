package net.mp3skater;

import net.mp3skater.ball.Ball;
import net.mp3skater.ball.BallColor;

import java.util.ArrayList;

public class Utils {

    public static Ball randomBall() {
        // Returns a ball with random parameters within the set boundaries
        return new Ball(Math.random()*460+70,
                Math.random()*460+70,
                Math.random()*8+2,
                Math.random()*8+2,
                (int) (Math.random()*30+30),
                randomColor());
    }
    public static BallColor randomColor() {
        int r = (int) (Math.random()*7);

        return switch (r) {
            case 0 -> BallColor.MAGENTA;
            case 1 -> BallColor.CYAN;
            case 2 -> BallColor.GREEN;
            case 3 -> BallColor.RED;
            case 4 -> BallColor.ORANGE;
            case 5 -> BallColor.PINK;
            default -> BallColor.BLUE;
        };
    }
    public static void setBalls(ArrayList<Ball> balls, int numb_balls) {
        for(int i = 0; i < numb_balls; i++) {
            balls.add(randomBall());
        }
    }
    public static Ball createSun() {
        return new Ball(100, 100, 0, 0, 200, BallColor.YELLOW);
    }
}
