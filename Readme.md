# Intrucciones

Estos ejemplos utilizan MariaDB, usted puede optar por alguna de las 2 opciones:

* Installe MariaDB localmente
* Levante una imagen de MariaDB en Docker

## Leventar una imagen de MariaDB en DOCKER

- Realice un pull de la imágen

### docker pull mariadb

- Ejecute el contenedor

### docker run -d --rm --name mariadb -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 mariadb

Puede verificar si el contenedor está corriendo con el siguiente comando:

### docker ps

Y, cuando no necesita el contenedor en funcionamiento puedo realizar un stop del mismo:

### docker stop #contenedor

-----------

Utilice alguna herramienta de gestión de bases de datos (que sea compatible con MariaDb) para ejecutar el script