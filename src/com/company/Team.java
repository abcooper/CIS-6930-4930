package com.company;

import java.util.ArrayList;

public class Team {
    private Player players;

    public Team(){
        players = new Player();
    }

    public Player getPlayers(){
        return players;
    }

    public ArrayList<Player> getPlayer(int a){
        ArrayList<Player> list = new ArrayList<Player>();
        list.add(players);
        return list;
    }
}
