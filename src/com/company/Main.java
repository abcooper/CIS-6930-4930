package com.company;

//import javafx.util.Pair;

import java.util.*;

public class Main {

    // to store all the positions each player was ever at
    static Map<Integer, Queue<Player>> playerMap = new HashMap<>();

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
        oLine.add(new Runner(2,15,4, 0));
        oLine.add(new OLinePlayer(1, 5, 10, 1));
        oLine.add(new OLinePlayer(1, 10, 10, 2));
        oLine.add(new OLinePlayer(1, 15, 10, 3));
        oLine.add(new OLinePlayer(1, 20, 10, 4));
        oLine.add(new OLinePlayer(1, 25, 10, 5));
        oLine.add(new OLinePlayer(1, 30, 10, 6));
        oLine.add(new OLinePlayer(1, 35, 10, 7));
        oLine.add(new OLinePlayer(1, 40, 10, 8));
        oLine.add(new OLinePlayer(1, 45, 10, 9));
        oLine.add(new OLinePlayer(1, 50, 10, 10));

        List<Player> dLine = new ArrayList<>(11);
        dLine.add(new DLinePlayer(1, 22, 20, 21));
        dLine.add(new DLinePlayer(1, 5,16, 11));
        dLine.add(new DLinePlayer(1, 10, 16, 12));
        dLine.add(new DLinePlayer(1, 15, 16, 13));
        dLine.add(new DLinePlayer(1, 20, 16, 14));
        dLine.add(new DLinePlayer(1, 25, 16, 15));
        dLine.add(new DLinePlayer(1, 30, 16, 16));
        dLine.add(new DLinePlayer(1, 35, 16, 17));
        dLine.add(new DLinePlayer(1, 40, 16, 18));
        dLine.add(new DLinePlayer(1, 45, 16, 19));
        dLine.add(new DLinePlayer(1, 50, 16, 20));

        Set<Player> offense = new HashSet<>();
        for(Player p : oLine) {
            offense.add(p);
        }

        Set<Player> defense = new HashSet<>();
        for(Player p : dLine) {
            defense.add(p);
        }

        // populating player map with initial state for movements queue
        for(Player p : oLine) {
            Queue<Player> movements = new LinkedList<>();
            movements.add(p);
            playerMap.put(p.getId(),movements);
        }

        for(Player p : dLine) {
            Queue<Player> movements = new LinkedList<>();
            movements.add(p);
            playerMap.put(p.getId(),movements);
        }

        for(int i = 0; i < 3; i++) {
            for (Player p : defense) {
                Player p1 = new DLinePlayer();
                Queue<Player> movements = playerMap.get(p.getId());
                Player latest = (Player) movements.toArray()[movements.size()-1];

                p1.setX(latest.getX() - 1);
                p1.setY(latest.getY() - 1);
                p1.setId(latest.getId());

                movements.add(p1);
                playerMap.put(p.getId(), movements);
            }

            for (Player p : offense) {
                Player p1 = new OLinePlayer();
                Queue<Player> movements = playerMap.get(p.getId());
                Player latest = (Player) movements.toArray()[movements.size()-1];

                if(p instanceof Runner) {
                    p1.setX(latest.getX()-1);
                    p1.setY(latest.getY()+3);
                }
                else {
                    p1.setX(latest.getX() - 1);
                    p1.setY(latest.getY() + 1);
                }

                p1.setId(latest.getId());
                movements.add(p1);
                playerMap.put(p.getId(), movements);
            }
        }


        double [][] distanceMatrix = new double[11][11];
        for(int i = 0; i < oLine.size(); i++) {
            for(int j = 0; j < dLine.size(); j++) {
                distanceMatrix[i][j] = oLine.get(i).computeDistance(dLine.get(j).getX(), dLine.get(j).getY());
                System.out.println("Distance between OLine Player " + i + " and DLine Player " + j + ": " + distanceMatrix[i][j]);
            }
        }

        //while(oLine.get(0).getSpeed() != 0 && oLine.get(0).getY() < 110) {
        while((!offense.isEmpty() && !defense.isEmpty())) {
            for(Player p : defense) {
                p.run(oLine.get(0).getX(), oLine.get(0).getY());
                Queue<Player> movements = playerMap.get(p.getId());
                movements.add(new DLinePlayer(p.getSpeed(),p.getX(),p.getY(),p.getId()));
            }
        }

    }
}
