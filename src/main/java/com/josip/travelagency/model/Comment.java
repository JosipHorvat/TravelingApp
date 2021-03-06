package com.josip.travelagency.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment extends MainEntity{

    @Column(length = 500)
    private String content;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;


}
