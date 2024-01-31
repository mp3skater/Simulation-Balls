package net.mp3skater.main;

import java.awt.*;

public class Ball {

    // VARIABLES
    private final double[] pos;
    private final double[] vec;
    private final int r;

    // COLOR
    private final BallColor ballColor;

    // CONSTRUCTOR
    public Ball(double x, double y, double vecX, double vecY, int radius, BallColor ballColor) {

        pos = new double[]{x, y};
        vec = new double[]{vecX, vecY};
        r = radius;
        this.ballColor = ballColor;
    }

    // DRAW
    public void draw(Graphics2D g2) {
        g2.setColor(ballColor.color);
        g2.fillOval((int) this.getX(),(int) this.getY(), r*2, r*2);
        g2.setColor(Color.black);
        g2.drawOval((int) this.getX(),(int) this.getY(), r*2, r*2);
    }

    // GETTER AND SETTER
    public double getX() {
        return pos[0];
    }
    public double getY() {
        return pos[1];
    }
    public void setX(double x) {
        pos[0] = x;
    }
    public void setY(double y) {
        pos[1] = y;
    }
    public void turnX() {
        vec[0] *=-1;
    }
    public void turnY() {
        vec[1] *=-1;
    }
    public double[] getPos() {
        return pos;
    }
    public double[] getVec() {
        return vec;
    }
    public void addVec(double new_x, double new_y) {
        vec[0] += new_x;
        vec[1] += new_y;
    }
    public void setVecX(double new_x) {
        vec[0] = new_x;
    }
    public void setVecY(double new_y) {
        vec[1] = new_y;
    }
    public void addGravity(double grav, int direction) {
        switch(direction) {
            case 1: vec[1] += grav; break;
            case 2: vec[1] -= grav; break;
            case 3: vec[0] += grav; break;
            case 4: vec[0] -= grav; break;
        }
    }
    public int getRadius() {
        return r;
    }
    public void applyVec() {
        pos[0] += vec[0];
        pos[1] += vec[1];
    }
    public void slow(double slow) {
        vec[0] *= slow;
        vec[1] *= slow;
    }
}
