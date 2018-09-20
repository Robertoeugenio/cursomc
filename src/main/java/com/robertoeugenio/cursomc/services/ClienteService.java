package com.robertoeugenio.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.robertoeugenio.cursomc.domain.Cliente;
import com.robertoeugenio.cursomc.dto.ClienteDTO;
import com.robertoeugenio.cursomc.repositories.ClienteRepository;
import com.robertoeugenio.cursomc.services.exceptions.DataIntegrityException;
import com.robertoeugenio.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
 
	public Cliente find (Integer id) {
	 Optional <Cliente>obj = repo.findById(id);
	//nova atualização
		 return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não Encontrado Id: " + id + " , Tipo " + Cliente.class.getName()));
		 
	 }
	
	public Cliente update(Cliente obj) { // metodo para atualizar
		Cliente newObj = find(obj.getId()); // instanciando o cliente a partir do banco de dados
		updateData(newObj, obj);  // atualiza o dado que vem do banco de dados que veio como argumento
		return repo.save(newObj); // atualizar.....
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
			
		} 
		catch (DataIntegrityViolationException e) { // mensagem quando for deletar 
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");

		}
	}
	public List<Cliente> findAll(){
		return repo.findAll();
		
	}
		
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){ //metodo de serviço
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);	
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
			return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());    // atualizar com novos dados 
		newObj.setEmail(obj.getEmail()); //atualiza com novos dados por isso usando o get fornecido pelo objeto obj
	}
}
	 
