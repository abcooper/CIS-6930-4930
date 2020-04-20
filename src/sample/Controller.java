package sample;

import com.company.Player;
import com.company.Team;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Controller {

    Team offense, defense;
    Controller(){
        offense = new Team();
        defense = new Team();
    }

    public ArrayList<XYChart.Series> sim(Stage primaryStage, int startLine){
        ArrayList<XYChart.Series> seriesArrayList = new ArrayList<XYChart.Series>();
        for(int a = 0; a < 10; a++){
            seriesArrayList.add(randomRun(startLine));
        }
         return seriesArrayList;
    }

    public ArrayList<XYChart.Series> simAlgo(Plays offensePlay, Plays defensePlay, int startLine){
        ArrayList<XYChart.Series> seriesArrayList = new ArrayList<XYChart.Series>();
        offense.setPlay(offensePlay, startLine);
        defense.setPlay(defensePlay,startLine);

        double x = startLine, y= 25, z = 0;
        while(x < 100){
            int moveAngle = findBestAngle(offense.getPlayers()[0].getPosition(), defense.getPlayers());
            System.out.println(x + " " + Math.cos(Math.toRadians(moveAngle)));
            offense.getPlayers()[0].setPosition(new XYChart.Data(x += Math.cos(Math.toRadians(moveAngle)) , y += Math.sin(Math.toRadians(moveAngle))));
            offense.getPlayers()[0].getSeries().getData().add(offense.getPlayers()[0].getPosition());
        }
        offense.getPlayers()[0].getSeries().setName("Runner\nDistance Traveled: " + ((int) x - startLine) + " yards");
        //seriesArrayList.add(offense.getPlayers()[0].getSeries());

        for(int a = 0; a < 11; a++) {
            seriesArrayList.add(offense.getPlayers()[a].getSeries());
        }
        for(int a = 0; a < 11; a++) {
            seriesArrayList.add(defense.getPlayers()[a].getSeries());
        }

        return seriesArrayList;
    }

    //simple method to create random runs while testing
    public XYChart.Series randomRun(int start){
        double x = start, y= 25, z = 0;
        XYChart.Series series = new XYChart.Series();
        while(x < 100){
            series.getData().add(new XYChart.Data(x += Math.random() , y += (Math.random() * 4) - 2));

            if(.99 < Math.random()){
                break;
            }
        }
        series.setName("Runner\nDistance Traveled: " + ((int) x - start) + " yards");


        return series;
    }

    public ArrayList<XYChart.Series> Smoothsim(Stage primaryStage, int startLine){
        ArrayList<XYChart.Series> seriesArrayList = new ArrayList<XYChart.Series>();
        seriesArrayList.add(smoothCurveRun(startLine));

        return seriesArrayList;
    }

    public XYChart.Series smoothCurveRun(int start){
        double x = start*10, y= 25, z = 0;
        XYChart.Series series = new XYChart.Series();
        while(x < 1000){
            series.getData().add(new XYChart.Data(x++/10,  y = 25 + 10 * Math.sin(x/10 * (3.14159/4))));
        }
        series.setName("Runner\nDistance Traveled: " + ( Math.round(x) - start) + " yards");


        return series;
    }

    public int findBestAngle(XYChart.Data currentPosition, Player[] defense){
        int bestAngle = 0;
        double bestMinDistance = 0;
        for(int angle = -45; angle <= 45; angle++){
            XYChart.Data testPosition = new XYChart.Data((Double.parseDouble(currentPosition.getXValue().toString())) + Math.cos(Math.toRadians(angle)), (Double.parseDouble(currentPosition.getYValue().toString()))  + Math.sin(Math.toRadians(angle)));
            if(Double.parseDouble(testPosition.getYValue().toString()) > 0 && Double.parseDouble(testPosition.getYValue().toString()) < 50 )
            {

                double minDistance = Double.MAX_VALUE;
                for (int a = 0; a < defense.length; a++) {
                    if(Double.parseDouble(testPosition.getXValue().toString()) < Double.parseDouble(defense[a].getPosition().getXValue().toString())) {
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
        if(bestMinDistance == Double.MAX_VALUE){
           bestAngle = 0;
        }
        return bestAngle;
    }

    public double calculateDistance(XYChart.Data testPosition, XYChart.Data defense) {
        double xdiff = Double.parseDouble(testPosition.getXValue().toString()) - Double.parseDouble(defense.getXValue().toString());
        double ydiff = Double.parseDouble(testPosition.getYValue().toString()) - Double.parseDouble(defense.getYValue().toString());
        //System.out.println( (Math.sqrt( Math.pow( xdiff, 2) + Math.pow( ydiff, 2)) ) );
        return Math.sqrt( Math.pow( xdiff, 2) + Math.pow( ydiff, 2));
    }
}
