package sample;

import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Controller {

    public ArrayList<XYChart.Series> sim(Stage primaryStage, int startLine){
        ArrayList<XYChart.Series> seriesArrayList = new ArrayList<XYChart.Series>();
        for(int a = 0; a < 10; a++){
            seriesArrayList.add(randomRun(startLine));
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
}
