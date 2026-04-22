package com.guilhermef.br.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermef.br.Dtos.PedidoRequestDto;
import com.guilhermef.br.Dtos.PedidoResponseDto;
import com.guilhermef.br.entities.Pedido;
import com.guilhermef.br.services.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "pedidos", description = "Aqui fazemos operações com pedidos")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	private final PedidoService pedidoService;

	@Autowired
	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@PostMapping("/criar")
	@Operation(summary = "Aqui fazemos um post simples do pedido")
	public PedidoResponseDto salvarPedido(@RequestBody PedidoRequestDto pedido) {
		return pedidoService.salvarPedido(pedido);
	}

	@DeleteMapping("/deletar/{idPedido}")
	@Operation(summary = "Aqui fazemos um delete simples do pedido")
	public void deletarPedido(@PathVariable Long idPedido) {
		pedidoService.deletarPedido(idPedido);
	}

	@GetMapping("/{idPedido}")
	@Operation(summary = "Aqui fazemos um get de um pedido por id")
	public PedidoResponseDto encontrarPedido(@PathVariable Long idPedido) {
		return pedidoService.encontrarPedido(idPedido);
	}

	@GetMapping
	@Operation(summary = "Aqui fazemos um get de todos os pedidos")
	public List<PedidoResponseDto> listarPedidos() {
		return pedidoService.listarPedidos();
	}
	
	@GetMapping("/decrescente")
	@Operation(summary = "Aqui fazemos um get de todos os pedidos por ordem decrescente")
	public List<Pedido> listarPedidosMaisCaros() {
		return pedidoService.listarMaisCaros();
	}
	
	@GetMapping("/crescente")
	@Operation(summary = "Aqui fazemos um get de todos os pedidos por ordem crescente")
	public List<Pedido> listarPedidosMaisBaratos() {
		return pedidoService.listarMaisBaratos();
	}
	
	@GetMapping("/cliente/{nome}")
	@Operation(summary = "Aqui é feito um get de todos os pedidos pelo nome")
	public List<Pedido> buscarPorNome(@PathVariable String nome){
		return pedidoService.buscarPorNomeCliente(nome);
	}

}
