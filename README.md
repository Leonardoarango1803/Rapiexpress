# RapiExpress

RapiExpress es una plataforma innovadora diseñada para ofrecer servicios de envío nacionales e internacionales, garantizando rapidez, eficiencia y calidad. Este proyecto combina una API REST robusta con un frontend desarrollado en Angular y Bootstrap, y un backend construido con Spring Boot y MySQL.

## Tabla de Contenidos
1. [Requisitos del Sistema](#requisitos-del-sistema)
2. [Tecnologías Utilizadas](#tecnologías-utilizadas)
3. [Instalación](#instalación)
    - [Frontend](#frontend)
    - [Backend](#backend)
4. [Descripción del Proyecto](#descripción-del-proyecto)
5. [Uso](#uso)
6. [Contribución](#contribución)
7. [Licencia](#licencia)

## Requisitos del Sistema
### General
- Sistema Operativo: Windows, MacOS o Linux.
- Node.js: >= 20.18.0
- Java: >= 17
- MySQL: >= 8.0

### Herramientas necesarias:
- Angular CLI: 18.2.9
- Maven: >= 3.8
- IDE recomendado: IntelliJ IDEA para el backend, Visual Studio Code para el frontend.

## Tecnologías Utilizadas
- **Frontend**
  - Angular 18.2.8
  - Bootstrap
  - Leaflet (mapas interactivos)
  - jsPDF (generación de documentos PDF)
- **Backend**
  - Spring Boot 3.3.5
  - MySQL Connector
  - JSON Web Tokens (JWT) para autenticación
  - Lombok

## Instalación
### Frontend
1. Asegúrese de tener instalado [Node.js](https://nodejs.org/) y Angular CLI.
2. Clone el repositorio del frontend.
   ```bash
   git clone <url-del-repositorio>
   cd Front-end
   ```
3. Instale las dependencias del proyecto.
   ```bash
   npm install
   ```
4. Ejecute el servidor de desarrollo.
   ```bash
   ng serve
   ```
   El servidor estará disponible en [http://localhost:4200/](http://localhost:4200/).

### Backend
1. Asegúrese de tener instalado Java 17 y Maven.
2. Clone el repositorio del backend.
   ```bash
   git clone <url-del-repositorio>
   cd Back-end
   ```
3. Configure el archivo `application.properties` en `src/main/resources` con las credenciales de su base de datos MySQL:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/rapiexpress
   spring.datasource.username=<usuario>
   spring.datasource.password=<contraseña>
   spring.jpa.hibernate.ddl-auto=update
   ```
4. Compile y ejecute el proyecto.
   ```bash
   mvn spring-boot:run
   ```
   El servidor estará disponible en [http://localhost:8080/](http://localhost:8080/).

## Descripción del Proyecto
RapiExpress permite a los usuarios realizar las siguientes acciones:
- Gestión de clientes: creación, edición, y eliminación.
- Gestión de pedidos: registro y seguimiento en tiempo real.
- Asignación de vehículos y optimización de rutas.
- Generación de reportes en formato PDF.

El sistema aborda desafíos como:
- Organización de rutas óptimas.
- Asignación eficiente de vehículos.
- Gestión de incidencias como retrasos o daños.

## Uso
- Acceda a la interfaz web desde [http://localhost:4200/](http://localhost:4200/).
- Cree un usuario, inicie sesión y gestione envíos.
- Siga el estado de sus pedidos mediante el sistema de rastreo en tiempo real.

## Contribución
Contribuciones son bienvenidas. Por favor, siga los siguientes pasos:
1. Realice un fork del repositorio.
2. Cree una nueva rama para sus cambios.
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
3. Realice un pull request con la descripción detallada de sus cambios.

## Licencia
Este proyecto está licenciado bajo la Licencia MIT.
