create table salas(

    id bigserial not null,
    nome varchar(100) not null,
    dimensao varchar(50),
    equipamentos varchar(255),  
    observacao varchar(255),
    ativo boolean not null,

    primary key(id)

);