function modalCadastro(){
    const modalGeral = document.getElementById('modalGeral');
    const modalTitle = document.getElementById('modal-title');
    const formDados = document.getElementById('formDados');
    const btnNovoCadastro = document.getElementById('btn-novo-cadastro');

    if(window.location.href.includes('/cadastromotorista')){
        if (btnNovoCadastro) {
            btnNovoCadastro.addEventListener('click', () => {
                modalTitle.textContent = 'Adicionar Motorista';
                formDados.reset();
                formDados.action = '/motoristas/adicionar';
                openModal(modalGeral);
            });
        }
    }
    fecharModais();
}

function modalDelete(){
    const deleteModal = document.getElementById('deleteModal');
    const btnsExcluirMotorista = document.querySelectorAll('.btn-excluir');
    const confirmDeleteBtn = document.getElementById('confirmDelete');

    btnsExcluirMotorista.forEach(button => {
        button.addEventListener('click', (event) => {
            const motoristaId = event.currentTarget.dataset.id;
            confirmDeleteBtn.dataset.idToDelete = motoristaId;
            openModal(deleteModal);
        });
    });

    if(window.location.href.includes('cadastromotorista')){
        if (confirmDeleteBtn) {
            confirmDeleteBtn.addEventListener('click', (event) => {
                const idParaExcluir = event.currentTarget.dataset.idToDelete;
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = `/motoristas/excluir/${idParaExcluir}`;
                document.body.appendChild(form);
                form.submit();
            });
        }
    }

    if(window.location.href.includes('/emandamento') || window.location.href.includes('/concluidos')){
        if (confirmDeleteBtn) {
            confirmDeleteBtn.addEventListener('click', (event) => {
                const idParaExcluir = event.currentTarget.dataset.idToDelete;
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = `/trabalho/excluir/${idParaExcluir}`;
                document.body.appendChild(form);
                form.submit();
            });
        }
    }
    fecharModais();
}

function modalEditar(){
    const btnsEditar = document.querySelectorAll('.btn-editar');
    const modalTitle = document.getElementById('modal-title');
    const modalGeral = document.getElementById('modalGeral');
    const formDados = document.getElementById('formDados');

    if(window.location.href.includes('/cadastromotorista')){
        btnsEditar.forEach(button => {
        button.addEventListener('click', (event) => {
            const motoristaId = event.currentTarget.dataset.id;
            modalTitle.textContent = 'Editar Motorista';
            formDados.action = `/motoristas/editar/${motoristaId}`;

                fetch(`/api/motoristas/${motoristaId}`)
                .then(response => response.json())
                .then(data => {
                    formDados.querySelector('#vaga').value = data.vaga;
                    formDados.querySelector('#nome').value = data.nome;
                    formDados.querySelector('#cpf').value = formatarValorCPF(data.cpf);
                    formDados.querySelector('#cnh').value = data.cnh;
                    formDados.querySelector('#placaCaminhao').value = formatarValorPlaca(data.placaCaminhao);
                    formDados.querySelector('#placaCarreta').value = formatarValorPlaca(data.placaCarreta);
                    formDados.querySelector('#celular1').value = formatarValorCelular(data.celular1);
                    formDados.querySelector('#celular2').value = formatarValorCelular(data.celular2);
                    openModal(modalGeral);
                }).catch(error => console.error('Erro ao buscar dados:', error));
            });
        });
    }
    if(window.location.href.includes('/emandamento') || window.location.href.includes('/concluidos')){
        btnsEditar.forEach(button => {
            button.addEventListener('click', (event) => {
                const trabalhoId = event.currentTarget.dataset.id;
                modalTitle.textContent = 'Editar Trabalho';
                if(window.location.href.includes('/emandamento')){
                    formDados.action = `/trabalho/andamento/editar/${trabalhoId}`;
                }
                else{
                    formDados.action = `/trabalho/concluidos/editar/${trabalhoId}`;
                }

                fetch(`/api/trabalho/${trabalhoId}`)
                .then(response => response.json())
                .then(data => {
                    formDados.querySelector('#id').value = data.id;
                    formDados.querySelector('#transportadora').value = data.transportadora;
                    formDados.querySelector('#booking').value = data.booking;
                    formDados.querySelector('#navio').value = data.navio;
                    formDados.querySelector('#operacao').value = data.operacao;
                    formDados.querySelector('#entrega').value = data.entrega;
                    formDados.querySelector('#coleta').value = data.coleta;
                    formDados.querySelector('#dt_recebimento').value = data.dt_recebimento;
                    formDados.querySelector('#dt_termino').value = data.dt_termino;
                    formDados.querySelector('#tipo_container').value = data.tipo_container;
                    formDados.querySelector('#qt_container').value = data.qt_container;
                    formDados.querySelector('#valor').value = formatarValorNumerico(Number(data.valor).toFixed(2));
                    formDados.querySelector('#janelas').value = data.janelas;
                    formDados.querySelector('#status').value = data.status;

                    
                    openModal(modalGeral);
                }).catch(error => console.error('Erro ao buscar dados:', error));
            });
        }); 
    }
    fecharModais();
}

function modalSubstituirMotorista() {
    const replaceDriverModal = document.getElementById('replaceDriverModal');
    const btnsSubstituir = document.querySelectorAll('.btn-substituir');

    btnsSubstituir.forEach(button => {
        button.addEventListener('click', (event) => {
            
            const trabalhoMotoristaId = event.currentTarget.dataset.trabalhoMotoristaId;
            const currentDriverName = event.currentTarget.dataset.currentDriverName;
            const currentDriverVaga = event.currentTarget.dataset.currentDriverVaga;
            const currentDriverCpf = event.currentTarget.dataset.currentDriverCpf;
            
            

        
            document.getElementById('trabalhoMotoristaIdReplace').value = trabalhoMotoristaId;
            document.getElementById('currentDriverNameDisplay').textContent = currentDriverName;
            document.getElementById('currentDriverVagaDisplay').textContent = currentDriverVaga;
            document.getElementById('currentDriverCpfDisplay').textContent = currentDriverCpf;
            
           
            
           

            openModal(replaceDriverModal);
        });
    });
    fecharModais(); 
}


/*------------------- FUNÇÕES GLOBAIS -------------------*/
function fecharModais(){
    const allModals = document.querySelectorAll('.modal');
    allModals.forEach(modal => {
        const closeButton = modal.querySelector('.close-button');
        if (closeButton) {
            closeButton.addEventListener('click', () => closeModal(modal));
        }
        const cancelButton = modal.querySelector('.cancel');
        if (cancelButton) {
            cancelButton.addEventListener('click', () => closeModal(modal));
        }
        modal.addEventListener('click', (event) => {
            if (event.target === modal) {
                closeModal(modal);
            }
        });
    });
}

const openModal = (modalElement) => {
    if(modalElement) modalElement.style.display = 'flex';
};

const closeModal = (modalElement) => {
    if(modalElement) modalElement.style.display = 'none';
};

function formatarValorNumerico(valorNumerico) {
    let valor = String(valorNumerico).replace(/\D/g, '').replace(/^0+/, '');
    
    if (valor === '') valor = '0';
    
    valor = valor.padStart(3, '0');
    
    const valorFormatado = valor.slice(0, -2).replace(/\B(?=(\d{3})+(?!\d))/g, '.') + ',' + valor.slice(-2);
    
    return valorFormatado;
}

function formatarValorCPF(valorCPF){
        
    return valorCPF.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
    
    
}

function formatarValorPlaca(valorPlaca){

    if (valorPlaca.length === 7) {
        if (/[A-Za-z]{3}\d[A-Za-z]\d{2}/.test(valorPlaca)) { 
            return valorPlaca.replace(/^([a-zA-Z]{3})(\d[a-zA-Z]\d{2})$/, '$1-$2');
        } else if (/[A-Za-z]{3}\d{4}/.test(valorPlaca)) {
            return valorPlaca.replace(/^([a-zA-Z]{3})(\d{4})$/, '$1-$2');
        }
    }
    return valorPlaca;
}


function formatarValorCelular(valorCelular){
    let digitsOnly = valorCelular.replace(/\D/g, '');

    if (digitsOnly.length === 11) {
        return `(${digitsOnly.substring(0, 2)}) ${digitsOnly.substring(2, 7)}-${digitsOnly.substring(7, 11)}`;
    } else if (digitsOnly.length === 10) {
        return `(${digitsOnly.substring(0, 2)}) ${digitsOnly.substring(2, 6)}-${digitsOnly.substring(6, 10)}`;
    }
    return valorCelular;
}


document.addEventListener('DOMContentLoaded', () => {
    modalCadastro();
    modalDelete();
    modalEditar();
    modalSubstituirMotorista();
});