-- ------------------------------------------------------ ROLES,USUARIOS,CUENTAS

INSERT INTO rol (nombre) VALUES	('administrador'),('vendedor');

INSERT INTO usuario (ci, apellidos, nombres, telefono) VALUES ('0602447682', 'Bonilla', 'jhonnatan', '0992016416');

INSERT INTO cuenta (contrasena, email, fecha_creacion, ci, enabled) VALUES	('$2a$10$2o3ARSVKOKch2nv4Kcvzk.RfjAFy3mODgQIZZXNJWhUlfahAQ8fbe', 'jhonnatanbm@gmail.com', '2022-07-07', '0602447682', 1);

INSERT INTO cuenta_rol (id_rol, id_cuenta) VALUES (1, 1);

-- -------------------------------------------------------------------------- LIBROS

INSERT INTO autor (nombre) VALUES ('Christopher Paolini'),	('Gabriel García Marquez'),	('Liu Cinix');

INSERT INTO editorial (id_editorial, nombre) VALUES	(1, 'Rocalibro'),	(2, 'Salamandra'),	(3, 'Editorial Sudamericana'),	(4, 'Nova');

INSERT INTO genero (id_genero, nombre) VALUES	(1, 'Ficción'),	(2, 'Fantasia'),	(3, 'Realismo Mágico');

INSERT INTO libro (isbn, descripcion, fecha_registro, precio_unitario, stock, titulo) VALUES ('1111', 'Lorem Ipsum et dolor athem', '2022-07-06', 15.25, 10, 'Eragon'),	('1112', 'Lorem Ipsum et dolor athem', '2022-07-10', 15.25, 10, 'Eldest'),	('1113', 'Lorem Ipsum et dolor athem', '2022-07-01', 12.5, 10, 'Cien años de soledad'),	('1114', 'Lorem Ipsum et dolor athem', '2022-07-10', 8.9, 15, 'El amor en tiempos del colera'),	('1115', 'Lorem Ipsum et dolor athem', '2022-07-10', 5.3, 3, 'El coronel no tiene quien le escriba'),	('1116', 'Lorem Ipsum et dolor athem', '2022-07-10', 25.3, 2, '11 Cuentos Peregrinos'),	('1117', 'Lorem Ipsum et dolor athem', '2022-07-10', 16.5, 8, 'Brisingr'),	('1118', 'Lorem Ipsum et dolor athem', '2022-07-11', 17, 3, 'Legado'),	('9789585206380', 'Lorem Ipsum et dolor athem', '2022-07-12', 18, 7, 'El Bosque Oscuro');


INSERT INTO libros_cuenta (id_cuenta, isbn) VALUES	(1, '1111'),	(1, '1112'),	(1, '1113'),	(1, '1114'),	(1, '1115'),	(1, '1116'),	(1, '1117'),	(1, '1118'),	(1, '9789585206380');

INSERT INTO libros_autores (isbn, id_autor) VALUES	('1111', 1),	('1112', 1),	('1113', 2),	('1114', 2),	('1115', 2),	('1116', 2),	('1117', 1),	('1118', 1),	('9789585206380', 3);

INSERT INTO libros_editoriales (isbn, id_editorial) VALUES	('1111', 1),	('1112', 1),	('1113', 2),	('1114', 3),	('1115', 3),	('1116', 3),	('1117', 1),	('1118', 1),	('9789585206380', 4);

INSERT INTO libros_generos (isbn, id_genero) VALUES	('1111', 1),	('1112', 2),	('1113', 2),	('1114', 3),	('1115', 3),	('1116', 3),	('1117', 2),	('1118', 2),	('9789585206380', 2);