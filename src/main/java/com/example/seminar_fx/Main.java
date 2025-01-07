package com.example.seminar_fx;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {
  @Override
  public void start(Stage primaryStage) {
    HTreePane pane = new HTreePane();
    TextField tfOrder = new TextField();
    tfOrder.setOnAction(_ -> pane.setOrder(Integer.parseInt(tfOrder.getText())));
    tfOrder.setPrefColumnCount(4);
    tfOrder.setAlignment(Pos.BOTTOM_RIGHT);

    HBox hBox = new HBox(10);
    hBox.getChildren().addAll(new Label("Enter an order: "), tfOrder);
    hBox.setAlignment(Pos.CENTER);

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(pane);
    borderPane.setBottom(hBox);

    Scene scene = new Scene(borderPane, 400, 400);
    primaryStage.setTitle("Main");
    primaryStage.setScene(scene);
    primaryStage.show();

    pane.widthProperty().addListener(_ -> pane.paint());
    pane.heightProperty().addListener(_ -> pane.paint());
  }

  /** Pane for displaying HTrees */
  public static class HTreePane extends Pane {
    private int order = 0;

    HTreePane() {}

    /** Set a new order */
    public void setOrder(int order) {
      this.order = order;
      paint();
    }

    protected void paint() {
      this.getChildren().clear(); // Clear the pane before redisplay
      double size = Math.min(getWidth(), getHeight()) * 0.4;
      displayHTree(order, getWidth() / 2, getHeight() / 2, size);
    }

    private void displayHTree(int order, double xCenter, double yCenter, double length) {
      // Locate four endpoints of the H-shape
      Point2D p1 = new Point2D(xCenter - length / 2, yCenter - length / 2);
      Point2D p2 = new Point2D(xCenter + length / 2, yCenter - length / 2);
      Point2D p3 = new Point2D(xCenter - length / 2, yCenter + length / 2);
      Point2D p4 = new Point2D(xCenter + length / 2, yCenter + length / 2);

      // Draw three lines in an H-shape
      getChildren().add(new Line(p1.getX(), p1.getY(), p3.getX(), p3.getY())); // Left vertical line
      getChildren()
          .add(new Line(p2.getX(), p2.getY(), p4.getX(), p4.getY())); // Right vertical line
      getChildren()
          .add(
              new Line(
                  xCenter - length / 2, yCenter, xCenter + length / 2, yCenter)); // Horizontal line

      // Recursively draw smaller H-trees at the four corners of the current H
      if (order > 0) {
        displayHTree(order - 1, p1.getX(), p1.getY(), length / 2);
        displayHTree(order - 1, p2.getX(), p2.getY(), length / 2);
        displayHTree(order - 1, p3.getX(), p3.getY(), length / 2);
        displayHTree(order - 1, p4.getX(), p4.getY(), length / 2);
      }
    }
  }
}
