package com.guilhermef.br.Dtos;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PedidoResponseDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;

	@NotBlank(message = "Descrição obrigatória!")
	private String descricao;

	@NotNull(message = "Valor inválido!")
	private double valorTotal;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@NotBlank(message = "Data inválida!")
	private String dataPedido;

	@ManyToOne
	private ClienteResponseDto cliente;

	
	public PedidoResponseDto() {
		
	}
	
	public PedidoResponseDto(Long idPedido, @NotBlank(message = "Descrição obrigatória!") String descricao,
			@NotNull(message = "Valor inválido!") double valorTotal,
			@NotBlank(message = "Data inválida!") String dataPedido, ClienteResponseDto cliente) {
		super();
		this.idPedido = idPedido;
		this.descricao = descricao;
		this.valorTotal = valorTotal;
		this.dataPedido = dataPedido;
		this.cliente = cliente;
	}



	public Long getIdPedido() {
		return idPedido;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}

	public ClienteResponseDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteResponseDto cliente) {
		this.cliente = cliente;
	}
}
