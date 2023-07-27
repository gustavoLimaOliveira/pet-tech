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

CREATE TABLE tb_pessoa (
id BIGSERIAL PRIMARY KEY,
cpf VARCHAR(11) NOT NULL,
nome VARCHAR(100) NOT NULL,
nascimento DATE NOT NULL
);

```