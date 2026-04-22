package com.nutsolver.demo.entities;

public class UsuarioResponseDto {
	
	private Long idUsuario;
	
	private String nome;
	
	private String email;

	public UsuarioResponseDto(Long idUsuario, String nome, String email) {
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.email = email;
	}

	public Long getIdUsuario() {
		return idUsuario;
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
