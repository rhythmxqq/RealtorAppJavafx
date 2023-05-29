package com.example.realtoranalytics;

import animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogingClass {

    @FXML
    private Button button_reg;

    @FXML
    private Button enter_button;

    @FXML
    private TextField login_table;

    @FXML
    private PasswordField password_table;

    private int counter = 0;

    @FXML
    void reg_Action (ActionEvent event){
        button_reg.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("register.fxml"));
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
    void button_enter_menu(ActionEvent event) throws SQLException, ClassNotFoundException {
        String login = login_table.getText().trim();
        String loginPassword = password_table.getText().trim();

        if (!login.equals("") && !loginPassword.equals("")) {
            loginUser(login, loginPassword);
            if (counter >= 1) {
                enter_button.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("MenuMainApp.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Scene scene = new Scene(root, 756, 453);
                Stage stage = new Stage();
                Label label  = (Label) scene.lookup("#text_hide");
                label.setText(login);
                Label label1 = (Label) scene.lookup("#text_money");
                String money = "";
                DataBaseHandler db = new DataBaseHandler();
                Connection connection = db.getDbConnection();
                String sqlContent = String.format("select * from users where login='%s'", login);
                ResultSet rs = connection.createStatement().executeQuery(sqlContent);
                while (rs.next()){
                    money += (rs.getInt(6) + rs.getInt(8)) * ((rs.getInt(7)*0.01)+1);
                }
                label1.setText(money);
                stage.setScene(scene);
                stage.show();
            }
        }
    }
    private void loginUser (String login, String loginPassword) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setLogin(login);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        while (true) {
            try {
                if (!result.next()) {
                    break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }
        if (counter >= 1) ;
        else {
            Shake userLoginAnim = new Shake(login_table);
            Shake userPassAnim = new Shake(password_table);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }
    }
}
