# Gestor de Recetas

## Descripción

Gestor de Recetas es una aplicación de escritorio desarrollada en Java que permite consultar, buscar y registrar recetas de cocina mediante una interfaz gráfica. Cada receta contiene información sobre su categoría, tiempo de preparación, ingredientes y pasos de elaboración.

La aplicación utiliza archivos para guardar los datos, de modo que las recetas puedan conservarse entre ejecuciones. Este proyecto forma parte de mi aprendizaje en el ciclo de Desarrollo de Aplicaciones Multiplataforma (DAM).

## Tecnologías utilizadas

- **Java:** desarrollo de la lógica de la aplicación.
- **Java Swing:** creación de la interfaz gráfica.
- **Serialización de objetos:** almacenamiento y recuperación de las recetas mediante archivos.
- **Colecciones de Java:** organización de recetas por categorías y gestión de búsquedas.
- **Git y GitHub:** control de versiones y alojamiento del código.

## Funcionalidades

- Registrar nuevas recetas.
- Organizar las recetas por categorías.
- Buscar recetas por nombre.
- Buscar recetas por ingrediente.
- Filtrar las recetas por categoría.
- Consultar los ingredientes, las cantidades, las unidades y los pasos de preparación.
- Mostrar el tiempo de preparación de cada receta.
- Guardar y recuperar los datos mediante serialización de objetos.

## Estructura del proyecto

```text
Recetas/
├── .vscode/
│   └── settings.json
├── bin/
│   ├── AppRecetas.class
│   ├── GestorRecetas.class
│   ├── Ingrediente.class
│   ├── InterfazRecetas.class
│   ├── Receta.class
│   └── AppRecetas$1.class
├── lib/
├── src/
│   ├── AppRecetas.java
│   ├── GestorRecetas.java
│   ├── Ingrediente.java
│   ├── InterfazRecetas.java
│   └── Receta.java
├── recetas.dat
└── README.md
```

### Organización del código

- **`AppRecetas.java`:** punto de entrada de la aplicación. Inicia la interfaz gráfica.
- **`InterfazRecetas.java`:** implementa la ventana principal, las búsquedas, los filtros y los formularios para registrar recetas.
- **`GestorRecetas.java`:** administra las recetas, las organiza por categorías y gestiona las búsquedas y el almacenamiento.
- **`Receta.java`:** representa una receta con su nombre, categoría, ingredientes, pasos y tiempo de preparación.
- **`Ingrediente.java`:** representa un ingrediente con su nombre, cantidad y unidad de medida.
- **`recetas.dat`:** archivo utilizado para guardar los datos serializados.
- **`bin/`:** contiene los archivos compilados `.class`.
- **`.vscode/settings.json`:** configuración del proyecto para Visual Studio Code.

## Instalación y ejecución

### Requisitos

- Java JDK instalado.
- Un IDE compatible con Java, como Visual Studio Code, IntelliJ IDEA o Eclipse.

### Pasos

1. Clona el repositorio:

   ```bash
   git clone https://github.com/brando046/Recetas.git
   ```

2. Abre la carpeta del proyecto en tu IDE.

3. Comprueba que la carpeta `src/` contiene los archivos fuente Java.

4. Ejecuta `AppRecetas.java`, que contiene el método `main` de la aplicación.

La aplicación utiliza Java Swing para mostrar la interfaz gráfica y no requiere configurar una base de datos externa.

## Almacenamiento de datos

Las recetas se guardan mediante serialización de objetos Java en el archivo `recetas.dat`. La clase `GestorRecetas` se encarga de cargar los datos al iniciar la aplicación y guardarlos cuando se registra una nueva receta.
