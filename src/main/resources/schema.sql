-- This file is automatically read and executed by Hibernate

DROP TABLE IF EXISTS STUDENT;
DROP TABLE IF EXISTS STUDENT_SEQ;

-- create the tables
create table STUDENT (
                         STUDENT_ID bigint not null,
                         ADDRESS varchar(255),
                         NAME varchar(255),
                         primary key (STUDENT_ID)
) engine=InnoDB;

create table STUDENT_SEQ (
                             next_val bigint
) engine=InnoDB;

-- initialize the sequence
insert into STUDENT_SEQ values ( 1 );