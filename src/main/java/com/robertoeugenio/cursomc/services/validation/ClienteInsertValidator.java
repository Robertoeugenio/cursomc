package com.robertoeugenio.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.robertoeugenio.cursomc.domain.Cliente;
import com.robertoeugenio.cursomc.domain.enums.TipoCliente;
import com.robertoeugenio.cursomc.dto.ClienteNewDTO;
import com.robertoeugenio.cursomc.repositories.ClienteRepository;
import com.robertoeugenio.cursomc.resources.exception.FieldMessage;
import com.robertoeugenio.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	
	@Override
	public void initialize(ClienteInsert ann) {
	}	
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCpf(objDto.getCpfOuCnpj())) {

			list.add(new FieldMessage("CpfOuCnpj", "Cpf inválido"));
		}

		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCnpj(objDto.getCpfOuCnpj())) {

			list.add(new FieldMessage("CpfOuCnpj", "Cnpj inválido"));
		}
		
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux !=null) {
				list.add(new FieldMessage("email", "Email já existente"));
			}
		
		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}