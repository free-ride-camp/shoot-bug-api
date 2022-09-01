-- liquibase formatted sql

-- changeset zenox:2022090101

create unique index user_email_unique_idx on user(email);