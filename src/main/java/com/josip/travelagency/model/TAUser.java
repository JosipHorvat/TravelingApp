package com.josip.travelagency.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class TAUser extends MainEntity{

    private String login;

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
}
