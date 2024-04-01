const endpoint = "http://localhost:8080/user"; 

// Verifica se houve redirecionamento do cadastro
const urlParams = new URLSearchParams(window.location.search);
const cadastroSucesso = urlParams.has('cadastro') && urlParams.get('cadastro') === 'sucesso';

// Se houve cadastro bem-sucedido, exibir uma mensagem e redirecionar para a página de login
if (cadastroSucesso) {
    alert('Cadastro realizado com sucesso! Faça login para continuar.');
    // Redireciona para a página de login após 3 segundos
    setTimeout(function() {
        window.location.href = '/';
    }, 1000);
}

// Função para validar o email
function isValidEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}

// Captura o evento de envio do formulário
document.getElementById('formulario').addEventListener('submit', async function(event) {
    event.preventDefault(); // Prevenir o envio padrão do formulário

    const nome = document.getElementById('nome').value;
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;
    const confirmarSenha = document.getElementById('confirmarsenha').value;
    
    let erroEmail = '';
    let erroConfirmarSenha = '';

    // Validação do email
    if (!isValidEmail(email)) {
        erroEmail = 'Por favor, digite um email válido.';
    }

    // Validação da confirmação da senha
    if (senha !== confirmarSenha) {
        erroConfirmarSenha = 'As senhas não correspondem.';
    }

    // Exibir mensagens de erro
    document.querySelector('.erro-email').innerText = erroEmail;
    document.querySelector('.erro-confirmarsenha').innerText = erroConfirmarSenha;

    // Se houver erros, impedir o envio do formulário
    if (erroEmail || erroConfirmarSenha) {
        return;
    }

    // Criar objeto com os dados do formulário
    const data = {
        nome: nome,
        email: email,
        senha: senha
    };

    // Se não houver erros, fazer uma requisição POST para o backend
    try {
        const response = await fetch(endpoint, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            // Cadastro bem-sucedido
            alert('Cadastro realizado com sucesso!');
            // Redirecionar para a página de login após 3 segundos
            setTimeout(function() {
                window.location.href = '/';
            }, 3000);
        } else {
            // Tratar erro de cadastro
            const erroCadastro = await response.json();
            alert(erroCadastro.mensagem); // Exibir mensagem de erro do backend
        }
    } catch (erro) {
        // Tratar erro de conexão ou outros erros
        console.error('Erro:', erro);
        alert('Ocorreu um erro ao processar sua solicitação. Por favor, tente novamente.');
    }
});
