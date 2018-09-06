package com.robertoeugenio.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robertoeugenio.cursomc.domain.Cliente;
import com.robertoeugenio.cursomc.repositories.ClienteRepository;
import com.robertoeugenio.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
 
	public Cliente buscar (Integer id) {
	 Optional <Cliente>obj = repo.findById(id);
	//nova atualização
		 return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não Encontrado Id: " + id + " , Tipo " + Cliente.class.getName()));
		 
	 }
}
	 
