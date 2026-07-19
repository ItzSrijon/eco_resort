module com.summer.section1.group7.eco_resort {

    requires javafx.controls;
    requires javafx.fxml;

    opens com.summer.section1.group7.eco_resort to javafx.fxml;
    exports com.summer.section1.group7.eco_resort;

    exports com.summer.section1.group7.eco_resort.Nazmun.Controller;
    opens com.summer.section1.group7.eco_resort.Nazmun.Controller to javafx.fxml;

    exports com.summer.section1.group7.eco_resort.Nazmun.Model;
    opens com.summer.section1.group7.eco_resort.Nazmun.Model to javafx.fxml;

    exports Piya.controller;
    opens Piya.controller to javafx.fxml;

    exports Piya.model;
    opens Piya.model to javafx.fxml;

    exports Piya.repository;
}