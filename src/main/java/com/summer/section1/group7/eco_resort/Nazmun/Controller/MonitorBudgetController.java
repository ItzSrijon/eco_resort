package com.summer.section1.group7.eco_resort.Nazmun.Controller;

import com.summer.section1.group7.eco_resort.Nazmun.Model.FinanceEntry;
import com.summer.section1.group7.eco_resort.Nazmun.Model.FinanceManager;
import com.summer.section1.group7.eco_resort.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.openpdf.text.*;
import org.openpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class MonitorBudgetController
{
    @javafx.fxml.FXML
    private ComboBox<String> revenueSourceCB;
    @javafx.fxml.FXML
    private TextField revenueAmountTF;
    @javafx.fxml.FXML
    private ComboBox<String> expenseCategoryCB;
    @javafx.fxml.FXML
    private TextField expenseAmountTF;

    @javafx.fxml.FXML
    private TableView<FinanceEntry> entryTableView;
    @javafx.fxml.FXML
    private TableColumn<FinanceEntry, String> typeTC;
    @javafx.fxml.FXML
    private TableColumn<FinanceEntry, String> categoryTC;
    @javafx.fxml.FXML
    private TableColumn<FinanceEntry, Double> amountTC;

    @javafx.fxml.FXML
    private XYChart<String, Double> financeChart;
    @javafx.fxml.FXML
    private Label summaryLabel;

    private final XYChart.Series<String, Double> series1 = new XYChart.Series<>();

    @javafx.fxml.FXML
    public void initialize() {
        revenueSourceCB.getItems().addAll("Room Service", "F&B", "Gym & Spa", "Event Hall", "Other");
        expenseCategoryCB.getItems().addAll("Staff", "Supplies", "Maintenance");

        typeTC.setCellValueFactory(new PropertyValueFactory<>("type"));
        categoryTC.setCellValueFactory(new PropertyValueFactory<>("category"));
        amountTC.setCellValueFactory(new PropertyValueFactory<>("amount"));

        entryTableView.getItems().addAll(FinanceManager.getEntryList());

        financeChart.getData().add(series1);
        series1.setName("Amount");

        rebuildChart();
    }

    @javafx.fxml.FXML
    public void addRevenueButtonOA(ActionEvent actionEvent) {
        String source = revenueSourceCB.getValue();

        double amount;
        try {
            amount = Double.parseDouble(revenueAmountTF.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Amount must be a valid number.");
            alert.showAndWait();
            return;
        }

        if (source == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a revenue source.");
            alert.showAndWait();
            return;
        }

        FinanceEntry entry = new FinanceEntry("Revenue", source, amount);
        entryTableView.getItems().add(entry);
        FinanceManager.getEntryList().add(entry);
        FinanceManager.saveToFile();

        revenueSourceCB.setValue(null);
        revenueAmountTF.setText("");
        rebuildChart();
    }

    @javafx.fxml.FXML
    public void addExpenseButtonOA(ActionEvent actionEvent) {
        String category = expenseCategoryCB.getValue();

        double amount;
        try {
            amount = Double.parseDouble(expenseAmountTF.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Amount must be a valid number.");
            alert.showAndWait();
            return;
        }

        if (category == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an expense category.");
            alert.showAndWait();
            return;
        }

        FinanceEntry entry = new FinanceEntry("Expense", category, amount);
        entryTableView.getItems().add(entry);
        FinanceManager.getEntryList().add(entry);
        FinanceManager.saveToFile();

        expenseCategoryCB.setValue(null);
        expenseAmountTF.setText("");
        rebuildChart();
    }

    @javafx.fxml.FXML
    public void generateSummaryButtonOA(ActionEvent actionEvent) {
        double totalRevenue = 0, totalExpense = 0;

        for (FinanceEntry entry : entryTableView.getItems()) {
            if (entry.getType().equals("Revenue")) totalRevenue += entry.getAmount();
            else totalExpense += entry.getAmount();
        }

        double profit = totalRevenue - totalExpense;
        summaryLabel.setText("Revenue: " + totalRevenue + "   Expense: " + totalExpense + "   Profit/Loss: " + profit);
    }

    private void rebuildChart() {
        series1.getData().clear();

        double totalRevenue = 0, totalExpense = 0;
        for (FinanceEntry entry : entryTableView.getItems()) {
            if (entry.getType().equals("Revenue")) totalRevenue += entry.getAmount();
            else totalExpense += entry.getAmount();
        }

        series1.getData().add(new XYChart.Data<>("Revenue", totalRevenue));
        series1.getData().add(new XYChart.Data<>("Expense", totalExpense));
    }

    @javafx.fxml.FXML
    public void exportPDFButtonOA(ActionEvent actionEvent) {
        Document doc = new Document();

        FileChooser chooser = new FileChooser();
        chooser.setInitialFileName("BudgetReport.pdf");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF File", "*.pdf"));
        File file = chooser.showSaveDialog(summaryLabel.getScene().getWindow());

        if (file == null) return;

        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(file));
            doc.open();

            Paragraph par = new Paragraph("Eco Resort - Budget & Finance Report");
            doc.add(par);
            doc.add(new Paragraph("Generated at: " + LocalDate.now()));

            Table tab = new Table(3);
            tab.addCell("Type");
            tab.addCell("Category");
            tab.addCell("Amount");

            double totalRevenue = 0, totalExpense = 0;
            for (FinanceEntry entry : entryTableView.getItems()) {
                tab.addCell(entry.getType());
                tab.addCell(entry.getCategory());
                tab.addCell("" + entry.getAmount());

                if (entry.getType().equals("Revenue")) totalRevenue += entry.getAmount();
                else totalExpense += entry.getAmount();
            }
            doc.add(tab);

            doc.add(new Paragraph("Total Revenue: " + totalRevenue));
            doc.add(new Paragraph("Total Expense: " + totalExpense));
            doc.add(new Paragraph("Profit/Loss: " + (totalRevenue - totalExpense)));

            doc.close();

        } catch (DocumentException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Could not generate PDF");
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void backButtonOA(ActionEvent actionEvent) {
        SceneSwitcher.switchTo("Nazmun/ManagerDashboard.fxml");
    }
}