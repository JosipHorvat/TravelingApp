package com.josip.travelagency.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;


@Entity
@Table(name = "tour_details")
public class TourDetails extends MainEntity{

    private String country;

    @Column(length = 2000)
    private String description;

	/*
	 *  if I need bidirectional mapping
	 * @OneToOne(mappedBy = "tourDetails")
	private Tour tour;
	 */

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
