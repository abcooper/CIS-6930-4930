package sample;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class PlayModel {

    Team offense, defense;
    private final double tackleDistance = 1;

    PlayModel(){
        offense = new Team();
        defense = new Team();
    }

    public Team getOffense() {
        return offense;
    }

    public Team getDefense() {
        return defense;
    }

    public void setPlay(Plays offensePlay, Plays defensePlay, int startLine){
        offense.setPlay(offensePlay, startLine);
        defense.setPlay(defensePlay, startLine);
    }

    //moves the runner on the offence to the most open forward spot
    public void moveRunner(){
        int moveAngle = findBestAngle(offense.getRunner(), defense.getPlayers());
        System.out.println(offense.getPlayer(0).isBlocked());
        offense.getPlayer(0).setPosition(new XYChart.Data(offense.getRunner().getX() + Math.cos(Math.toRadians(moveAngle)), offense.getRunner().getY() + Math.sin(Math.toRadians(moveAngle))));
        offense.getPlayer(0).getSeries().getData().add(offense.getRunner().getPosition());
        setBlock(offense.getRunner(), defense);
    }

    //moves offensive linemen towards the closest defensive player
    public void moveOLine(){
        for (int a = 1; a < 11; a++) {
            if(!offense.getPlayer(a).isBlocked() && offense.getPlayer(a).getX() + 2 > offense.getPlayer(0).getX()) {
                int moveAngle = findOLineAngle(offense.getPlayer(a), defense);
                offense.getPlayer(a).setPosition(new XYChart.Data(offense.getPlayer(a).getX() + Math.cos(Math.toRadians(moveAngle)), offense.getPlayer(a).getY() + Math.sin(Math.toRadians(moveAngle))));
                offense.getPlayer(a).getSeries().getData().add(offense.getPlayer(a).getPosition());
                setBlock(offense.getPlayer(a), defense);
            }
        }
    }

    //moves all defenders towards the ball carrier
    public void moveDefense(){
        for (int a = 0; a < 11; a++) {
            if(!defense.getPlayer(a).isBlocked()) {
                int moveAngle = findDefenseAngle(defense.getPlayer(a).getPosition(), offense.getPlayer(0).getPosition());
                if (moveAngle != Integer.MAX_VALUE) {
                    defense.getPlayer(a).setPosition(new XYChart.Data(defense.getPlayer(a).getX() + Math.cos(Math.toRadians(moveAngle)), defense.getPlayer(a).getY() + Math.sin(Math.toRadians(moveAngle))));
                    defense.getPlayers()[a].getSeries().getData().add(defense.getPlayer(a).getPosition());
                    setBlock(defense.getPlayer(a), offense);
                }
            }
        }
    }

    //finds the angle of the best move by testing every angle in front of the runner
    public int findBestAngle(Player runner, Player[] defense) {
        int bestAngle = 0;
        double bestMinDistance = 0;

        for (int angle = -90; angle <= 90; angle++) {

            XYChart.Data testPosition = new XYChart.Data(runner.getX() + Math.cos(Math.toRadians(angle)), runner.getY() + Math.sin(Math.toRadians(angle)));
            if (Double.parseDouble(testPosition.getYValue().toString()) > 0 && Double.parseDouble(testPosition.getYValue().toString()) < 40) {

                double minDistance = Double.MAX_VALUE;
                for (int a = 0; a < defense.length; a++) {
                    if (Double.parseDouble(testPosition.getXValue().toString()) < Double.parseDouble(defense[a].getPosition().getXValue().toString()) && !defense[a].isBlocked() ) {
                        double distance = calculateDistance(testPosition, defense[a].getPosition());
                        if (distance < minDistance && distance < 5) {
                            minDistance = distance;
                        }

                    }
                }
                if (minDistance > bestMinDistance) {
                    bestMinDistance = minDistance;
                    bestAngle = angle;
                }

            }
        }
        if (bestMinDistance == Double.MAX_VALUE) {
            bestAngle = 0;
        }
        return bestAngle;
    }

    //finds the best move for an offensive
    public int findOLineAngle(Player player, Team defense){
        int closest = 0;
        double closestDistance = Double.MAX_VALUE, temp;
        for(int a =0;a<defense.getPlayers().length; a++){
            if(!defense.getPlayer(a).isBlocked()) {
                if ((temp = calculateDistance(player.getPosition(), defense.getPlayer(a).getPosition())) < closestDistance) {
                    closest = a;
                    closestDistance = temp;
                }
            }
        }
        double xdiff = defense.getPlayer(closest).getX() - player.getX();
        double ydiff = defense.getPlayer(closest).getY() - player.getY();


        return (int) Math.toDegrees(Math.atan2(ydiff, xdiff));
    }

    public int findDefenseAngle(XYChart.Data currentPosition, XYChart.Data runner) {
        double xdiff = Double.parseDouble(currentPosition.getXValue().toString()) - Double.parseDouble(runner.getXValue().toString());
        double ydiff = Double.parseDouble(currentPosition.getYValue().toString()) - Double.parseDouble(runner.getYValue().toString());
        if (xdiff < 0) {
            return Integer.MAX_VALUE;
        }

        return (int) Math.toDegrees(Math.atan2(ydiff, xdiff)) + 180;
    }

    public double calculateDistance(XYChart.Data testPosition, XYChart.Data defense) {
        double xdiff = Double.parseDouble(testPosition.getXValue().toString()) - Double.parseDouble(defense.getXValue().toString());
        double ydiff = Double.parseDouble(testPosition.getYValue().toString()) - Double.parseDouble(defense.getYValue().toString());
        return Math.sqrt(Math.pow(xdiff, 2) + Math.pow(ydiff, 2));
    }

    public void setBlock(Player player, Team defense) {
        for (int a = 0; a < defense.getPlayers().length; a++) {
            if (calculateDistance(player.getPosition(), defense.getPlayer(a).getPosition()) < tackleDistance && !defense.getPlayer(a).isBlocked()) {
                player.setBlocked();
                defense.getPlayer(a).setBlocked();
            }
        }
    }

    public boolean isOver(){
        return offense.getRunner().isBlocked() || (offense.getRunner().getX() > 100);
    }

    public ArrayList<XYChart.Series> getSeries(int start){
        offense.getRunner().getSeries().setName("Runner\nDistance Traveled: " + ((int) offense.getPlayer(0).getX() - start) + " yards");
        ArrayList<XYChart.Series> seriesArrayList = new ArrayList<XYChart.Series>();
        for (int a = 0; a < 11; a++) {
            seriesArrayList.add(offense.getPlayer(a).getSeries());
        }
        for (int a = 0; a < 11; a++) {
            seriesArrayList.add(defense.getPlayer(a).getSeries());
        }
        return seriesArrayList;
    }
}
