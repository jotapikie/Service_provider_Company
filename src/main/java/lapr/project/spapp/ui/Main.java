/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.project.spapp.ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lapr.project.spapp.model.Company;
import lapr.project.spapp.utils.Binary;

import java.io.IOException;

public class Main extends Application {

    public static Company company;

    @Override
    public void start(Stage stage) {
        try {
            company = Binary.importCompany();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SAPPMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                    alert.setTitle("SPApp");
                    alert.setHeaderText("Confirmation");
                    alert.setContentText("Do you really want to close the application?");

                    if (alert.showAndWait().get() == ButtonType.CANCEL) {
                        event.consume();
                    } else {
                        Binary.exportCompany(company);
                    }
                }
            });
            stage.show();
        } catch (IOException ex) {
            createErrorAlert(ex).show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Creates an alert if a exception is thrown while the application loads.
     *
     * @param ex exception.
     * @return alert.
     */
    private Alert createErrorAlert(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Application");
        alert.setHeaderText("Problems on application launch");
        alert.setContentText(ex.getMessage());

        return alert;
    }

}
