drop table if exists TASK_EVENTS;
create table TASK_EVENTS
(
    id         int primary key auto_increment,
    task_id    int,
    occurrence datetime,
    name       varchar(30)
);