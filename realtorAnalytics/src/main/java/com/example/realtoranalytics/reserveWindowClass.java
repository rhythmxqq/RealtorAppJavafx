package com.example.realtoranalytics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class reserveWindowClass implements Initializable {

    @FXML
    private Button DeleteButtonRes;

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

    String number = "";
    ObservableList<flatConfig> listM = FXCollections.observableArrayList();

    @FXML
    void OnSelectedflat(MouseEvent event) {
        try{
            int index = tableList_saling.getSelectionModel().getSelectedIndex();
            if(index < -1){
                return;
            }
           number =  phoneNumber_column.getCellData(index).toString();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    @FXML
    void clickBackButton(ActionEvent event) {
        backButton.getScene().getWindow().hide();
    }

    @FXML
    void clickDeleteButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        DataBaseHandler db = new DataBaseHandler();
        Connection connection = db.getDbConnection();

            Statement statement = connection.createStatement();
            int rows3 = statement.executeUpdate("UPDATE flatbase SET TypeSale = '" + "Продается" + "' WHERE phone_number = " + number);
        Connection con =  db.getDbConnection();
        listM.clear();
        ResultSet rs = con.createStatement().executeQuery("select * from flatbase");
        address_column.setCellValueFactory(new PropertyValueFactory<>("address"));
        price_column.setCellValueFactory(new PropertyValueFactory<>("price"));
        payment_column.setCellValueFactory(new PropertyValueFactory<>("payment"));
        phoneNumber_column.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        paymentType_column.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        tableColumnColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        while (rs.next()){
            if(rs.getString("TypeSale").equals("Бронировано")) {
                listM.add(new flatConfig(rs.getString("TypeSale"), rs.getString("address"),
                        rs.getInt("price"), rs.getInt("payment"), rs.getLong("phone_number"),
                        rs.getString("paymentType")));
            }

        }
        tableList_saling.setItems(listM);
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
                if(rs.getString("TypeSale").equals("Бронировано")) {
                    listM.add(new flatConfig(rs.getString("TypeSale"), rs.getString("address"),
                            rs.getInt("price"), rs.getInt("payment"), rs.getLong("phone_number"),
                            rs.getString("paymentType")));
                }

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tableList_saling.setItems(listM);
    }
}
