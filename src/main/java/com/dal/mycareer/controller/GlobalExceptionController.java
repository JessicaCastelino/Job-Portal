package com.dal.mycareer.controller;


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
	
	@ExceptionHandler(SQLException.class)
	  public ModelAndView myError(SQLException exception) {
	    ModelAndView mv = new ModelAndView();
	    mv.addObject("exception", exception.getLocalizedMessage());
	    mv.setViewName("exception");
	    logger.debug("Redirecting to Error page");
	    return mv;
	  }
}
