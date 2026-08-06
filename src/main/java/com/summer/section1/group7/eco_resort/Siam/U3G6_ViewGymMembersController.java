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

    private ObservableList<GymMember> memberList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        memberStatusCB.getItems().addAll(
                "Active",
                "Expired"
        );

        memberIdTC.setCellValueFactory(
                new PropertyValueFactory<>("guestId"));

        guestNameTC.setCellValueFactory(
                new PropertyValueFactory<>("guestName"));

        phoneNumberTC.setCellValueFactory(
                new PropertyValueFactory<>("phoneNumber"));

        packageTC.setCellValueFactory(
                new PropertyValueFactory<>("packageName"));

        statusTC.setCellValueFactory(
                new PropertyValueFactory<>("status"));

    }

    @FXML
    public void loadMembersOA(ActionEvent actionEvent) {

        memberTV.getItems().clear();
        memberList.clear();

        if (memberStatusCB.getValue() == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Error",
                    null,
                    "Please select a membership status."
            );
            return;
        }

        int count = 0;

        try {

            FileInputStream fis = new FileInputStream("gymMember.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {

                try {

                    GymMember gm = (GymMember) ois.readObject();

                    if (gm.getStatus().equalsIgnoreCase(memberStatusCB.getValue())) {

                        memberList.add(gm);
                        count++;

                    }

                } catch (EOFException e) {

                    ois.close();
                    break;

                }

            }

            memberTV.setItems(memberList);

            if (count == 0) {

                showAlert(
                        Alert.AlertType.INFORMATION,
                        "No Data",
                        null,
                        "No gym members found with the selected status."
                );

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @FXML
    public void backButtonOA(ActionEvent actionEvent) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("GymManagerDashboard.fxml"));

            Node node = loader.load();

            mainPane.getChildren().setAll(node);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    private void showAlert(Alert.AlertType type,
                           String title,
                           String header,
                           String message) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        alert.showAndWait();

    }
}