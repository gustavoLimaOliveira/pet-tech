# Pettech - Introdução ao Spring Boot

Este projeto foi desenvolvido como parte de uma aula de pós-graduação na FIAP, com o objetivo de ensinar os conceitos fundamentais do Spring Boot e suas funcionalidades. O projeto aborda vários aspectos importantes do desenvolvimento de aplicativos Java, incluindo a criação de uma API REST, integração com banco de dados usando Spring Data JPA, validação de entrada de dados com Spring Validation e testes unitários e de integração.

## Visão Geral

Neste projeto de exemplo, exploramos os seguintes tópicos:

### O que é o Spring Boot?

O Spring Boot é um projeto do ecossistema Spring que simplifica o desenvolvimento de aplicativos Java. Ele fornece um ambiente de execução independente com configuração automática, facilitando a criação de aplicativos robustos e altamente configuráveis. Com o Spring Boot, é possível criar rapidamente aplicativos Java de alta qualidade com menos esforço em configuração.

### O que é uma API REST?

Uma API REST (Representational State Transfer) é um estilo arquitetural para projetar redes com base no protocolo HTTP. Ela usa métodos HTTP (como GET, POST, PUT e DELETE) para realizar operações em recursos, que podem ser representados em diferentes formatos, como JSON ou XML. As APIs REST são amplamente usadas para criar serviços web simples e eficazes.

### Spring Data JPA com Banco de Dados H2

Neste projeto, utilizamos o Spring Data JPA para simplificar a interação com o banco de dados. O banco de dados H2 foi escolhido como um banco de dados em memória para fins de demonstração. O Spring Data JPA oferece uma maneira elegante de definir e executar consultas SQL sem a necessidade de escrever código SQL manualmente.

### Spring Validation

O Spring Validation é uma parte essencial do Spring Framework que permite validar os dados de entrada em seus aplicativos. Ele oferece anotações (como `@NotNull`, `@Size`, `@Email`, etc.) para aplicar regras de validação aos campos de entrada, garantindo que os dados estejam corretos antes de serem processados.

### Testes Unitários e de Integração

No projeto, são demonstrados testes unitários e de integração para garantir a qualidade e a confiabilidade do código. Os testes unitários garantem que componentes individuais funcionem conforme o esperado, enquanto os testes de integração verificam a integração adequada entre os componentes do sistema.

### Relacionamentos Many-to-Many, Many-to-One e One-to-One

Foram criados e demonstrados três tipos de relacionamentos de entidade no projeto:

- **Many-to-Many:** Esse relacionamento envolve várias instâncias de uma entidade associadas a várias instâncias de outra entidade. Por exemplo, muitos alunos podem se inscrever em muitos cursos e vice-versa.

- **Many-to-One:** Neste relacionamento, várias instâncias de uma entidade estão associadas a uma única instância de outra entidade. Por exemplo, muitos estudantes podem estar associados a uma única universidade.

- **One-to-One:** Este relacionamento envolve uma única instância de uma entidade associada a uma única instância de outra entidade. Por exemplo, um aluno pode ter apenas um registro de endereço e vice-versa.

## Scripts para criação de tabelas 

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

CREATE TABLE tb_pessoa (
id BIGSERIAL PRIMARY KEY,
cpf VARCHAR(11) not null,
nome VARCHAR(100) not null,
nascimento DATE not null,
email varchar(255) not null
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


```
