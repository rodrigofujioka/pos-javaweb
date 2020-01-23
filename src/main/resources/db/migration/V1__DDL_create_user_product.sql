create table t_user (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR not null,
    password VARCHAR not null,
    date_birth DATE,
    CONSTRAINT uk_user_username UNIQUE (username)
);

create table t_product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR not null,
    description VARCHAR not null,
    manufacture_year INTEGER,
    id_warehouse BIGINT
);

create table t_warehouse (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR not null,
    address VARCHAR not null
);

create table t_employee (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR not null,
    date_birth DATE
);

create table t_order (
    id BIGSERIAL PRIMARY KEY,
    date_order TIMESTAMP WITHOUT TIME ZONE not null,
    id_user BIGINT
);

