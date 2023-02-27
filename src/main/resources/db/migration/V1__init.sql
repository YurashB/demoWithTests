create table users
(
    id         integer primary key not null,
    name       character varying,
    email      character varying,
    country    character varying,
    is_deleted boolean
);
create unique index users_id_uindex on users using btree (id);

create table addresses
(
    id                 bigint primary key not null,
    address_has_active boolean,
    city               character varying(255),
    country            character varying(255),
    street             character varying(255),
    employee_id        integer,
    foreign key (employee_id) references users (id)
);



