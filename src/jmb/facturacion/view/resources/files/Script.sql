/* CREAR BASE DE DATOS */
sqlite3 facturacion.db

/* CREAR TABLAS */
CREATE TABLE empresas(id INTEGER PRIMARY KEY autoincrement, nombre TEXT, direccion TEXT, localidad TEXT, cod_postal TEXT, provincia TEXT, pais TEXT, telefono TEXT, fax TEXT, email TEXT, cif TEXT, registro_mercantil TEXT, password TEXT);
CREATE TABLE clientes(id INTEGER PRIMARY KEY autoincrement, tratamiento TEXT, nombre TEXT, primer_apellido TEXT, segundo_apellido TEXT, direccion TEXT, localidad TEXT, cod_postal TEXT, provincia TEXT, pais TEXT, telefono TEXT, fax TEXT, email TEXT, dni TEXT);
CREATE TABLE sesiones(id INTEGER PRIMARY KEY autoincrement, id_empresa INTEGER, fecha TEXT, hora TEXT, FOREIGN KEY(id_empresa) REFERENCES empresas(id));
CREATE TABLE parametros(id INTEGER PRIMARY KEY autoincrement, version TEXT, ruta_index TEXT, ruta_archivos TEXT, generar_log BOOLEAN);
CREATE TABLE tipos_factura(id INTEGER PRIMARY KEY autoincrement, descripcion TEXT);
CREATE TABLE vendedores(id INTEGER PRIMARY KEY autoincrement, nombre TEXT);
CREATE TABLE formas_pago(id INTEGER PRIMARY KEY autoincrement, descripcion TEXT);
CREATE TABLE facturas(id TEXT PRIMARY KEY NOT NULL, fecha DATE, id_empresa INTEGER, id_cliente INTEGER, serie_factura TEXT, numero_factura TEXT, id_tipo_factura INTEGER, id_vendedor INTEGER, id_forma_pago INTEGER, matricula TEXT, modelo TEXT, bastidor TEXT, color TEXT, referencia_factura_cliente TEXT, notas TEXT, base REAL, cuota REAL, total REAL, franquicia REAL, FOREIGN KEY(id_empresa) REFERENCES empresas(id), FOREIGN KEY(id_cliente) REFERENCES clientes(id), FOREIGN KEY(id_tipo_factura) REFERENCES tipos_factura(id), FOREIGN KEY(id_vendedor) REFERENCES vendedores(id), FOREIGN KEY(id_forma_pago) REFERENCES formas_pago(id));
CREATE TABLE lineas_factura(id INTEGER PRIMARY KEY autoincrement, id_factura INTEGER, linea INTEGER, descripcion TEXT, tiempo_cantidad REAL, precio REAL, porcentaje_descuento REAL, importe REAL, porcentaje_iva REAL, importe_total REAL, observaciones TEXT, FOREIGN KEY(id_factura) REFERENCES facturas(id));

/* INSERCCION DE DATOS */
INSERT INTO empresas VALUES(NULL, "GRUAS MAZA", "BARRIO SALTO DEL OSO S/N", "RAMALES DE LA VICTORIA", "39800", "CANTABRIA", "ESPAÑA", "942646056", "", "mazagruas@telefonica.net", "78865208W", "", "c4ca4238a0b923820dcc509a6f75849b");
INSERT INTO clientes VALUES(NULL, "SR.", "JOSE MANUEL", "BALBAS", "MURIEL", "BARRIO EL MATO 5, N2, ATICO J", "MORTERA", "39120", "CANTABRIA", "ESPAÑA", "685346463", "", "jmbalbas87@gmail.com", "72074453C");
INSERT INTO parametros VALUES(NULL, "1.0", "file:/C:/Users/jmbalbas/Documents/NetBeansProjects/Facturacion/facturacion_web/index.html", "", 0);
INSERT INTO tipos_factura VALUES(NULL, "NORMAL");
INSERT INTO vendedores VALUES(NULL, "VARIOS");
INSERT INTO formas_pago VALUES(NULL, "CONTADO");