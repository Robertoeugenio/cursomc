package com.robertoeugenio.cursomc.resources;

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

import com.robertoeugenio.cursomc.domain.Cliente;
import com.robertoeugenio.cursomc.dto.ClienteDTO;
import com.robertoeugenio.cursomc.services.ClienteService;

// fazendo o endpoint

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	@Autowired
	private ClienteService service;
    
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {// tipo especial armazena informaçoes
		Cliente obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) // requisição feito no put
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
		Cliente obj = service.fromDTO(objDto);
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
	public ResponseEntity<List<ClienteDTO>> findAll() {// tipo especial armazena informaçoes
		List<Cliente> list = service.findAll(); // vai buscar uma lista por isso o metodo é de lista
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());// converter lista para outra lista 																																																					
		return ResponseEntity.ok().body(listDto); // retornar lista de categorias
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET) // nao precisa de id pois vai buscar todas as categorias

	public ResponseEntity<Page<ClienteDTO>> findPage( // tipo especial armazena informaçoes
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction); // vai buscar uma lista por isso o metodo é de lista 																		
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj)); // converter uma lista para outra lista
		return ResponseEntity.ok().body(listDto); // retornar lista de categorias
	}
	
}
