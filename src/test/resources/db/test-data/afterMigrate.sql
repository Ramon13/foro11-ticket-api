#DELETE DATA AFTER EACH FLYWAY MIGRATION

set foreign_key_checks = 0;

delete from tag;
delete from user;
delete from ticket;
delete from ticket_has_tag;
delete from comment;

set foreign_key_checks = 1;

alter table tag auto_increment = 1;
alter table user auto_increment = 1;
alter table ticket auto_increment = 1;
alter table comment auto_increment = 1;