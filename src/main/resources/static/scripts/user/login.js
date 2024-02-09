// Adiciona um evento de envio ao formulário de login
document.getElementById('formulario-login').addEventListener('submit', async function(event) {
    event.preventDefault(); // Prevenir o envio padrão do formulário

    const email = document.getElementById('email').value.trim();
    const senha = document.getElementById('senha').value.trim();

    try {
        const response = await fetch('http://localhost:8080/user/confirmarlogin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: email,
                senha: senha
            })
        });

        if (response.ok) {
            // Login bem-sucedido
            alert('Login realizado com sucesso!');
            // Redirecionar para a página de home ou fazer outra ação necessária
            window.location.href = '/cadastracar';
        } else {
            // Tratar erro de login
            alert('Credenciais inválidas. Por favor, tente novamente.');
        }
    } catch (error) {
        // Tratar erro de conexão ou outros erros
        console.error('Erro:', error);
        alert('Ocorreu um erro ao processar sua solicitação. Por favor, tente novamente.');
    }
});
