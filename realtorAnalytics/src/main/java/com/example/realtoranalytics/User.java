package com.example.realtoranalytics;

public class User {
    private Integer iduser;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Integer salari;
    private Integer numberOfSale;
    private Integer plusSalari;

    public User(String firstName, String lastName, String login, String password,
                Integer salari, Integer numberOfSale, Integer plusSalari){
        this.firstName = firstName;
        this.lastName=lastName;
        this.login=login;
        this.password=password;
        this.salari=salari;
        this.numberOfSale=numberOfSale;
        this.plusSalari=plusSalari;
    }

    public User() {
    }
    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSalari() {
        return salari;
    }

    public void setSalari(Integer salari) {
        this.salari = salari;
    }

    public Integer getNumberOfSale() {
        return numberOfSale;
    }

    public void setNumberOfSale(Integer numberOfSale) {
        this.numberOfSale = numberOfSale;
    }

    public Integer getPlusSalari() {
        return plusSalari;
    }

    public void setPlusSalari(Integer plusSalari) {
        this.plusSalari = plusSalari;
    }
}
