create table if not exists country
(
    id   serial primary key,
    name varchar(255) not null unique
    );

create table if not exists region
(
    id         serial primary key,
    name       varchar(255) not null unique,
    country_id bigint,
    foreign key (country_id)
    references country (id)
    on delete cascade
    on update cascade
    );

create table if not exists district
(
    id        serial primary key,
    name      varchar(255) not null,
    region_id bigint,
    foreign key (region_id)
    references region (id)
    on delete cascade
    on update cascade
    );

create table if not exists quarter
(
    id         serial primary key,
    name       varchar(255) not null,
    district_id bigint,
    foreign key (district_id)
    references district (id)
    on delete cascade
    on update cascade
    );
