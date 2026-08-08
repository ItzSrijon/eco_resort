package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.DailyPrepItem;
import com.summer.section1.group7.eco_resort.Nazmun.Model.DailyPrepManager;
import com.summer.section1.group7.eco_resort.Nazmun.Model.FoodQualityRecord;
import com.summer.section1.group7.eco_resort.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ChefInspectFoodQualityController
{
    @javafx.fxml.FXML
    private TableView<DailyPrepItem> dishTableView;
    @javafx.fxml.FXML
    private TableColumn<DailyPrepItem, String> dishIdTC;
    @javafx.fxml.FXML
    private TableColumn<DailyPrepItem, String> dishNameTC;
    @javafx.fxml.FXML
    private TableColumn<DailyPrepItem, String> stationTC;
    @javafx.fxml.FXML
    private TableColumn<DailyPrepItem, String> assignedStaffTC;

    @javafx.fxml.FXML
    private RadioButton taste1RB, taste2RB, taste3RB, taste4RB, taste5RB;
    @javafx.fxml.FXML
    private RadioButton presentation1RB, presentation2RB, presentation3RB, presentation4RB, presentation5RB;
    @javafx.fxml.FXML
    private RadioButton portion1RB, portion2RB, portion3RB, portion4RB, portion5RB;

    @javafx.fxml.FXML
    private TableView<FoodQualityRecord> logTableView;
    @javafx.fxml.FXML
    private TableColumn<FoodQualityRecord, String> logDishNameTC;
    @javafx.fxml.FXML
    private TableColumn<FoodQualityRecord, Integer> logTasteTC;
    @javafx.fxml.FXML
    private TableColumn<FoodQualityRecord, Integer> logPresentationTC;
    @javafx.fxml.FXML
    private TableColumn<FoodQualityRecord, Integer> logPortionTC;

    private final ToggleGroup tasteGroup = new ToggleGroup();
    private final ToggleGroup presentationGroup = new ToggleGroup();
    private final ToggleGroup portionGroup = new ToggleGroup();

    @javafx.fxml.FXML
    public void initialize() {
        dishIdTC.setCellValueFactory(new PropertyValueFactory<>("dishId"));
        dishNameTC.setCellValueFactory(new PropertyValueFactory<>("dishName"));
        stationTC.setCellValueFactory(new PropertyValueFactory<>("station"));
        assignedStaffTC.setCellValueFactory(new PropertyValueFactory<>("assignedStaff"));

        dishTableView.getItems().addAll(DailyPrepManager.getPrepList());

        taste1RB.setToggleGroup(tasteGroup);
        taste2RB.setToggleGroup(tasteGroup);
        taste3RB.setToggleGroup(tasteGroup);
        taste4RB.setToggleGroup(tasteGroup);
        taste5RB.setToggleGroup(tasteGroup);

        presentation1RB.setToggleGroup(presentationGroup);
        presentation2RB.setToggleGroup(presentationGroup);
        presentation3RB.setToggleGroup(presentationGroup);
        presentation4RB.setToggleGroup(presentationGroup);
        presentation5RB.setToggleGroup(presentationGroup);

        portion1RB.setToggleGroup(portionGroup);
        portion2RB.setToggleGroup(portionGroup);
        portion3RB.setToggleGroup(portionGroup);
        portion4RB.setToggleGroup(portionGroup);
        portion5RB.setToggleGroup(portionGroup);

        logDishNameTC.setCellValueFactory(new PropertyValueFactory<>("dishName"));
        logTasteTC.setCellValueFactory(new PropertyValueFactory<>("taste"));
        logPresentationTC.setCellValueFactory(new PropertyValueFactory<>("presentation"));
        logPortionTC.setCellValueFactory(new PropertyValueFactory<>("portionSize"));
    }

    @javafx.fxml.FXML
    public void saveRatingButtonOA(ActionEvent actionEvent) {
        DailyPrepItem selected = dishTableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        FoodQualityRecord record = new FoodQualityRecord(
                selected.getDishName(), selected.getStation(), selected.getAssignedStaff());

        record.setTaste(getSelectedRating(tasteGroup));
        record.setPresentation(getSelectedRating(presentationGroup));
        record.setPortionSize(getSelectedRating(portionGroup));

        logTableView.getItems().add(record);

        tasteGroup.selectToggle(null);
        presentationGroup.selectToggle(null);
        portionGroup.selectToggle(null);
    }

    private int getSelectedRating(ToggleGroup group) {
        RadioButton selected = (RadioButton) group.getSelectedToggle();
        if (selected == null) return 0;
        return Integer.parseInt(selected.getText());
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Nazmun/ChefDashboard.fxml");
    }
}