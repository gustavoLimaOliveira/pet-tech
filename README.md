# pet-tech

# Scripts para criação de tabelas 

```
CREATE TABLE tb_produto (
id UUID PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
descricao TEXT,
url_imagem VARCHAR(255),
preco DOUBLE PRECISION
);

CREATE TABLE tb_categoria (
id SERIAL PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
data_de_criacao TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE tb_produto_categoria (
produto_id UUID NOT NULL,
categoria_id SERIAL NOT NULL,
PRIMARY KEY (produto_id, categoria_id),
FOREIGN KEY (produto_id) REFERENCES tb_produto (id) ON DELETE CASCADE,
FOREIGN KEY (categoria_id) REFERENCES tb_categoria (id) ON DELETE CASCADE
);

CREATE TABLE tb_endereco (
    id SERIAL PRIMARY KEY,
    rua VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    cep VARCHAR(10) NOT NULL
);

create table tb_pessoa(
	id bigserial primary key,
	nome varchar(255),
	nascimento Date,
	cpf varchar(14),
	email varchar(255)
);


alter table tb_endereco 
add column pessoa_id bigint not null;

alter table tb_endereco 
add constraint fk_endereco_pessoa
foreign key (pessoa_id)
references tb_pessoa(id);

CREATE TABLE tb_usuario (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
   	password VARCHAR(255) NOT NULL
);

alter table tb_pessoa 
add column usuario_id int unique,
add constraint fk_usuario_id foreign key (usuario_id) references tb_usuario(id);

add constraint fk_usuario_id foreign key (usuario_id) references tb_usuario(id);

```
