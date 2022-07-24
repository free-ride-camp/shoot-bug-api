-- liquibase formatted sql

-- changeset zenox:2022072402

insert into role values (1, 'SUPER_ADMIN'),
                        (2, 'NORMAL'),
                        (3, 'AUDITOR');