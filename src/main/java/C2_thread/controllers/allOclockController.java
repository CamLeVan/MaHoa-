package C2_thread.controllers;

import C2_thread.allOclockView;
import C2_thread.oclockView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;

import javafx.scene.control.Label;


public class allOclockController {

    @FXML
    public Stage stage;
    @FXML
    public Button reTurn;
    @FXML
    public Button exit;
    @FXML
    private Label GMT;
    @FXML
    private Label GMT_1;
    @FXML
    private Label GMT_2;
    @FXML
    private Label GMT_3;
    @FXML
    private Label GMT_4;
    @FXML
    private Label GMT_5;
    @FXML
    private Label GMT_6;
    @FXML
    private Label GMT_7;
    @FXML
    private Label GMT_8;
    @FXML
    private Label GMT_9;
    @FXML
    private Label GMT_10;
    @FXML
    private Label GMT_11;
    @FXML
    private Label GMT_12;


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



    public allOclockController() {
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


    public Label getGMT(){return GMT;}
    public Label getGMT_1(){return GMT_1;}
    public Label getGMT_2(){return GMT_2;}
    public Label getGMT_3(){return GMT_3;}
    public Label getGMT_4(){return GMT_4;}
    public Label getGMT_5(){return GMT_5;}
    public Label getGMT_6(){return GMT_6;}
    public Label getGMT_7(){return GMT_7;}
    public Label getGMT_8(){return GMT_8;}
    public Label getGMT_9(){return GMT_9;}
    public Label getGMT_10(){return GMT_10;}
    public Label getGMT_11(){return GMT_11;}
    public Label getGMT_12(){return GMT_12;}
    public Stage getStage(){
        return stage;
    }
    public  void exit(ActionEvent even ){
        System.exit(0);
    }
    public void reTurn(ActionEvent even){
        getStage().close();
        showOclockView();
    }
    public void showOclockView() {
        try {
            new oclockView().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void main(String[] args) {
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
