CREATE TABLE materia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    area VARCHAR(100) NOT NULL,
    dificuldade VARCHAR(100),
    horas_estudadas INT DEFAULT 0,
    data_prova DATE
);