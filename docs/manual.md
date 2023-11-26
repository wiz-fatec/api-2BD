# Configuração ambiente

## Postgresql

### Instalação
Para utilizar o programa, é necessario postgresql, de preferencia versão 16. [Download](https://www.postgresql.org/download/)

### Configurar usuario
Criar usuario `tgmanager` com a senha `tgmanager` para acesso do programa a base de dados postgres. Após a criação do usuario, é necessario criar o banco também chamado `tgmanager`, e garantir os privilegios corretos ao usuario. Segue as entregas para configurar utilizando terminal do `psql`:

```psql
-- Criar database
CREATE DATABASE tgmananger;

-- Criar usuario
CREATE USER tgmanager WITH PASSWORD 'tgmanager';
ALTER USER tgmanager WITH SUPERUSER;
```

### Criar tabelas do banco
Após a configuração do usuario, conectar ao banco `tgmanager` e criar as tabelas do sistema. Segue o script:
```psql
-- Conectar
\c tgmanager;

-- Script das tabelas
CREATE TABLE TURMA (
id SERIAL PRIMARY KEY,
semestre INT NOT NULL,
ano INT NOT NULL,
CONSTRAINT unique_semestre_ano UNIQUE (semestre, ano)
);

CREATE TABLE ORIENTADOR (
id SERIAL PRIMARY KEY,
email_fatec VARCHAR(255) NOT NULL,
nome VARCHAR(255) NOT NULL,
CONSTRAINT orientador_email_fatec UNIQUE (email_fatec)
);

CREATE TABLE ALUNO (
id SERIAL PRIMARY KEY,
email_fatec VARCHAR(255) NOT NULL,
email VARCHAR(255),
nome VARCHAR(255) NOT NULL,
idORIENTADOR INT,
idTURMA INT,
CONSTRAINT aluno_email_fatec UNIQUE (email_fatec)
);
CREATE TABLE TG (
id SERIAL PRIMARY KEY,
descricao VARCHAR(255) NOT NULL,
tipo VARCHAR(255) NOT NULL,
problema VARCHAR(255),
empresa VARCHAR(255),
disciplina VARCHAR(255),
idALUNO INT
);

CREATE TABLE ENTREGA (
id SERIAL PRIMARY KEY,
descricao VARCHAR(255) NOT NULL,
data_inicial DATE NOT NULL,
data_final DATE NOT NULL,
idTURMA INT,
modelo VARCHAR(255) NOT NULL
);

CREATE TABLE VALOR_ENTREGA (
id SERIAL PRIMARY KEY,
nota FLOAT NOT NULL,
feedback VARCHAR(255) NOT NULL,
idALUNO INT,
idENTREGA INT
);

ALTER TABLE TG ADD FOREIGN KEY (idALUNO) REFERENCES ALUNO (id);
ALTER TABLE ENTREGA ADD FOREIGN KEY (idTURMA) REFERENCES TURMA (id);
ALTER TABLE ALUNO ADD FOREIGN KEY (idORIENTADOR) REFERENCES ORIENTADOR (id);
ALTER TABLE ALUNO ADD FOREIGN KEY (idTURMA) REFERENCES TURMA (id);
ALTER TABLE VALOR_ENTREGA ADD FOREIGN KEY (idALUNO) REFERENCES ALUNO (id);
ALTER TABLE VALOR_ENTREGA ADD FOREIGN KEY (idENTREGA) REFERENCES ENTREGA (id);
```

# Utilização

