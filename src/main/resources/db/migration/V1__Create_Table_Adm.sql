CREATE TABLE IF NOT EXISTS Administrador (
    id serial not null primary key,
    nome varchar(60) not null,
    email varchar(100) not null,
    matricula varchar(20) not null,
    senha varchar(20) not null
);

CREATE TABLE IF NOT EXISTS Fornecedor (
    id serial not null primary key,
    nome varchar(60) not null,
    CPF varchar(60) not null,
    CNPJ varchar(60) not null,
    email varchar(100) not null,
    telefone varchar(11) not null,
    endereco varchar(250) not null,
    reagente varchar(250) not null
);

CREATE TABLE IF NOT EXISTS Professor (
    id serial not null primary key,
    nome varchar(60) not null,
    email varchar(100) not null,
    matricula varchar(20) not null,
    senha varchar(20) not null
);

CREATE TABLE IF NOT EXISTS Reagente (
    id serial not null primary key,
    nome varchar(60) not null,
    descricao varchar(100) not null,
    qtd varchar(5) not null,
    dataCadastro varchar(10) not null
);

CREATE TABLE IF NOT EXISTS Projeto (
    id serial not null primary key,
    nome varchar(60) not null,
    descricao varchar(100) not null,
    dataCadastro varchar(10) not null,
    dataTermino varchar(10) not null
);

CREATE TABLE IF NOT EXISTS Aluno (
    id serial not null primary key,
    nome varchar(60) not null,
    email varchar(100) not null,
    matricula varchar(20) not null,
    senha varchar(20) not null
);