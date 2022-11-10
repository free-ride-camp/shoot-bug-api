-- liquibase formatted sql

-- changeset zenox:2022111001

alter table reply add column is_deleted boolean;

update reply set is_deleted = false;
