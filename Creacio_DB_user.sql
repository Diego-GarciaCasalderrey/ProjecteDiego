create schema if not exists projecteDiego;
create user 'diegogarcia'@'localhost' identified by 'P@ssw0rd';
grant ALL PRIVILEGES on projecteDiego.* to 'diegogarcia'@'localhost';
flush privileges;
show grants for 'diegogarcia'@'localhost';
-- esborrar l'usuari i la BD un cop finalitzat amb:
-- drop user 'diegogarcia'@'localhost';
-- drop schema projecteDiego;