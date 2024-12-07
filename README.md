# Fortnite API Backend

## Descripción General
Este proyecto es el backend de una aplicación diseñada para consultar datos y estadísticas del videojuego Fortnite. Ofrece funcionalidades avanzadas que interactúan con:
- **API Interna**: Creada con JSON Server y gestionada en un contenedor Docker.
- **API Externa creada por la comunidad de Fortnite**: Proporciona información en tiempo real sobre cosméticos, tienda y otros datos relevantes.

## Tecnologías Utilizadas
- **Java 17** (Azul Zulu 17.0.12)
- **Maven 3**
- **Spring Boot**
- **Resilience4j**: Circuit Breaker para mejorar la resiliencia ante fallos en la API externa.
- **Docker** y **Docker Compose**
- **JUnit 5** y **Mockito**: Pruebas unitarias e integración.

## Características del Proyecto
- **Interacción con APIs**:
  - **Interna**: Simula datos con un archivo `db.json` gestionado mediante JSON Server.
  - **Externa**: Consume datos de una API desarrollada por la comunidad de Fortnite para mantener la información actualizada.
- **Endpoints Principales**:
  - Consultar **cosméticos** con filtros por nombre, rareza, capítulo y cantidad de resultados.
  - Obtener la lista de **artículos de la tienda** con opciones de filtrado por precio.
- **Diseño Modular**:
  - Separación en capas (`clients`, `services`, `controllers`, etc.) para facilitar la escalabilidad y el mantenimiento.
- **Validaciones**:
  - Límite en la cantidad de resultados retornados.
  - Manejo de errores con respuestas claras al usuario.

## Requisitos Previos
1. Tener instalados:
   - **Docker**.
   - **Java 17**.
   - **Maven 3**.
