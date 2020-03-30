package com.company;

import javafx.util.Pair;

public class Player {
    private int speed;
    private Pair position;
    private double tackleChance;
    private double dodgeChance;

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
}