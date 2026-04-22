package com.guilhermef.br.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilhermef.br.entities.Pedido;
import com.guilhermef.br.repositories.PedidoRepository;

@Service
public class PedidoService {
	private final PedidoRepository pedidoRepository;

	@Autowired
	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	public Pedido salvarPedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	public void deletarPedido(Long idPedido) {
		pedidoRepository.deleteById(idPedido);
	}

	public Pedido encontrarPedido(Long idPedido) {
		return pedidoRepository.findById(idPedido).orElse(null);
	}

	public List<Pedido> listarPedidos() {
		return pedidoRepository.findAll();
	}
	
	public List<Pedido> listarMaisCaros(){
		return pedidoRepository.pedidosMaisCaros();
	}

	public List<Pedido> listarMaisBaratos(){
		return pedidoRepository.pedidosMaisBaratos();
	}

	
}
