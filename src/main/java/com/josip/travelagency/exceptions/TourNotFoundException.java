package com.josip.travelagency.exceptions;

import com.josip.travelagency.constants.ExceptionMessages;

public class TourNotFoundException extends Exception{

   public TourNotFoundException(Long id){
       super(String.format("%s: %d", ExceptionMessages.ERROR_MSG, id));
   }
}
