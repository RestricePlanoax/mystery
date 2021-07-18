package com.example.myapplication;

public class specialistDetails {
    String name;
    String emailAddress;
    String phoneNumber;
    String password;
    String isDoctor;

    public specialistDetails(){

    }
    public specialistDetails(String name,String emailAddress,String phoneNumber,String password)
    {
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
        isDoctor="True";
    }
    public String getEmailAddress(){return emailAddress;}
    public String getPhoneNumber(){return phoneNumber;}
    public String getName(){return name;}
    public String getPassword(){return password;}
    public String getIsDoctor(){return isDoctor;}

}
