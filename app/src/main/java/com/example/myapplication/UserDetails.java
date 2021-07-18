package com.example.myapplication;

public class UserDetails {
    String userId;
    String name;
    String gender;
    String ageGroup;
    String covid_vac;
    String pre_existing;

    public UserDetails()
    {

    }
    public UserDetails(String userId, String name, String gender, String ageGroup, String covid_vac, String pre_existing)
    {
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.ageGroup=ageGroup;
        this.covid_vac = covid_vac;
        this.pre_existing = pre_existing;
    }

    public String getUserId() {
        return userId;
    }

    public  String getName(){return name;}
    public String getGender()
    {
        return gender;
    }

    public  String getAgeGroup() {
        return ageGroup;
    }

    public  String getCovid_vac() {
        return covid_vac;
    }

    public  String getPre_existing() {
        return pre_existing;
    }
}
