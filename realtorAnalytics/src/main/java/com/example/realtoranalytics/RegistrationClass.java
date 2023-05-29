package com.example.realtoranalytics;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;


public class RegistrationClass {

    @FXML
    private Button regButton;

    @FXML
    private TextField reg_lastName_table;

    @FXML
    private TextField reg_log_table;

    @FXML
    private TextField reg_name_table;

    @FXML
    private TextField reg_password_table;

    @FXML
    void reg_button_enter(ActionEvent event) {
        boolean result = reg_name_table.getText().matches("[A-zА-я]*") || reg_lastName_table.getText().matches("[A-zА-я]*");
      if(reg_name_table.getText().equals("") && reg_lastName_table.getText().equals("")&& reg_password_table.getText().equals("") && reg_log_table.getText().equals("")){
      //  JOptionPane.showMessageDialog(null,"Данные не введены");
        }
        else if(!result){
      // JOptionPane.showMessageDialog(null, "Данные введены неверно");
       }
       else {
            regButton.getScene().getWindow().hide();
            regNewUser();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("meniloging.fxml"));
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
  }

    private void regNewUser() {
        DataBaseHandler dbHandler = new DataBaseHandler();
        String name = reg_name_table.getText();
        String surname = reg_lastName_table.getText();
        String log = reg_log_table.getText();
        String pass = reg_password_table.getText();

        User user = new User(name,surname,log,pass,0,0,0);
        dbHandler.signUpUser(user);
        regButton.getScene().getWindow().hide();
    }

}
