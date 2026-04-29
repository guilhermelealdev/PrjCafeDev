const Api = "http://localhost:8080";

async function cadastrarClientes(event) {
	event?.preventDefault();
	const nomeCliente = document.getElementById("nomeCliente").value.trim();
	const email = document.getElementById("emailCliente").value.trim();

	try {
		const resposta = await fetch(`${Api}/clientes/cadastrar`, {
			method: "POST",
			headers: { "Content-type": "application/json" },
			body: JSON.stringify({
				nome: nomeCliente,
				email: email,
			}),
		});

		if (!resposta.ok) throw new Error(resposta.status);

		alert("CLIENTE CADASTRADO COM SUCESSO!");

		document.getElementById("cadastro").reset();
	} catch (err) {
		alert("ERRO AO CADASTRAR");
		console.error(err);
	}
}

async function cadastrarPedido(event) {
	event?.preventDefault();
	const descricao = document.getElementById("descricao").value.trim();
	const valorTotal = document.getElementById("valorTotal").value.trim();
	const dataPedido = document.getElementById("dataPedido").value.trim();
	const cliente = {
		idCliente: document.getElementById("idCliente").value,
	};
	try {
		const resposta = await fetch(`${Api}/pedidos/cadastrar`, {
			method: "POST",
			headers: { "Content-type": "application/json" },
			body: JSON.stringify({ descricao, valorTotal, dataPedido, cliente }),
		});
		if (!resposta.ok) throw new Error(resposta.status);
		alert("PEDIDO CADASTRADO COM SUCESSO!");
		document.getElementById("cadastro").reset();
	} catch (err) {
		alert("ERRO AO CADASTRAR");
		console.error(err);
	}
}

async function atualizarCliente(event) {
	event?.preventDefault();
	const nome = document.getElementById("nomeCliente").value.trim();
	const email = document.getElementById("emailCliente").value.trim();
	const idCliente = document.getElementById("idCliente").value.trim();
	try {
		const resposta = await fetch(`${Api}/clientes/${idCliente}`, {
			method: "PUT",
			headers: { "Content-type": "application/json" },
			body: JSON.stringify({
				idCliente: idCliente,
				nome: nome,
				email: email
			}),
		});

		if (!resposta.ok) throw new Error(resposta.status);

		alert("CLIENTE ATUALIZADO COM SUCESSO!");

		document.getElementById("cadastro").reset();
	} catch (err) {
		alert("ERRO AO ATUALIZAR");
		console.error(err);
	}
}

function buscarClientes() {
	fetch(`${Api}/clientes`)
		.then((response) => {
			if (!response.ok) {
				throw new Error(`Erro HTTP: ${response.status}`);
			}
			return response.json();
		})
		.then((data) => {
			const tbody = document
				.getElementById("clientes-tabela")
				.querySelector("tbody");
			tbody.innerHTML = "";

			if (data.length === 0) {
				alert("Nenhum cliente encontrado.");
				return;
			}

			data.forEach((cliente) => {
				const linha = document.createElement("tr");
				linha.innerHTML = `
                    <td>${cliente.idCliente}</td>
                    <td>${cliente.nome}</td>
                `;
				tbody.appendChild(linha);
			});
		})
		.catch((error) => {
			console.error("Erro ao listar clientes:", error);
			alert("Erro ao carregar a lista de clientes!");
		});
}

function buscarPedidos() {
	fetch(`${Api}/pedidos`)
		.then((response) => {
			if (!response.ok) {
				throw new Error(`Erro HTTP: ${response.status}`);
			}
			return response.json();
		})
		.then((data) => {
			const tbody = document
				.getElementById("pedidos-tabela")
				.querySelector("tbody");
			tbody.innerHTML = "";

			if (data.length === 0) {
				alert("Nenhum pedido encontrado.");
				return;
			}

			data.forEach((pedido) => {
				const linha = document.createElement("tr");
				linha.innerHTML = `
					<td>${pedido.idPedido}</td>
                    <td>${pedido.descricao}</td>
                    <td>R$${pedido.valorTotal}</td>
                     <td>${pedido.dataPedido}</td>
                     <td>${pedido.cliente.idCliente}</td>
                    <td>${pedido.cliente.nome}</td>
                    
                `;
				tbody.appendChild(linha);
			});
		})
		.catch((error) => {
			console.error("Erro ao listar pedidos:", error);
			alert("Erro ao carregar a lista de pedidos!");
		});
}

async function deletarCliente(event) {
	event?.preventDefault();
	const idCliente = document.getElementById("deletar").value.trim();
	try {
		const resposta = await fetch(`${Api}/clientes/${idCliente}`, {
			method: "DELETE",
			headers: { "Content-type": "application/json" },
		});
		if (!resposta.ok) throw new Error(resposta.status);
		alert("CLIENTE DELETADO COM SUCESSO!");
		document.getElementById("deletarFormulario").reset();
	} catch (err) {
		alert("ERRO AO DELETAR");
		console.error(err);
	}
}

function pedidosCrescente() {
	fetch(`${Api}/pedidos/crescente`)
		.then((response) => {
			if (!response.ok) {
				throw new Error(`Erro HTTP: ${response.status}`);
			}
			return response.json();
		})
		.then((data) => {
			const tbody = document
				.getElementById("pedidos-tabela")
				.querySelector("tbody");
			tbody.innerHTML = "";

			if (data.length === 0) {
				alert("Nenhum pedido encontrado.");
				return;
			}

			data.forEach((pedido) => {
				const linha = document.createElement("tr");
				linha.innerHTML = `
					<td>${pedido.cliente.idCliente}</td>
					<td>${pedido.cliente.nome}</td>
                    <td>${pedido.descricao}</td>
                     <td>${pedido.dataPedido}</td>
					 <td>R$${pedido.valorTotal}</td>
                    
                    
                `;
				tbody.appendChild(linha);
			});
		})
		.catch((error) => {
			console.error("Erro ao listar pedidos:", error);
			alert("Erro ao carregar a lista de pedidos!");
		});
}

function pedidosDecrescente() {
	fetch(`${Api}/pedidos/decrescente`)
		.then((response) => {
			if (!response.ok) {
				throw new Error(`Erro HTTP: ${response.status}`);
			}
			return response.json();
		})
		.then((data) => {
			const tbody = document
				.getElementById("pedidos-tabela")
				.querySelector("tbody");
			tbody.innerHTML = "";

			if (data.length === 0) {
				alert("Nenhum pedido encontrado.");
				return;
			}

			data.forEach((pedido) => {
				const linha = document.createElement("tr");
				linha.innerHTML = `
		<td>${pedido.cliente.idCliente}</td>
		<td>${pedido.cliente.nome}</td>
		<td>${pedido.descricao}</td>
		 <td>${pedido.dataPedido}</td>
		 <td>R$${pedido.valorTotal}</td>
                    
                `;
				tbody.appendChild(linha);
			});
		})
		.catch((error) => {
			console.error("Erro ao listar pedidos:", error);
			alert("Erro ao carregar a lista de pedidos!");
		});
}

function buscarPorNome(nome) {
	fetch(`${Api}/pedidos/cliente/${nome}`)
		.then((response) => {
			if (!response.ok) {
				throw new Error(`Erro HTTP: ${response.status}`);
			}
			return response.json();
		})
		.then((data) => {
			const tbody = document
				.getElementById("pedidos-tabela")
				.querySelector("tbody");
			tbody.innerHTML = "";

			if (data.length === 0) {
				alert("Nenhum pedido encontrado.");
				return;
			}

			data.forEach((pedido) => {
				const linha = document.createElement("tr");
				linha.innerHTML = `
                    <td>${pedido.idPedido}</td>
                    <td>${pedido.descricao}</td>
                    <td>${pedido.dataPedido}</td>
                    <td>R$${pedido.valorTotal}</td>
                     <td>${pedido.cliente.idCliente}</td>
                    <td>${pedido.cliente.nome}</td>
                    
                `;
				tbody.appendChild(linha);
			});
		})
		.catch((error) => {
			console.error("Erro ao listar pedidos:", error);
			alert("Erro ao carregar a lista de pedidos!");
		});
}

function buscarPorId(id) {
	fetch(`${Api}/pedidos/${id}`)
		.then((response) => {
			if (!response.ok) {
				throw new Error(`Erro HTTP: ${response.status}`);
			}
			return response.json();
		})
		.then((data) => {
			const tbody = document
				.getElementById("pedidos-tabela")
				.querySelector("tbody");
			tbody.innerHTML = "";

			if (data.length === 0) {
				alert("Nenhum pedido encontrado.");
				return;
			}
			const listaPedido = [data];
			listaPedido.forEach((pedido) => {
				const linha = document.createElement("tr");
				linha.innerHTML = `
                    <td>${pedido.idPedido}</td>
                    <td>${pedido.descricao}</td>
                    <td>${pedido.dataPedido}</td>
                    <td>R$${pedido.valorTotal}</td>
                    <td>${pedido.cliente.idCliente}</td>
                    <td>${pedido.cliente.nome}</td>
                    
                `;
				tbody.appendChild(linha);
			});
		})
		.catch((error) => {
			console.error("Erro ao listar pedidos:", error);
			alert("Erro ao carregar a lista de pedidos!");
		});
}

function buscarPedido() {
	const informacao = document.getElementById("informacaoPedido").value.trim();

	if (!isNaN(informacao)) {
		console.log(typeof (informacao));

		return buscarPorId(informacao);
	}
	return buscarPorNome(informacao);
}
