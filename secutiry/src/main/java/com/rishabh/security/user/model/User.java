package com.rishabh.security.user.model;

import javax.persistence.*;
import com.rishabh.security.user.model.UserDetailsImpl;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    public User(){
    }

    public User(UserDetailsImpl userDetails){
        this.userDetails=userDetails;
    }

    @JoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private UserDetailsImpl userDetails;

    public long getId() {
        return id;
    }

    public UserDetailsImpl getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetailsImpl userDetails) {
        this.userDetails = userDetails;
    }
}
