function adicionarAviso(){
    const modalAviso = document.getElementById('modalAviso');
    const modalAvisoTitle = document.getElementById('modal-aviso-title');
    const formAviso = document.getElementById('formAviso');
    const btnNovoAviso = document.getElementById('btn-novo-aviso');
    
    btnNovoAviso.addEventListener('click', () => {
        modalAvisoTitle.textContent = 'Adicionar Aviso';
        formAviso.reset();
        formAviso.action = '/avisos/adicionar';
        formAviso.querySelector('#avisoId').value = ''; 
        abrirModalAviso(modalAviso);
    });
    fecharModaisAviso()
}

function editarAviso(){
    const btnEditarAviso = document.querySelectorAll('.btn-editar-aviso');
    const modalAvisoTitle = document.getElementById('modal-aviso-title');
    btnEditarAviso.forEach(button => {
        button.addEventListener('click', (event) => {
            const avisoId = event.currentTarget.dataset.id;
            modalAvisoTitle.textContent = 'Editar Aviso';
            formAviso.action = `/avisos/editar/${avisoId}`;

            fetch(`/api/avisos/${avisoId}`)
            .then(response => response.json())
            .then(data => {
                formAviso.querySelector('#avisoId').value = data.id;
                formAviso.querySelector('#titulo').value = data.titulo;
                formAviso.querySelector('#mensagem').value = data.mensagem;
                abrirModalAviso(modalAviso);
            }).catch(error => console.error('Erro ao buscar dados do aviso:', error));
        });
    });
    fecharModaisAviso()
}

function excluirAviso(){
    const deleteModal = document.getElementById('deleteModal');
    const confirmDeleteBtn = document.getElementById('confirmDelete');
    const deleteMessage = document.getElementById('delete-message');

    document.querySelectorAll('.btn-excluir-aviso').forEach(button => {
        button.addEventListener('click', (event) => {
            deleteMessage.textContent = 'Você tem certeza que deseja excluir este aviso?';
            confirmDeleteBtn.dataset.idToDelete = event.currentTarget.dataset.id;
            abrirModalAviso(deleteModal);
        });
    });

    if (confirmDeleteBtn) {
        confirmDeleteBtn.addEventListener('click', (event) => {
            const id = event.currentTarget.dataset.idToDelete;
            let formAction = `/avisos/excluir/${id}`;

            if (formAction) {
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = formAction;
                document.body.appendChild(form);
                form.submit();
            }
        });
    }
    fecharModaisAviso()
}





/*------------------- FUNÇÕES GLOBAIS -------------------*/
function abrirModalAviso(abrirModalElemento){
    abrirModalElemento.style.display = 'flex';
}

function fecharModaisAviso(){
    const allModals = document.querySelectorAll('.modal');
    allModals.forEach(modal => {
        const closeButton = modal.querySelector('.close-button');
        if (closeButton) {
            closeButton.addEventListener('click', () => fecharModalAviso(modal));
        }
        const cancelButton = modal.querySelector('.cancel');
        if (cancelButton) {
            cancelButton.addEventListener('click', () => fecharModalAviso(modal));
        }
        modal.addEventListener('click', (event) => {
            if (event.target === modal) {
                fecharModalAviso(modal);
            }
        });
    });

}

function fecharModalAviso(fecharModalElemento){
    fecharModalElemento.style.display = 'none';
}




document.addEventListener('DOMContentLoaded', adicionarAviso(), excluirAviso(), editarAviso());