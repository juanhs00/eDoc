insert into users(username, password, enabled) values ('alumno', 'xxx', true);
insert into authorities (username, authority) values('alumno', 'ROLE_ALUM');

insert into users(username, password, enabled) values ('profesor', '{bcrypt}$2a$04$5UCGcwUAwJQvauWOWL8DMO6wB6DvytNu9/SxHp/3njMOqSFUG4CdW', true);
insert into authorities (username, authority) values('profesor', 'ROLE_PROF');

insert into users(username, password, enabled) values ('admin', 'xxx', true);
insert into authorities (username, authority) values('admin', 'ROLE_ADMIN');