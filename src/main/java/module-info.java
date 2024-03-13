module demo6 {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires jbcrypt;
    requires java.desktop;


    opens com.levancam.demo6 to javafx.fxml;
    opens C2_thread.controllers to javafx.fxml;

    opens com.levancam.demo6.encode to javafx.fxml;
    exports com.levancam.demo6.encode;
    opens com.levancam.demo6.controllers to javafx.fxml;
    exports com.levancam.demo6;
    exports C2_thread;
    exports C2_thread.controllers;
    exports com.levancam.demo6.controllers;
    opens com.levancam.demo6.models to javafx.fxml;
    exports com.levancam.demo6.models;
}