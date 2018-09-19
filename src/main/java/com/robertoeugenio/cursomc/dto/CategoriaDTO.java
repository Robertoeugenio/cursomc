package com.robertoeugenio.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.robertoeugenio.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	

	@NotEmpty(message="Prennchimento obrigatório")                                //validação dos campos
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")  //validação dos campos
	private String nome;
	
	public CategoriaDTO() {
	}

	public CategoriaDTO(Categoria obj) { //instanciando o objeto da categoria
		id= obj.getId();
		nome= obj.getNome();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
