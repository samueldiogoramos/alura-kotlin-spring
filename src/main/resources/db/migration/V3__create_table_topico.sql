create table topico(
    id bigint auto_increment not null,
    titulo varchar(100),
    mensagem varchar(100),
    data_criacao timestamp,
    curso_id bigint not null,
    autor_id bigint not null,
    status varchar(50) not null,
    primary key (id),
    foreign key (curso_id) references curso(id),
    foreign key (autor_id) references usuario(id)
);