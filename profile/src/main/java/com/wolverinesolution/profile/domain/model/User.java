package com.wolverinesolution.profile.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class User {
    @Id
    private String username;
    private String firstname;
    private String lastname;
    private int age;
    private String occupation;
    private LocalDate registeredSince;

    public User(String username, String firstname, String lastname, int age, String occupation, LocalDate registeredSince) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.occupation = occupation;
        this.registeredSince = registeredSince;
    }

    public User() {
    }

    public void setRegisteredSince(LocalDate registeredSince) {
        this.registeredSince = registeredSince;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public String getOccupation() {
        return occupation;
    }

    public LocalDate getRegisteredSince() {
        return registeredSince;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(username, user.username) && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname) && Objects.equals(occupation, user.occupation) && Objects.equals(registeredSince, user.registeredSince);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstname, lastname, age, occupation, registeredSince);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", occupation='" + occupation + '\'' +
                ", registeredSince=" + registeredSince +
                '}';
    }
}
