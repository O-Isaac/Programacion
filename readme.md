# CRUD Java - Pokémon

![Java](https://img.shields.io/badge/Java-22%2B-blue) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green) ![MySQL](https://img.shields.io/badge/MySQL-8.0-orange) ![License](https://img.shields.io/badge/License-MIT-yellow)

Este proyecto es una aplicación CRUD de Java utilizando Spring Boot y MySQL. Permite gestionar una lista de Pokémon mediante una API REST.

## Tecnologías utilizadas
- **Spring Boot** - Framework para el desarrollo de la aplicación.
- **MySQL** - Base de datos para almacenar los datos de los Pokémon.
- **DBngin** - Software para gestionar la base de datos.
- **DBeaver** - Cliente para interactuar con la base de datos.
- **Cartero (Postman u otra herramienta HTTP)** - Cliente para probar la API.

## Endpoints de la API
La API expone los siguientes endpoints:

| Método | Endpoint            | Descripción                          |
|--------|---------------------|--------------------------------------|
| GET    | `/pokemon`         | Obtener todos los Pokémon           |
| GET    | `/pokemon/{id}`    | Obtener un Pokémon por ID           |
| POST   | `/pokemon/save`    | Añadir un nuevo Pokémon             |
| PUT    | `/pokemon/{id}`    | Actualizar un Pokémon existente     |
| DELETE | `/pokemon/{id}`    | Eliminar un Pokémon por ID          |

## Instalación y ejecución
### Requisitos previos
- Tener instalado Java 22 o superior.
- Tener configurado MySQL y crear la base de datos correspondiente.
- Tener instalado DBngin y DBeaver (opcional para la gestión de la base de datos).

### Pasos para ejecutar el proyecto
1. Clona este repositorio:
   ```sh
   git clone https://github.com/tuusuario/crud-java-pokemon.git
   ```
2. Configura la conexión a MySQL en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/nombre_basedatos
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Ejecuta la aplicación con Maven o desde tu IDE:
   ```sh
   mvn spring-boot:run
   ```

## Uso de la API
Puedes probar la API con herramientas como Postman o cURL. Por ejemplo, para obtener todos los Pokémon:
```sh
curl -X GET http://localhost:8080/pokemon
```

## Contribución
Si deseas contribuir a este proyecto, siéntete libre de hacer un fork y enviar pull requests.

## Licencia
Este proyecto está bajo la licencia MIT. ¡Siéntete libre de usarlo y modificarlo!

