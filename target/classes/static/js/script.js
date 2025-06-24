function animacaoNavbar() {
    let toggleBtn = document.getElementById('toggle-btn');
    let navIcon = toggleBtn.querySelector('i');
    let SIDENAV_KEY = 'sidenav-state';

    const dropdownToggles = document.querySelectorAll('.sidenav-dropdown-toggle');

        dropdownToggles.forEach(toggle => {
            toggle.addEventListener('click', function(event) {
                event.preventDefault(); 

                const submenuContainer = this.nextElementSibling;
                
                this.classList.toggle('active');
                submenuContainer.classList.toggle('active');
            });
        });

    function applySidenavState(state) {
        if (state === 'open') {
            document.body.classList.add('sidenav-open');
            navIcon.textContent = 'close';
        } else {
            document.body.classList.remove('sidenav-open');
            navIcon.textContent = 'menu';
        }
    }

    function handleNavToggle() {
        let isSidenavOpen = document.body.classList.contains('sidenav-open');
        let newState = isSidenavOpen ? 'closed' : 'open';
        applySidenavState(newState);
        localStorage.setItem(SIDENAV_KEY, newState);
    }

    let savedSidenavState = localStorage.getItem(SIDENAV_KEY);
    applySidenavState(savedSidenavState || 'closed');
    toggleBtn.addEventListener('click', handleNavToggle);
}


function darkMode() {
    let themeToggleBtn = document.getElementById('theme-toggle');
    let themeIcon = themeToggleBtn.querySelector('i');
    let THEME_KEY = 'user-theme';

    function applyTheme(theme) {
        if (theme === 'dark') {
            document.body.classList.add('dark-theme');
            themeIcon.textContent = 'light_mode';
        } else {
            document.body.classList.remove('dark-theme');
            themeIcon.textContent = 'dark_mode';
        }
    }

    let savedTheme = localStorage.getItem(THEME_KEY);
    applyTheme(savedTheme || 'light');

    themeToggleBtn.addEventListener('click', () => {
        let isDarkMode = document.body.classList.contains('dark-theme');
        let newTheme = isDarkMode ? 'light' : 'dark';
        applyTheme(newTheme);
        localStorage.setItem(THEME_KEY, newTheme);
    });
}





function mostrarSucesso() {
    const modal = document.getElementById('modal-sucesso');
    modal.classList.add('mostrar');
    setTimeout(() => {
        modal.classList.remove('mostrar');
    }, 2000);
}

document.addEventListener('DOMContentLoaded', () => {
    const paramsUrl = new URLSearchParams(window.location.search);
    if (paramsUrl.get('sucesso') === 'true') {
        mostrarSucesso();
    }
    animacaoNavbar();
    darkMode();
});

window.addEventListener('load', () => {
    document.body.classList.remove('no-transition');
});