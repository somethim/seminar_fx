package com.example.seminar_fx;

import java.util.ArrayList;
import java.util.List;

public class FindSolutions {
  private static final int BOARD_SIZE = 8;
  private static final List<int[][]> solutions = new ArrayList<>();

  private static boolean checkDiagonal(int[][] board, int row, int col) {
    // Check upper diagonal on left side
    for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
      if (board[i][j] == 1) return false;
    }

    // Check upper diagonal on right side
    for (int i = row, j = col; i >= 0 && j < BOARD_SIZE; i--, j++) {
      if (board[i][j] == 1) return false;
    }

    return true;
  }

  private static boolean checkRow(int[][] board, int row) {
    for (int j = 0; j < BOARD_SIZE; j++) {
      if (board[row][j] == 1) return false;
    }
    return true;
  }

  private static boolean checkColumn(int[][] board, int col) {
    for (int i = 0; i < BOARD_SIZE; i++) {
      if (board[i][col] == 1) return false;
    }
    return true;
  }

  private static boolean isSafe(int[][] board, int row, int col) {
    return checkRow(board, row) && checkColumn(board, col) && checkDiagonal(board, row, col);
  }

  private static void solveNQueens(int[][] board, int row) {
    if (row == BOARD_SIZE) {
      int[][] solution = new int[BOARD_SIZE][BOARD_SIZE];
      for (int i = 0; i < BOARD_SIZE; i++) {
        System.arraycopy(board[i], 0, solution[i], 0, BOARD_SIZE);
      }
      solutions.add(solution);
      return;
    }

    for (int col = 0; col < BOARD_SIZE; col++) {
      if (isSafe(board, row, col)) {
        board[row][col] = 1;
        solveNQueens(board, row + 1);
        board[row][col] = 0;
      }
    }
  }

  public static List<int[][]> positions() {
    solutions.clear();
    int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    solveNQueens(board, 0);
    return solutions;
  }
}
