package com.levancam.demo6;

import com.levancam.demo6.controllers.LoginController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginView.class.getResource("login.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 574, 550);
        stage.initStyle(StageStyle.TRANSPARENT);//ẩn thanh tiêu đề và khung cửa sổ
        stage.setTitle("Login CAM");
        LoginController controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
