package com.robertoeugenio.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.robertoeugenio.cursomc.services.exceptions.DataIntegrityException;
import com.robertoeugenio.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotfound(ObjectNotFoundException e, HttpServletRequest request) {
		
		StandardError err  = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());//NOT FOUND GERANDO CODIGO DE ERRO
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		
		//MUDOU PARA BAD PARA NÃO SER O MESMO CÓDIGO DE ERRO
		StandardError err  = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());//BAD QUEST GERANDO CODIGO DE ERRO
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		
	}

}
