package com.summer.section1.group7.eco_resort.Siam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class U3G5_EquipmentUsageController {

    @FXML
    private TextField memberNameTF;
    @FXML
    private TextField availableQtyTF;
    @FXML
    private ComboBox<String> durationCB;
    @FXML
    private ComboBox<Equipment> equipmentCB;
    @FXML
    private TextField memberIdTF;
    @FXML
    private TextField startTimeTF;
    @FXML
    private TextField endTimeTF;
    @FXML
    private TextField equipmentStatusTF;
    @FXML
    private AnchorPane mainPane;

    private GymMember loadedMember;

    private ObservableList<Equipment> equipmentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        durationCB.getItems().addAll("30 Minutes", "1 Hour", "2 Hours"
        );
        createEquipmentFile();

        loadEquipment();

    }

    private void createEquipmentFile() {

        File file = new File("equipment.bin");

        if (file.exists()) {
            return;
        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

            oos.writeObject(new Equipment("Treadmill",15));
            oos.writeObject(new Equipment("Exercise Bike",15));
            oos.writeObject(new Equipment("Bench Press",10));
            oos.writeObject(new Equipment("Dumbbell Set",20));
            oos.writeObject(new Equipment("Elliptical Machine",10));
            oos.writeObject(new Equipment("Rowing Machine",10));

            oos.close();

        }

        catch (Exception e){

            e.printStackTrace();

        }

    }

    private void loadEquipment() {

        equipmentCB.getItems().clear();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("equipment.bin"));

            while (true){
                try{
                    Equipment equipment = (Equipment) ois.readObject();
                    equipmentCB.getItems().add(equipment);

                }
                catch (EOFException e){
                    break;
                }
            }
            ois.close();
        }

        catch (Exception e){
            e.printStackTrace();

        }

    }

    @FXML
    public void searchButtonOA(ActionEvent actionEvent) {
        loadedMember = null;
        memberNameTF.clear();
        String memberId = memberIdTF.getText().trim();

        if (memberId.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter Member ID."
            );

            return;
        }
        loadedMember = GymManager.findGymMember(memberId);
        if (loadedMember == null) {
            showAlert(Alert.AlertType.ERROR, "Not Found", "Gym member not found.");
            return;
        }
        memberNameTF.setText(loadedMember.getGuestName());

        showAlert(Alert.AlertType.INFORMATION, "Success", "Gym member loaded successfully."
        );
    }

    @FXML
    public void showAvailability(ActionEvent actionEvent) {

        availableQtyTF.clear();
        equipmentStatusTF.clear();

        if (equipmentCB.getValue() == null) {
            return;
        }

        Equipment equipment = equipmentCB.getValue();
        int totalQuantity = equipment.getQuantity();

        int activeUsage = 0;

        try {

            File file = new File("equipmentUsage.bin");

            if (!file.exists()) {

                availableQtyTF.setText(String.valueOf(totalQuantity));
                equipmentStatusTF.setText("Available");
                return;

            }

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            while (true) {

                try {
                    EquipmentUsage usage = (EquipmentUsage) ois.readObject();

                    if (usage.getEquipmentName().equals(equipment.getEquipmentName()) && usage.getUsageDate().equals(LocalDate.now()) &&
                            usage.getEndTime().isAfter(LocalTime.now())) {

                        activeUsage++;

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

        int available = totalQuantity - activeUsage;
        availableQtyTF.setText(String.valueOf(available));

        if (available > 0) {
            equipmentStatusTF.setText("Available");
        }
        else {
            equipmentStatusTF.setText("Unavailable");
        }

    }
    @FXML
    public void assignEquipmentOA(ActionEvent actionEvent) {

        if (loadedMember == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please load a member first.");
            return;
        }

        if (equipmentCB.getValue() == null) {

            showAlert(Alert.AlertType.ERROR, "Error", "Please select equipment.");

            return;
        }

        if (durationCB.getValue() == null) {

            showAlert(Alert.AlertType.ERROR, "Error", "Please select duration.");

            return;
        }

        if (availableQtyTF.getText().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Error", "Please select equipment.");

            return;
        }

        if (Integer.parseInt(availableQtyTF.getText()) <= 0) {

            showAlert(Alert.AlertType.ERROR, "Unavailable", "Equipment is currently unavailable.");

            return;
        }

        LocalTime startTime = LocalTime.now();
        LocalTime endTime;

        switch (durationCB.getValue()) {

            case "30 Minutes":
                endTime = startTime.plusMinutes(30);
                break;

            case "1 Hour":
                endTime = startTime.plusHours(1);
                break;

            default:
                endTime = startTime.plusHours(2);

        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

        startTimeTF.setText(startTime.format(formatter));
        endTimeTF.setText(endTime.format(formatter));

        EquipmentUsage usage = new EquipmentUsage(
                        loadedMember.getGuestId(),
                        loadedMember.getGuestName(),
                        equipmentCB.getValue().getEquipmentName(),
                        durationCB.getValue(),
                        LocalDate.now(),
                        startTime,
                        endTime);

        try {

            File file = new File("equipmentUsage.bin");
            ObjectOutputStream oos;

            if (file.exists()) {

                oos = new AppendableObjectOutputStream(new FileOutputStream(file, true));
            }

            else {
                oos = new ObjectOutputStream(new FileOutputStream(file));

            }
            oos.writeObject(usage);
            oos.close();
            showAlert(Alert.AlertType.INFORMATION, "Success",
                    "Equipment Usage Recorded Successfully.\n\n"
                            + "Member ID : " + loadedMember.getGuestId()
                            + "\nMember Name : " + loadedMember.getGuestName()
                            + "\nEquipment : " + equipmentCB.getValue().getEquipmentName()
                            + "\nStart Time : " + startTime.format(formatter)
                            + "\nEnd Time : " + endTime.format(formatter)
            );

            clearFields();
            loadEquipment();

        }

        catch (Exception e) {
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
    private void clearFields(){

        memberIdTF.clear();
        memberNameTF.clear();
        availableQtyTF.clear();
        equipmentStatusTF.clear();
        startTimeTF.clear();
        endTimeTF.clear();
        equipmentCB.getSelectionModel().clearSelection();
        durationCB.getSelectionModel().clearSelection();
        loadedMember = null;

    }

    private void showAlert(Alert.AlertType type, String title,String message){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);

        alert.showAndWait();

    }
}

