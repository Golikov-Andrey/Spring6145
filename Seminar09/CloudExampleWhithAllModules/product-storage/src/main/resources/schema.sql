create table if not exists product
(
    id       bigserial primary key,
    name     varchar(255) not null,
    price    decimal(12, 2) check ( price > 0 ),
    amount   int check ( amount > -1 ),
    reserved int check ( reserved <= product.amount )
);