package com.robertoeugenio.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robertoeugenio.cursomc.domain.Pedido;
import com.robertoeugenio.cursomc.repositories.PedidoRepository;
import com.robertoeugenio.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
 
	public Pedido find (Integer id) {
	 Optional <Pedido>obj = repo.findById(id);
	
		 return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não Encontrado Id: " + id + " , Tipo " + Pedido.class.getName()));
		 
	 }
}
	 
