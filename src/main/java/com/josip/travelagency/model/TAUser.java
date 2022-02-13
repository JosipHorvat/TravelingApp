package com.josip.travelagency.model;

import javax.persistence.*;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class TAUser extends MainEntity{

    private String login;

    @Column(length = 68)
    private String password;

    @Transient//means it won't be saved in db
    private String confirmedPassword;

    private boolean enabled;

    @ManyToMany
    @JoinTable(name = "tour2user",
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "tour_id"))
    private List<Tour> tours;


}
