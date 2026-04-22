package com.guilhermef.br.Dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class ClienteResponseDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;

	@NotBlank(message="Nome inválido!")
	private String nome;
	

	public ClienteResponseDto() {

	}

	
	
	public ClienteResponseDto(Long idCliente, @NotBlank(message = "Nome inválido!") String nome) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
	}



	public Long getIdCliente() {
		return idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
