DO $schemaconta$
    BEGIN
        CREATE SCHEMA IF NOT EXISTS contas;
    END $schemaconta$;

DO $criaçãotabelas$
    BEGIN
        -- Criação da sequência para a tabela de usuários
        CREATE SEQUENCE sq_usuario
            START WITH 2
            INCREMENT BY 1
            MINVALUE 1
            MAXVALUE 9223372036854775807
            CACHE 1;

        -- Criação da tabela tb_usuario
        CREATE TABLE tb_usuario (
                                    id BIGINT NOT NULL DEFAULT nextval('sq_usuario') CONSTRAINT pk_usuario PRIMARY KEY UNIQUE,
                                    nome VARCHAR(200) COLLATE pg_catalog."default" NOT NULL,
                                    email VARCHAR(255) NOT NULL,  -- Alterado de CPF para Email
                                    CONSTRAINT uq_email_usuario UNIQUE (email), -- Adicionando constraint de unicidade para email
                                    id_usuario_criacao BIGINT NOT NULL DEFAULT 1,
                                    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                    data_ultima_atualizacao TIMESTAMP NULL,
                                    id_usuario_ultima_atualizacao BIGINT NULL,
                                    avatar TEXT NULL,
                                    CONSTRAINT fk_usuario_atualizacao FOREIGN KEY (id_usuario_ultima_atualizacao) REFERENCES tb_usuario (id)
        );

        -- Sequência para a tabela de perfis
        CREATE SEQUENCE sq_perfil
            START WITH 1
            INCREMENT BY 1
            MINVALUE 1
            MAXVALUE 9223372036854775807
            CACHE 1;

        -- Criação da tabela tb_perfil
        CREATE TABLE tb_perfil (
                                   id BIGINT NOT NULL DEFAULT nextval('sq_perfil') CONSTRAINT pk_perfil PRIMARY KEY UNIQUE,
                                   descricao CHARACTER VARYING(200) COLLATE pg_catalog."default" NOT NULL
        );

        -- Sequência para a tabela de usuários-perfis
        CREATE SEQUENCE sq_usuario_perfil
            START WITH 1
            INCREMENT BY 1
            MINVALUE 1
            MAXVALUE 9223372036854775807
            CACHE 1;

        -- Criação da tabela de relacionamento entre usuários e perfis
        CREATE TABLE tb_usuario_perfil (
                                           id BIGINT NOT NULL DEFAULT nextval('sq_usuario_perfil') CONSTRAINT pk_usuario_perfil PRIMARY KEY,
                                           id_usuario BIGINT NOT NULL,
                                           id_perfil BIGINT NOT NULL,
                                           id_usuario_criacao BIGINT NOT NULL,
                                           data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                           id_usuario_alteracao BIGINT NULL,
                                           data_atualizacao TIMESTAMP NULL,
                                           CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id),
                                           CONSTRAINT fk_perfil FOREIGN KEY (id_perfil) REFERENCES tb_perfil(id),
                                           CONSTRAINT fk_usuario_criacao FOREIGN KEY (id_usuario_criacao) REFERENCES tb_usuario(id),
                                           CONSTRAINT fk_usuario_alteracao FOREIGN KEY (id_usuario_alteracao) REFERENCES tb_usuario(id),
                                           CONSTRAINT uq_usuario_perfil UNIQUE (id_usuario)
        );

        -- Sequência para Categoria
        CREATE SEQUENCE sq_categoria
            START WITH 1
            INCREMENT BY 1
            MINVALUE 1
            MAXVALUE 9223372036854775807
            CACHE 1;

        -- Tabela Categoria
        CREATE TABLE categoria (
                                   id BIGINT NOT NULL DEFAULT nextval('sq_categoria') CONSTRAINT pk_categoria PRIMARY KEY,
                                   nome VARCHAR(50) NOT NULL
        );

        -- Sequência para Endereço
        CREATE SEQUENCE sq_endereco
            START WITH 1
            INCREMENT BY 1
            MINVALUE 1
            MAXVALUE 9223372036854775807
            CACHE 1;

        -- Tabela Endereço
        CREATE TABLE endereco (
                                  id BIGINT NOT NULL DEFAULT nextval('sq_endereco') CONSTRAINT pk_endereco PRIMARY KEY,
                                  logradouro VARCHAR(255) NOT NULL,
                                  rua VARCHAR(50) NOT NULL,
                                  numero VARCHAR(50) NOT NULL,
                                  complemento VARCHAR(255),
                                  bairro VARCHAR(100) NOT NULL,
                                  cep VARCHAR(10) NOT NULL,
                                  cidade VARCHAR(100) NOT NULL,
                                  estado VARCHAR(2) NOT NULL
        );

        -- Sequência para Pessoa
        CREATE SEQUENCE sq_pessoa
            START WITH 1
            INCREMENT BY 1
            MINVALUE 1
            MAXVALUE 9223372036854775807
            CACHE 1;

        -- Tabela Pessoa
        CREATE TABLE pessoa (
                                id BIGINT NOT NULL DEFAULT nextval('sq_pessoa') CONSTRAINT pk_pessoa PRIMARY KEY,
                                nome VARCHAR(50) NOT NULL,
                                ativo BOOLEAN NOT NULL,
                                id_endereco BIGINT NOT NULL,
                                FOREIGN KEY (id_endereco) REFERENCES endereco(id)
        );

        -- Sequência para Lançamento
        CREATE SEQUENCE sq_lancamento
            START WITH 1
            INCREMENT BY 1
            MINVALUE 1
            MAXVALUE 9223372036854775807
            CACHE 1;

        -- Tabela Lançamento
        CREATE TABLE lancamento (
                                    id BIGINT NOT NULL DEFAULT nextval('sq_lancamento') CONSTRAINT pk_lancamento PRIMARY KEY,
                                    descricao VARCHAR(50) NOT NULL,
                                    data_nascimento DATE,
                                    data_pagamento DATE NOT NULL,
                                    valor DECIMAL(10,2) NOT NULL,
                                    observacao VARCHAR(100),
                                    tipo VARCHAR(20) NOT NULL,
                                    id_categoria BIGINT NOT NULL,
                                    id_pessoa BIGINT NOT NULL,
                                    FOREIGN KEY (id_categoria) REFERENCES categoria(id),
                                    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
        );

    END $criaçãotabelas$;
