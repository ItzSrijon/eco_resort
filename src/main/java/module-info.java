module com.summer.section1.group7.eco_resort {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.summer.section1.group7.eco_resort to javafx.fxml;
    exports com.summer.section1.group7.eco_resort;

    // Nazmun
    exports com.summer.section1.group7.eco_resort.Nazmun.Controller;
    opens com.summer.section1.group7.eco_resort.Nazmun.Controller to javafx.fxml;

    exports com.summer.section1.group7.eco_resort.Nazmun.Model;
    opens com.summer.section1.group7.eco_resort.Nazmun.Model to javafx.fxml;

    // Piya
    exports com.summer.section1.group7.eco_resort.Piya.controller;
    opens com.summer.section1.group7.eco_resort.Piya.controller to javafx.fxml;

    exports com.summer.section1.group7.eco_resort.Piya.model;
    opens com.summer.section1.group7.eco_resort.Piya.model to javafx.fxml;

    exports com.summer.section1.group7.eco_resort.Piya.repository;

    // Siam
    exports com.summer.section1.group7.eco_resort.Siam;
    opens com.summer.section1.group7.eco_resort.Siam to javafx.fxml;

    // Srijon
    exports com.summer.section1.group7.eco_resort.Srijon.controller;
    opens com.summer.section1.group7.eco_resort.Srijon.controller to javafx.fxml;

    exports com.summer.section1.group7.eco_resort.Srijon.model;
    opens com.summer.section1.group7.eco_resort.Srijon.model to javafx.fxml;

    exports com.summer.section1.group7.eco_resort.Srijon.util;
    opens com.summer.section1.group7.eco_resort.Srijon.util to javafx.fxml;
}
