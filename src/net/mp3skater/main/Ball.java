package net.mp3skater.main;

import java.awt.*;
import java.awt.geom.Point2D;

public class Ball {

    // VARIABLES
    private static Point2D pos;
    private Point2D vec;
    private final int r;
    private double speed;

    // COLOR
    private final BallColor ballColor;

    // CONSTRUCTORS
    public Ball(int x, int y, double vecX, double vecY, int radius, double speed,  BallColor ballColor) {

        pos = new Point2D() {
            @Override
            public double getX() {
                return x;
            }
            @Override
            public double getY() {
                return y;
            }
            @Override
            public void setLocation(double x, double y) {

            }
        };
        this.vec = new Point2D() {
            @Override
            public double getX() {
                return vecX;
            }

            @Override
            public double getY() {
                return vecY;
            }

            @Override
            public void setLocation(double x, double y) {

            }
        };
        r = radius;
        this.speed = speed;
        this.ballColor = ballColor;
    }
    public Ball(int x, int y, Point2D vec, int radius, double speed,  BallColor ballColor) {

        pos = new Point2D() {
            @Override
            public double getX() {
                return x;
            }
            @Override
            public double getY() {
                return y;
            }
            @Override
            public void setLocation(double x, double y) {

            }
        };
        this.vec = vec;
        r = radius;
        this.speed = speed;
        this.ballColor = ballColor;
    }
    public Ball(Point2D pos, double vecX, double vecY, int radius, double speed,  BallColor ballColor) {

        Ball.pos = pos;
        this.vec = new Point2D() {
            @Override
            public double getX() {
                return vecX;
            }

            @Override
            public double getY() {
                return vecY;
            }

            @Override
            public void setLocation(double x, double y) {

            }
        };
        r = radius;
        this.speed = speed;
        this.ballColor = ballColor;
    }
    public Ball(Point2D pos, Point2D vec, int radius, double speed,  BallColor ballColor) {

        Ball.pos = pos;
        this.vec = vec;
        r = radius;
        this.speed = speed;
        this.ballColor = ballColor;
    }

    // DRAW
    public void draw(Graphics2D g2) {
        g2.setColor(ballColor.color);
        g2.fillOval(this.getX(), this.getY(), r, r);
        g2.setColor(Color.black);
        g2.drawOval(this.getX(), this.getY(), r, r);
    }

    // GETTER AND SETTER
    public void setX(int x) {
        pos.setLocation(x, pos.getY());
    }
    public void setY(int y) {
        pos.setLocation(pos.getX(), y);
    }
    public void turnX() {
        vec.setLocation(vec.getX()*-1, vec.getY());
    }
    public void turnY() {
        vec.setLocation(vec.getX(), vec.getY()*-1);
    }
    public Point2D getPos() {
        return pos;
    }
    public void setPos(Point2D pos) {
        Ball.pos = pos;
    }
    public int getX() {
        return (int) pos.getX();
    }
    public int getY() {
        return (int) pos.getY();
    }
    public Point2D getVec() {
        return vec;
    }
    public void setVec(Point2D vec) {
        this.vec = vec;
    }
    public int getRadius() {
        return r;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public double getSpeed() {
        return speed;
    }
}
