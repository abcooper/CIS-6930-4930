package sample;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.sun.javafx.charts.Legend;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        showMenu(primaryStage);
    }

    public static void showMenu(Stage primaryStage){
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
        start.setMaxWidth(105);
        grid.add(start,2, 3);

        //custom start point field
        Label startLineLabel = new Label("Enter where the play will start:");
        TextField startLineField = new TextField("0");
        startLineField.setMaxWidth(105);
        grid.add(startLineLabel,0, 2);
        grid.add(startLineField,2, 2);

        start.setOnAction(new EventHandler<ActionEvent>() {

            //TODO: method to start simulation given inputs on main menu
            @Override
            public void handle(ActionEvent event) {
                sim(primaryStage, Integer.parseInt(startLineField.getText()));
            }
        });

        Scene scene = new Scene(grid, 600, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showSim(Stage primaryStage, ArrayList<XYChart.Series> seriesArrayList){

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

        //creating the chart that acts as the field
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setTitle("Simulation");
        //lineChart.setVerticalGridLinesVisible(true);
        lineChart.setCreateSymbols(false);
        lineChart.setMinSize(800, 600);

        //defining series
        for(int a = 0; a < seriesArrayList.size(); a++){
            lineChart.getData().add(seriesArrayList.get(a));
            Node line = seriesArrayList.get(a).getNode().lookup(".chart-series-line");
            if(a >  seriesArrayList.size()/2) {
                line.setStyle("-fx-stroke: red");
            }
            else if(a == 0) {
                line.setStyle("-fx-stroke: white");
            }
            else{
                line.setStyle("-fx-stroke: blue");
            }
        }

        ArrayList<XYChart.Series> yardMarkers = createYardMarkers();
        for(int a = 0; a < yardMarkers.size(); a++){
            lineChart.getData().add(yardMarkers.get(a));
            Node line = yardMarkers.get(a).getNode().lookup(".chart-series-line");
            line.setStyle("-fx-stroke: white");
        }

        for (Node n : lineChart.getChildrenUnmodifiable()) {
            if (n instanceof Legend) {
                Legend l = (Legend) n;
                List<Legend.LegendItem> list = new ArrayList<Legend.LegendItem>();
                list.add(l.getItems().get(0));
                ObservableList<Legend.LegendItem> olist = FXCollections.observableList(list);
                l.setItems(olist);
            }
        }

        //button to start over
        Button start = new Button();
        start.setText("Restart");
        start.setAlignment(Pos.CENTER);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMenu(primaryStage);
            }
        });
        //hbox for spacing
        HBox box = new HBox();
        box.getChildren().add(start);
        box.setAlignment(Pos.CENTER);

        //create new grid to hold linechart
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(lineChart,0, 0);
        grid.add(box, 0,1);

        //finish scene
        Scene scene  = new Scene(grid,900,700);
        scene.getStylesheets().add("sample/stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void sim(Stage primaryStage, int startLine){
        ArrayList<XYChart.Series> seriesArrayList = new ArrayList<XYChart.Series>();
        for(int a = 0; a < 10; a++){
            seriesArrayList.add(randomRun(startLine));
        }
        showSim(primaryStage, seriesArrayList);
    }

    //simple method to create random runs while testing
    public static XYChart.Series randomRun(int start){
        double x = start, y= 25, z = 0;
        XYChart.Series series = new XYChart.Series();
        while(z < 100){
            series.getData().add(new XYChart.Data(x += Math.random() * 2, y += (Math.random() * 4) - 2));
            z = x;
            if(.95 < Math.random()){
                z = 100;
            }
        }
        series.setName("Runner\nDistance Traveled: " + ((int) x - start) + " yards");


        return series;
    }

    public static ArrayList<XYChart.Series> createYardMarkers(){
        double x = 0, y= 0, z = 53.3;
        ArrayList<XYChart.Series> yardMarkers = new ArrayList<XYChart.Series>();

        for(int a = 0; a <= 10; a++){
            XYChart.Series temp = new XYChart.Series();
            temp.getData().add(new XYChart.Data(a * 10, y));
            temp.getData().add(new XYChart.Data(a * 10, z));
            yardMarkers.add(temp);
        }
        return yardMarkers;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
