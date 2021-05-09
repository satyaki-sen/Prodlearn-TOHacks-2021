package com.satyaki.courierdemo;

class User {

    String name,email,pass,phonenumber,ranking,medal;

    public User(String name, String email, String pass, String phonenumber, String ranking,String medal) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.phonenumber = phonenumber;
        this.ranking = ranking;
        this.medal=medal;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getRanking() {
        return ranking;
    }

    public String getMedal() {
        return medal;
    }
}
