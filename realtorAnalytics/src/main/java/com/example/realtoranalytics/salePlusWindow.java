package com.example.realtoranalytics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class salePlusWindow {
    @FXML
    private Button enterButton;

    @FXML
    private Label textUserNameHide;

    @FXML
    private CheckBox correctionBox;

    @FXML
    private CheckBox fastProgressBox;

    @FXML
    private CheckBox helpBox;

    @FXML
    private CheckBox inspectionHelpBox;

    @FXML
    private Label plusMoney;

    @FXML
    private Label hideLabelMoney;

    int num1 = 0;
    int num2 = 0;
    int num3 = 0;
    int num4 = 0;
    Integer resault = 0;


    @FXML
    void clickEnterSaleButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        enterButton.getScene().getWindow().hide();
        DataBaseHandler db = new DataBaseHandler();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("billWindow.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection connection = db.getDbConnection();
        String sqlContent = String.format("select * from flatbase where payment='%s'", hideLabelMoney.getText());
        ResultSet rs = connection.createStatement().executeQuery(sqlContent);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root, 446, 566);
        Stage stage = new Stage();
        Label label  = (Label) scene.lookup("#payment_text_bill11");
        label.setText(hideLabelMoney.getText());
        Label label1  = (Label) scene.lookup("#PlusSalary_text_money");
        Label label2  = (Label) scene.lookup("#address_text_bill");
        Label label3  = (Label) scene.lookup("#price_text_bill1");
        Label label4  = (Label) scene.lookup("#paymentType_text_bill");
        Label labelUser = (Label) scene.lookup("#UserName");
        Label labelres  = (Label) scene.lookup("#TotalSumMoney");

        while (rs.next()){
            label2.setText(rs.getString(2));
            label4.setText(rs.getString(6));
            label3.setText(rs.getString(3));
            labelres.setText(String.valueOf(Integer.parseInt(hideLabelMoney.getText())+Integer.parseInt(plusMoney.getText())+Integer.parseInt(label3.getText())));

        }
        labelUser.setText(textUserNameHide.getText());
        label1.setText(resault.toString());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void changeCheckBoxHelp(ActionEvent event) {
        if (helpBox.isSelected()){
            num3 = 3000;
            resault += num3;
            plusMoney.setText(resault.toString());
        }
        else{
            resault -= num3;
            plusMoney.setText(resault.toString());
        }
    }

    @FXML
    void changeCheckBoxMinus(ActionEvent event) {
        if(correctionBox.isSelected()){
            num2 = 2000;
            resault += num2;
            plusMoney.setText(resault.toString());
        }
        else{
            resault -= num2;
            plusMoney.setText(resault.toString());
        }
    }

    @FXML
    void changeCheckBoxRun(ActionEvent event) {
        if (fastProgressBox.isSelected()){
            num1 = 1500;
            resault += num1;
            plusMoney.setText(resault.toString());
        }
        else {
            resault -= num1;
            plusMoney.setText(resault.toString());
        }
    }
    @FXML
    void changeCheckBoxSeeTwo(ActionEvent event) {
        if (inspectionHelpBox.isSelected()){
            num4 = 4000;
            resault += 4000;
            plusMoney.setText(resault.toString());
        }
        else {
            resault -= 4000;
            plusMoney.setText(resault.toString());
        }
    }
}
