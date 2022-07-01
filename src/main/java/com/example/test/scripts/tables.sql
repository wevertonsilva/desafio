CREATE DATABASE test;

create table usuario (
	id bigint auto_increment primary key,
	nome varchar(255) not null,
	email varchar(255) not null,
	senha varchar(255) not null,
	token_id bigint
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

create table token (
	id bigint auto_increment primary key,
	expiracao timestamp not null,
	email varchar(255) not null,
	access_token varchar(1000) not null
)

create table tarefa (
	id bigint auto_increment primary key,
	descricao varchar(255) not null,
	prioridade varchar(255) not null,
	status varchar(255) not null,
	usuario_id bigint not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

alter table usuario add constraint fk_token_id
foreign key (token_id) references token(id);

alter table tarefa add constraint fk_usuario_id
foreign key (usuario_id) references usuario(id);