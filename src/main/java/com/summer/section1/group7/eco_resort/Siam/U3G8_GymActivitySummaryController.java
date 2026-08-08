package com.summer.section1.group7.eco_resort.Siam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.openpdf.text.Document;
import org.openpdf.text.DocumentException;
import org.openpdf.text.Element;
import org.openpdf.text.Paragraph;
import org.openpdf.text.Table;
import org.openpdf.text.pdf.PdfWriter;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class U3G8_GymActivitySummaryController {

    @FXML
    private TableView<GymSummary> summaryTV;
    @FXML
    private TableColumn<GymSummary, String> summaryItemTC;
    @FXML
    private TableColumn<GymSummary, String> valueTC;
    @FXML
    private DatePicker fromDateDP;
    @FXML
    private DatePicker toDateDP;
    @FXML
    private AnchorPane mainPane;

    private ObservableList<GymSummary> summaryList =FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        summaryItemTC.setCellValueFactory(new PropertyValueFactory<>("summaryItem"));
        valueTC.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    @FXML
    public void generateSummaryOA(ActionEvent actionEvent) {

        summaryList.clear();

        if (fromDateDP.getValue() == null || toDateDP.getValue() == null) {

            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Please select both dates.");

            return;
        }

        if (fromDateDP.getValue().isAfter(toDateDP.getValue())) {

            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "From Date cannot be after To Date.");

            return;
        }

        LocalDate fromDate = fromDateDP.getValue();
        LocalDate toDate = toDateDP.getValue();

        int totalMembers = 0;
        int totalAttendance = 0;
        int totalEquipmentUsage = 0;
        int activeMembers = 0;
        double totalRevenue = 0;

        int treadmill = 0;
        int rowing = 0;
        int bike = 0;
        int bench = 0;
        int dumbbell = 0;
        int elliptical = 0;

        ArrayList<String> activeMemberList = new ArrayList<>();

        try {

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gymMember.bin"));

            while (true) {

                try {

                    GymMember member =
                            (GymMember) ois.readObject();

                    totalMembers++;
                    totalRevenue += member.getTotalFee();

                }

                catch (EOFException e) {

                    break;

                }

            }

            ois.close();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        // Attendance

        try {

            ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream("attendance.bin"));

            while (true) {

                try {

                    Attendance attendance =
                            (Attendance) ois.readObject();

                    if (!attendance.getAttendanceDate().isBefore(fromDate)
                            &&
                            !attendance.getAttendanceDate().isAfter(toDate)) {

                        totalAttendance++;

                        if (!activeMemberList.contains(attendance.getGuestId())) {

                            activeMemberList.add(attendance.getGuestId());

                        }

                    }

                }

                catch (EOFException e) {

                    break;

                }

            }

            ois.close();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        activeMembers = activeMemberList.size();

        // Equipment Usage

        try {

            ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream("equipmentUsage.bin"));

            while (true) {

                try {

                    EquipmentUsage usage =
                            (EquipmentUsage) ois.readObject();

                    if (!usage.getUsageDate().isBefore(fromDate)
                            &&
                            !usage.getUsageDate().isAfter(toDate)) {

                        totalEquipmentUsage++;

                        if (usage.getEquipmentName().equals("Treadmill")) {

                            treadmill++;

                        }

                        else if (usage.getEquipmentName().equals("Rowing Machine")) {

                            rowing++;

                        }

                        else if (usage.getEquipmentName().equals("Exercise Bike")) {

                            bike++;

                        }

                        else if (usage.getEquipmentName().equals("Bench Press")) {

                            bench++;

                        }

                        else if (usage.getEquipmentName().equals("Dumbbell Set")) {

                            dumbbell++;

                        }

                        else if (usage.getEquipmentName().equals("Elliptical Machine")) {

                            elliptical++;

                        }

                    }

                }

                catch (EOFException e) {

                    break;

                }

            }

            ois.close();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        String mostUsedEquipment = "Treadmill";
        int max = treadmill;

        if (rowing > max) {

            max = rowing;
            mostUsedEquipment = "Rowing Machine";

        }

        if (bike > max) {

            max = bike;
            mostUsedEquipment = "Exercise Bike";

        }

        if (bench > max) {

            max = bench;
            mostUsedEquipment = "Bench Press";

        }

        if (dumbbell > max) {

            max = dumbbell;
            mostUsedEquipment = "Dumbbell Set";

        }

        if (elliptical > max) {

            max = elliptical;
            mostUsedEquipment = "Elliptical Machine";

        }

        summaryList.add(new GymSummary("Total Registered Members", String.valueOf(totalMembers)));

        summaryList.add(new GymSummary("Active Members", String.valueOf(activeMembers)));

        summaryList.add(new GymSummary("Total Attendance Records", String.valueOf(totalAttendance)));

        summaryList.add(new GymSummary("Total Equipment Usage Records", String.valueOf(totalEquipmentUsage)));

        summaryList.add(new GymSummary("Total Membership Revenue", "BDT " + totalRevenue));

        summaryList.add(new GymSummary("Most Used Equipment", mostUsedEquipment));

        summaryTV.setItems(summaryList);

    }

    @FXML
    public void exportPdfOA(ActionEvent actionEvent) {

        if (summaryTV.getItems().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Error", "Please generate the summary first.");
            return;

        }

        Document doc = new Document();

        FileChooser chooser = new FileChooser();

        chooser.setInitialDirectory(new File(System.getProperty("user.home"), "Downloads"));
        chooser.setInitialFileName("GymActivitySummary.pdf");

        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        File file = chooser.showSaveDialog(mainPane.getScene().getWindow());

        if (file == null) {

            return;

        }

        try {

            PdfWriter.getInstance(doc, new FileOutputStream(file));

            doc.open();
            Paragraph title = new Paragraph("GYM ACTIVITY SUMMARY REPORT");
            title.setAlignment(Element.ALIGN_CENTER);

            doc.add(title);
            doc.add(new Paragraph("Generated Date : " + LocalDate.now()));
            doc.add(new Paragraph(
                    "Report Period : "
                            + fromDateDP.getValue()
                            + " to "
                            + toDateDP.getValue()));

            doc.add(new Paragraph(" "));
            Table table = new Table(2);

            table.addCell("Summary Item");
            table.addCell("Value");

            for (GymSummary summary : summaryTV.getItems()) {
                table.addCell(summary.getSummaryItem());
                table.addCell(summary.getValue());

            }
            doc.add(table);
            doc.close();
            showAlert(Alert.AlertType.INFORMATION, "Success", "PDF exported successfully.");

        }

        catch (DocumentException | IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to export PDF.");
            e.printStackTrace();

        }

    }

    @FXML
    public void backToDashboardOA(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GymManagerDashboard.fxml"));
            Node node = loader.load();
            mainPane.getChildren().setAll(node);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void showAlert(Alert.AlertType type, String title, String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();

    }


}