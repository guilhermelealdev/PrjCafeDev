package com.guilhermef.br.strategies;

import com.guilhermef.br.entities.Cliente;

public interface ClienteUpdateStrategy {
	void verificarEmail(Cliente clienteAtualizado, Cliente cliente);
	void verificarNome(Cliente clienteAtualizado, Cliente cliente);
}
