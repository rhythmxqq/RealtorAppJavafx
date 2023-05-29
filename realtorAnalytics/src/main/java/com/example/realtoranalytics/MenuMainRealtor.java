package com.example.realtoranalytics;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MenuMainRealtor implements Initializable {
    

    @FXML
    private Button buttonCheckMoney;

    @FXML
    private Button add_button;

    @FXML
    private TableColumn<?, ?> address_column;

    @FXML
    private TableColumn<?, ?> paymentType_column;

    @FXML
    private TableColumn<?, ?> payment_column;

    @FXML
    private TableColumn<?, ?> phoneNumber_column;

    @FXML
    private TableColumn<?, ?> price_column;

    @FXML
    private Button reserve_button;

    @FXML
    private TableColumn<?, ?> tableColumnColor;

    @FXML
    private Button sale_button;
    @FXML
    private Label text_hide;

    @FXML
    private Label text_money;
    @FXML
    private Label text_address;

    @FXML
    private Label text_payment;

    @FXML
    private Label text_phone;

    @FXML
    private Label text_price;

    @FXML
    private Label text_typeP;
    Integer payment = 0;
    @FXML
    private TableView<flatConfig> tableList_saling;
    ObservableList<flatConfig> listM = FXCollections.observableArrayList();
    int index = -1;
    String chislo;
    Integer length = 0;
    @FXML
    void clickAddButton(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        for (int i = 0; i < length; i++) {
            if(tableColumnColor.getCellData(i).equals("Продано")){
                tableList_saling.setRowFactory(tv -> {
                    TableRow<flatConfig> row = new TableRow<>();
                    row.itemProperty().addListener((obs, previousPerson, currentPerson) -> {
                        row.setStyle("-fx-background-color: skyblue; -fx-text-background-color: black;");
                    });
                    return row;
                });
            }
        }
        loader.setLocation(getClass().getResource("addWindow.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void reloadList(){
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
                if(rs.getString("TypeSale").equals("Продается")) {
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
    @FXML
    void clickSaleButton(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("saliList.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void clickReserveButtonList(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("reserveWindow.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void click_saleButton(ActionEvent event) {
        sale_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("saleWindow.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Scene scene = new Scene(root, 296, 429);
        Stage stage = new Stage();
        Label label  = (Label) scene.lookup("#hideLabelMoney");
        Label label1 = (Label) scene.lookup("#textUserNameHide");
        label1.setText(text_hide.getText());
        label.setText(chislo);
        stage.setScene(scene);
        stage.show();

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        reloadList();
    }
    @FXML
    void OnSelectedflat(MouseEvent event) {
        try{
            int index = tableList_saling.getSelectionModel().getSelectedIndex();
            if(index < -1){
                return;
            }
            chislo = payment_column.getCellData(index).toString();
            text_address.setText(address_column.getCellData(index).toString());
            text_price.setText(price_column.getCellData(index).toString());
            text_payment.setText(payment_column.getCellData(index).toString());
            text_phone.setText(phoneNumber_column.getCellData(index).toString());
            text_typeP.setText(paymentType_column.getCellData(index).toString());
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    @FXML
    void clickButtonReservePlus(ActionEvent event) throws SQLException, ClassNotFoundException {
            DataBaseHandler db = new DataBaseHandler();
            Connection connection = db.getDbConnection();

            Statement statement = connection.createStatement();
            int rows3 = statement.executeUpdate("UPDATE flatbase SET TypeSale = '" + "Бронировано" + "' WHERE phone_number = " + text_phone.getText().toString());
            listM.clear();
           reloadList();
        }

    @FXML
    void ClickButtonCheckMoney(ActionEvent event) throws SQLException, ClassNotFoundException {
        listM.clear();
        String money = "";
        DataBaseHandler db = new DataBaseHandler();
        Connection con =  db.getDbConnection();
        Connection connection = db.getDbConnection();
        String sqlContent = String.format("select * from users where login='%s'", text_hide.getText());
        ResultSet rs = connection.createStatement().executeQuery(sqlContent);
        while (rs.next()){
            money += (rs.getInt(6) + rs.getInt(8)) * ((rs.getInt(7)*0.01)+1);
        }
        text_money.setText(money);

        ResultSet cs = con.createStatement().executeQuery("select * from flatbase");
        address_column.setCellValueFactory(new PropertyValueFactory<>("address"));
        price_column.setCellValueFactory(new PropertyValueFactory<>("price"));
        payment_column.setCellValueFactory(new PropertyValueFactory<>("payment"));
        phoneNumber_column.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        paymentType_column.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        tableColumnColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        while (cs.next()){
            if(cs.getString("TypeSale").equals("Продается")) {
                listM.add(new flatConfig(cs.getString("TypeSale"), cs.getString("address"),
                        cs.getInt("price"), cs.getInt("payment"), cs.getLong("phone_number"),
                        cs.getString("paymentType")));
            }

        }

        tableList_saling.setItems(listM);
    }
}
