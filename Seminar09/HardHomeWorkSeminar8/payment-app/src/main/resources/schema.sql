create table if not exists bank_account
(
    id          INT AUTO_INCREMENT primary key,
    number       bigint not null unique,
    balance   decimal(12, 2)
);