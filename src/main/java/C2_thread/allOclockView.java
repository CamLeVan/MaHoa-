package C2_thread;
import C2_thread.controllers.allOclockController;
import C2_thread.controllers.oclockControllers;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
public class allOclockView extends Application {
    @Override
    public void start(Stage stage) throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(allOclockView.class.getResource("/com/levancam/demo6/allOclock.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),761,507);
        stage.initStyle(StageStyle.TRANSPARENT);//ẩn thanh tiêu đề và khung cửa sổ
        stage.setTitle("Login CAM");
        allOclockController controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}