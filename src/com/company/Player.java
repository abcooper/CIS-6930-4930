package com.company;

import javafx.scene.chart.XYChart;
import javafx.util.Pair;

public class Player {
    private int speed;
    private XYChart.Data position;
    private double tackleChance;
    private double dodgeChance;
    private XYChart.Series series;

    public XYChart.Series getSeries() {
        return series;
    }

    public void setSeries(XYChart.Series series) {
        this.series = series;
    }

    Player(){
        series = new XYChart.Series();
    }

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

    public XYChart.Data getPosition() {
        return position;
    }

    public void setPosition(XYChart.Data position) {
        this.position = position;
    }
}