package services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.Cliente;
import entities.ClienteResponseDto;
import repositories.ClienteRepository;

@Service
public class ClienteService {

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
			if ((clienteAtualizado.getNome() != null && clienteAtualizado.getEmail() != null)
					|| (clienteAtualizado.getNome() != "" && clienteAtualizado.getEmail() != "")) {

				cliente.setNome(clienteAtualizado.getNome());
				cliente.setEmail(clienteAtualizado.getEmail());

			}
			if ((clienteAtualizado.getNome() == null && clienteAtualizado.getEmail() != null)
					|| (clienteAtualizado.getNome() == "" && clienteAtualizado.getEmail() != "")) {
				cliente.setEmail(clienteAtualizado.getEmail());
			}
			if ((clienteAtualizado.getNome() != null && clienteAtualizado.getEmail() == null)
					|| (clienteAtualizado.getNome() != "" && clienteAtualizado.getEmail() == "")) {
				cliente.setNome(clienteAtualizado.getNome());
			}

			return clienteRepository.save(cliente);
		}
		return null;
	}

}
