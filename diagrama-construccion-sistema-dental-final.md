# 🏥 Diagrama de Construcción del Sistema Dental en Java

## 📋 Resumen del Sistema
**Sistema de Gestión de Clínica Dental** - Aplicación web desarrollada con Spring Boot 3.4.2, Java 21, MySQL y arquitectura MVC.

---

## 🏗️ Arquitectura del Sistema

```
┌─────────────────────────────────────────────────────────────┐
│                    PRESENTATION LAYER                       │
├─────────────────────────────────────────────────────────────┤
│  📱 Frontend (Thymeleaf + Bootstrap + jQuery)              │
│  ├── Templates HTML                                         │
│  ├── JavaScript (appointment.js, etc.)                     │
│  └── CSS/Static Resources                                   │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                     CONTROLLER LAYER                        │
├─────────────────────────────────────────────────────────────┤
│  🎮 Controllers                                            │
│  ├── Web Controllers (MVC)                                 │
│  │   ├── IndexController                                   │
│  │   ├── DashboardController                               │
│  │   └── [Otros Controllers Web]                           │
│  └── API Controllers (REST)                                │
│      ├── AppointmentController                             │
│      ├── PatientController                                 │
│      ├── DoctorController                                  │
│      ├── MedicalRecordController                           │
│      └── AuthController                                    │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                      SERVICE LAYER                          │
├─────────────────────────────────────────────────────────────┤
│  ⚙️ Business Logic                                         │
│  ├── AppointmentService                                    │
│  ├── PatientService                                        │
│  ├── DoctorService                                         │
│  ├── MedicalRecordService                                  │
│  └── AuthService                                           │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                     REPOSITORY LAYER                        │
├─────────────────────────────────────────────────────────────┤
│  🗄️ Data Access Layer                                      │
│  ├── IAppointmentRepository                                │
│  ├── IPatientRepository                                    │
│  ├── IDoctorRepository                                     │
│  ├── IMedicalRecordRepository                              │
│  └── IUserRepository                                       │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                      DATA LAYER                             │
├─────────────────────────────────────────────────────────────┤
│  💾 MySQL Database                                         │
│  ├── patient table                                         │
│  ├── doctor table                                          │
│  ├── appointment table                                      │
│  ├── medical_record table                                  │
│  └── user table                                            │
└─────────────────────────────────────────────────────────────┘
```

---

## 🔄 Flujo de Construcción Paso a Paso

### **FASE 1: Configuración Inicial** 🚀

```mermaid
flowchart TD
    A[Crear proyecto Spring Boot] --> B[Configurar pom.xml]
    B --> C[Definir dependencias]
    C --> D[Configurar application.properties]
    D --> E[Crear clase principal DentalClinicApplication]
    
    C --> C1[Spring Boot Starter Web]
    C --> C2[Spring Boot Starter Data JPA]
    C --> C3[Spring Boot Starter Security]
    C --> C4[Spring Boot Starter Thymeleaf]
    C --> C5[MySQL Connector]
    C --> C6[Lombok]
    C --> C7[SpringDoc OpenAPI]
```

### **FASE 2: Configuración de Base de Datos** 🗄️

```mermaid
flowchart TD
    A[Configurar MySQL] --> B[Crear base de datos dentalclinic]
    B --> C[Ejecutar script-db.sql]
    C --> D[Configurar conexión en application.properties]
    D --> E[Configurar Hibernate DDL auto=update]
    
    C --> C1[Crear tablas: patient, doctor, appointment, medical_record, user]
    C --> C2[Insertar datos de prueba]
```

### **FASE 3: Creación de Modelos (Entidades)** 📊

```mermaid
flowchart TD
    A[Crear entidades JPA] --> B[PatientModel]
    A --> C[DoctorModel]
    A --> D[AppointmentModel]
    A --> E[MedicalRecordModel]
    A --> F[UserModel]
    
    B --> B1[Entity Table Id GeneratedValue]
    B --> B2[Column Temporal JoinColumn]
    C --> C1[Entity Table Id GeneratedValue]
    D --> D1[Entity Table ManyToOne Enumerated]
    E --> E1[Entity Table Id GeneratedValue]
    F --> F1[Entity Table Id GeneratedValue]
```

### **FASE 4: Creación de Repositorios** 🔍

```mermaid
flowchart TD
    A[Crear interfaces Repository] --> B[IPatientRepository]
    A --> C[IDoctorRepository]
    A --> D[IAppointmentRepository]
    A --> E[IMedicalRecordRepository]
    A --> F[IUserRepository]
    
    B --> B1[extends JpaRepository]
    B --> B2[findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase]
    C --> C1[extends JpaRepository]
    D --> D1[extends JpaRepository]
    E --> E1[extends JpaRepository]
    F --> F1[extends JpaRepository]
    F --> F2[findByUsername]
```

### **FASE 5: Creación de Servicios** ⚙️

```mermaid
flowchart TD
    A[Crear clases Service] --> B[PatientService]
    A --> C[DoctorService]
    A --> D[AppointmentService]
    A --> E[MedicalRecordService]
    A --> F[AuthService]
    
    B --> B1[Service Autowired Repository]
    B --> B2[getAllPatient getPatientById savePatient searchPatients]
    C --> C1[Service Autowired Repository]
    C --> C2[getAllDoctor getDoctorById saveDoctor searchDoctor]
    D --> D1[Service Autowired Repository]
    D --> D2[getAllAppointments createAppointment updateAppointment deleteAppointment]
    E --> E1[Service Autowired Repository]
    F --> F1[Service Autowired Repository]
    F --> F2[login method]
```

### **FASE 6: Creación de Controladores** 🎮

```mermaid
flowchart TD
    A[Crear Controladores] --> B[Web Controllers]
    A --> C[API Controllers]
    
    B --> B1[IndexController]
    B --> B2[DashboardController]
    
    C --> C1[AppointmentController]
    C --> C2[PatientController]
    C --> C3[DoctorController]
    C --> C4[MedicalRecordController]
    C --> C5[AuthController]
    
    C1 --> C1A[RestController RequestMapping]
    C1 --> C1B[CRUD operations GET POST PUT DELETE]
    C2 --> C2A[RestController RequestMapping]
    C3 --> C3A[RestController RequestMapping]
    C4 --> C4A[RestController RequestMapping]
    C5 --> C5A[RestController RequestMapping]
    C5 --> C5B[PostMapping login]
```

### **FASE 7: Configuración de Seguridad** 🔒

```mermaid
flowchart TD
    A[Configurar Spring Security] --> B[SecurityConfig]
    B --> C[Configuration Bean SecurityFilterChain]
    C --> D[Configurar rutas protegidas]
    D --> E[api permitAll]
    D --> F[anyRequest authenticated]
    C --> G[Desactivar CSRF para pruebas]
    C --> H[Configurar formLogin y httpBasic]
```

### **FASE 8: Creación de Frontend** 🎨

```mermaid
flowchart TD
    A[Crear templates Thymeleaf] --> B[Layout principal]
    A --> C[Vistas específicas]
    
    C --> C1[dashboardView.html]
    C --> C2[patientView.html]
    C --> C3[appointmentView.html]
    C --> C4[doctorView.html]
    C --> C5[medicalrecordView.html]
    
    A --> D[Crear archivos JavaScript]
    D --> D1[appointment.js]
    D --> D2[patient.js]
    D --> D3[doctor.js]
    
    A --> E[Configurar Bootstrap y jQuery]
    E --> E1[DataTables]
    E --> E2[Select2]
    E --> E3[Modales Bootstrap]
```

### **FASE 9: Manejo de Excepciones** ⚠️

```mermaid
flowchart TD
    A[Crear sistema de excepciones] --> B[InvalidCredentialsException]
    A --> C[GlobalExceptionHandler]
    
    B --> B1[ResponseStatus UNAUTHORIZED]
    C --> C1[ControllerAdvice]
    C --> C2[ExceptionHandler]
    C --> C3[handleInvalidCredentialsException]
```

### **FASE 10: Utilidades y DTOs** 🛠️

```mermaid
flowchart TD
    A[Crear clases utilitarias] --> B[JsonResponseUtil]
    A --> C[DTOs]
    
    B --> B1[success method]
    B --> B2[error method]
    
    C --> C1[LoginRequest]
    C --> C2[Data annotation]
```

---

## 📦 Estructura Final del Proyecto

```
DentalClinic/
├── src/
│   ├── main/
│   │   ├── java/upn/pe/dentalClinic/
│   │   │   ├── DentalClinicApplication.java          # 🚀 Punto de entrada
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java               # 🔒 Configuración de seguridad
│   │   │   ├── controller/
│   │   │   │   ├── web/                              # 🎮 Controladores MVC
│   │   │   │   │   ├── IndexController.java
│   │   │   │   │   └── DashboardController.java
│   │   │   │   └── api/                              # 🌐 Controladores REST
│   │   │   │       ├── AppointmentController.java
│   │   │   │       ├── PatientController.java
│   │   │   │       ├── DoctorController.java
│   │   │   │       ├── MedicalRecordController.java
│   │   │   │       └── AuthController.java
│   │   │   ├── service/                              # ⚙️ Lógica de negocio
│   │   │   │   ├── AppointmentService.java
│   │   │   │   ├── PatientService.java
│   │   │   │   ├── DoctorService.java
│   │   │   │   ├── MedicalRecordService.java
│   │   │   │   └── AuthService.java
│   │   │   ├── repository/                           # 🗄️ Acceso a datos
│   │   │   │   ├── IAppointmentRepository.java
│   │   │   │   ├── IPatientRepository.java
│   │   │   │   ├── IDoctorRepository.java
│   │   │   │   ├── IMedicalRecordRepository.java
│   │   │   │   └── IUserRepository.java
│   │   │   ├── model/                                # 📊 Entidades JPA
│   │   │   │   ├── AppointmentModel.java
│   │   │   │   ├── PatientModel.java
│   │   │   │   ├── DoctorModel.java
│   │   │   │   ├── MedicalRecordModel.java
│   │   │   │   └── UserModel.java
│   │   │   ├── dto/                                  # 📦 Objetos de transferencia
│   │   │   │   └── LoginRequest.java
│   │   │   ├── exception/                            # ⚠️ Manejo de excepciones
│   │   │   │   ├── InvalidCredentialsException.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   └── common/                               # 🛠️ Utilidades
│   │   │       └── JsonResponseUtil.java
│   │   └── resources/
│   │       ├── application.properties                # ⚙️ Configuración
│   │       ├── templates/                            # 🎨 Vistas Thymeleaf
│   │       │   ├── dashboardView.html
│   │       │   ├── patientView.html
│   │       │   ├── appointmentView.html
│   │       │   ├── doctorView.html
│   │       │   └── medicalrecordView.html
│   │       └── static/                               # 📱 Recursos estáticos
│   │           ├── css/
│   │           ├── js/
│   │           │   ├── logic/
│   │           │   │   ├── appointment.js
│   │           │   │   ├── patient.js
│   │           │   │   └── doctor.js
│   │           │   └── common.js
│   │           └── images/
│   └── test/                                         # 🧪 Pruebas unitarias
├── pom.xml                                           # 📦 Dependencias Maven
├── script-db.sql                                     # 🗄️ Script de base de datos
└── docker-compose.yml                                # 🐳 Configuración Docker
```

---

## 🎯 Tecnologías Utilizadas

| **Capa** | **Tecnología** | **Versión** | **Propósito** |
|----------|----------------|-------------|---------------|
| **Framework** | Spring Boot | 3.4.2 | Framework principal |
| **Lenguaje** | Java | 21 | Lenguaje de programación |
| **Base de Datos** | MySQL | 8.0+ | Persistencia de datos |
| **ORM** | Hibernate/JPA | 6.x | Mapeo objeto-relacional |
| **Frontend** | Thymeleaf | 3.x | Motor de plantillas |
| **UI Framework** | Bootstrap | 5.2.0 | Framework CSS |
| **JavaScript** | jQuery | - | Manipulación DOM |
| **Seguridad** | Spring Security | 6.x | Autenticación y autorización |
| **Documentación** | SpringDoc OpenAPI | 2.2.0 | Documentación API |
| **Build Tool** | Maven | - | Gestión de dependencias |
| **Lombok** | Lombok | - | Reducción de código boilerplate |

---

## 🚀 Comandos de Ejecución

```bash
# 1. Compilar el proyecto
mvn clean compile

# 2. Ejecutar la aplicación
mvn spring-boot:run

# 3. Acceder a la aplicación
http://localhost:8680

# 4. Acceder a Swagger UI
http://localhost:8680/swagger-ui

# 5. Ejecutar pruebas
mvn test
```

---

## 📊 Métricas del Proyecto

- **Líneas de código**: ~2,000+ líneas
- **Clases Java**: 20+ clases
- **Endpoints API**: 15+ endpoints REST
- **Vistas**: 5+ templates Thymeleaf
- **Entidades**: 5 entidades JPA
- **Servicios**: 5 servicios de negocio
- **Repositorios**: 5 interfaces de repositorio

---

## 🔄 Flujo de Desarrollo Recomendado

1. **Configurar entorno** (Java 21, Maven, MySQL)
2. **Crear proyecto Spring Boot** con dependencias básicas
3. **Configurar base de datos** y conexión
4. **Crear entidades JPA** para el modelo de datos
5. **Implementar repositorios** para acceso a datos
6. **Desarrollar servicios** con lógica de negocio
7. **Crear controladores** REST y MVC
8. **Configurar seguridad** y autenticación
9. **Desarrollar frontend** con Thymeleaf y JavaScript
10. **Implementar manejo de excepciones**
11. **Agregar documentación** con Swagger
12. **Realizar pruebas** y optimizaciones
13. **Desplegar** y monitorear

---

*Este diagrama representa la construcción paso a paso del sistema de gestión de clínica dental, siguiendo las mejores prácticas de desarrollo con Spring Boot y arquitectura en capas.* 