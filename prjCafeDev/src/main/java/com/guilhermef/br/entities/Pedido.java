package com.guilhermef.br.entities;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;

	@NotBlank
	private String descricao;

	@NotNull
	private double valorTotal;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@NotBlank
	private String dataPedido;

	@ManyToOne
	private Cliente cliente;

	public Pedido() {

	}

	public Pedido(Long idPedido, @NotBlank String descricao, double valorTotal, @NotBlank String dataPedido,
			@NotBlank Cliente cliente) {
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
