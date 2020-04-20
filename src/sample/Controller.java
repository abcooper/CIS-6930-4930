package sample;

import com.company.Player;
import com.company.Team;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Controller {

    Team offense, defense;
    private final double tackleDistance = 1;

    Controller() {
        offense = new Team();
        defense = new Team();
    }

    public ArrayList<XYChart.Series> sim(Stage primaryStage, int startLine) {
        ArrayList<XYChart.Series> seriesArrayList = new ArrayList<XYChart.Series>();
        for (int a = 0; a < 10; a++) {
            seriesArrayList.add(randomRun(startLine));
        }
        return seriesArrayList;
    }

    public ArrayList<XYChart.Series> simAlgo(Plays offensePlay, Plays defensePlay, int startLine) {

        ArrayList<XYChart.Series> seriesArrayList = new ArrayList<XYChart.Series>();
        offense.setPlay(offensePlay, startLine);
        defense.setPlay(defensePlay, startLine);

        double x = offense.getPlayer(0).getX(), y = offense.getPlayer(0).getY(), z = 0;
        while (x < 100) {
            x = isTackled(offense.getPlayer(0), defense) ? 100 : x;
            int moveAngle = findBestAngle(offense.getPlayers()[0].getPosition(), defense.getPlayers());
            offense.getPlayers()[0].setPosition(new XYChart.Data(x += Math.cos(Math.toRadians(moveAngle)), y += Math.sin(Math.toRadians(moveAngle))));
            offense.getPlayers()[0].getSeries().getData().add(offense.getPlayers()[0].getPosition());
            for (int a = 1; a < 11; a++) {
                if(!offense.getPlayer(a).isBlocked()) {
                    moveAngle = findOLineAngle(offense.getPlayer(a), defense);
                    offense.getPlayer(a).setPosition(new XYChart.Data(offense.getPlayer(a).getX() + Math.cos(Math.toRadians(moveAngle)), offense.getPlayer(a).getY() + Math.sin(Math.toRadians(moveAngle))));
                    offense.getPlayer(a).getSeries().getData().add(offense.getPlayer(a).getPosition());
                    setBlock(offense.getPlayer(a), defense);
                }
            }
            for (int a = 0; a < 11; a++) {
                System.out.println(defense.getPlayer(a).isBlocked());
                if(!defense.getPlayer(a).isBlocked()) {
                    moveAngle = findDefenseAngle(defense.getPlayer(a).getPosition(), offense.getPlayer(0).getPosition());
                    if (moveAngle != Integer.MAX_VALUE) {
                        defense.getPlayer(a).setPosition(new XYChart.Data(defense.getPlayer(a).getX() + Math.cos(Math.toRadians(moveAngle)), defense.getPlayer(a).getY() + Math.sin(Math.toRadians(moveAngle))));
                        defense.getPlayers()[a].getSeries().getData().add(defense.getPlayer(a).getPosition());
                    }
                }
            }
            x = isTackled(offense.getPlayer(0), defense) ? 100 : x;

        }
        offense.getPlayers()[0].getSeries().setName("Runner\nDistance Traveled: " + ((int) offense.getPlayer(0).getX() - startLine) + " yards");
        //seriesArrayList.add(offense.getPlayers()[0].getSeries());

        for (int a = 0; a < 11; a++) {
            seriesArrayList.add(offense.getPlayer(a).getSeries());
        }
        for (int a = 0; a < 11; a++) {
            seriesArrayList.add(defense.getPlayer(a).getSeries());
        }

        return seriesArrayList;
    }

    //simple method to create random runs while testing
    public XYChart.Series randomRun(int start) {
        double x = start, y = 25, z = 0;
        XYChart.Series series = new XYChart.Series();
        while (x < 100) {
            series.getData().add(new XYChart.Data(x += Math.random(), y += (Math.random() * 4) - 2));

            if (.99 < Math.random()) {
                break;
            }
        }
        series.setName("Runner\nDistance Traveled: " + ((int) x - start) + " yards");


        return series;
    }

    public ArrayList<XYChart.Series> Smoothsim(Stage primaryStage, int startLine) {
        ArrayList<XYChart.Series> seriesArrayList = new ArrayList<XYChart.Series>();
        seriesArrayList.add(smoothCurveRun(startLine));

        return seriesArrayList;
    }

    public XYChart.Series smoothCurveRun(int start) {
        double x = start * 10, y = 25, z = 0;
        XYChart.Series series = new XYChart.Series();
        while (x < 1000) {
            series.getData().add(new XYChart.Data(x++ / 10, y = 25 + 10 * Math.sin(x / 10 * (3.14159 / 4))));
        }
        series.setName("Runner\nDistance Traveled: " + (Math.round(x) - start) + " yards");


        return series;
    }

    public int findBestAngle(XYChart.Data currentPosition, Player[] defense) {
        int bestAngle = 0;
        double bestMinDistance = 0;

        for (int angle = -90; angle <= 90; angle++) {

            XYChart.Data testPosition = new XYChart.Data((Double.parseDouble(currentPosition.getXValue().toString())) + Math.cos(Math.toRadians(angle)), (Double.parseDouble(currentPosition.getYValue().toString())) + Math.sin(Math.toRadians(angle)));
            if (Double.parseDouble(testPosition.getYValue().toString()) > 0 && Double.parseDouble(testPosition.getYValue().toString()) < 50) {

                double minDistance = Double.MAX_VALUE;
                for (int a = 0; a < defense.length; a++) {
                    if (Double.parseDouble(testPosition.getXValue().toString()) < Double.parseDouble(defense[a].getPosition().getXValue().toString())) {
                        double distance = calculateDistance(testPosition, defense[a].getPosition());
                        if (distance < minDistance && distance < 10) {
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

    public int findDefenseAngle(XYChart.Data currentPosition, XYChart.Data runner) {
        double xdiff = Double.parseDouble(currentPosition.getXValue().toString()) - Double.parseDouble(runner.getXValue().toString());
        double ydiff = Double.parseDouble(currentPosition.getYValue().toString()) - Double.parseDouble(runner.getYValue().toString());
        if (xdiff < 0) {
            System.out.println("Stopped");
            return Integer.MAX_VALUE;
        }

        return (int) Math.toDegrees(Math.atan2(ydiff, xdiff)) + 180;
    }

    public double calculateDistance(XYChart.Data testPosition, XYChart.Data defense) {
        double xdiff = Double.parseDouble(testPosition.getXValue().toString()) - Double.parseDouble(defense.getXValue().toString());
        double ydiff = Double.parseDouble(testPosition.getYValue().toString()) - Double.parseDouble(defense.getYValue().toString());
        return Math.sqrt(Math.pow(xdiff, 2) + Math.pow(ydiff, 2));
    }

    public boolean isTackled(Player runner, Team defense) {
        for (int a = 0; a < defense.getPlayers().length; a++) {
            if (calculateDistance(runner.getPosition(), defense.getPlayer(a).getPosition()) < 1 && !defense.getPlayer(a).isBlocked()) {
                return true;
            }
        }
        return false;
    }

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

        System.out.println((int) Math.toDegrees(Math.atan2(ydiff, xdiff)));
        return (int) Math.toDegrees(Math.atan2(ydiff, xdiff));
    }

    public void setBlock(Player player, Team defense) {
        for (int a = 0; a < defense.getPlayers().length; a++) {
            if (calculateDistance(player.getPosition(), defense.getPlayer(a).getPosition()) < tackleDistance && !defense.getPlayer(a).isBlocked()) {
                player.setBlocked();
                defense.getPlayer(a).setBlocked();
                System.out.println("Blocked: " + a);
            }
        }
    }

}
