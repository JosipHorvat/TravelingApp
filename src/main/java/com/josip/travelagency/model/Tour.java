package com.josip.travelagency.model;
import static com.josip.travelagency.constants.TourErrorMessage.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
public class Tour extends MainEntity{

    public Tour (){
       setTourDetails(new TourDetails());
    }
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_details_id")
    private TourDetails tourDetails;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany( cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Image> images;


    @NotBlank(message = TOUR_NAME_NOT_NULL)
    @Size(min = 5, message = TOUR_STRING_SIZE)
    private String name;
    @Pattern(regexp = "^[a-zA-Z]{2}-[0-9]{2}[a-zA-Z]{1}$", message = TOUR_CODE_PATTERN)
    private String code;
    private Continent continent;
    @NotNull(message = TOUR_DATE_NOT_NULL)
    @Future(message = TOUR_DATE_FUTURE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Min(value = 7, message = TOUR_DURATION)
    @Max(value = 21, message = TOUR_DURATION)
    private int duration;
    private boolean allInclusive = false;

    @ManyToMany
    @JoinTable(name = "tour2user",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<TAUser> users;

    public List<TAUser> getUsers() {
        return users;
    }

    public void setUsers(List<TAUser> users) {
        this.users = users;
    }

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

    public TourDetails getTourDetails() {
        return tourDetails;
    }

    public void setTourDetails(TourDetails tourDetails) {
        this.tourDetails = tourDetails;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public enum Continent {
        AFRICA, ASIA, EUROPE, NORTH_AMERICA, SOUTH_AMERICA, AUSTRALIA;
    }


}
