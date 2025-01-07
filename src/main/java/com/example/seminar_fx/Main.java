package com.example.seminar_fx;

import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    List<int[][]> positions = FindSolutions.positions();
    
    VBox root = new VBox(20);
    root.setPadding(new Insets(20));
    root.setAlignment(Pos.TOP_CENTER);

    // Add solution count at the top
    Label countLabel = new Label("Total Solutions: " + positions.size());
    countLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
    root.getChildren().add(countLabel);

    // Create horizontal container for solutions
    HBox solutionsContainer = new HBox(20);
    solutionsContainer.setPadding(new Insets(10));

    for (int i = 0; i < positions.size(); i++) {
      VBox solutionBox = new VBox(10);
      solutionBox.setAlignment(Pos.TOP_CENTER);
      Label solutionLabel = new Label("Solution " + (i + 1));
      ChessBoardPane boardPane = new ChessBoardPane(positions.get(i));
      
      solutionBox.getChildren().addAll(solutionLabel, boardPane);
      solutionsContainer.getChildren().add(solutionBox);
    }

    ScrollPane scrollPane = new ScrollPane(solutionsContainer);
    scrollPane.setFitToHeight(true);
    scrollPane.setPrefViewportHeight(500);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    
    root.getChildren().add(scrollPane);

    Scene scene = new Scene(root, 800, 600);
    primaryStage.setTitle("Eight Queens Solutions");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
