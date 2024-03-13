package C2_thread.controllers;

import javafx.application.Platform;
import C2_thread.controllers.allOclockController;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Thread_2 extends Thread{
    private oclockControllers controller;
    private allOclockController allOclock;

    public Thread_2(allOclockController allController) {
        this.allOclock = allController;
    }
    public Thread_2(oclockControllers controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            updateTime();
            updateAllTime();
            System.out.println("Luồng 2");
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
            allOclock.getGMT_2().setText(timeH);
        });
    }
    private void updateTime() {
        String timeH = getTimeH();
        Platform.runLater(() -> {
            controller.getMui_2().setText(timeH);

        });
    }
    public String getTimeH(){
        ZonedDateTime time = ZonedDateTime.now(ZoneId.of("GMT+2"));
        String timeH = time.getHour()+":"+time.getMinute()+":"+time.getSecond();
        return timeH;
    }
}
