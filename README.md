# Agendamiento de Citas para el Centro Estetico Brii utilizando Java y [MySQL Workbench](https://www.mysql.com/products/workbench/)
> Usando Flatlaf Light [Flatlaf Demo](https://github.com/JFormDesigner/FlatLaf.git)
<p align="center">
  <img src="https://i.imgur.com/CHga11H.png">
</p>

## Base de Datos
<p>Se Utilizo el Sistema Gestor de Base de Datos MySQL Workbench 8.0</p>

- [`Script de La Base de Datos`](https://uniandeseduec-my.sharepoint.com/:u:/g/personal/raulde17_uniandes_edu_ec/ESeohsBgLVFGru1wQ5jhSO0BM626Ib0Pb64hH93dZ8p5sw?e=2fLrhl)
- [`Triggers de la Base de Datos`](https://uniandeseduec-my.sharepoint.com/:u:/r/personal/raulde17_uniandes_edu_ec/Documents/Universidad%20Uniandes/CUARTO%20SEMESTRE/PROYECTO%20FINAL%20FASE%202/BASE%20DE%20DATOS/TRIGGERS/reporte.sql?csf=1&web=1&e=dgoaGQ)

## Instalacion
Primero debemos descargar los dos archivos de la base de datos, que se mencionan anteriormente. Creamos una nueva base de datos llamada `centro_estetico` la seleccionamos y ejecutamos el Script en la base de datos, por preferencia en `MySQL Workbench 8.0`

- Agregamos las siguientes consultas despues de ejecutar el Script:
``` MySQL
INSERT INTO centro_estetico.CLIENTE
(ID_CLIENTE, TIPO_DOCUMENTO, NOMBRE, PRIMER_APELLIDO, SEGUNDO_APELLIDO,
FECHA_NACIMIENTO, GENERO, DIRECCION, CORREO, TELEFONO)
VALUES ('0', '0', '0', '0', '0', '2024-01-01', '0', '0', '0', '0');
```

- Ahora agregamos otra consulta para la Tabla `RESERVACION`
``` MySQL
INSERT INTO centro_estetico.RESERVACION 
(ID_RESERVACION, ID_CLIENTE, FECHA_HORA_RES, FECHA_HORA_CITA, ESTADO) 
VALUES ('0', '0', '2024-01-01', '2024-01-01', 'Cancelada');
```

## Equipo
Este equipo esta conformado por las siguientes personas:

| [![Raul Duran](https://i.imgur.com/ZsXtlXI.png)](https://github.com/NosakaKing) |
| --------------------------------------------------------------------------------------------------------------------- |
| [Raul Duran](https://github.com/NosakaKing)  


