module com.summer.sectiton1.group7.eco_resort {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.summer.sectiton1.group7.eco_resort to javafx.fxml;
    exports com.summer.sectiton1.group7.eco_resort;
}