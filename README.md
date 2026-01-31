# 🏦 Banco API - Microservicio de Gestión Bancaria

[cite_start]Este proyecto es una solución técnica para la gestión de clientes, cuentas y movimientos financieros, desarrollada bajo estándares de nivel **Junior** con **Java 21**[cite: 14].

## 🛠️ Stack Tecnológico
* [cite_start]**Lenguaje:** Java 21[cite: 14].
* [cite_start]**Framework:** Spring Boot 3.x[cite: 14].
* [cite_start]**Base de Datos:** H2 (En memoria)[cite: 16, 90].
* [cite_start]**Persistencia:** Spring Data JPA / Hibernate[cite: 7].

## 📂 Estructura de la Capa de Modelo (Entidades)
[cite_start]Se han implementado las entidades base siguiendo el diseño relacional solicitado[cite: 28, 31, 35, 39]:

* [cite_start]**Persona & Cliente:** Implementación de herencia mediante la estrategia `JOINED`[cite: 32].
* [cite_start]**Cuenta:** Entidad con clave única `numeroCuenta` y relación con movimientos[cite: 35, 38].
* [cite_start]**Movimientos:** Registro detallado de transacciones con trazabilidad de saldo[cite: 39, 43].

## 📈 Estado del Proyecto
- [x] Configuración inicial del entorno y base de datos H2.
- [x] Definición del modelo de datos con JPA y relaciones bidireccionales.
- [x] Control de versiones con Git siguiendo convenciones profesionales.
- [ ] Implementación de Repositorios (En curso).
- [ ] Desarrollo de Lógica de Negocio (Servicios).
- [ ] Exposición de Endpoints REST (Controladores).