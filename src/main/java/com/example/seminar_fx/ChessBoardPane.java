package com.example.seminar_fx;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ChessBoardPane extends GridPane {
    private static final int SQUARE_SIZE = 50;
    private static final int BOARD_SIZE = 8;

    public ChessBoardPane(int[][] position) {
        createBoard(position);
    }

    private void createBoard(int[][] position) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Rectangle square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
                square.setFill((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);
                square.setStroke(Color.BLACK);
                
                if (position[row][col] == 1) {
                    Text queen = new Text("♕");
                    queen.setStyle("-fx-font-size: 30px;");
                    add(square, col, row);
                    add(queen, col, row);
                } else {
                    add(square, col, row);
                }
            }
        }
    }
}
