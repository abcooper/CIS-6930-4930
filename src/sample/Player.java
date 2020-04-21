package sample;

import javafx.scene.chart.XYChart;
import javafx.util.Pair;

public class Player {
    private int speed;
    private XYChart.Data position;
    private double tackleChance;
    private double dodgeChance;
    private XYChart.Series series;
    private boolean isBlocked;

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked() {
        isBlocked = true;
    }

    public XYChart.Series getSeries() {
        return series;
    }

    public void setSeries(XYChart.Series series) {
        this.series = series;
    }

    Player(){
        isBlocked = false;
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

    public double getX(){
        return  Double.parseDouble(position.getXValue().toString());
    }

    public double getY(){
        return  Double.parseDouble(position.getYValue().toString());
    }
}