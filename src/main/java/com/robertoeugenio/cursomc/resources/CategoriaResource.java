package com.robertoeugenio.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.robertoeugenio.cursomc.domain.Categoria;
import com.robertoeugenio.cursomc.dto.CategoriaDTO;
import com.robertoeugenio.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {// tipo especial armazena informaçoes
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO) { // vai para o banco de dados e fornecer o id // //requestbody faz ser convertido para objeto// categoria automaticamente																
		Categoria obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();// caracteristica																														// do																												// framework
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) // requisição feito no put
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id) {
		Categoria obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build(); // conteudo vazio
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {// tipo especial armazena informaçoes
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET) // nao precisa de id pois vai buscar todas as categorias
	public ResponseEntity<List<CategoriaDTO>> findAll() {// tipo especial armazena informaçoes
		List<Categoria> list = service.findAll(); // vai buscar uma lista por isso o metodo é de lista
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());// converter lista para outra lista 																																																					
		return ResponseEntity.ok().body(listDto); // retornar lista de categorias
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET) // nao precisa de id pois vai buscar todas as categorias

	public ResponseEntity<Page<CategoriaDTO>> findPage( // tipo especial armazena informaçoes
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction); // vai buscar uma lista por isso o metodo é de lista 																		
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj)); // converter uma lista para outra lista
		return ResponseEntity.ok().body(listDto); // retornar lista de categorias
	}
}
