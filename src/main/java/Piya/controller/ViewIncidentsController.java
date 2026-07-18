package Piya.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViewIncidentsController
{
    @javafx.fxml.FXML
    private TableColumn locationTC;
    @javafx.fxml.FXML
    private TableColumn incidentIdTC;
    @javafx.fxml.FXML
    private TableColumn dateTC1;
    @javafx.fxml.FXML
    private TableColumn incidentTypeTC;
    @javafx.fxml.FXML
    private TableView incidentTableView;
    @javafx.fxml.FXML
    private TableColumn dateTC;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private TableColumn descriptionTC;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void deleteButtonOA(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewDetailsButtonOA(ActionEvent actionEvent) {
    }
}