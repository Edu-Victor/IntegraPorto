document.addEventListener('DOMContentLoaded', function() {
    const cells = document.querySelectorAll('.tabela tbody td');
    
    cells.forEach(cell => {
        cell.addEventListener('click', function(e) {
            if (e.target.closest('button')) {
                return;
            }
            
            let texto = this.textContent;
            texto = texto.replace(/[.\-()]/g, '');
            
            navigator.clipboard.writeText(texto);
            
            this.classList.add('copied');
            setTimeout(() => {
                this.classList.remove('copied');
            }, 400);
        });
    });
});