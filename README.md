
**Curso:** IF0009 - Desarrollo de Software IV  
**Ciclo:** II-2026  
**Laboratorio:** 6 - ExpresoFast Parte II: Seguridad, DTOs y Bitácora  
**Estudiante:** [Nombre Completo]  
**Carnet:** C5J263  

---

## Descripción

Segunda parte de la plataforma logística ExpresoFast. Implementa seguridad basada en JWT, control de acceso por roles (RBAC), capa de DTOs con validaciones OWASP, bitácora de auditoría transaccional y manejo centralizado de excepciones.

---

## Requisitos de Entorno

| Herramienta | Versión |
|---|---|
| Java | 21 |
| Maven | 3.9+ |
| Microsoft SQL Server | Developer Edition 2019/2022 |
| Navegador | Chrome / Edge (con DevTools) |
| IDE | VS Code / IntelliJ IDEA |

---

## Estructura del Repositorio

```
expresofast-lab6-c5j263/
├── backend/          → Proyecto Spring Boot (expresofast/)
│   ├── src/
│   └── pom.xml
├── database/
│   ├── 01_schema_lab5.sql           → Tablas base (Lab 5)
│   ├── 02_schema_lab6_extension.sql → Tablas nuevas (Lab 6)
│   └── 03_data_seeds.sql            → Datos de prueba con usuarios
├── frontend/
│   ├── index.html    → Dashboard principal
│   ├── login.html    → Pantalla de inicio de sesión
│   ├── styles.css    → Estilos
│   └── app.js        → Lógica JS con JWT
└── README.md
```

