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