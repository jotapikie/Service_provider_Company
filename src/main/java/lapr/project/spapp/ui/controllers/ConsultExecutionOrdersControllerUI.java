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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import lapr.project.spapp.controller.ConsultExecutionOrdersController;
import lapr.project.spapp.model.ExecutionOrder;
import lapr.project.spapp.ui.Main;

public class ConsultExecutionOrdersControllerUI {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttBacktoMenu;

    @FXML
    private Label lblConsultExecutionOrder;

    @FXML
    private TableView<ExecutionOrder> tableView;

    @FXML
    private TableColumn<ExecutionOrder, String> clientNameColumn;

    @FXML
    private TableColumn<ExecutionOrder, Double> distanceColumn;

    @FXML
    private TableColumn<ExecutionOrder, String> categoryColumn;

    @FXML
    private TableColumn<ExecutionOrder, String> startDateTimeColumn;

    @FXML
    private TableColumn<ExecutionOrder, String> serviceTypeColumn;

    @FXML
    private TableColumn<ExecutionOrder, String> clientAddressColumn;

    @FXML
    private Button bttGetOrders;

    private ConsultExecutionOrdersController m_controller;

    @FXML
    void onActionGetOrders(ActionEvent event) {
        tableView.setItems(getExecutionOrders());
    }

    @FXML
    void onActionBackToMenu(ActionEvent event) {
        tableView.getItems().clear();
        close(event);
    }

    @SuppressWarnings("Duplicates")
    @FXML
    void initialize() {

        m_controller = new ConsultExecutionOrdersController(Main.company);

        clientNameColumn.setCellValueFactory(new PropertyValueFactory<ExecutionOrder, String>("clientName"));

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

        clientAddressColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ExecutionOrder, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ExecutionOrder, String> c) {
                return new SimpleStringProperty(c.getValue().getAddress().toString());
            }
        });

    }

    boolean areExecutionOrdersEmpty() {
        if (m_controller.getExecutionOrdersAfterToday().isEmpty())
            return true;
        else return false;
    }

    private ObservableList<ExecutionOrder> getExecutionOrders() {
        ObservableList<ExecutionOrder> executionOrders = FXCollections.observableArrayList();
        executionOrders.addAll(m_controller.getExecutionOrdersAfterToday());
        return executionOrders;
    }

    void close(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
}

