package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entities.Pedido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import services.PedidoService;

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
	public Pedido salvarPedido(@RequestBody Pedido pedido) {
		return pedidoService.salvarPedido(pedido);
	}

	@DeleteMapping("/deletar/{idPedido}")
	@Operation(summary = "Aqui fazemos um delete simples do pedido")
	public void deletarPedido(@PathVariable Long idPedido) {
		pedidoService.deletarPedido(idPedido);
	}

	@GetMapping("/{idPedido}")
	@Operation(summary = "Aqui fazemos um get de um pedido por id")
	public Pedido encontrarPedido(@PathVariable Long idPedido) {
		return pedidoService.encontrarPedido(idPedido);
	}

	@GetMapping
	@Operation(summary = "Aqui fazemos um get de todos os pedidos")
	public List<Pedido> listarPedidos() {
		return pedidoService.listarPedidos();
	}

}
