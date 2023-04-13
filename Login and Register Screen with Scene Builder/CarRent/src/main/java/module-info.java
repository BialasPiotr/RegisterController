module sample.carrent {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.j;


    opens sample.carrent to javafx.fxml;
    exports sample.carrent;
}