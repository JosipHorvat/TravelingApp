package com.josip.travelagency.service;

import com.josip.travelagency.SampleTestData;
import com.josip.travelagency.exceptions.TourNotFoundException;
import com.josip.travelagency.model.Tour;
import com.josip.travelagency.repository.TourRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class TourServiceImplTest {

    @Mock
    private TourRepository tourRepository;

    @InjectMocks
    private TourService tourService = new TourServiceImpl();

    @Captor
    private ArgumentCaptor valueCaptor;

    @Test
    @DisplayName("GIVEN two tour records exist in db" +
            " WHEN all tours are requested" +
            " THEN then two tours are returned")
    void getAllTours() {

        List<Tour> expectedTours = Arrays.asList(SampleTestData.TOUR1(), SampleTestData.TOUR2());

        when(tourRepository.findAll()).thenReturn(expectedTours);

        final List<Tour> actualTours = tourService.getAll();

        verify(tourRepository, times(1)).findAll();
        assertEquals(expectedTours, actualTours);
    }

    @Nested
    @DisplayName("Tour service, get tour by id")
    class TourServiceGetTourById {

        @Test
        @DisplayName("GIVEN tour record exist in db" +
                " WHEN a single tour is requested" +
                " THEN tour with trquested id is returned")
        void getTourByIdTest() {
            final Tour expectedTour = SampleTestData.TOUR1();

            when(tourRepository.findById((Long) valueCaptor.capture()))
                    .thenReturn(Optional.of(expectedTour));

            final Tour actualTour = tourService.getById(1L);

            verify(tourRepository, times(1)).findById((Long) valueCaptor.getValue());

            assertEquals(expectedTour,actualTour);
        }

        @Test
        @DisplayName("GIVEN tour record exist in db" +
                " WHEN a single tour is requested" +
                " THEN tour with requested id is returned")
        void testGetTourByIdNonExisting() {
            when(tourRepository.findById((Long) valueCaptor.capture()))
                    .thenReturn(Optional.empty());

            assertThrows(TourNotFoundException.class, () -> tourService.getById(99L));

            verify(tourRepository, times(1)).findById((Long) valueCaptor.getValue());


        }
    }


}