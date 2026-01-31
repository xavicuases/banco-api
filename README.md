# 🏦 Banco API - Microservicio de Gestión Bancaria

Este proyecto es una solución técnica para la gestión de clientes, cuentas y movimientos financieros, desarrollada bajo estándares de nivel **Junior** con **Java 21**.

---

## 🛠️ Stack Tecnológico
* **Lenguaje:** Java 21
* **Framework:** Spring Boot 3.x
* **Base de Datos:** H2 (En memoria)
* **Persistencia:** Spring Data JPA / Hibernate

---

## 📂 Estructura de la Capa de Modelo (Entidades)
Se han implementado las entidades base siguiendo el diseño relacional solicitado:

* **Persona & Cliente:** Implementación de herencia mediante la estrategia `JOINED`.
* **Cuenta:** Entidad con clave única `numeroCuenta` y relación con movimientos.
* **Movimientos:** Registro detallado de transacciones con trazabilidad de saldo.

---

## 📈 Estado del Proyecto
* [x] Configuración inicial del entorno y base de datos H2.
* [x] Definición del modelo de datos con JPA y relaciones bidireccionales.
* [x] Control de versiones con Git siguiendo convenciones profesionales.
* [ ] Implementación de Repositorios (En curso).
* [ ] Desarrollo de Lógica de Negocio (Servicios).
* [ ] Exposición de Endpoints REST (Controladores).