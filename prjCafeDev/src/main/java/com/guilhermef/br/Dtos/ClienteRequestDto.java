package com.guilhermef.br.Dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClienteRequestDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;

	@NotBlank(message="Nome inválido!")
	private String nome;
	
	@NotBlank(message="Email inválido")
	@Email(message="Email inválido!")
	private String email;

	public ClienteRequestDto() {

	}

	public ClienteRequestDto(Long idCliente, @NotBlank(message = "Nome inválido!") String nome,
			@NotBlank(message = "Email inválido") @Email(message = "Email inválido!") String email) {
		this.idCliente = idCliente;
		this.nome = nome;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
