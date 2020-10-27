package sample;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class SierpinskiTriangle extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        SierpinskiTrianglePane pane = new SierpinskiTrianglePane();
        TextField tfOrder = new TextField();
        tfOrder.setOnAction(e -> pane.setOrder(Integer.parseInt(tfOrder.getText())));
        tfOrder.setPrefColumnCount(4);
        tfOrder.setAlignment(Pos.BOTTOM_CENTER);

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(new Label("Enter an order: "), tfOrder);
        hBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(hBox);


        Scene scene = new Scene(borderPane, 200, 100);
        stage.setTitle("SierpinskiTriangle");
        stage.setScene(scene);
        stage.show();

        pane.widthProperty().addListener(ov -> pane.paint());
        pane.heightProperty().addListener(ov -> pane.paint());


    }


    // Pane for displaying triangles
    static class SierpinskiTrianglePane extends Pane {
        private int order = 0;

        // Set new order
        public void setOrder(int order) {
            this.order = order;
            paint();
        }

        public SierpinskiTrianglePane() {
        }


        protected void paint() {
            // Select three points in proportion to the pane size
            Point2D p1 = new Point2D(getWidth() / 2, 10);
            Point2D p2 = new Point2D(10, getHeight() - 10);
            Point2D p3 = new Point2D(getWidth() - 10, getHeight() - 10);

            this.getChildren().clear(); // Clear the pane before redisplay

            displayTriangles(order, p1, p2, p3);
        }

        private void displayTriangles(int order, Point2D p1, Point2D p2, Point2D p3) {
            if (order == 0) {
                // Draw a triangle to connect three points
//                Polygon triangle = new Polygon();
//                triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
//                triangle.setStroke(Color.BLACK);
//                triangle.setFill(Color.WHITE);
                
//                this.getChildren().addAll(triangle);

                Line l1 = new Line(p1.getX(), p1.getY(), p3.getX(), p3.getY());
                Line l2 = new Line(p3.getX(), p3.getY(), p2.getX(), p2.getY());
                Line l3 = new Line(p2.getX(), p2.getY(), p1.getX(), p1.getY());

                this.getChildren().addAll(l1,l2,l3);
            }
            else {
                Point2D p12 = p1.midpoint(p2);
                Point2D p23 = p2.midpoint(p3);
                Point2D p31 = p3.midpoint(p1);

                displayTriangles(order - 1, p1, p12, p31);
                displayTriangles(order - 1, p12, p2, p23);
                displayTriangles(order - 1, p31, p23, p3);
            }
        }


    }
}