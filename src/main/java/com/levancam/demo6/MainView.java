package com.levancam.demo6;
import com.levancam.demo6.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 742, 537);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Login HLBook");
        MainController controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}