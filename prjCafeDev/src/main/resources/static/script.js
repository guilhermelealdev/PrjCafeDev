const Api = "http://localhost:8080";

async function cadastrarClientes(event) {
  event?.preventDefault();
  const nomeCliente = document.getElementById("nomeCliente").value.trim();

  try {
    const resposta = await fetch(`${Api}/clientes/cadastrar`, {
      method: "POST",
      headers: { "Content-type": "application/json" },
      body: JSON.stringify({
        nomeCliente: nomeCliente,
        email: email,
      }),
    });

    if (!resposta.ok) throw new Error(resposta.status);

    alert("CLIENTE CADASTRADO COM SUCESSO!");

    document.getElementById("cadastro");
  } catch (err) {
    alert("ERRO AO CADASTRAR");
    console.error(err);
  }
}

async function cadastrarPedido() {
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

function listarClientes() {
  fetch(`${Api/clientes}`)
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
                    <td>${cliente.nomeCliente}</td>
                `;
        tbody.appendChild(linha);
      });
    })
    .catch((error) => {
      console.error("Erro ao listar clientes:", error);
      alert("Erro ao carregar a lista de clientes!");
    });
}

function listarPedidos() {
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
                    <td>${pedido.descricao}</td>
                    <td>${pedido.valorTotal}</td>
                     <td>${pedido.dataPedido}</td>
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

async function deletarCliente() {
	const idCliente = document.getElementById("idCliente").value.trim();
	try {
		const resposta = await fetch(`${Api}/clientes/${idCliente}`, {
			method: "DELETE",
			headers: { "Content-type": "application/json" },
			body: JSON.stringify({ idCliente })
		});
		if (!resposta.ok) throw new Error(resposta.status);
		alert("CLIENTE DELETADO COM SUCESSO!");
		document.getElementById("deletar").reset();
	} catch (err) {
		alert("ERRO AO DELETAR");
		console.error(err);
	}
}

async function deletarPedido() {
	const idPedido = document.getElementById("idPedido").value.trim();
	try {
		const resposta = await fetch(`${Api}/clientes/${idPedido}`, {
			method: "DELETE",
			headers: { "Content-type": "application/json" },
			body: JSON.stringify({ idPedido })
		});
		if (!resposta.ok) throw new Error(resposta.status);
		alert("PEDIDO DELETADO COM SUCESSO!");
		document.getElementById("deletar").reset();
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
                    <td>${pedido.descricao}</td>
                    <td>${pedido.valorTotal}</td>
                     <td>${pedido.dataPedido}</td>
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
                    <td>${pedido.descricao}</td>
                    <td>${pedido.valorTotal}</td>
                     <td>${pedido.dataPedido}</td>
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
                    <td>${pedido.descricao}</td>
                    <td>${pedido.valorTotal}</td>
                     <td>${pedido.dataPedido}</td>
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

      data.forEach((pedido) => {
        const linha = document.createElement("tr");
        linha.innerHTML = `
                    <td>${pedido.descricao}</td>
                    <td>${pedido.valorTotal}</td>
                     <td>${pedido.dataPedido}</td>
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



function buscarPedido(){
     const informacao = document.getElementById("informacaoPedido").value.trim();

    if(informacao == 'number'){
	return buscarPorId(informacao);
    }
    return buscarPorNome(informacao)
}
