# ExploradorDeArchivos 📂

![Java](https://img.shields.io/badge/Java-21-blue)
![Swing](https://img.shields.io/badge/Swing-GUI-orange)

ExploradorDeArchivos es una aplicación de escritorio desarrollada en Java que permite explorar directorios y visualizar archivos de texto e imágenes.

## Características ✨

- 📁 Explora directorios y archivos en tu sistema de archivos.
- 📝 Visualiza archivos de texto (.txt, .html).
- 🖼️ Visualiza imágenes (.jpg, .png).
- 💾 Guarda cambios en archivos de texto.
- 🗑️ Elimina archivos y muestra sus propiedades.

## Requisitos 🛠️

- ☕ Java 21 o superior
- 🐍 Maven

## Instalación 🧩

1. Clona el repositorio:

    ```sh
    git clone https://github.com/tu-usuario/ExploradorDeArchivos.git
    cd ExploradorDeArchivos
    ```

2. Compila el proyecto usando Maven:

    ```sh
    mvn clean install
    ```

## Ejecución 🚀

Para ejecutar la aplicación, usa el siguiente comando:

```sh
mvn exec:java -Dexec.mainClass="io.github.oisaac.exploradordearchivos.Main"
```

## Estructura del Proyecto 📂

```
ExploradorDeArchivos/
├── nbactions.xml
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── io/
│   │   │       └── github/
│   │   │           └── oisaac/
│   │   │               ├── exploradordearchivos/
│   │   │               │   ├── Main.java
│   │   │               │   ├── logica/
│   │   │               │   │   ├── JGuiController.java
│   │   │               │   │   ├── JIOController.java
│   │   │               │   │   ├── JTreeDirectoryController.java
│   │   │               │   │   └── JTreeDirectoryNodeController.java
│   │   │               │   └── vistas/
│   │   │               │       ├── Explorador.java
│   │   │               │       ├── visualizadores/
│   │   │               │       │   ├── VisualizadorImagenes.java
│   │   │               │       │   └── VisualizadorTexto.java
│   │   │               │       └── Explorador.form
│   │   │               └── resources/
│   │   │                   ├── folder.png
│   │   │                   └── folder50x50.png
│   └── test/
│       └── java/
└── target/
    ├── ExploradorDeArchivos-1.0-SNAPSHOT.jar
    └── classes/
        ├── folder.png
        └── folder50x50.png
```

## Uso

1. Al iniciar la aplicación, puedes abrir un directorio usando el menú `File > Abrir` o el botón `Explorador`.
2. Navega por los directorios y selecciona archivos para visualizarlos.
3. Los archivos de texto se pueden editar y guardar.
4. Usa el menú contextual (clic derecho) en los archivos para ver propiedades o eliminarlos.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request en el repositorio.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.