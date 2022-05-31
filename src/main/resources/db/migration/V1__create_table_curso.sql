create table curso(
    id bigint auto_increment not null,
    nome varchar(50) not null,
    categoria varchar(50) not null,
    primary key (id)
);

insert into curso values (1, 'Kotlin', 'DEV');