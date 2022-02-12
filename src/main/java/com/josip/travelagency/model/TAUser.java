package com.josip.travelagency.model;

import javax.persistence.*;
import java.util.List;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}
