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
		find(obj.getId()); // ele busca e se nao houver ele lança uma recessao
		return repo.save(obj); // atualizar.....
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
}
