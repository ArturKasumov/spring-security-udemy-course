create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

insert into users
values ('user', '{bcrypt}$2a$12$ft.yzbx49qqRvk5pCzAnT.yEMIT5mzV1VcDOvcVUAz.bTvbFeMveW', true),
       ('admin', '{bcrypt}$2a$12$ft.yzbx49qqRvk5pCzAnT.yEMIT5mzV1VcDOvcVUAz.bTvbFeMveW', true);
insert into authorities
values
    ('user', 'user'),
    ('admin', 'admin');

CREATE TABLE `customer` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `email` varchar(45) NOT NULL,
                            `pwd` varchar(200) NOT NULL,
                            `role` varchar(45) NOT NULL,
                            PRIMARY KEY (`id`)
);

insert into customer (email, pwd, role)
values ('user@test.com', '{bcrypt}$2a$12$ft.yzbx49qqRvk5pCzAnT.yEMIT5mzV1VcDOvcVUAz.bTvbFeMveW', 'user'),
       ('admin@test.com', '{bcrypt}$2a$12$ft.yzbx49qqRvk5pCzAnT.yEMIT5mzV1VcDOvcVUAz.bTvbFeMveW', 'admin');