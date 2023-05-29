package com.example.realtoranalytics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;

public class MoneyBillClass {
    @FXML
    private Button EnterButton;

    @FXML
    private Label UserName;

    @FXML
    private Label PlusSalary_text_money;

    @FXML
    private Label TotalSumMoney;

    @FXML
    private Label address_text_bill;

    @FXML
    private Label paymentType_text_bill;

    @FXML
    private Label payment_text_bill11;

    @FXML
    private Label price_text_bill1;

    Integer money = 0;

    @FXML
    void ClickEnterTotalSumButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        Random rand = new Random();
        Integer randin = rand.nextInt(0,1000000000);
        try(FileWriter writer = new FileWriter(randin.toString()+".txt", false))
        {
            writer.append("\t" +  "  Bill");
            writer.append("\n Address:" +address_text_bill.getText());
            writer.append("\n Price:" + price_text_bill1.getText());
            writer.append("\n Payment:" + payment_text_bill11.getText());
            writer.append("\n Payment type:" +paymentType_text_bill.getText() );
            writer.append("\n Additional services:" + PlusSalary_text_money.getText());
            writer.append("\n" + "\n");
            writer.append("\n Total amount payable:" + TotalSumMoney.getText());

            writer.append("\n Employee:" + UserName.getText());

            writer.append("\n \n Выдано в приложении AppRealtor");
            writer.append("\n Номер вашей покупки: " + randin);


            writer.flush();
        }
        catch(IOException ex){
            ex.getMessage();
        }
        DataBaseHandler db = new DataBaseHandler();
        Connection connection = db.getDbConnection();

        Integer payment = Integer.parseInt(payment_text_bill11.getText());
        Integer plusSalari =  Integer.parseInt(PlusSalary_text_money.getText());
        try {
            money = Integer.parseInt(payment_text_bill11.getText());
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate("UPDATE users SET salari = salari + " + payment + " where login='" + UserName.getText() + "'");
            int rows1 = statement.executeUpdate("UPDATE users SET plusSalari= plusSalari +" + plusSalari +" where login='"+UserName.getText()+"'");
            int rows2 = statement.executeUpdate("UPDATE users SET numberOfSale= numberOfSale +" + 1 +" where login='"+UserName.getText()+"'");
           int rows3 = statement.executeUpdate("UPDATE flatbase SET TypeSale = '" + "Продано" + "' WHERE price = " + price_text_bill1.getText());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        EnterButton.getScene().getWindow().hide();
    }

}
