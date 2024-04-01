async function carregarCarros() {
    try {
        const response = await fetch('http://localhost:8080/car');
        const carros = await response.json();

        const tbody = document.getElementById('carros-lista');
        tbody.innerHTML = '';

        carros.forEach(carro => {
          
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${carro.modelo}</td>
                <td>${carro.fabricante}</td>
                <td>${carro.anofabricacao}</td> <!-- Exibe a data formatada -->
                <td>
                    <button onclick="excluirCarro(${carro.id})">Excluir</button>
                    <button onclick="editarCarro(${carro.id})">Editar</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        console.error('Erro ao carregar os carros:', error);
    }
}

window.onload = carregarCarros;

async function excluirCarro(id) {
    try {
        const response = await fetch(`http://localhost:8080/car/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            alert('Carro excluído com sucesso.');
            carregarCarros(); // Recarrega a lista de carros após exclusão
        } else {
            console.error('Erro ao excluir o carro:', response.status);
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
    }
}

async function editarCarro(id) {
    // Aqui você pode implementar a lógica para redirecionar para a página de edição de carro
    // Por exemplo:
    window.location.href = `editarcar?id=${id}`;
}
