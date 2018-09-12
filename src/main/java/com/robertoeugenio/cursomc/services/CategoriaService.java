package com.robertoeugenio.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robertoeugenio.cursomc.domain.Categoria;
import com.robertoeugenio.cursomc.repositories.CategoriaRepository;
import com.robertoeugenio.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
 
	public Categoria find (Integer id) {
	 Optional <Categoria>obj = repo.findById(id);
	
		 return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não Encontrado Id: " + id + " , Tipo " + Categoria.class.getName()));
		 
	 }
		public Categoria insert(Categoria obj) {
			obj.setId(null);    //esse metodo para garantir que o objeto que vai ser inserido seja nulo
			return repo.save(obj);
		}
		public Categoria update(Categoria obj) {
			find(obj.getId()); //ele busca e se nao houver ele lança uma recessao
			return repo.save(obj); // atualizar.....
		}
}
	 
