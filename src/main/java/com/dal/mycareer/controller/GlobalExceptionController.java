package com.dal.mycareer.controller;


import java.io.IOException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController
{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler({SQLException.class,IOException.class})
	  public ModelAndView catchException(SQLException exception) {
		logger.debug("GlobalExceptionController: catchException method: Entered");
	    ModelAndView model = new ModelAndView();
	    model.addObject("exception", exception.getLocalizedMessage());
	    model.setViewName("exception");
	    logger.debug("Redirecting to Exception page");
	    return model;
	  }
}
