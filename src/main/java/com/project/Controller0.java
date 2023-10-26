package com.project;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller0 {

    @FXML
    private Button buttonVista1, buttonTask1, buttonTask2, buttonTask3;
    @FXML
    private ProgressBar task1Bar, task2Bar, task3Bar;
    @FXML
    private Label task1, task2, task3;

    private ExecutorService executor = Executors.newFixedThreadPool(3); // Creem una pool de tres fils
//    @FXML
//    private void animateToView1(ActionEvent event) {
//        UtilsViews.setViewAnimating("View1");
//    }

    @FXML
    private void runTask() {

        backgroundTask(0);
        backgroundTask(1);
        backgroundTask(2);
    }
    private void backgroundTask(int index) {
        // Executar la tasca
        executor.submit(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    final int currentValue = i;
                    if (index == 0) {
                        // Actualitzar el Text en el fil d'aplicació de l'UI
                        Platform.runLater(() -> {
                            task1.setText(String.valueOf(currentValue) + "%");
                        });
                        Thread.sleep(20);
                    }
                    if (index == 1) {
                        // Actualitzar el Label en el fil d'aplicació de l'UI
                        Platform.runLater(() -> {
                            task1.setText(String.valueOf(currentValue) + "%");
                        });
                        Thread.sleep(40);
                    }
                    if (index == 2) {
                        // Actualitzar el Label en el fil d'aplicació de l'UI
                        Platform.runLater(() -> {
                            task1.setText(String.valueOf(currentValue) + "%");
                        });
                        Thread.sleep(40);
                    }
                    System.out.println("Updating label: " + index + ", Value: " + currentValue);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    public void stopExecutor() {
        executor.shutdown();
    }
}
