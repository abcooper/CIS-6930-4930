package com.company;

public class Team {
    private Player[] players;

    public Team(){
        players = new Player[11];
    }

    public Player[] getPlayers(){
        return players;
    }

    public Player getPlayer(int a){
        return players[a];
    }
}
