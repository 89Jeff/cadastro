// Função para carregar os detalhes do carro para alteração
async function carregarDetalhesCarroParaAlteracao() {
    const urlParams = new URLSearchParams(window.location.search);
    const carroId = urlParams.get('id');

    if (carroId) {
        // Busca os detalhes do carro pelo ID
        const response = await fetch(`http://localhost:8080/car/${carroId}`);
        const carroDetalhes = await response.json();

        // Preenche os campos do formulário com os detalhes do carro existente
        document.getElementById('carroId').value = carroDetalhes.id;
        document.getElementById('modelo').value = carroDetalhes.modelo;
        document.getElementById('fabricante').value = carroDetalhes.fabricante;
        document.getElementById('anofabricacao').value = carroDetalhes.anofabricacao;
    }
}

// Função para cadastrar ou atualizar o carro
async function cadastrarOuAtualizarCarro() {
    const carroId = document.getElementById('carroId').value;
    const modelo = document.getElementById('modelo').value;
    const fabricante = document.getElementById('fabricante').value;
    const anofabricacao = document.getElementById('anofabricacao').value;

    const novoCarro = {
        modelo: modelo,
        fabricante: fabricante,
        anofabricacao: anofabricacao
    };

    // Verifica se é uma atualização (carroId não está vazio)
    if (carroId) {
        // Faz uma requisição PUT para atualizar o carro existente
        const response = await fetch(`http://localhost:8080/car/${carroId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(novoCarro)
        });

        if (response.ok) {
            alert('Carro atualizado com sucesso!');
            window.location.href = "listcar";
        } else if (response.status === 404) {
                alert('Carro não encontrado.');
        } else {
                alert('Erro ao atualizar o carro.');
            }
    } else {
        // Caso contrário, é uma operação de cadastro (POST)
        const response = await fetch('http://localhost:8080/car', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(novoCarro)
        });

        if (response.ok) {
            alert('Carro cadastrado com sucesso!');
            // Redireciona para a página de listar carros após o cadastro
            window.location.href = "listcar";
        } else {
            alert('Erro ao cadastrar o carro.');
        }
    }
}

// Chama a função para carregar detalhes do carro ao carregar a página
window.onload = carregarDetalhesCarroParaAlteracao;
