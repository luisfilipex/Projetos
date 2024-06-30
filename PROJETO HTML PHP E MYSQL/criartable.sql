// cria a tabela controle financeiro
CREATE DATABASE controle_financeiro;
USE controle_financeiro;

// cria a tabela empresa
CREATE TABLE tbl_empresa (
    id_empresa INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

// cria a tabela conta a pagar
CREATE TABLE tbl_conta_pagar (
    id_conta_pagar INT AUTO_INCREMENT PRIMARY KEY,
    cliente VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    data_pagar DATE NOT NULL,
    pago TINYINT(1) NOT NULL DEFAULT 0,
    id_empresa INT,
    FOREIGN KEY (id_empresa) REFERENCES tbl_empresa(id_empresa)
);
