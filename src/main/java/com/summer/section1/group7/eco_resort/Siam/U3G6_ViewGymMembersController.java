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

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class U3G6_ViewGymMembersController {
    @FXML
    private TableColumn<GymMember, String> guestNameTC;
    @FXML
    private TableView<GymMember> memberTV;
    @FXML
    private TableColumn<GymMember, String> phoneNumberTC;
    @FXML
    private ComboBox<String> memberStatusCB;
    @FXML
    private TableColumn<GymMember, String> packageTC;
    @FXML
    private TableColumn<GymMember, String> memberIdTC;
    @FXML
    private TableColumn<GymMember, String> statusTC;
    @FXML
    private AnchorPane mainPane;
    private ObservableList<GymMember> memberList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        memberStatusCB.getItems().addAll("Active", "Expired");

        memberIdTC.setCellValueFactory(new PropertyValueFactory<>("guestId"));
        guestNameTC.setCellValueFactory(new PropertyValueFactory<>("guestName"));
        phoneNumberTC.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        packageTC.setCellValueFactory(new PropertyValueFactory<>("packageName"));
        statusTC.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @FXML
    public void viewMembersOA(ActionEvent actionEvent) {
        memberTV.getItems().clear();
        memberList.clear();

        if (memberStatusCB.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a membership status.");

            return;
        }

        ObservableList<GymMember> allMembers = GymManager.loadMembers();

        int count = 0;

        for (GymMember gm : allMembers) {
            if (gm.getStatus().equalsIgnoreCase(memberStatusCB.getValue())) {

                memberList.add(gm);
                count++;

            }

        }

        memberTV.setItems(memberList);

        if (count == 0) {

            showAlert(Alert.AlertType.INFORMATION, "No Data", "No gym members found with the selected status.");

        }

    }
    @FXML
    public void backButtonOA(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GymManagerDashboard.fxml"));
            Node node = loader.load();
            mainPane.getChildren().setAll(node);

        } catch (Exception e) {
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