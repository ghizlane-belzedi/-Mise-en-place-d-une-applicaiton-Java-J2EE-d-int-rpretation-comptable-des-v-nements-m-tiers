package ma.inetum.brique.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ma.inetum.brique.bean.ErrorResponse;
import ma.inetum.brique.exception.ExceptionTechnique;

@ControllerAdvice
public class ExceptionTechniqueController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(value = ExceptionTechnique.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleException(ExceptionTechnique ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}
}
