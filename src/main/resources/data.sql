CREATE TABLE IF NOT EXISTS author (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    author_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE CASCADE
);

INSERT INTO author (name) VALUES ('Allan');
INSERT INTO author (name) VALUES ('Alice');
INSERT INTO author (name) VALUES ('Bruno');
INSERT INTO author (name) VALUES ('Carla');
INSERT INTO author (name) VALUES ('Daniel');
INSERT INTO author (name) VALUES ('Eduarda');
INSERT INTO author (name) VALUES ('Felipe');
INSERT INTO author (name) VALUES ('Gabriel');
INSERT INTO author (name) VALUES ('Igor');
INSERT INTO author (name) VALUES ('Juliana');
INSERT INTO author (name) VALUES ('Karla');
INSERT INTO author (name) VALUES ('Lucas');
INSERT INTO author (name) VALUES ('Mariana');
INSERT INTO author (name) VALUES ('Nicolas');
INSERT INTO author (name) VALUES ('Tiago');

INSERT INTO book (title, description, author_id) VALUES ('Primeiro Livro do Autor 1', 'Descrição do primeiro livro do autor 1', 1);
INSERT INTO book (title, description, author_id) VALUES ('Segundo Livro do Autor 1', 'Descrição do segundo livro do autor 1', 1);

INSERT INTO book (title, description, author_id) VALUES ('Primeiro Livro do Autor 2', 'Descrição do primeiro livro do autor 2', 2);
INSERT INTO book (title, description, author_id) VALUES ('Segundo Livro do Autor 2', 'Descrição do segundo livro do autor 2', 2);

INSERT INTO book (title, description, author_id) VALUES ('Primeiro Livro do Autor 3', 'Descrição do primeiro livro do autor 3', 3);
INSERT INTO book (title, description, author_id) VALUES ('Segundo Livro do Autor 3', 'Descrição do segundo livro do autor 3', 3);

INSERT INTO book (title, description, author_id) VALUES ('Primeiro Livro do Autor 4', 'Descrição do primeiro livro do autor 4', 4);
INSERT INTO book (title, description, author_id) VALUES ('Segundo Livro do Autor 4', 'Descrição do segundo livro do autor 4', 4);

INSERT INTO book (title, description, author_id) VALUES ('Primeiro Livro do Autor 5', 'Descrição do primeiro livro do autor 5', 5);
INSERT INTO book (title, description, author_id) VALUES ('Segundo Livro do Autor 5', 'Descrição do segundo livro do autor 5', 5);

INSERT INTO book (title, description, author_id) VALUES ('Primeiro Livro do Autor 6', 'Descrição do primeiro livro do autor 6', 6);
INSERT INTO book (title, description, author_id) VALUES ('Segundo Livro do Autor 6', 'Descrição do segundo livro do autor 6', 6);

INSERT INTO book (title, description, author_id) VALUES ('Primeiro Livro do Autor 7', 'Descrição do primeiro livro do autor 7', 7);
INSERT INTO book (title, description, author_id) VALUES ('Segundo Livro do Autor 7', 'Descrição do segundo livro do autor 7', 7);

INSERT INTO book (title, description, author_id) VALUES ('Primeiro Livro do Autor 8', 'Descrição do primeiro livro do autor 8', 8);
INSERT INTO book (title, description, author_id) VALUES ('Segundo Livro do Autor 8', 'Descrição do segundo livro do autor 8', 8);

INSERT INTO book (title, description, author_id) VALUES ('Primeiro Livro do Autor 9', 'Descrição do primeiro livro do autor 9', 9);
INSERT INTO book (title, description, author_id) VALUES ('Segundo Livro do Autor 9', 'Descrição do segundo livro do autor 9', 9);

INSERT INTO book (title, description, author_id) VALUES ('Primeiro Livro do Autor 10', 'Descrição do primeiro livro do autor 10', 10);
INSERT INTO book (title, description, author_id) VALUES ('Segundo Livro do Autor 10', 'Descrição do segundo livro do autor 10', 10);

INSERT INTO book (title, description, author_id) VALUES ('Primeiro Livro do Autor 11', 'Descrição do primeiro livro do autor 11', 11);
INSERT INTO book (title, description, author_id) VALUES ('Segundo Livro do Autor 11', 'Descrição do segundo livro do autor 11', 11);

INSERT INTO book (title, description, author_id) VALUES ('Primeiro Livro do Autor 12', 'Descrição do primeiro livro do autor 12', 12);
INSERT INTO book (title, description, author_id) VALUES ('Segundo Livro do Autor 12', 'Descrição do segundo livro do autor 12', 12);

INSERT INTO book (title, description, author_id) VALUES ('Primeiro Livro do Autor 13', 'Descrição do primeiro livro do autor 13', 13);
INSERT INTO book (title, description, author_id) VALUES ('Segundo Livro do Autor 13', 'Descrição do segundo livro do autor 13', 13);

INSERT INTO book (title, description, author_id) VALUES ('Primeiro Livro do Autor 14', 'Descrição do primeiro livro do autor 14', 14);
INSERT INTO book (title, description, author_id) VALUES ('Segundo Livro do Autor 14', 'Descrição do segundo livro do autor 14', 14);

INSERT INTO book (title, description, author_id) VALUES ('Primeiro Livro do Autor 15', 'Descrição do primeiro livro do autor 15', 15);
INSERT INTO book (title, description, author_id) VALUES ('Segundo Livro do Autor 15', 'Descrição do segundo livro do autor 15', 15);
