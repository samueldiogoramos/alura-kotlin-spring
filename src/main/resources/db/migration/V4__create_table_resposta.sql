create table resposta(
    id bigint auto_increment not null,
    mensagem varchar(100),
    data_criacao timestamp,
    autor_id bigint not null,
    topico_id bigint not null,
    solucao bit,
    primary key (id),
    foreign key (autor_id) references usuario(id),
    foreign key (topico_id) references topico(id)
);