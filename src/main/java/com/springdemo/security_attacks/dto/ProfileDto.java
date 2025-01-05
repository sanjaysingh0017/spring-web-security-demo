package com.springdemo.security_attacks.dto;

public class ProfileDto {

    private String name;
    private String username;
    private String mobileNumber;
    private String creditCardNumber;

    public ProfileDto(String name, String username, String mobileNumber, String creditCardNumber) {
        this.name = name;
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.creditCardNumber = creditCardNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
