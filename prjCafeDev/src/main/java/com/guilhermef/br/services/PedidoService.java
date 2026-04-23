package com.guilhermef.br.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilhermef.br.Dtos.ClienteResponseDto;
import com.guilhermef.br.Dtos.PedidoRequestDto;
import com.guilhermef.br.Dtos.PedidoResponseDto;
import com.guilhermef.br.entities.Pedido;
import com.guilhermef.br.repositories.PedidoRepository;

@Service
public class PedidoService {
	private final PedidoRepository pedidoRepository;

	@Autowired
	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	public PedidoResponseDto salvarPedido(PedidoRequestDto pedidoDto) {

		Pedido pedido = new Pedido();
		pedido.setCliente(pedidoDto.getCliente());
		pedido.setDataPedido(pedidoDto.getDataPedido());
		pedido.setDescricao(pedidoDto.getDescricao());
		pedido.setValorTotal(pedidoDto.getValorTotal());
		Pedido pedidoSalvo = pedidoRepository.save(pedido);
		ClienteResponseDto clienteDto = new ClienteResponseDto(pedidoSalvo.getCliente().getIdCliente(),
				pedidoSalvo.getCliente().getNome());

		return new PedidoResponseDto(pedidoSalvo.getIdPedido(), pedidoSalvo.getDescricao(), pedidoSalvo.getValorTotal(),
				pedidoSalvo.getDataPedido(), clienteDto);
	}

	public void deletarPedido(Long idPedido) {
		pedidoRepository.deleteById(idPedido);
	}

	public PedidoResponseDto encontrarPedido(Long idPedido) {
		Pedido pedido = pedidoRepository.findById(idPedido)
				.orElseThrow(() -> new RuntimeException("Pedido Não Encontrado"));
		
		ClienteResponseDto clienteDto = new ClienteResponseDto(pedido.getCliente().getIdCliente(),
				pedido.getCliente().getNome());

		return new PedidoResponseDto(pedido.getIdPedido(), pedido.getDescricao(), pedido.getValorTotal(),
				pedido.getDataPedido(), clienteDto);
	}

	public List<PedidoResponseDto> listarPedidos() {
	    return pedidoRepository.findAll().stream().map(pedido -> {
	        ClienteResponseDto clienteDto = new ClienteResponseDto(
	            pedido.getCliente().getIdCliente(),
	            pedido.getCliente().getNome()
	        );

	        return new PedidoResponseDto(
	            pedido.getIdPedido(),
	            pedido.getDescricao(),
	            pedido.getValorTotal(),
	            pedido.getDataPedido(),
	            clienteDto
	        );
	    }).collect(Collectors.toList());
	}

	public List<PedidoResponseDto> listarMaisCaros() {
		return pedidoRepository.pedidosMaisCaros();
	}

	public List<PedidoResponseDto> listarMaisBaratos() {
		return pedidoRepository.pedidosMaisBaratos();
	}

	public List<PedidoResponseDto> buscarPorNomeCliente(String nome) {
		if (nome.isBlank() || nome.isEmpty()) {
			throw new RuntimeException("Usuário não encontrado ou inexistente");
		}

		return pedidoRepository.buscarPorNomeCliente(nome);
	}

}
