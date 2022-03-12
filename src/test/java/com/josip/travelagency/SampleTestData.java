package com.josip.travelagency;

import com.josip.travelagency.model.Image;
import com.josip.travelagency.model.TAUser;
import com.josip.travelagency.model.Tour;
import com.josip.travelagency.model.TourDetails;

import java.util.*;

public class SampleTestData {


    public static final Tour TOUR1(){
        Calendar c = Calendar.getInstance();
        c.set(2033, 2,12);
        Date tourDate = c.getTime();
        List<Image> img = Arrays.asList(image2());

        Tour tour = new Tour();
        tour.setId(1L);
        tour.setName("Izlet u Varazdin");
        tour.setCode("OS-11F");
        tour.setContinent(Tour.Continent.EUROPE);
        tour.setDuration(10);
        tour.setDate(tourDate);
        tour.setImages(img);
        tour.setTourDetails(tourDetailsForTourOne());
        return tour;
    }
    public static final Tour TOUR2(){
        Calendar c = Calendar.getInstance();
        c.set(2023, 2,12);
        Date tourDate = c.getTime();
        List<Image> img = Arrays.asList(image1());

        Tour tour = new Tour();
        tour.setId(2L);
        tour.setName("Izlet u Japan");
        tour.setCode("VU-11F");
        tour.setContinent(Tour.Continent.ASIA);
        tour.setDuration(20);
        tour.setDate(tourDate);
        tour.setImages(img);
        tour.setTourDetails(tourDetailsForTourTwo());
        return tour;
    }

    public static final Image image1(){
        Image image = new Image();
        image.setId(1L);
        image.setName("Image from tour in Varazdin");
        image.setDescription("Test description");
        image.setImage(new byte[]{1,2,3,4,5,6,7});
        return image;
    }

    public static final Image image2(){
        Image image = new Image();
        image.setId(2L);
        image.setName("Image from tour in Osijek");
        image.setDescription("Test description");
        image.setImage(new byte[]{1,2,3,4,5,6,7});
        return image;
    }

    public static final TourDetails tourDetailsForTourOne(){
        TourDetails tourDetails = new TourDetails();
        tourDetails.setId(1L);
        tourDetails.setDescription("This are tour details for tour1");
        tourDetails.setCountry("Croatia");
        return tourDetails;
    }

    public static final TourDetails tourDetailsForTourTwo(){
        TourDetails tourDetails = new TourDetails();
        tourDetails.setId(2L);
        tourDetails.setDescription("This are tour details for tour2");
        tourDetails.setCountry("Japan");
        return tourDetails;
    }

    public static final TAUser user1(){
        List<Tour> tours = Arrays.asList(TOUR1(), TOUR2());

        TAUser user = new TAUser();
        user.setEnabled(true);
        user.setId(1L);
        user.setLogin("Pero");
        user.setPassword("123");
        user.setConfirmedPassword("123");
        user.setTours(tours);
        return user;
    }


}
