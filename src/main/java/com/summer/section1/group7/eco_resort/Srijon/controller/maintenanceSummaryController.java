package com.summer.section1.group7.eco_resort.Srijon.controller;

import com.summer.section1.group7.eco_resort.Srijon.model.MaintenanceRequest;
import com.summer.section1.group7.eco_resort.Srijon.util.BinaryFileManager;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class maintenanceSummaryController {

    @FXML private BarChart<String, Number> summaryBarChart;
    @FXML private Label totalLabel;
    @FXML private Label completedLabel;
    @FXML private Label pendingLabel;

    private final String FILE = "maintenance.bin";

    @FXML
    public void initialize() {
        updateSummary();
    }

    @FXML
    public void onRefresh() {
        updateSummary();
    }

    private void updateSummary() {
        ArrayList<MaintenanceRequest> list = BinaryFileManager.loadList(FILE);
        int total = list == null ? 0 : list.size();
        int completed = 0;
        int pending = 0;
        int closed = 0;
        int inProgress = 0;
        if (list != null) {
            for (MaintenanceRequest r : list) {
                String s = r.getStatus() == null ? "" : r.getStatus().toLowerCase();
                if (s.contains("completed")) completed++;
                else if (s.contains("closed")) closed++;
                else if (s.contains("in progress")) { inProgress++; pending++; }
                else if (s.contains("pending")) pending++;
                else pending++;
            }
        }

        totalLabel.setText("Total requests: " + total);
        completedLabel.setText("Completed: " + completed);
        pendingLabel.setText("Pending/In Progress: " + pending);

        // populate bar chart
        summaryBarChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Requests");
        series.getData().add(new XYChart.Data<>("Completed", completed));
        series.getData().add(new XYChart.Data<>("In Progress", inProgress));
        series.getData().add(new XYChart.Data<>("Pending", pending));
        series.getData().add(new XYChart.Data<>("Closed", closed));
        summaryBarChart.getData().add(series);
    }

    @FXML
    public void onExportPdf() {
        // open file chooser to select save location
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Maintenance Summary PDF");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        Window window = summaryBarChart.getScene().getWindow();
        File file = chooser.showSaveDialog(window);
        if (file == null) return;

        try {
            createPdfSummary(file);
        } catch (IOException e) {
            e.printStackTrace();
            // simple feedback
            totalLabel.setText("Failed to create PDF: " + e.getMessage());
        }
    }

    // --- PDF generation using Apache PDFBox ---
    private void createPdfSummary(File file) throws IOException {
        ArrayList<MaintenanceRequest> list = BinaryFileManager.loadList(FILE);
        int total = list == null ? 0 : list.size();
        int completed = 0;
        int pending = 0;
        int closed = 0;
        int inProgress = 0;
        if (list != null) {
            for (MaintenanceRequest r : list) {
                String s = r.getStatus() == null ? "" : r.getStatus().toLowerCase();
                if (s.contains("completed")) completed++;
                else if (s.contains("closed")) closed++;
                else if (s.contains("in progress")) { inProgress++; pending++; }
                else if (s.contains("pending")) pending++;
                else pending++;
            }
        }

        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            doc.addPage(page);

            try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 18);
                cs.newLineAtOffset(50, 700);
                cs.showText("Maintenance Summary");
                cs.endText();

                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA, 12);
                cs.newLineAtOffset(50, 660);
                cs.showText("Total requests: " + total);
                cs.newLineAtOffset(0, -16);
                cs.showText("Completed: " + completed);
                cs.newLineAtOffset(0, -16);
                cs.showText("In Progress: " + inProgress);
                cs.newLineAtOffset(0, -16);
                cs.showText("Pending: " + pending);
                cs.newLineAtOffset(0, -16);
                cs.showText("Closed: " + closed);
                cs.endText();

                // optional: list top 10 recent requests (id + room + status)
                cs.beginText();
                cs.setFont(PDType1Font.HELVETICA_BOLD, 12);
                cs.newLineAtOffset(50, 560);
                cs.showText("Recent requests (up to 10):");
                cs.endText();

                int y = 540;
                cs.setFont(PDType1Font.HELVETICA, 10);
                int count = 0;
                for (int i = Math.max(0, list.size() - 10); i < list.size(); i++) {
                    MaintenanceRequest r = list.get(i);
                    String line = String.format("%s | Room:%s | %s", r.getRequestId(), r.getRoomNumber(), r.getStatus());
                    cs.beginText();
                    cs.newLineAtOffset(50, y);
                    cs.showText(line);
                    cs.endText();
                    y -= 14;
                    count++;
                    if (count >= 10) break;
                }
            }

            doc.save(file);
        }
    }
}
