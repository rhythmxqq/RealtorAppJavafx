module com.example.realtoranalytics {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.realtoranalytics to javafx.fxml;
    exports com.example.realtoranalytics;
}