package com.robertoeugenio.cursomc.domain.enums;

public enum EstadoPagamento {
	PENDENTE (1, "Pendente"), 
	QUITADO  (2, "Quitado"),
	CANCELADO (3, "Cancelado");
	
	private int cod;
	private String descricao;

	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() { // somente o get
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer cod) {

		if (cod == null) { // só para garantir
			return null;
		}

		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);

	}
}
	

