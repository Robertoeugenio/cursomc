package com.robertoeugenio.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> erros = new ArrayList<>();
	
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}


	public List<FieldMessage> getErros() {
		return erros;
	}


	public void addError (String fieldName, String messagem) {  //mostrará o tipo e a mensagem de erro
		erros.add(new FieldMessage(fieldName, messagem));
		
	}	
}
