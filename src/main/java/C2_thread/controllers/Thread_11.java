package C2_thread.controllers;

import javafx.application.Platform;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Thread_11 extends Thread{
    private oclockControllers controller;
    private allOclockController allOclock;
    public Thread_11(allOclockController allController) {
        this.allOclock = allController;
    }
    public Thread_11(oclockControllers controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            updateTime();
            updateAllTime();
            System.out.println("Luồng 11");
            try {
                sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void updateAllTime() {
        String timeH = getTimeH();
        // Gọi phương thức trong oclockControllers để cập nhật Label mui_0
        Platform.runLater(() -> {
            allOclock.getGMT_11().setText(timeH);
        });
    }
    private void updateTime() {
        String timeH = getTimeH();
        Platform.runLater(() -> {
            controller.getMui_11().setText(timeH);

        });
    }
    public String getTimeH(){
        ZonedDateTime time = ZonedDateTime.now(ZoneId.of("GMT+11"));
        String timeH = time.getHour()+":"+time.getMinute()+":"+time.getSecond();
        return timeH;
    }
}
