package com.company;

//import javafx.util.Pair;

public abstract class Player {
    // private instance variables
    private int speed;
    private double x;
    private double y;
    private int id;

    //private Pair position;
    //private double tackleChance;
    //private double dodgeChance;


    // constructor
    public Player(int speed, double xPos, double yPos, int id) {
        this.speed = speed;
        this.x = xPos;
        this.y = yPos;
        this.id = id;
    }

    public Player() {

    }

    // getter and setter methods
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // difference between x-coordinates of this player and another
    public double xDifference(double x2) {
        return x - x2;
    }

    // difference between y-coordinates of this player and another
    public double yDifference(double y2) {
        return y - y2;
    }

    // finds distance between this player and another
    public double computeDistance(double x2, double y2) {
        return Math.sqrt(Math.pow(xDifference(x2), 2) + Math.pow(yDifference(y2), 2));
    }

    // runs along hypotenuse towards player at (x2, y2) once
    public void run(double x2, double y2) {
        double xChange = speed/computeDistance(x2, y2) * xDifference(x2);
        double yChange = speed/computeDistance(x2, y2) * yDifference(y2);
        x -= xChange;
        y -= yChange;
    }
}