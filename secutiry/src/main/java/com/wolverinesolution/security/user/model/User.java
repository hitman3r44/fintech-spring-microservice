package com.wolverinesolution.security.user.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    public User(){
    }

    public User(UserDetailsImpl userDetails){
        this.userDetails=userDetails;
    }

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private UserDetailsImpl userDetails;

    public UUID getId() {
        return id;
    }

    public UserDetailsImpl getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetailsImpl userDetails) {
        this.userDetails = userDetails;
    }
}
