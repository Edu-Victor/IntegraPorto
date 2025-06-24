CREATE TABLE IF NOT EXISTS usuarios (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS aviso(
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(250),
    mensagem VARCHAR(1000)
);

CREATE TABLE IF NOT EXISTS motoristas (
    id BIGSERIAL PRIMARY KEY,
    vaga INT NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(16) NOT NULL UNIQUE,
    cnh VARCHAR(16) NOT NULL UNIQUE,
    placa_caminhao VARCHAR(8) NOT NULL,
    placa_carreta VARCHAR(8) NOT NULL,
    celular1 VARCHAR(15) NOT NULL,
    celular2 VARCHAR(15),
    ultima_atribuicao TIMESTAMP
);

CREATE TABLE IF NOT EXISTS trabalhos (
    id BIGSERIAL PRIMARY KEY,
    transportadora VARCHAR(255),
    booking VARCHAR(32),
    navio VARCHAR(32),
    operacao VARCHAR(32),
    entrega VARCHAR(64),
    coleta VARCHAR(64),
    dt_recebimento TIMESTAMP,
    dt_termino TIMESTAMP,
    tipo_container VARCHAR(16),
    qt_container int,
    valor DECIMAL(10,2),
    janelas int,
    status VARCHAR(15)
);



CREATE TABLE IF NOT EXISTS trabalhos_motoristas (
    id BIGSERIAL PRIMARY KEY,
    id_trabalhos INT NOT NULL,
    id_motoristas INT NOT NULL,

    FOREIGN KEY (id_trabalhos) REFERENCES trabalhos(id) ON DELETE CASCADE,
    FOREIGN KEY (id_motoristas) REFERENCES motoristas(id) ON DELETE CASCADE
);



