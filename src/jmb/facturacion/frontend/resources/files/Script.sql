sqlite3 facturacion
CREATE TABLE empresas(id INTEGER PRIMARY KEY autoincrement, nombre TEXT, direccion TEXT, localidad TEXT, cod_postal TEXT, telefono TEXT, fax TEXT, email TEXT, cif TEXT, registro_mercantil TEXT, password TEXT);
INSERT INTO empresas VALUES(NULL, "GRUAS MAZA", "BARRIO SALTO DEL OSO S/N", "RAMALES DE LA VICTORIA", "39800", "942646056", "", "mazagruas@telefonica.net", "78865208W", "", "c4ca4238a0b923820dcc509a6f75849b");
CREATE TABLE sesiones(id INTEGER PRIMARY KEY autoincrement, id_empresa INTEGER, fecha TEXT, hora TEXT, FOREIGN KEY(id_empresa) REFERENCES empresas(id));