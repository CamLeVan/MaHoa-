package C2_thread.controllers;

import C2_thread.allOclockView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;
public class oclockControllers {
    @FXML
    public Stage stage;
    @FXML
    private Label plist;
    @FXML
    private Button exit;
    @FXML
    private Button Open;
    @FXML
    private Button allView;
    @FXML
    private TextField timeZone;
    @FXML
    private Label falseTime;
    @FXML
    private Label mui_0;
    @FXML
    private Label mui_1;
    @FXML
    private Label mui_2;
    @FXML
    private Label mui_3;
    @FXML
    private Label mui_4;
    @FXML
    private Label mui_5;
    @FXML
    private Label mui_6;
    @FXML
    private Label mui_7;
    @FXML
    private Label mui_8;
    @FXML
    private Label mui_9;
    @FXML
    private Label mui_10;
    @FXML
    private Label mui_11;
    @FXML
    private Label mui_12;
    @FXML
    private Label timeDay;
    @FXML
    private Label nullTime;
    private Thread_1 thread_1;
    private Thread_0 thread_0;
    private Thread_2 thread_2;

    private Thread_3 thread_3;
    private Thread_4 thread_4;
    private Thread_5 thread_5;
    private Thread_6 thread_6;
    private Thread_7 thread_7;
    private Thread_8 thread_8;
    private Thread_9 thread_9;
    private Thread_10 thread_10;
    private Thread_11 thread_11;
    private Thread_12 thread_12;

    public oclockControllers() {
        thread_0 = new Thread_0(this);
        thread_1 = new Thread_1(this);
        thread_2 = new Thread_2(this);
        thread_3 = new Thread_3(this);
        thread_4 = new Thread_4(this);
        thread_5 = new Thread_5(this);
        thread_6 = new Thread_6(this);
        thread_7 = new Thread_7(this);
        thread_8 = new Thread_8(this);
        thread_9 = new Thread_9(this);
        thread_10 = new Thread_10(this);
        thread_11 = new Thread_11(this);
        thread_12 = new Thread_12(this);

    }

    // Phương thức để bắt đầu chạy luồng Thread_0
    public void startThread_0() {
        thread_0.start();
    }
    public void startThread_1() {
        thread_1.start();
    }
    public void startThread_2() {
        thread_2.start();
    }
    public void startThread_3() {
        thread_3.start();
    }
    public void startThread_4() {
        thread_4.start();
    }
    public void startThread_5() {
        thread_5.start();
    }
    public void startThread_6() {
        thread_6.start();
    }
    public void startThread_7() {
        thread_7.start();
    }
    public void startThread_8() {
        thread_8.start();
    }
    public void startThread_9() {
        thread_9.start();
    }
    public void startThread_10() {
        thread_10.start();
    }
    public void startThread_11() {
        thread_11.start();
    }
    public void startThread_12() {
        thread_12.start();
    }
    public Label getTimeDay(){
        return timeDay;
    }

    public Label getMui_1() {
        return mui_1;
    }
    public Label getMui_0() {return mui_0;  }
    public Label getMui_2() {
        return mui_2;
    }
    public Label getMui_3() {
        return mui_3;
    }
    public Label getMui_4() {
        return mui_4;
    }
    public Label getMui_5() {
        return mui_5;
    }
    public Label getMui_6() {
        return mui_6;
    }
    public Label getMui_7() {
        return mui_7;
    }
    public Label getMui_8() {
            return mui_8;
        }
    public Label getMui_9() {
            return mui_9;
        }
    public Label getMui_10() {
            return mui_10;
        }
    public Label getMui_11() {
            return mui_11;
        }
    public Label getMui_12() {
            return mui_12;
        }

    public void showTime(String time){
        switch (time){
            case "0":
                mui_0.setVisible(true);
                mui_1.setVisible(false);
                mui_2.setVisible(false);
                mui_3.setVisible(false);
                mui_4.setVisible(false);
                mui_5.setVisible(false);
                mui_6.setVisible(false);
                mui_7.setVisible(false);
                mui_8.setVisible(false);
                mui_9.setVisible(false);
                mui_10.setVisible(false);
                mui_11.setVisible(false);
                mui_12.setVisible(false);
                plist.setVisible(false);
                break;
            case "1":
                plist.setVisible(false);
                mui_0.setVisible(false);
                mui_1.setVisible(true);
                mui_2.setVisible(false);
                mui_3.setVisible(false);
                mui_4.setVisible(false);
                mui_5.setVisible(false);
                mui_6.setVisible(false);
                mui_7.setVisible(false);
                mui_8.setVisible(false);
                mui_9.setVisible(false);
                mui_10.setVisible(false);
                mui_11.setVisible(false);
                mui_12.setVisible(false);
                break;
            case "2":
                plist.setVisible(false);
                mui_0.setVisible(false);
                mui_1.setVisible(false);
                mui_2.setVisible(true);
                mui_3.setVisible(false);
                mui_4.setVisible(false);
                mui_5.setVisible(false);
                mui_6.setVisible(false);
                mui_7.setVisible(false);
                mui_8.setVisible(false);
                mui_9.setVisible(false);
                mui_10.setVisible(false);
                mui_11.setVisible(false);
                mui_12.setVisible(false);
                break;
            case "3":
                plist.setVisible(false);
                mui_0.setVisible(false);
                mui_1.setVisible(false);
                mui_2.setVisible(false);
                mui_3.setVisible(true);
                mui_4.setVisible(false);
                mui_5.setVisible(false);
                mui_6.setVisible(false);
                mui_7.setVisible(false);
                mui_8.setVisible(false);
                mui_9.setVisible(false);
                mui_10.setVisible(false);
                mui_11.setVisible(false);
                mui_12.setVisible(false);
                break;
            case "4":
                plist.setVisible(false);
                mui_0.setVisible(false);
                mui_1.setVisible(false);
                mui_2.setVisible(false);
                mui_3.setVisible(false);
                mui_4.setVisible(true);
                mui_5.setVisible(false);
                mui_6.setVisible(false);
                mui_7.setVisible(false);
                mui_8.setVisible(false);
                mui_9.setVisible(false);
                mui_10.setVisible(false);
                mui_11.setVisible(false);
                mui_12.setVisible(false);
                break;
            case "5":
                plist.setVisible(false);
                mui_0.setVisible(false);
                mui_1.setVisible(false);
                mui_2.setVisible(false);
                mui_3.setVisible(false);
                mui_4.setVisible(false);
                mui_5.setVisible(true);
                mui_6.setVisible(false);
                mui_7.setVisible(false);
                mui_8.setVisible(false);
                mui_9.setVisible(false);
                mui_10.setVisible(false);
                mui_11.setVisible(false);
                mui_12.setVisible(false);
                break;
            case "6":
                plist.setVisible(false);
                mui_0.setVisible(false);
                mui_1.setVisible(false);
                mui_2.setVisible(false);
                mui_3.setVisible(false);
                mui_4.setVisible(false);
                mui_5.setVisible(false);
                mui_6.setVisible(true);
                mui_7.setVisible(false);
                mui_8.setVisible(false);
                mui_9.setVisible(false);
                mui_10.setVisible(false);
                mui_11.setVisible(false);
                mui_12.setVisible(false);
                break;
            case "7":
                plist.setVisible(false);
                mui_0.setVisible(false);
                mui_1.setVisible(false);
                mui_2.setVisible(false);
                mui_3.setVisible(false);
                mui_4.setVisible(false);
                mui_5.setVisible(false);
                mui_6.setVisible(false);
                mui_7.setVisible(true);
                mui_8.setVisible(false);
                mui_9.setVisible(false);
                mui_10.setVisible(false);
                mui_11.setVisible(false);
                mui_12.setVisible(false);
                break;
            case "8":
                plist.setVisible(false);
                mui_0.setVisible(false);
                mui_1.setVisible(false);
                mui_2.setVisible(false);
                mui_3.setVisible(false);
                mui_4.setVisible(false);
                mui_5.setVisible(false);
                mui_6.setVisible(false);
                mui_7.setVisible(false);
                mui_8.setVisible(true);
                mui_9.setVisible(false);
                mui_10.setVisible(false);
                mui_11.setVisible(false);
                mui_12.setVisible(false);
                break;
            case "9":
                plist.setVisible(false);
                mui_0.setVisible(false);
                mui_1.setVisible(false);
                mui_2.setVisible(false);
                mui_3.setVisible(false);
                mui_4.setVisible(false);
                mui_5.setVisible(false);
                mui_6.setVisible(false);
                mui_7.setVisible(false);
                mui_8.setVisible(false);
                mui_9.setVisible(true);
                mui_10.setVisible(false);
                mui_11.setVisible(false);
                mui_12.setVisible(false);
                break;
            case "10":
                plist.setVisible(false);
                mui_0.setVisible(false);
                mui_1.setVisible(false);
                mui_2.setVisible(false);
                mui_3.setVisible(false);
                mui_4.setVisible(false);
                mui_5.setVisible(false);
                mui_6.setVisible(false);
                mui_7.setVisible(false);
                mui_8.setVisible(false);
                mui_9.setVisible(false);
                mui_10.setVisible(true);
                mui_11.setVisible(false);
                mui_12.setVisible(false);
                break;
            case "11":
                plist.setVisible(false);
                mui_0.setVisible(false);
                mui_1.setVisible(false);
                mui_2.setVisible(false);
                mui_3.setVisible(false);
                mui_4.setVisible(false);
                mui_5.setVisible(false);
                mui_6.setVisible(false);
                mui_7.setVisible(false);
                mui_8.setVisible(false);
                mui_9.setVisible(false);
                mui_10.setVisible(false);
                mui_11.setVisible(true);
                mui_12.setVisible(false);
                break;
            case "12":
                plist.setVisible(false);
                mui_0.setVisible(false);
                mui_1.setVisible(false);
                mui_2.setVisible(false);
                mui_3.setVisible(false);
                mui_4.setVisible(false);
                mui_5.setVisible(false);
                mui_6.setVisible(false);
                mui_7.setVisible(false);
                mui_8.setVisible(false);
                mui_9.setVisible(false);
                mui_10.setVisible(false);
                mui_11.setVisible(false);
                mui_12.setVisible(true);
                break;
        }
    }
    public void Open(ActionEvent even){
        if (checkTimeZone(timeZone.getText())){
            falseTime.setVisible(false);
            nullTime.setVisible(false);
            showTime(timeZone.getText());
        }
        else if (timeZone.getText().equals(null)){
            nullTime.setVisible(true);
            falseTime.setVisible(false);
        }
        else{
            falseTime.setVisible(true);
            nullTime.setVisible(false);
        }
    }
    public void allView(ActionEvent even){
        showAllOclockView();
    }

    public void exit(ActionEvent even)  {
            System.exit(0);
    }
    public boolean checkTimeZone(String time){
        try {
            int intTime = Integer.parseInt(time);
            if (intTime >= 0 && intTime <= 12) {
                return true;
            }
            else return false;
        }
        catch (Exception e){
            return false;
        }
    }
    public void main(String[] args) {
    }

    public void showAllOclockView() {
        try {
            new allOclockView().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setStage(Stage stage) {
        startThread_0();
        startThread_1();
        startThread_2();
        startThread_3();
        startThread_4();
        startThread_5();
        startThread_6();
        startThread_7();
        startThread_8();
        startThread_9();
        startThread_10();
        startThread_11();
        startThread_12();
    }
}
