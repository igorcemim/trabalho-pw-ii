DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS proposta;
DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente (
    id int(11) NOT NULL AUTO_INCREMENT,
    tipo varchar(1) DEFAULT NULL,
    nome varchar(200) DEFAULT NULL,
    email varchar(200) DEFAULT NULL,
    endereco varchar(200) DEFAULT NULL,
    dddTelefone varchar(2) DEFAULT NULL,
    numeroTelefone varchar(9) DEFAULT NULL,
    dddCelular varchar(2) DEFAULT NULL,
    numeroCelular varchar(9) DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE TABLE proposta (
    id int(11) NOT NULL AUTO_INCREMENT,
    cliente_id int(11) NOT NULL,
    valor decimal(10, 2) DEFAULT NULL,
    detalhes text DEFAULT NULL,
    situacao varchar(30) DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (cliente_id) references cliente(id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE TABLE usuario (
    id int(11) NOT NULL AUTO_INCREMENT,
    login varchar(30) NOT NULL,
    senha varchar(64) NOT NULL,
    PRIMARY KEY(id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

INSERT INTO usuario values(null, 'admin', sha2('admin+3d1880b3b11836cabb66afa7f275789d9b659f70ca1266781653647ac1642248', 256));
