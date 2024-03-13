package C2_thread.controllers;

import javafx.application.Platform;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Thread_0 extends Thread{
    private oclockControllers controller;
    private allOclockController allOclock;
    public Thread_0(oclockControllers controller) {
        this.controller = controller;
    }
    public Thread_0(allOclockController allController) {
        this.allOclock = allController;
    }
    @Override
    public void run() {
        while (true) {
            updateTime();
            updateAllTime();
            System.out.println("Luồng 0");
            try {
                sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void updateTime() {
        String timeH = getTimeH();
        String timeD = getTimeD();
        // Gọi phương thức trong oclockControllers để cập nhật Label mui_0
        Platform.runLater(() -> {
            controller.getMui_0().setText(timeH);
            controller.getTimeDay().setText(timeD);
        });
    }
    private void updateAllTime() {
        String timeH = getTimeH();
        // Gọi phương thức trong oclockControllers để cập nhật Label mui_0
        Platform.runLater(() -> {
            allOclock.getGMT().setText(timeH);
        });
    }
    public String getTimeH(){
        ZonedDateTime time = ZonedDateTime.now(ZoneId.of("GMT"));
        String timeH = time.getHour()+":"+time.getMinute()+":"+time.getSecond();;
        return timeH;
    }
    public String getTimeD(){
        ZonedDateTime time = ZonedDateTime.now(ZoneId.of("GMT"));
        String timeD = time.getDayOfMonth()+"/"+time.getMonthValue()+"/"+time.getYear();
        return timeD;
    }
}
