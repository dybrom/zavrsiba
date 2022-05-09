package com.example.fixba.exception;

import com.example.fixba.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleExceptions(NotFoundException exception, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setCode(HttpStatus.NOT_FOUND.value());
		response.setTimestamp(LocalDateTime.now());
		response.setType(exception.getClass().getSimpleName());

		String exceptionMessage = exception.getMessage();
		response.setMessage(exceptionMessage == null ? "Resource Not Found" : exceptionMessage);

		String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
		response.setPath(uri);

		ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//		log.error("USER: " + ((ServletWebRequest) request).getRequest().getRemoteUser());
		log.error(response.toString());
		return entity;

	}

}
