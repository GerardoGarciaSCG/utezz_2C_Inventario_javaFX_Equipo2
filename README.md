# utezz_2C_Inventario_javaFX_Equipo2

# Inventario de Productos (JavaFX)

## Descripción del sistema

Este proyecto es una aplicación de escritorio desarrollada con Java y JavaFX. Su propósito es administrar los productos de una tienda mediante un sistema CRUD.

El sistema permite realizar las siguientes acciones:

* Agregar productos
* Visualizar productos en una tabla
* Editar productos
* Eliminar productos

La información se guarda en un archivo CSV, lo que permite conservar los datos incluso después de cerrar el programa.

---

## Objetivo

El objetivo de este proyecto es aplicar los conocimientos de JavaFX, programación orientada a objetos y manejo de archivos, sin utilizar una base de datos.

---

## Cómo ejecutar el proyecto

1. Clonar o descargar el repositorio.
2. Abrir el proyecto en IntelliJ IDEA.
3. Ejecutar la clase principal:

MainApp.java

---

## Archivo de datos

El sistema utiliza un archivo llamado:

productos.csv

Este archivo almacena los datos de los productos.

Ejemplo de contenido:

001,Pepsi,18.5,20,Bebidas
002,Atun,5.0,50,Alimentos
003,Yogurt,22.0,15,Lacteos

Formato de los datos:

codigo,nombre,precio,stock,categoria

---

## Funcionalidades

* CRUD completo (Crear, Leer, Actualizar, Eliminar)
* Persistencia de datos en archivo CSV
* Validaciones:

    * Campos no vacíos
    * Nombre mínimo de 3 caracteres
    * Precio mayor a 0
    * Stock mayor o igual a 0
    * Código único (sin duplicados)
* Búsqueda en tiempo real por nombre o código
* Ordenamiento de productos en la tabla

---

## Interfaz

El sistema cuenta con:

* Tabla (TableView) para mostrar los productos
* Campo de búsqueda
* Botones: Nuevo, Editar, Eliminar
* Formulario para agregar y editar productos

---

## Notas importantes

* No se utiliza base de datos
* Los datos se almacenan en un archivo local
* El archivo se crea automáticamente si no existe
* Se utilizan listas y ObservableList para manejar los datos

---

## Autores

* Gerardo Garcia Rodriguez 
* Diego Yael Avila Estrada

