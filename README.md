# Tasks App - Manual de Usuario

Aplicación móvil desarrollada en Android con Jetpack Compose para la gestión y visualización de listas de tareas.

---

## 1. Descripción General

Tasks App es una herramienta sencilla e intuitiva diseñada para visualizar pendientes y dar seguimiento a actividades escolares o laborales. Permite el acceso a través de una pantalla de autenticación y ofrece un listado interactivo con opciones para consultar y descartar tareas en tiempo real.

---

## 2. Requisitos del Sistema

* Dispositivo Android con versión 8.0 (API nivel 26) o superior.
* Conexión a internet (únicamente requerida para la descarga o clonación del repositorio).
* Para desarrolladores: Android Studio instalado con soporte para Kotlin y Jetpack Compose.

---

## 3. Pantallas y Funcionamiento

### 3.1. Pantalla de Inicio de Sesión (LoginScreen)

Es la pantalla principal que aparece al abrir la aplicación.

* **Campos disponibles:**
  * **Correo electrónico:** Espacio para ingresar la dirección de correo o nombre de usuario.
  * **Contraseña:** Campo protegido que oculta los caracteres ingresados para mayor privacidad.
* **Acceso:**
  * Presione el botón **Iniciar sesión** para acceder directamente al panel de tareas. No se requiere validación de credenciales para fines demostrativos.

---

### 3.2. Pantalla de Tareas (ListTasksScreen)

Muestra el catálogo completo de tareas organizadas en tarjetas verticales.

* **Estructura de cada tarea:**
  * **Título:** Nombre de la actividad a realizar.
  * **Descripción:** Detalle explicativo sobre la tarea.
  * **Status:** Estado actual de la actividad (*Pendiente* o *Completada*).
* **Acciones disponibles:**
  * **Eliminar tarea:** Al presionar el botón circular con el icono de papelera ubicado en cada tarjeta, la tarea se elimina inmediatamente de la lista.
  * **Navegación de retorno:** En la barra superior (TopAppBar), presione la flecha de regreso para volver a la pantalla de inicio de sesión.
* **Estado vacío:**
  * Si se eliminan todas las tareas del listado, la pantalla mostrará automáticamente el mensaje:  
    `"Oops, te quedaste sin tareas"`.

---

## 4. Instrucciones de Instalación y Ejecución

1. Clonar el repositorio:
   ```bash
   git clone [https://github.com/J0seAntonio/Android-Actividad-2.git](https://github.com/J0seAntonio/Android-Actividad-2.git)
