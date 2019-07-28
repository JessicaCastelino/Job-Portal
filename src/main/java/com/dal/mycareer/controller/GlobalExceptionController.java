package com.dal.mycareer.controller;


import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

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
	  public ModelAndView myError(Exception exception) {
	    ModelAndView mv = new ModelAndView();
	    logger.debug(exception.getMessage());
	    logger.debug(" ************** "+exception.getLocalizedMessage());
	    mv.addObject("exception", exception);
	    mv.setViewName("exception");
	    logger.debug("Redirecting to error page");
	    return mv;
	  }
}
