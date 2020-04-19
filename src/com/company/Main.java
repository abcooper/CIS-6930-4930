package com.company;

//import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

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

        //System.out.println("Hello");

        // starting O Line at Y coordinates of 10 because end zone is 10 yards long

        List<Player> oLine = new ArrayList<>(11);
        oLine.add(new Runner(2, 20,5));
        oLine.add(new OLinePlayer(1, 5, 10));
        oLine.add(new OLinePlayer(1, 10, 10));
        oLine.add(new OLinePlayer(1, 15, 10));
        oLine.add(new OLinePlayer(1, 20, 10));
        oLine.add(new OLinePlayer(1, 25, 10));
        oLine.add(new OLinePlayer(1, 30, 10));
        oLine.add(new OLinePlayer(1, 35, 10));
        oLine.add(new OLinePlayer(1, 40, 10));
        oLine.add(new OLinePlayer(1, 45, 10));
        oLine.add(new OLinePlayer(1, 50, 10));

        List<Player> dLine = new ArrayList<>(11);
        dLine.add(new DLinePlayer(1, 15,20));
        dLine.add(new OLinePlayer(1, 30, 20));
        dLine.add(new OLinePlayer(1, 2, 15));
        dLine.add(new OLinePlayer(1, 8, 15));
        dLine.add(new OLinePlayer(1, 14, 15));
        dLine.add(new OLinePlayer(1, 20, 15));
        dLine.add(new OLinePlayer(1, 26, 15));
        dLine.add(new OLinePlayer(1, 32, 15));
        dLine.add(new OLinePlayer(1, 38, 15));
        dLine.add(new OLinePlayer(1, 44, 15));
        dLine.add(new OLinePlayer(1, 50, 15));

        double [][] distanceMatrix = new double[11][11];
        for(int i = 0; i < oLine.size(); i++) {
            for(int j = 0; j < dLine.size(); j++) {
                distanceMatrix[i][j] = oLine.get(i).computeDistance(dLine.get(j).getX(), dLine.get(j).getY());
                System.out.println("Distance between OLine Player " + i + " and DLine Player " + j + ": " + distanceMatrix[i][j]);
            }
        }

        //DLinePlayer dp = new DLinePlayer(1, 5, 4);
        //dp.run(1, 1);
        //System.out.println(dp.getX() + "\t" + dp.getY());


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
