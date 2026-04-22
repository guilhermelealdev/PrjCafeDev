package com.guilhermef.br.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilhermef.br.Dtos.ClienteRequestDto;
import com.guilhermef.br.Dtos.ClienteResponseDto;
import com.guilhermef.br.entities.Cliente;
import com.guilhermef.br.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name =" clientes", description="Aqui fazemos operações nos clientes")
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	private final ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@Operation(summary="Aqui fazemos o post de um cliente")
	@PostMapping("/criar")
	public ClienteResponseDto salvarCliente(@RequestBody ClienteRequestDto clienteDto) {
		return clienteService.salvarCliente(clienteDto);
	}
	
	@Operation(summary="Aqui deletamos um cliente por id")
	@DeleteMapping("/deletar/{idCliente}")
	public void deletarClientePorId(@PathVariable Long idCliente) {
		clienteService.deletarClientePorId(idCliente);
	}
	
	@Operation(summary="Aqui é feito um get em todos os DTOs de clientes")
	@GetMapping
	public List<ClienteResponseDto> listarClientes() {
		return clienteService.listarClientes();
	}
	
	@Operation(summary = "Aqui é feito um único get de um cliente por id")
	@GetMapping("/{idCliente}")
	public ClienteResponseDto encontrarCliente(@PathVariable Long idCliente) {
		return clienteService.encontrarCliente(idCliente);
	}
	
	@Operation(summary = "Aqui criamos um response entity de um cliente para atualizar o email e o nome de um cliente")
	@PutMapping("/atualizar/{idCliente}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long idCliente, @RequestBody Cliente cliente) {
		Cliente clienteAtualizado = clienteService.atualizarCliente(idCliente, cliente);

		if (clienteAtualizado != null) {
			return ResponseEntity.ok(clienteAtualizado);
		}

		return ResponseEntity.notFound().build();
	}

}
