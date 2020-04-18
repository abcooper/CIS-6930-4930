package com.company;

import javafx.util.Pair;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //player 0 on offense is always runner
        /*
        Team offense = new Team();
        Team defense = new Team();

        boolean tackled = false;

        while(!tackled){
            tackled = move(offense, defense);
        }
        */

        System.out.println("Hello");


    }
    /*
    public static boolean move(Team o, Team d){
        for(int a = 0; a < 11; a++){
            o.getPlayer(a).setPosition(findBestMoveOffense(o.getPlayer(a).getPosition(), d.getPlayers()));

        }
        for(int a = 0; a < 11; a++){
            d.getPlayer(a).setPosition(findBestMoveDefense(d.getPlayer(a).getPosition(), o.getPlayer(0).getPosition()));
            if(tackle( d.getPlayer(a), o.getPlayer(0))){
                return true;
            }
        }
        return false;
    }

    public static Pair findBestMoveOffense(Pair player, Player[] team){
        //the runner will move to the most open spot in front of him
        //other offensive players will run straight forward for a block
    }

    public static Pair findBestMoveDefense(Pair player, Pair runner){
        //all defensive player will run towards the runner
    }

    public static boolean tackle(Player d, Player runner){
        //this method can calulate the chances of a tackle based on proximity and
    }
    */


}
