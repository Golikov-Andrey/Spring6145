create table if not exists bank_account
(
    id        bigserial primary key,
    number    bigint not null unique,
    balance   decimal(12, 2)
);