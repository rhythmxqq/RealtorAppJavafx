package com.example.realtoranalytics;


import java.sql.*;


public class DataBaseHandler extends Configs {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        dbConnection = DriverManager.getConnection(connectionString, dbUser,dbPass);
        return dbConnection;
    }
    public void add_flat(flatConfig flatCon){
        String insert = "INSERT INTO flatbase (address, price, payment, phone_number, paymentType)" + "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,flatCon.getAddress());
            prSt.setInt(2, flatCon.getPrice());
            prSt.setInt(3, flatCon.getPayment());
            prSt.setLong(4, flatCon.phone_number);
            prSt.setString(5, flatCon.getPaymentType());
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void signUpUser(User user){
        String insert = "INSERT INTO users (firstName, lastName, login, password, salari, numberOfSale, plusSalari) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getLogin());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, String.valueOf(user.getSalari()));
            prSt.setString(6, String.valueOf(user.getNumberOfSale()));
            prSt.setString(7, String.valueOf(user.getPlusSalari()));
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet getUser(User user){
        ResultSet resSet = null;

        try {

            String select = "select * from users where login =? AND password =?";
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());

            resSet =  prSt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }
}

