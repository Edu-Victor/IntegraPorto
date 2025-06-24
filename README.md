<h1>IntegraPorto: Sistema de Gerenciamento de Operações Portuárias</h1>
<p>
    <strong>IntegraPorto</strong> é uma aplicação web completa desenvolvida com <strong>Java</strong> e o framework <strong>Spring Boot</strong>, projetada para gerenciar e otimizar as operações logísticas e de transporte em um ambiente portuário.
</p>
<p>
    O sistema oferece uma solução centralizada para o cadastro e controle de motoristas, o registro detalhado de trabalhos, e o acompanhamento em tempo real dos serviços. Sua arquitetura robusta permite uma gestão eficiente, desde a alocação automática de motoristas até a finalização e o histórico de trabalhos, garantindo segurança e organização para todos os processos.
</p>

<h2>✨ Funcionalidades Principais</h2>
<ul>
    <li>
        <strong>Autenticação e Segurança:</strong>
        <ul>
            <li>Sistema de login seguro para usuários cadastrados no sistema.</li>
            <li>Controle de acesso às páginas, onde apenas usuários autenticados podem acessar as funcionalidades do sistema, com exceção da página de login.</li>
            <li>As senhas dos usuários são armazenadas de forma segura usando criptografia BCrypt.</li>
        </ul>
    </li>
    <li>
        <strong>Gerenciamento de Motoristas (CRUD):</strong>
        <ul>
            <li>Cadastro completo de motoristas com informações como vaga, nome, CPF, CNH e placas do veículo.</li>
            <li>Listagem de todos os motoristas cadastrados, com a possibilidade de editar ou excluir registros através de uma interface intuitiva com modais.</li>
            <li>A tela de motoristas organiza os registros de forma crescente pela vaga.</li>
        </ul>
    </li>
    <li>
        <strong>Gerenciamento de Trabalhos:</strong>
        <ul>
            <li>Formulário detalhado para cadastrar novos trabalhos, incluindo dados como transportadora, booking, navio, tipo de operação, locais de coleta/entrega e datas.</li>
            <li>
                <strong>Alocação Inteligente de Motoristas:</strong> Ao registrar um novo trabalho, o sistema escala automaticamente os motoristas necessários, priorizando aqueles que estão há mais tempo sem trabalhar (com base na data da última atribuição) para garantir uma distribuição justa.
            </li>
        </ul>
    </li>
    <li>
        <strong>Acompanhamento de Serviços:</strong>
        <ul>
            <li>
                <strong>Painel "Em Andamento":</strong> Seção para visualizar todos os trabalhos que estão sendo executados no momento.
            </li>
            <li>Permite a edição dos dados de um trabalho em andamento e a sua exclusão.</li>
            <li>
                <strong>Substituição de Motorista:</strong> Em uma tela de detalhes do trabalho, é possível substituir um motorista alocado por outro motorista disponível.
            </li>
            <li>Funcionalidade para marcar um trabalho como "Concluído", movendo-o para a seção de histórico.</li>
            <li>
                <strong>Painel "Concluídos":</strong> Mantém um histórico de todos os serviços que já foram finalizados para consulta e registro.
            </li>
        </ul>
    </li>
    <li>
        <strong>Painel Principal e Avisos:</strong>
        <ul>
            <li>A página inicial funciona como um dashboard central, exibindo avisos importantes para todos os usuários.</li>
            <li>Funcionalidade de CRUD para avisos, permitindo que administradores adicionem, editem ou removam comunicados no painel.</li>
        </ul>
    </li>
</ul>

<h2>🛠️ Tecnologias Utilizadas</h2>
<h4>Backend</h4>
<ul>
    <li><strong>Java</strong></li>
    <li><strong>Spring Boot:</strong> Framework principal para a construção da aplicação.</li>
    <li><strong>Spring Web:</strong> Para criar os controllers que gerenciam as requisições web.</li>
    <li><strong>Spring Data JPA:</strong> Para a persistência e comunicação com o banco de dados através de repositórios.</li>
    <li><strong>Spring Security:</strong> Para implementar a camada de autenticação e segurança.</li>
</ul>

<h4>Frontend</h4>
<ul>
    <li><strong>HTML5</strong> e <strong>CSS3</strong></li>
    <li><strong>Thymeleaf:</strong> Motor de templates para renderizar as páginas dinamicamente no lado do servidor, integrando os dados do backend com o HTML.</li>
    <li><strong>JavaScript:</strong> Utilizado para interatividade, como a manipulação de modais para edição e exclusão, e validações no lado do cliente.</li>
    <li><strong>Font Awesome:</strong> Biblioteca de ícones utilizada em toda a aplicação para melhorar a interface do usuário.</li>
</ul>

<h4>Banco de Dados e Segurança</h4>
<ul>
    <li><strong>Banco de Dados Relacional (ex: H2, PostgreSQL, MySQL):</strong> A aplicação utiliza JPA, sendo compatível com diversos bancos de dados SQL.</li>
    <li><strong>BCrypt Password Encoder:</strong> Para a codificação segura das senhas dos usuários.</li>
</ul>

<h2>🏛️ Arquitetura</h2>
<p>O projeto adota a arquitetura <strong>Model-View-Controller (MVC)</strong>, com uma clara separação de responsabilidades para facilitar a manutenção e escalabilidade.</p>
<ul>
    <li>
        <strong>Model (<code>com.navegacaoeinteracao.p2navegacao.model</code>):</strong>
        Contém as classes de entidade (POJOs) que mapeiam as tabelas do banco de dados, como <code>Motorista</code>, <code>Trabalhos</code>, <code>Aviso</code>, <code>Usuario</code> e <code>TrabalhoMotorista</code>.
    </li>
    <li>
        <strong>View (<code>src/main/resources/templates</code>):</strong>
        Composta pelos arquivos HTML que utilizam o Thymeleaf para exibir os dados. Inclui páginas como <code>1 - index.html</code> (painel), <code>2 - cadastromotorista.html</code> (gerenciamento de motoristas), e <code>4 - emandamento.html</code> (serviços em andamento).
    </li>
    <li>
        <strong>Controller (<code>com.navegacaoeinteracao.p2navegacao.controller</code>):</strong>
        Responsável por intermediar as requisições do usuário, acionar a lógica de negócio e retornar a view apropriada. Inclui controllers como <code>CadastroMotorista</code>, <code>CadastroTrabalho</code>, <code>EmAndamento</code> e <code>IndexController</code>.
    </li>
    <li>
        <strong>Repository (<code>com.navegacaoeinteracao.p2navegacao.repository</code>):</strong>
        Interfaces que estendem o Spring Data JPA para fornecer as operações de banco de dados (CRUD) de forma abstraída para as entidades, como <code>MotoristaRepository</code>, <code>TrabalhoRepository</code> e <code>AvisoRepository</code>.
    </li>
    <li>
        <strong>Configuration (<code>com.navegacaoeinteracao.p2navegacao.config</code>):</strong>
        Classes de configuração do Spring, como o <code>SecurityConfig</code>, que define as regras de segurança, autenticação e autorização da aplicação.
    </li>
</ul>
