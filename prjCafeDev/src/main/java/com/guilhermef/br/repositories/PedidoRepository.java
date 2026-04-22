package com.guilhermef.br.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.guilhermef.br.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Query("SELECT pedido FROM Pedido pedido ORDER BY pedido.valorTotal DESC")
	List<Pedido> pedidosMaisCaros();
	
	@Query("SELECT pedido FROM Pedido pedido ORDER BY pedido.valorTotal ASC")
	List<Pedido> pedidosMaisBaratos();
	
	@Query("SELECT pedido FROM Pedido pedido WHERE pedido.cliente.nome = :nome")
	List<Pedido> buscarPorNomeCliente(@Param("nome") String nome);
}
