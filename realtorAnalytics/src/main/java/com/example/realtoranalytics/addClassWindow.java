package com.example.realtoranalytics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addClassWindow {
    @FXML
    private TextField address_textField;

    @FXML
    private Button enterButton;

    @FXML
    private TextField paymentType_textField;

    @FXML
    private TextField payment_textField;

    @FXML
    private TextField phone_textField;

    @FXML
    private TextField price_textField;

    @FXML
    void clickEnterButton(ActionEvent event) {
        DataBaseHandler db = new DataBaseHandler();
        String address = address_textField.getText();

        Integer price = Integer.parseInt(price_textField.getText());

        Integer payment = Integer.parseInt(payment_textField.getText());

        Long phone_number = Long.parseLong(phone_textField.getText());

        String paymentType = paymentType_textField.getText();

        flatConfig flat = new flatConfig(address, price, payment, phone_number, paymentType);
        db.add_flat(flat);
        enterButton.getScene().getWindow().hide();
    }
}
