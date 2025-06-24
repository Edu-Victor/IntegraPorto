function formatarValor(dados) {
    const input = document.getElementById(dados);
    let valor = input.value.replace(/\D/g, '');
    valor = valor.padStart(3, '0'); 
    input.value = (parseInt(valor) / 100).toFixed(2);
}


function formatarInput(input) {
    let valor = input.value.replace(/\D/g, '').replace(/^0+/, '');
    
    if (valor === '') valor = '0';
    
    valor = valor.padStart(3, '0');
    
    const valorFormatado = valor.slice(0, -2).replace(/\B(?=(\d{3})+(?!\d))/g, '.') + ',' + valor.slice(-2);
    
    input.value = valorFormatado;
    
    setTimeout(() => {input.selectionStart = input.selectionEnd = valorFormatado.length;}, 0);
}



