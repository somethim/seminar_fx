package com.example.seminar_fx;

import com.example.seminar_fx.sorters.BaseSorter;
import com.example.seminar_fx.sorters.Sorters;
import java.util.List;
import java.util.Random;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  private int[] generateRandomArray(int size) {
    Random random = new Random();
    int[] array = new int[size];
    for (int i = 0; i < size; i++) {
      array[i] = random.nextInt(1000000);
    }
    return array;
  }

  @Override
  public void start(Stage primaryStage) {
    TableView<SortResult> table = new TableView<>();
    ObservableList<SortResult> data = FXCollections.observableArrayList();

    int[] sizes = {50000, 100000, 150000, 200000, 250000, 300000};

    TableColumn<SortResult, String> arraySizeColumn = new TableColumn<>("Array size");
    arraySizeColumn.setCellValueFactory(
        cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().size())));

    TableColumn<SortResult, String> selectionSortColumn = new TableColumn<>("Selection Sort");
    selectionSortColumn.setCellValueFactory(
        cellData ->
            new SimpleStringProperty(String.valueOf(cellData.getValue().selectionSortTime())));

    TableColumn<SortResult, String> radixSortColumn = new TableColumn<>("Radix Sort");
    radixSortColumn.setCellValueFactory(
        cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().radixSortTime())));

    TableColumn<SortResult, String> bubbleSortColumn = new TableColumn<>("Bubble Sort");
    bubbleSortColumn.setCellValueFactory(
        cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().bubbleSortTime())));

    TableColumn<SortResult, String> mergeSortColumn = new TableColumn<>("Merge Sort");
    mergeSortColumn.setCellValueFactory(
        cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().mergeSortTime())));

    TableColumn<SortResult, String> quickSortColumn = new TableColumn<>("Quick Sort");
    quickSortColumn.setCellValueFactory(
        cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().quickSortTime())));

    TableColumn<SortResult, String> heapSortColumn = new TableColumn<>("Heap Sort");
    heapSortColumn.setCellValueFactory(
        cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().heapSortTime())));

    for (int size : sizes) {
      int[] testArray = generateRandomArray(size);
      data.add(
          new SortResult(
              size,
              BaseSorter.run(testArray, Sorters::selectionSort),
              BaseSorter.run(testArray, Sorters::radixSort),
              BaseSorter.run(testArray, Sorters::bubbleSort),
              BaseSorter.run(testArray, Sorters::mergeSort),
              BaseSorter.run(testArray, Sorters::quickSort),
              BaseSorter.run(testArray, Sorters::heapSort)));
    }

    arraySizeColumn.setPrefWidth(100);
    selectionSortColumn.setPrefWidth(100);
    radixSortColumn.setPrefWidth(100);
    bubbleSortColumn.setPrefWidth(100);
    mergeSortColumn.setPrefWidth(100);
    quickSortColumn.setPrefWidth(100);
    heapSortColumn.setPrefWidth(100);

    table
        .getColumns()
        .setAll(
            List.of(
                arraySizeColumn,
                selectionSortColumn,
                radixSortColumn,
                bubbleSortColumn,
                mergeSortColumn,
                quickSortColumn,
                heapSortColumn));

    table.setItems(data);

    table.setPrefHeight(250);
    table.setPrefWidth(700);
    table.setStyle("-fx-font-size: 14px;");

    VBox vbox = new VBox(table);
    vbox.setSpacing(10);
    vbox.setStyle("-fx-padding: 10;");
    Scene scene = new Scene(vbox);

    primaryStage.setScene(scene);
    primaryStage.setTitle("Sorting Algorithm Performance");
    primaryStage.setWidth(800);
    primaryStage.setHeight(300);
    primaryStage.show();
  }

  public record SortResult(
      int size,
      long selectionSortTime,
      long radixSortTime,
      long bubbleSortTime,
      long mergeSortTime,
      long quickSortTime,
      long heapSortTime) {}
}
