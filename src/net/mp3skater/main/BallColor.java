package net.mp3skater.main;

import java.awt.*;

public enum BallColor {

    MAGENTA(Color.magenta),
    YELLOW(Color.yellow),
    GREEN(Color.green),
    RED(Color.red),
    ORANGE(Color.orange),
    PINK(Color.pink),
    CYAN(Color.cyan),
    BLUE(Color.blue);

    public final Color color;

    BallColor(Color color) {
        this.color = color;
    }
}
