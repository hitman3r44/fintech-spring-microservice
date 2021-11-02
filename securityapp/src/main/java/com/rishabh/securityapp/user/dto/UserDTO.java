package com.rishabh.securityapp.user.dto;


public class UserDTO {

    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private int age;
    private String occupation;

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getAge() {
        return age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
