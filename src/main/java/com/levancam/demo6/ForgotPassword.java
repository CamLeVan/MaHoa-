package com.levancam.demo6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ForgotPassword extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ForgotPassword.class.getResource("doimk.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1020, 570);
        stage.setTitle("Login HLBook");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
