package com.robertoeugenio.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.robertoeugenio.cursomc.domain.Categoria;
import com.robertoeugenio.cursomc.dto.CategoriaDTO;
import com.robertoeugenio.cursomc.repositories.CategoriaRepository;
import com.robertoeugenio.cursomc.services.exceptions.DataIntegrityException;
import com.robertoeugenio.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não Encontrado Id: " + id + " , Tipo " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) { // metodo para inserir
		obj.setId(null); // esse metodo para garantir que o objeto que vai ser inserido seja nulo
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) { // metodo para atualizar
		Categoria newObj = find(obj.getId()); // instanciando o cliente a partir do banco de dados
		updateData(newObj, obj);  // atualiza o dado que vem do banco de dados que veio como argumento
		return repo.save(newObj); // atualizar.....
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
			
		} 
		catch (DataIntegrityViolationException e) { // mensagem quando for deletar 
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");

		}
	}
	public List<Categoria> findAll(){
		return repo.findAll();
		
	}
		
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){ //metodo de serviço
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);	
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome()); //metodo auxiliar para obj => objTDO
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());    // atualizar com novos dados 
	}
}
