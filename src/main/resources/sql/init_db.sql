create table worker (
                        ID SERIAL primary key,
                        NAME varchar not null check (length(NAME::text) > 2 AND length(NAME::text) <= 100),
                        BIRTHDAY date check (extract(year from BIRTHDAY) > 1900),
                        LEVEL varchar not null check (LEVEL IN('Trainee','Junior','Middle','Senior')),
                        SALARY BIGINT check (SALARY > 100 AND SALARY < 100000)
);

create table client(
                       ID SERIAL primary key,
                       NAME varchar not null check(length(NAME::text) > 2 and length(NAME::text) <= 1000)
);

create table project(
                        ID SERIAL primary key,
                        CLIENT_ID INT not null,
                        START_DATE date,
                        FINISH_DATE date,
                        constraint client_id_fk foreign key(CLIENT_ID) references client(ID) on delete cascade
);

create table project_worker(
                               PROJECT_ID INT,
                               WORKER_ID INT,
                               primary key(PROJECT_ID, WORKER_ID),
                               constraint project_id_fk foreign key(PROJECT_ID) references project(ID) on delete cascade,
                               constraint worker_id_fk foreign key(WORKER_ID) references worker(ID) on delete cascade
);