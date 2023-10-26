package com.project;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Button;

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

    private boolean running1 = false;
    private boolean paused1 = false;
    private boolean running2 = false;
    private boolean paused2 = false;
    private boolean running3 = false;
    private boolean paused3 = false;

    @FXML
    private void animateToView1(ActionEvent event) {
        UtilsViews.setViewAnimating("View1");
    }

    @FXML
    private void runTask1() {
        if (!running1 & !paused1) {
            backgroundTask(0);
            running1 = true;
        } else if (paused1){
            running1 = true;
            paused1 = false;
        } else {
            running1 = false;
            paused1 = true;
        }
    }
    @FXML
    private void runTask2() {
        if (!running2 & !paused2) {
            backgroundTask(1);
            running2 = true;
        } else if (paused2){
            running2 = true;
            paused2 = false;
        } else {
            running2 = false;
            paused2 = true;
        }
    }
    @FXML
    private void runTask3() {
        if (!running3 & !paused3) {
            backgroundTask(2);
            running3 = true;
        } else if (paused3){
            running3 = true;
            paused3 = false;
        } else {
            running3 = false;
            paused3 = true;
        }
    }
    private void backgroundTask(int index) {
        // Executar la tasca
        executor.submit(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    final int currentValue = i;
                    if (index == 0) {
                        while (!running1) {
                            Thread.sleep(1000);
                        }
                        Platform.runLater(() -> {
                            double pgr = (double) currentValue /100;;
                            task1.setText("Tasca 1, " + String.valueOf(currentValue) + "%");
                            task1Bar.setProgress(pgr);
                            System.out.println("Updating label: " + index + ", Value: " + currentValue);
                        });
                        Thread.sleep(1000);
                    }
                    if (index == 1) {
                        while (!running2) {
                            Thread.sleep(1000);
                        }
                        int random1 = (int) (Math.random() * 2001) + 3000;
                        int random2 = (int) (Math.random() * 2) + 2;
                        double pgr = ((double) currentValue + (double) random2) / 100;
                        i = currentValue + random2;
                        Platform.runLater(() -> {
                            task2.setText("Tasca 2, " + String.valueOf(currentValue + random2) + "%");
                            task2Bar.setProgress(pgr);
                            System.out.println("Updating label: " + index + ", Value: " + (currentValue + random2));
                        });
                        Thread.sleep(random1);
                    }
                    if (index == 2) {
                        while (!running3) {
                            Thread.sleep(1000);
                        }
                        int random1 = (int) (Math.random() * 5001) + 3000;
                        int random2 = (int) (Math.random() * 4) + 2;
                        double pgr = ((double) currentValue + (double) random2) / 100;
                        i = currentValue + random2;
                        Platform.runLater(() -> {
                            task3.setText("Tasca 3, " + String.valueOf(currentValue + random2) + "%");
                            task3Bar.setProgress(pgr);
                            System.out.println("Updating label: " + index + ", Value: " + (currentValue + random2));
                        });
                        Thread.sleep(random1);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    public void stopExecutor() {
        executor.shutdownNow();
    }
}
