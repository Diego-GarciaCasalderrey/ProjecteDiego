# Projecte Diego
### Informació del projecte
Aquest projecte consisteix en un **CRUD** (Create, Read, Update & Delete) creat en Java (Netbeans), d'una Base de Dades de vaixells amb JDBC. 
L'usuari pot crear, veure, actualitzar o eliminar vaixells dins d'una interfície creada amb JFrames i JDialogs, que es comunica amb un controlador (controlador únic, ja que és un projecte petit) que accedeix a classes per a actualitzar aquestes dades dins la base de dades i a l'inrevés (**Model-Vista-Controlador + DAO**).
### Per a executar
Per a poder executar aquest projecte cal crear una nova base de dades **MySQL** i un usuari amb permisos a aquesta.
Dins d'aquesta carpeta hi ha un arxiu .sql amb comandes que es podrien utilitzar per a la creació d'aquesta DB i usuari (*Creacio_DB_user.sql*), però si es vol utilitzar un nom d'usuari o base de dades diferent, es pot fer canviant-ho a l'arxiu dins la ruta *src/DAO/BaseDAO.java* del projecte.

```
create schema if not exists projecteDiego;
create user 'diegogarcia'@'localhost' identified by 'P@ssw0rd';
grant ALL PRIVILEGES on projecteDiego.* to 'diegogarcia'@'localhost';
flush privileges;
show grants for 'diegogarcia'@'localhost';
-- esborrar l'usuari i la BD un cop finalitzat amb:
-- drop user 'diegogarcia'@'localhost';
-- drop schema projecteDiego;
```

Un cop tenim la base de dades i l'usuari creats, podem executar el Main de l'aplicació situat a *src/Main/Main.java*

### Més informació
Pots trobar informació dels mètodes del codi en uns Javadoc que es troben dins la carpeta *GarciaCasalderreyDiego_M3_UF6_Pt1/dist/javadoc*.
