package lapr.project.spapp.ui.controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import lapr.project.spapp.controller.AnalyzeClientTimelineController;
import lapr.project.spapp.model.ExecutionOrder;
import lapr.project.spapp.ui.Main;

@SuppressWarnings("Duplicates")
public class AnalyzeClientTimelineControllerUI {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttGoBack;


    @FXML
    private Label lblChooseClient;


    @FXML
    private ComboBox<String> cmbChooseClient;

    @FXML
    private Button bttConfirm;

    @FXML
    private TableView<ExecutionOrder> tableView;

    @FXML
    private TableColumn<ExecutionOrder, Integer> numberColumn;

    @FXML
    private TableColumn<ExecutionOrder, Double> distanceColumn;

    @FXML
    private TableColumn<ExecutionOrder, String> startDateTimeColumn;

    @FXML
    private TableColumn<ExecutionOrder, String> categoryColumn;

    @FXML
    private TableColumn<ExecutionOrder, String> serviceTypeColumn;

    @FXML
    private TableColumn<ExecutionOrder, String> addressColumn;

    @FXML
    private Label lblAnalyzeClientTimeline;

    @FXML
    private Label lblServiceAlreadyPerformed;

    private AnalyzeClientTimelineController m_controller;

    @FXML
    void onActionConfirmClient(ActionEvent event) {
        tableView.getItems().clear();
        tableView.setItems(getExecutionOrders());
    }

    @FXML
    void onActionBackToMenu(ActionEvent event) {
        clear();
        close(event);
    }

    @FXML
    void initialize() {
        m_controller = new AnalyzeClientTimelineController(Main.company);

        numberColumn.setCellValueFactory(new PropertyValueFactory<ExecutionOrder, Integer>("orderNumber"));

        distanceColumn.setCellValueFactory(new PropertyValueFactory<ExecutionOrder, Double>("distanceToClient"));

        categoryColumn.setCellValueFactory(new PropertyValueFactory<ExecutionOrder, String>("category"));

        startDateTimeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExecutionOrder, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExecutionOrder, String> c) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                String date = formatter.format(c.getValue().getExecutionDate());
                return new SimpleStringProperty(date);
            }
        });

        serviceTypeColumn.setCellValueFactory(new PropertyValueFactory<ExecutionOrder, String>("serviceType"));

        addressColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExecutionOrder, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExecutionOrder, String> c) {
                return new SimpleStringProperty(c.getValue().getAddress().toString());
            }
        });

    }

    boolean areExecutionOrdersEmpty() {
        if (m_controller.getExecutionOrdersBeforeToday().isEmpty())
            return true;
        else {
            for (String s : m_controller.getClients())
                cmbChooseClient.getItems().add(s);
            return false;
        }
    }

    private ObservableList<ExecutionOrder> getExecutionOrders() {
        ObservableList<ExecutionOrder> executionOrders = FXCollections.observableArrayList();
        executionOrders.addAll(m_controller.newClientTimeline(cmbChooseClient.getValue()));
        return executionOrders;
    }

    private void close(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    void clear() {
        cmbChooseClient.getItems().clear();
        tableView.getItems().clear();
    }
}


