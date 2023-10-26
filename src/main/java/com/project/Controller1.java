package com.project;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;


public class Controller1 {

    @FXML
    private Button buttonVista0, buttonCarregar, buttonAturar;

    @FXML
    private ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9, img10, img11, img12;
    @FXML
    private ImageView img13, img14, img15, img16, img17, img18, img19, img20, img21, img22, img23, img24;
    @FXML
    private ProgressBar progressBarV1;
    @FXML
    private Label progressText;

    @FXML
    private void animateToView0(ActionEvent event) {
        UtilsViews.setViewAnimating("View0");
    }

    @FXML
    private void loadImage() {
        System.out.println("Loading image...");
        img1.setImage(null);
        loadImageBackground((image) -> {
            System.out.println("Image loaded");
            img1.setImage(image);
        });
    }

    public void loadImageBackground(Consumer<Image> callBack) {
        // Use a thread to avoid blocking the UI
        CompletableFuture<Image> futureImage = CompletableFuture.supplyAsync(() -> {
            try {
                // Wait a second to simulate a long loading time
                Thread.sleep(1000);

                // Load the data from the assets file
                Image image = new Image(getClass().getResource("/assets/hongo.png").toString());
                return image;

            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        })
        .exceptionally(ex -> {
            ex.printStackTrace();
            return null;
        });
        futureImage.thenAcceptAsync(result -> {
            callBack.accept(result);
        }, Platform::runLater);
    }
}
