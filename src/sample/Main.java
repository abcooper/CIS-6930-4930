package sample;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        StackPane root = new StackPane();
        primaryStage.setTitle("Football Simulation");

        //creates main menu screen
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //labels for choices
        Label dSelect = new Label("Select a formation for defence:");
        Label playSelect = new Label("Select a play for offence:");
        grid.add(dSelect, 0, 0);
        grid.add(playSelect, 0, 1);

        //radio buttons for selecting defence play
        final ToggleGroup dGroup = new ToggleGroup();
        RadioButton d1 = new RadioButton("4-4");
        d1.setToggleGroup(dGroup);
        d1.setSelected(true);
        RadioButton d2 = new RadioButton("5-3");
        d2.setToggleGroup(dGroup);
        RadioButton d3 = new RadioButton("5-2");
        d3.setToggleGroup(dGroup);
        grid.add(d1,1,0);
        grid.add(d2,2,0);
        grid.add(d3,3,0);

        //radio buttons for offence play
        final ToggleGroup oGroup = new ToggleGroup();
        RadioButton o1 = new RadioButton("T Formation");
        o1.setToggleGroup(oGroup);
        o1.setSelected(true);
        RadioButton o2 = new RadioButton("I Formation");
        o2.setToggleGroup(oGroup);
        RadioButton o3 = new RadioButton("Shotgun Formation");
        o3.setToggleGroup(oGroup);
        grid.add(o1,1,1);
        grid.add(o2,2,1);
        grid.add(o3,3,1);

        //start button
        Button start = new Button();
        start.setText("Start Simulation");
        start.setOnAction(new EventHandler<ActionEvent>() {

            //TODO: method to start simulation given inputs on main menu
            @Override
            public void handle(ActionEvent event) {

                GridPane grid = new GridPane();
                grid.setAlignment(Pos.CENTER);
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(25, 25, 25, 25));

                //defining the axes
                final NumberAxis xAxis = new NumberAxis(0,100, 10);
                final NumberAxis yAxis = new NumberAxis(0, 53.3, 1);
                yAxis.setTickLabelsVisible(false);
                yAxis.setTickMarkVisible(false);
                xAxis.setLabel("Sideline");
                yAxis.translateXProperty().bind(
                        xAxis.widthProperty().add(35)
                );
                yAxis.setLabel("Endzone");
                //creating the chart
                final LineChart<Number,Number> lineChart =
                        new LineChart<Number,Number>(xAxis,yAxis);
                lineChart.setTitle("Simulation");
                lineChart.setVerticalGridLinesVisible(true);
                lineChart.setCreateSymbols(false);
                //defining a series
                XYChart.Series series = new XYChart.Series();
                series.setName("Runner");
                //populating the series with data
                series.getData().add(new XYChart.Data(1, 23));
                series.getData().add(new XYChart.Data(2, 14));
                series.getData().add(new XYChart.Data(3, 15));
                series.getData().add(new XYChart.Data(4, 24));
                series.getData().add(new XYChart.Data(5, 34));
                series.getData().add(new XYChart.Data(6, 36));
                series.getData().add(new XYChart.Data(7, 22));
                series.getData().add(new XYChart.Data(8, 45));
                series.getData().add(new XYChart.Data(9, 43));
                series.getData().add(new XYChart.Data(10, 17));
                series.getData().add(new XYChart.Data(11, 29));
                series.getData().add(new XYChart.Data(12, 25));
                XYChart.Series series1 = randomRun();
                grid.add(lineChart,0, 0);
                Scene scene  = new Scene(grid,800,600);

                //lineChart.getData().add(series);
                lineChart.getData().add(series1);
                scene.getStylesheets().add("sample/stylesheet.css");
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });

        grid.add(start,2, 2);
        Scene scene = new Scene(grid, 600, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static XYChart.Series randomRun(){
        double x = 0, y= 25, z = 0;
        XYChart.Series series = new XYChart.Series();
        while(z < 100){
            series.getData().add(new XYChart.Data(x += Math.random() * 2, y += (Math.random() * 4) - 2));
            z = x;
            if(.95 < Math.random()){
                z = 100;
            }
        }
        series.setName("Runner\nDistance Traveled: " + (int)x + " yards");
        return series;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
