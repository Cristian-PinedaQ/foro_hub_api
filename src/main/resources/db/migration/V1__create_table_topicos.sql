create table topics(

    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    mensaje varchar(200) not null unique,
    fecha_creation datetime not null,
    status tinyint  NULL DEFAULT 1,
    autor varchar(100) not null,
    curso varchar(100) not null,

    primary key(id)

);