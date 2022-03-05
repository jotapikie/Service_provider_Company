package lapr.project.spapp.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lapr.project.spapp.controller.ImportExecutionOrdersController;
import lapr.project.spapp.ui.Main;
import lapr.project.spapp.utils.Binary;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuServiceProviderControllerUI {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnConsultOders;

    @FXML
    private Button btnImportOders;

    @FXML
    private Button btnClientTimeline;

    @FXML
    private Button btnLogout;

    private ImportExecutionOrdersController m_controller;

    private Stage consultExecutionOrders;

    private Stage analyzeClientTimeline;

    private ConsultExecutionOrdersControllerUI consultExecutionOrdersControllerUI;

    private AnalyzeClientTimelineControllerUI analyzeClientTimelineControllerUI;

    @FXML
    void onActionImportExecutionOrders(ActionEvent event) {
        initImportExecutionOrders();
    }

    @FXML
    void onActionConsultExecutionOrders(ActionEvent event) {
        if (consultExecutionOrdersControllerUI.areExecutionOrdersEmpty()) {
            Alert alert = createAlert(Alert.AlertType.ERROR, "Service Execution Orders Error", "There are no execution orders in the system. We apologize, but the consulting of service execution orders will now end.");
            alert.showAndWait();
        } else {
            consultExecutionOrders.show();
        }
    }

    @FXML
    void onActionAnalyzeClientTimeline(ActionEvent event) {
        if (analyzeClientTimelineControllerUI.areExecutionOrdersEmpty()) {
            Alert alert = createAlert(Alert.AlertType.ERROR, "Client Timeline Analyzation Error", "There is no timeline in the system. We apologize, but the analyzation of a client's timeline will now end.");
            alert.showAndWait();
        } else {
            analyzeClientTimeline.show();
        }
    }

    @FXML
    void onActionLogout(ActionEvent event) {
        close(event);
    }

    @FXML
    void initialize() {
        m_controller = new ImportExecutionOrdersController(Main.company);
        try {
            initConsultExecutionOrder();
            initAnalyzeClientTimeline();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initImportExecutionOrders() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Execution Orders");
        File selectedFile = fileChooser.showOpenDialog(null);
        try{
            if (selectedFile.exists()) {
                if (m_controller.newImport(selectedFile)) {
                    createAlert(Alert.AlertType.INFORMATION, "Execution Orders Import", "Execution orders successfully imported!").show();
                } else
                    createAlert(Alert.AlertType.WARNING, "Execution Orders Import", "The file is empty. There will be no execution orders in the system.").show();
            }
        } catch (NullPointerException e){
            createAlert(Alert.AlertType.INFORMATION, "Execution Orders Import", "File selection cancelled.").show();
        }

    }

    private void initConsultExecutionOrder() throws IOException {
        consultExecutionOrders = new Stage();
        consultExecutionOrders.initModality(Modality.APPLICATION_MODAL);
        consultExecutionOrders.setTitle("Consult Service Execution Orders");
        consultExecutionOrders.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConsultExecutionOrder.fxml"));
        Parent root = loader.load();
        consultExecutionOrdersControllerUI = loader.getController();
        Scene scene = new Scene(root);
        consultExecutionOrders.setScene(scene);
    }

    private void initAnalyzeClientTimeline() throws IOException {
        analyzeClientTimeline = new Stage();
        analyzeClientTimeline.initModality(Modality.APPLICATION_MODAL);
        analyzeClientTimeline.setTitle("Analyze Client Timeline");
        analyzeClientTimeline.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AnalyzeClientTimeline.fxml"));
        Parent root = loader.load();
        analyzeClientTimelineControllerUI = loader.getController();
        Scene scene = new Scene(root);
        analyzeClientTimeline.setScene(scene);
        analyzeClientTimeline.setOnCloseRequest(event -> { analyzeClientTimelineControllerUI.clear(); });
    }

    void close(ActionEvent event) {
        Binary.exportCompany(Main.company);
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    Alert createAlert(Alert.AlertType alertType, String header, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle("SPApp");
        alert.setHeaderText(header);
        alert.setContentText(message);
        return alert;
    }
}


