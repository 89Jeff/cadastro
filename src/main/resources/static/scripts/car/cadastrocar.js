async function salvarCarro() {
    var modelo = document.getElementById("modelo").value;
    var fabricante = document.getElementById("fabricante").value;
    var anofabricacao = document.getElementById("anofabricacao").value;

     // Converter a data para o formato ISO 8601 (yyyy-MM-dd)
     var dataISO = anofabricacao.split('/').reverse().join('-');

    var carro = {
        modelo: modelo,
        fabricante: fabricante,
        anofabricacao: dataISO
    };

    // Envia a requisição POST para salvar o carro utilizando AJAX
    try {
        const response = await fetch('http://localhost:8080/car', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(carro),
        });

        if (response.ok) {
            // Se a requisição for bem-sucedida, redireciona para a página de listagem de carros
            window.location.href = "listcar";
        } else {
            // Se ocorrer um erro na requisição, exibe uma mensagem de erro
            console.error('Erro ao salvar o carro:', response.status);
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
    }
}
