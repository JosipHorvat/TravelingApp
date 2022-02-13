package com.josip.travelagency.model;
import static com.josip.travelagency.constants.TourErrorMessage.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Tour extends MainEntity{

    public enum Continent {
        AFRICA, ASIA, EUROPE, NORTH_AMERICA, SOUTH_AMERICA, AUSTRALIA;
    }

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

}
