package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    public ImageView waterfordMap;
    public ComboBox menuOption;
    public Button findRoute;
    public LoadPoints loadPoints = new LoadPoints();
    public LandMark landMark = new LandMark();
    public ComboBox from;
    public ComboBox to;
    public ComboBox avoid;
    public TextArea route;
    public Button clear;
    public Pane pane;


    public void initialize() throws IOException {
        setWaterfordMap();
        setMenuSelection();
        loadPoints.loadPoints();
        drawLandMarkPoints();
        setComboBoxes();
    }

    public void setWaterfordMap() {
        File file = new File("src/WaterfordMap.png");
        Image image = new Image(file.toURI().toString(),758.0,387,false,false);
        waterfordMap.setImage(image);
    }

    public void setMenuSelection() throws IOException {
        menuOption.getItems().add("Generate Direct Route");
        menuOption.getItems().add("Generate Multiple Routes");
        menuOption.getItems().add("Generate Shortest Route using Dijkstra’s algorithm");
        menuOption.getItems().add("Generate Shortest Route using breadth-first search (BFS)");
        menuOption.getItems().add("Generate Most cultural/historic Route");
    }

    public void setComboBoxes(){
        for (int i = 0 ; i < loadPoints.landMarks.size(); i++) {
            String name = loadPoints.landMarks.get(i).landMarkName;
            from.getItems().add(name);
            to.getItems().add(name);
            avoid.getItems().add(name);
        }
    }


    public void findRoute() throws IOException {
        String findRoute = menuOption.getValue().toString();
        switch (findRoute) {

            case "Generate Direct Route":
                generateDirectRoute();
                break;

            case "Generate Multiple Routes":
                generateMultipleRoutes();
                break;

            case "Generate Shortest Route using Dijkstra’s algorithm":
                generateShortestRouteusingDijkstrasalgorithm();
                break;

            case "Generate Shortest Route using breadth-first search (BFS)":
                generateShortestRouteusingbreadthfirstsearch();
                break;

            case "Generate Most cultural/historic Route":
                generateMostculturalRoute();
                break;

        }
    }

    public void drawLandMarkPoints() throws IOException {
        for (int i = 0 ; i < loadPoints.landMarks.size(); i++) {
            String name = loadPoints.landMarks.get(i).landMarkName;
            int x = Integer.parseInt(loadPoints.landMarks.get(i).xCoord);
            int y = Integer.parseInt(loadPoints.landMarks.get(i).yCoord);

            Circle circle = new Circle(x,y, 4,Paint.valueOf("Red"));
            circle.setTranslateX(waterfordMap.getLayoutX());
            circle.setTranslateY(waterfordMap.getLayoutY());


            Tooltip mousePositionToolTip = new Tooltip("");
            circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    String msg = "Name : " + name  + "\n";
                    mousePositionToolTip.setText(msg);
                    circle.setRadius(8);
                    mousePositionToolTip.show(circle, event.getScreenX() + 20, event.getScreenY());
                }

            });

            circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    circle.setRadius(6);
                    circle.setFill(Paint.valueOf("Blue"));

                    if(from.getValue() == null){
                        from.setValue(name);
                    }else{
                        to.setValue(name);
                    }

                }

            });


            circle.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mousePositionToolTip.hide();//hides the tooltip on exit

                    if(circle.getFill() != Paint.valueOf("Blue"))
                        circle.setRadius(4);
                }
            });

            ((Pane) waterfordMap.getParent()).getChildren().add(circle);
        }
    }

    public void generateDirectRoute() throws IOException {
        String startingPoint = from.getValue().toString();
        String endPoint = to.getValue().toString();

        GraphNode<LandMark> startNode = new GraphNode<>();
        LandMark endNode = new LandMark();

        for(int i = 0 ; i < loadPoints.graphNodes.size(); i++){
            if(loadPoints.graphNodes.get(i).data.landMarkName.equalsIgnoreCase(startingPoint)){
                startNode = loadPoints.graphNodes.get(i);
            }if(loadPoints.graphNodes.get(i).data.landMarkName.equalsIgnoreCase(endPoint)){
                endNode = loadPoints.graphNodes.get(i).data;
            }
        }

        CostedPath thePath = CostedPath.searchGraphDepthFirstCheapestPath(startNode,null,0,endNode);
        StringBuilder stringRoute = new StringBuilder();
        for(int i = 0 ; i < thePath.pathList.size()-1; i++) {
            stringRoute.append(((LandMark) (thePath.pathList.get(i).data)).landMarkName + " to " + ((LandMark) (thePath.pathList.get(i + 1).data)).landMarkName + " via " +
                    thePath.pathList.get(i).adjList.get(0).roadName+ " ("+thePath.pathList.get(i).adjList.get(0).cost+"m)" + "\n");

            String startXCoord = ((LandMark) (thePath.pathList.get(i).data)).xCoord;
            String startYCoord = ((LandMark) (thePath.pathList.get(i).data)).yCoord;

            String endXCoord = ((LandMark) (thePath.pathList.get(i + 1).data)).xCoord;
            String endYCoord = ((LandMark) (thePath.pathList.get(i + 1).data)).yCoord;

            int startX = Integer.parseInt(startXCoord);
            int startY = Integer.parseInt(startYCoord);

            int endX = Integer.parseInt(endXCoord);
            int endY = Integer.parseInt(endYCoord);

            drawLineForRoute(startX, startY, endX, endY);
            route.setText(String.valueOf(stringRoute));
        }
    }


    public void generateMultipleRoutes(){
    }

    public void generateShortestRouteusingDijkstrasalgorithm(){

    }

    public void generateShortestRouteusingbreadthfirstsearch(){

    }

    public void generateMostculturalRoute(){

    }

    public void drawLineForRoute(int startX, int startY,int endX,int endY) {
        Line line = new Line(startX, startY, endX, endY);
        line.setTranslateX(waterfordMap.getLayoutX());
        line.setTranslateY(waterfordMap.getLayoutY());
        line.setStrokeWidth(3);
        ((Pane) waterfordMap.getParent()).getChildren().add(line);

        for (int i = 0; i < pane.getChildren().size(); i++) {
            if (pane.getChildren().get(i) instanceof Circle) {
                Circle circle = (Circle) pane.getChildren().get(i);
                if(circle.getCenterX() == startX && circle.getCenterY() == startY) {
                    circle.setRadius(6);
                    circle.setFill(Paint.valueOf("Blue"));
                }
            }

        }
    }

    public void clear() throws IOException {
        route.clear();
        from.setValue(null);
        to.setValue(null);
        for (int i = 0 ; i < pane.getChildren().size(); i++) {
            if (pane.getChildren().get(i) instanceof Circle) {
                Circle circle = (Circle) pane.getChildren().get(i);
                circle.setRadius(4);
                circle.setFill(Paint.valueOf("Red"));
            }else if(pane.getChildren().get(i) instanceof Line){
                Line line = (Line) pane.getChildren().get(i);
                line.setStrokeWidth(0);
            }
        }
    }
}
