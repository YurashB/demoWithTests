create table passports
(
    id               serial
        primary key,
    date_of_birth    date,
    expire_date      date,
    name             varchar(255),
    serial_number    varchar(255),
    is_free          boolean,
    is_deleted       boolean,
    prev_passport_id integer
        constraint passports_passports_id_fk
            references passports,
    status           varchar
);

alter table passports
    owner to postgres;

create table users
(
    id          serial
        primary key,
    country     varchar(255),
    email       varchar(255),
    name        varchar(255),
    gender      varchar(255),
    identifier  varchar(255),
    is_deleted  boolean,
    passport_id integer
        constraint users_passports_id_fk
            references passports
);

alter table users
    owner to postgres;

create table addresses
(
    id                 bigint not null
        primary key,
    address_has_active boolean,
    city               varchar(255),
    country            varchar(255),
    street             varchar(255),
    employee_id        integer
        constraint addresses_users_id_fk
            references users
);

alter table addresses
    owner to postgres;

create table photos
(
    id          serial
        constraint photo_pkey
            primary key,
    add_date    timestamp,
    image       bytea,
    photo_type  varchar(255),
    employee_id integer
        constraint photos_users_id_fk
            references users
);

alter table photos
    owner to postgres;



