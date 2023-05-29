package com.example.realtoranalytics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class saleWindowList implements Initializable {

    @FXML
    private Button saleListButton;

    @FXML
    private Button buttonReserveList;

    @FXML
    private TableColumn<?, ?> address_column;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> paymentType_column;

    @FXML
    private TableColumn<?, ?> payment_column;

    @FXML
    private TableColumn<?, ?> phoneNumber_column;

    @FXML
    private TableColumn<?, ?> price_column;

    @FXML
    private TableColumn<?, ?> tableColumnColor;

    @FXML
    private TableView<flatConfig> tableList_saling;
    ObservableList<flatConfig> listM = FXCollections.observableArrayList();


    @FXML
    void clickBackButton(ActionEvent event) {
        backButton.getScene().getWindow().hide();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DataBaseHandler db = new DataBaseHandler();
        try {
            Connection con =  db.getDbConnection();

            ResultSet rs = con.createStatement().executeQuery("select * from flatbase");
            address_column.setCellValueFactory(new PropertyValueFactory<>("address"));
            price_column.setCellValueFactory(new PropertyValueFactory<>("price"));
            payment_column.setCellValueFactory(new PropertyValueFactory<>("payment"));
            phoneNumber_column.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
            paymentType_column.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
            tableColumnColor.setCellValueFactory(new PropertyValueFactory<>("color"));
            while (rs.next()){
                if(rs.getString("TypeSale").equals("Продано")) {
                    listM.add(new flatConfig(rs.getString("TypeSale"), rs.getString("address"),
                            rs.getInt("price"), rs.getInt("payment"), rs.getLong("phone_number"),
                            rs.getString("paymentType")));
                }

            }
    } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        tableList_saling.setItems(listM);
    }

    }
