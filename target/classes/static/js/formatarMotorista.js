function formatarCPF(){
    const cpfInput = document.getElementById('cpf');
    cpfInput.addEventListener('input', function(e) {
        let value = e.target.value.replace(/\D/g, '');
        if (value.length > 11) value = value.substring(0, 11);
        value = value.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
        e.target.value = value;
    });
}

function formatarCNH(){
    const cnhInput = document.getElementById('cnh');
    cnhInput.addEventListener('input', function(e) {
        e.target.value = e.target.value.replace(/\D/g, '').substring(0, 11);
    });
}

function formatarCelular(){
    const celular1Input = document.getElementById('celular1');
    const celular2Input = document.getElementById('celular2');
    
    function formatarDadosCelular(input) {
        let value = input.value.replace(/\D/g, '');
        if (value.length > 11) value = value.substring(0, 11);
        value = value.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
        input.value = value;
    }

    celular1Input.addEventListener('input', () => formatarDadosCelular(celular1Input));
    celular2Input.addEventListener('input', () => formatarDadosCelular(celular2Input));
}

function formatarPlaca() {
    function formatarDadosPlaca(input) {
        let value = input.value.replace(/[^a-zA-Z0-9]/g, ''); 

        if (value.length > 7) {
            value = value.substring(0, 7); 
        }

        if (value.length > 3) {
            value = value.replace(/^([a-zA-Z0-9]{3})(.*)$/, '$1-$2');
        }
        input.value = value;
    }
    const placaCaminhaoInput = document.getElementById('placaCaminhao');
    const placaCarretaInput = document.getElementById('placaCarreta');
    
    placaCaminhaoInput.addEventListener('input', () => formatarDadosPlaca(placaCaminhaoInput));
    placaCarretaInput.addEventListener('input', () => formatarDadosPlaca(placaCarretaInput));
    
}

function showError(elementId, message) {
    let errorElement = document.getElementById(elementId + '-error');
    if (!errorElement) {
        errorElement = document.createElement('span');
        errorElement.id = elementId + '-error';
        errorElement.className = 'error-message';
        document.getElementById(elementId).parentNode.appendChild(errorElement);
    }
    errorElement.textContent = message;
}

function clearError(elementId) {
    const errorElement = document.getElementById(elementId + '-error');
    if (errorElement) {
        errorElement.textContent = '';
    }
}

document.addEventListener('DOMContentLoaded', () => {
    formatarCPF();
    formatarCNH();
    formatarCelular();
    formatarPlaca();
});

document.getElementById('formDados').addEventListener('submit', function(dados) {
    let isValid = true;

    const cpf = document.getElementById('cpf').value.replace(/\D/g, '');
    const cnh = document.getElementById('cnh').value.replace(/\D/g, '');
    const placaCaminhao = document.getElementById('placaCaminhao').value.replace(/[.\-()\s]/g, '');
    const placaCarreta = document.getElementById('placaCarreta').value.replace(/[.\-()\s]/g, '');
    const celular1 = document.getElementById('celular1').value.replace(/\D/g, '');
    const celular2 = document.getElementById('celular2').value.replace(/\D/g, '');


    if (cpf.length !== 11) {
        showError('cpf', 'CPF inválido. Deve conter 11 dígitos.');
        isValid = false;
    } else {
        clearError('cpf');
    }

    if (cnh.length !== 11) {
        showError('cnh', 'CNH inválida. Deve conter 11 dígitos.');
        isValid = false;
    } else {
        clearError('cnh');
    }

    if (placaCaminhao.length !== 7 || !/^[a-zA-Z0-9]{7}$/.test(placaCaminhao)) {
        showError('placaCaminhao', 'Placa do Caminhão inválida. Deve conter 7 caracteres alfanuméricos.');
        isValid = false;
    } else {
        clearError('placaCaminhao');
    }

    if (placaCarreta.trim() !== '' && (placaCarreta.length !== 7 || !/^[a-zA-Z0-9]{7}$/.test(placaCarreta))) {
        showError('placaCarreta', 'Placa da Carreta inválida. Deve conter 7 caracteres alfanuméricos.');
        isValid = false;
    } else {
        clearError('placaCarreta');
    }

    if (celular1.length !== 11) {
        showError('celular1', 'Celular 1 inválido. Deve conter 11 dígitos (DDD + 9 dígitos).');
        isValid = false;
    } else {
        clearError('celular1');
    }

    if (celular2.trim() !== '' && celular2.length !== 11) {
        showError('celular2', 'Celular 2 inválido. Se preenchido, deve conter 11 dígitos (DDD + 9 dígitos).');
        isValid = false;
    } else {
        clearError('celular2');
    }


    if (!isValid) {
        dados.preventDefault();
    } else {
        document.getElementById('cpf').value = cpf;
        document.getElementById('cnh').value = cnh;
        document.getElementById('celular1').value = celular1;
        document.getElementById('celular2').value = celular2;
        document.getElementById('placaCaminhao').value = placaCaminhao;
        document.getElementById('placaCarreta').value = placaCarreta;

        return true;
    }
});