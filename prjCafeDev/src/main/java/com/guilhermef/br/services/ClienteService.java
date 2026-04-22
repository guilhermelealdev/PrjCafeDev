package com.guilhermef.br.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.guilhermef.br.Dtos.ClienteResponseDto;
import com.guilhermef.br.entities.Cliente;
import com.guilhermef.br.repositories.ClienteRepository;
import com.guilhermef.br.strategies.ClienteUpdateStrategy;

@Service
public class ClienteService implements ClienteUpdateStrategy {

	private final ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public Cliente salvarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente encontrarCliente(Long idCliente) {
		return clienteRepository.findById(idCliente).orElse(null);
	}

	public List<ClienteResponseDto> listarClientes() {
		return clienteRepository.findAll().stream()
				.map(Cliente -> new ClienteResponseDto(Cliente.getIdCliente(), Cliente.getNome()))
				.collect(Collectors.toList());
	}

	public void deletarClientePorId(Long idCliente) {
		clienteRepository.deleteById(idCliente);
	}

	public Cliente atualizarCliente(Long idCliente, Cliente clienteAtualizado) {
		Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);

		if (clienteOptional.isPresent()) {
			Cliente cliente = clienteOptional.get();
			verificarEmail(clienteAtualizado, cliente);
			verificarNome(clienteAtualizado, cliente);
			return clienteRepository.save(cliente);
		}
		return null;
	}

	@Override
	public void verificarEmail(Cliente clienteAtualizado, Cliente cliente) {
		if (!clienteAtualizado.getEmail().isBlank() || !clienteAtualizado.getEmail().isEmpty()) {
			cliente.setEmail(clienteAtualizado.getEmail());
		}
		cliente.setEmail(cliente.getEmail());

	}

	@Override
	public void verificarNome(Cliente clienteAtualizado, Cliente cliente) {
		if (!clienteAtualizado.getNome().isBlank() || !clienteAtualizado.getNome().isEmpty()) {
			cliente.setNome(clienteAtualizado.getNome());
		}
		cliente.setNome(cliente.getNome());
	}

}
