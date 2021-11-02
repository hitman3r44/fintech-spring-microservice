package com.wolverinesolution.lendingengine.domain.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class User {

    @Id
    private String username;
    private String first_name;
    private String last_name;
    private int age;
    private String occupation;
    @OneToOne(cascade = CascadeType.ALL) /*when user object gets deleted, balance will also get deleted*/
    private Balance balance;

    public User() {
    }

    public User(String username, String first_name, String last_name, int age, String occupation, Balance balance) {
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.occupation = occupation;
        this.balance = balance;
    }

    public void setBalance(Balance balance){
        if(this.balance==null){
            this.balance=balance;
        }
    }

    public void topUp(final Money money){
        balance.topUp(money);
    }

    public void withdraw(final Money money){
        balance.withdraw(money);
    }

    public Balance getBalance() {
        return balance;
    }
//
//    public void setBalance(Balance balance) {
//        this.balance = balance;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(username, user.username) && Objects.equals(first_name, user.first_name) && Objects.equals(last_name, user.last_name) && Objects.equals(occupation, user.occupation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, first_name, last_name, age, occupation);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", occupation='" + occupation + '\'' +
                '}';
    }
}
