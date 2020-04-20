package com.company;

import javafx.scene.chart.XYChart;
import sample.Plays;

import java.util.ArrayList;

public class Team {
    private Player[] players;

    public Team() {
        players = new Player[11];
        for (int a = 0; a < 11; a++) {
            players[a] = new Player();
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getPlayer(int a) {
        return players[a];
    }

    public void setPlay(Plays play, int start) {
        switch (play) {
            case FOURFOUR:
                players[0].setPosition(new XYChart.Data(start + 1, 40));
                players[1].setPosition(new XYChart.Data(start + 1, 29));
                players[2].setPosition(new XYChart.Data(start + 1, 26));
                players[3].setPosition(new XYChart.Data(start + 1, 24));
                players[4].setPosition(new XYChart.Data(start + 1, 22));
                players[5].setPosition(new XYChart.Data(start + 1, 10));
                players[6].setPosition(new XYChart.Data(start + 3, 30));
                players[7].setPosition(new XYChart.Data(start + 5, 26));
                players[8].setPosition(new XYChart.Data(start + 5, 24));
                players[9].setPosition(new XYChart.Data(start + 3, 20));
                players[10].setPosition(new XYChart.Data(start + 10, 25));
                break;
            case T:
                players[0].setPosition(new XYChart.Data(start - 5, 25));
                players[1].setPosition(new XYChart.Data(start, 29));
                players[2].setPosition(new XYChart.Data(start, 27));
                players[3].setPosition(new XYChart.Data(start, 26));
                players[4].setPosition(new XYChart.Data(start, 25));
                players[5].setPosition(new XYChart.Data(start, 24));
                players[6].setPosition(new XYChart.Data(start, 23));
                players[7].setPosition(new XYChart.Data(start, 22));
                players[8].setPosition(new XYChart.Data(start - 1, 25));
                players[9].setPosition(new XYChart.Data(start - 5, 23));
                players[10].setPosition(new XYChart.Data(start - 5, 27));

            default:
                break;
        }

        for (int a = 0; a < 11; a++) {
            players[a].getSeries().getData().add(players[a].getPosition());
        }

    }
}
