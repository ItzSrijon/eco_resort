package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.WasteEntry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageFoodWasteLogController
{
    @javafx.fxml.FXML
    private ComboBox<String> wasteReasonCB;
    @javafx.fxml.FXML
    private TableView<WasteEntry> wasteLogTableView;
    @javafx.fxml.FXML
    private TextField wasteQuantityTF;
    @javafx.fxml.FXML
    private Label thresholdWarningLabel;
    @javafx.fxml.FXML
    private TableColumn<WasteEntry, String> reasonTC;
    @javafx.fxml.FXML
    private TextField wastedItemTF;
    @javafx.fxml.FXML
    private TableColumn<WasteEntry, String> quantityTC;
    @javafx.fxml.FXML
    private TableColumn<WasteEntry, String> itemNameTC;

    // Daily waste limit (total quantity units) used for the threshold warning
    private static final int DAILY_WASTE_LIMIT = 50;

    private final ObservableList<WasteEntry> wasteList = FXCollections.observableArrayList();

    @javafx.fxml.FXML
    public void initialize() {

        itemNameTC.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantityTC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        reasonTC.setCellValueFactory(new PropertyValueFactory<>("reason"));

        wasteReasonCB.setItems(FXCollections.observableArrayList(
                "Spoiled", "Overproduction", "Customer Return", "Preparation Waste", "Expired"
        ));

        // Sample starting data so the table isn't empty when you run it
        wasteList.add(new WasteEntry("Lettuce", "5", "Spoiled"));
        wasteList.add(new WasteEntry("Rice", "10", "Overproduction"));

        wasteLogTableView.setItems(wasteList);
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/summer/section1/group7/eco_resort/Nazmun/ChefDashboard.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void saveWasteEntryButtonOA(ActionEvent actionEvent) {

        String itemName = wastedItemTF.getText();
        String quantityText = wasteQuantityTF.getText();
        String reason = wasteReasonCB.getValue();

        // VL: validate that all waste entry fields are properly filled
        if (itemName == null || itemName.trim().isEmpty()) {
            thresholdWarningLabel.setText("Please enter the wasted item name.");
            return;
        }

        if (quantityText == null || quantityText.trim().isEmpty() || !quantityText.matches("\\d+")) {
            thresholdWarningLabel.setText("Please enter a valid quantity.");
            return;
        }

        if (reason == null || reason.isEmpty()) {
            thresholdWarningLabel.setText("Please select a reason.");
            return;
        }

        int newQuantity = Integer.parseInt(quantityText);

        // Save the entry (DP: waste entry is saved)
        wasteList.add(new WasteEntry(itemName, quantityText, reason));

        wastedItemTF.clear();
        wasteQuantityTF.clear();
        wasteReasonCB.setValue(null);

        // Check today's total waste against the daily limit
        int totalWasteToday = 0;
        for (WasteEntry entry : wasteList) {
            totalWasteToday += Integer.parseInt(entry.getQuantity());
        }

        if (totalWasteToday > DAILY_WASTE_LIMIT) {
            thresholdWarningLabel.setText(
                    "Warning: Today's total waste (" + totalWasteToday
                            + ") exceeds the daily limit of " + DAILY_WASTE_LIMIT + ".");
        } else {
            thresholdWarningLabel.setText("Waste entry saved successfully.");
        }
    }
}