CREATE TABLE `customer` (
                            `id` BIGINT NOT NULL AUTO_INCREMENT,
                            `email` varchar(45) NOT NULL,
                            `password` varchar(200) NOT NULL,
                            PRIMARY KEY (`id`)
);

CREATE TABLE `authorities` (
                               `id` BIGINT NOT NULL AUTO_INCREMENT,
                               `customer_id` BIGINT NOT NULL,
                               `authority` varchar(45) NOT NULL,
                               PRIMARY KEY (`id`),
                               CONSTRAINT `fk_customer_authorities`
                                   FOREIGN KEY (`customer_id`)
                                       REFERENCES `customer` (`id`)
                                       ON DELETE CASCADE
);

INSERT INTO customer (id, email, password) VALUES
                                                (1,'user1@test.com', '{bcrypt}$2a$12$ft.yzbx49qqRvk5pCzAnT.yEMIT5mzV1VcDOvcVUAz.bTvbFeMveW'),
                                                (2,'user2@test.com', '{bcrypt}$2a$12$ft.yzbx49qqRvk5pCzAnT.yEMIT5mzV1VcDOvcVUAz.bTvbFeMveW'),
                                                (3,'admin@test.com', '{bcrypt}$2a$12$ft.yzbx49qqRvk5pCzAnT.yEMIT5mzV1VcDOvcVUAz.bTvbFeMveW');


INSERT INTO authorities (customer_id, authority) VALUES
    (1, 'VIEWBALANCE'),
    (1, 'VIEWACCOUNT'),
    (1, 'VIEWCARDS'),
    (2, 'VIEWBALANCE'),
    (2, 'VIEWACCOUNT'),
    (3, 'ROLE_ADMIN');