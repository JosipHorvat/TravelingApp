package com.josip.travelagency.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Tour {

    public enum Continent {
        AFRICA, ASIA, EUROPE, NORTH_AMERICA, SOUTH_AMERICA, AUSTRALIA;
    }

    private String name;
    private String code;
    private Continent continent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private int duration;
    private boolean allInclusive = false;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Continent getContinent() {
        return continent;
    }
    public void setContinent(Continent continent) {
        this.continent = continent;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public boolean isAllInclusive() {
        return allInclusive;
    }
    public void setAllInclusive(boolean allInclusive) {
        this.allInclusive = allInclusive;
    }



}
