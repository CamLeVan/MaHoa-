package C2_thread;
import C2_thread.controllers.oclockControllers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
public class oclockView extends Application {
    @Override
    public void start(Stage stage) throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(oclockView.class.getResource("/com/levancam/demo6/oclock.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600,400);
        stage.initStyle(StageStyle.TRANSPARENT);//ẩn thanh tiêu đề và khung cửa sổ
        stage.setTitle("Login CAM");
        oclockControllers controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
