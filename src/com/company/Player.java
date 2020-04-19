package com.company;

//import javafx.util.Pair;

public abstract class Player {
    // private instance variables
    private int speed;
    private double x;
    private double y;
    //private Pair position;
    //private double tackleChance;
    //private double dodgeChance;


    // constructor
    public Player(int speed, double xPos, double yPos) {
        this.speed = speed;
        this.x = xPos;
        this.y = yPos;
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
        //System.out.println("Distance between points: " + computeDistance(1, 1));
        //System.out.println("X Distance between: " + xDifference(x2));
        //System.out.println("Y Distance between: " + yDifference(y2));
        double xChange = speed/computeDistance(x2, y2) * xDifference(x2);
        double yChange = speed/computeDistance(x2, y2) * yDifference(y2);
        x -= xChange;
        y -= yChange;
    }


    /*
    public double getTackleChance() {
        return tackleChance;
    }

    public void setTackleChance(double tackleChance) {
        this.tackleChance = tackleChance;
    }

    public double getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(double dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public Player(int speed) {
        this.speed = speed;
    }
    public int getSpeed() {
        return speed;
    }

    public Pair getPosition() {
        return position;
    }

    public void setPosition(Pair position) {
        this.position = position;
    }
    */
}