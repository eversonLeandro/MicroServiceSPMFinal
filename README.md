### Problematica Actual
En la actualidad, la vinculacion entre la academia y la industria es
fundamental para garantizar una formacion profesional alineada con las
necesidades del sector productivo. Sin embargo, muchas universidades
enfrentan desafios en la identificacion, gestion y asignacion de proyectos
empresariales a los estudiantes, limitando su acceso a experiencias practicas
reales antes de su graduacion.

El Sistema de Gestion de Proyectos Academicos para Empresas busca resolver
esta problematica mediante una plataforma digital que permite a las empresas
publicar sus necesidades de software, las cuales seran evaluadas y
asignadas por el coordinador del programa de ingenieria de sistemas a
estudiantes de semestres avanzados. De este modo, los proyectos seleccionados
seran desarrollados como parte de sus practicas profesionales o asignaturas
especializadas, promoviendo el aprendizaje basado en proyectos y fortaleciendo
la relacion universidad-empresa.

Ademas, el sistema integrara herramientas innovadoras como inteligencia artificial
para la recomendacion de proyectos y un modulo de networking tipo LinkedIn, facilitando
la interaccion entre estudiantes, docentes y empresas. De esta manera, no solo se
optimiza el proceso de asignacion de proyectos, sino que tambien se fomenta la visibilidad
del talento estudiantil en el mercado laboral.

Este proyecto representa una oportunidad para modernizar y agilizar la gestion de
practicas profesionales, garantizando que los estudiantes enfrenten desafios reales
y adquieran experiencia en el desarrollo de soluciones tecnologicas con impacto en
el sector empresarial.

Por lo anterior, el programa de Ingenieria de Sistemas de la Universidad del Cauca
necesita desarrollar una plataforma web que permitira a las empresas publicar necesidades
de software, las cuales seran evaluadas por el Coordinador de Programa. Los proyectos
seleccionados seran asignados a estudiantes de ultimos semestres, quienes los desarrollaran
como parte de sus practicas profesionales, pasantias o proyectos academicos.

# **Requisitos Funcionales Detallados**

## **Modulo de Gestion de Empresas**

Permite a las empresas registrarse, publicar necesidades de software y gestionar sus solicitudes.

### **1. Registro de Empresas**

* **Descripcion**: Las empresas pueden crear una cuenta en el sistema.
* **Criterios de Aceptacion**:

  * Formulario con campos obligatorios: nombre, NIT, sector, correo electronico, contrasena.
  * Validacion de correo electronico unico.

### **2. Publicacion de Necesidades de Software**

* **Descripcion**: Las empresas pueden describir problemas/proyectos que requieren solucion.
* **Criterios de Aceptacion**:

  * Formulario con campos: titulo, descripcion, tecnologias requeridas, plazo estimado.
  * Estado inicial del proyecto: "Pendiente de revision".

### **3. Gestion de Solicitudes**

* **Descripcion**: Las empresas pueden ver el estado de sus proyectos publicados.
* **Criterios de Aceptacion**:

  * Estados posibles: *Pendiente*, *Aprobado*, *Rechazado*, *Asignado*, *Finalizado*.
  * Notificaciones por correo electronico al cambiar de estado.

### **4. Comunicacion con el Coordinador**

* **Descripcion**: Sistema de mensajeria interna para ver las correcciones sobre los proyectos.
* **Criterios de Aceptacion**:

  * Chat en tiempo real o mensajeria asincrona.
  * Historial de conversaciones por proyecto.

---

## **Modulo del Coordinador del Programa**

Permite al coordinador revisar, aprobar y asignar proyectos.

### **5. Revision y Seleccion de Proyectos**

* **Descripcion**: El coordinador evalua las solicitudes de empresas.
* **Criterios de Aceptacion**:
.
  * Opcion de cambiar el estado del proyecto a : *Pendiente*, *Aprobado*, *Rechazado*, *Asignado*, *Finalizado*.
  * Posibilidad de solicitar ajustes a la empresa mediante los comentarios.
  * Opcion de asignar un equipo para desarrollar el proyecto

### **6. Dashboard de Estadisticas**

* **Descripcion**: Visualizacion de metricas clave.
* **Criterios de Aceptacion**:

  * Graficos de proyectos por estado, sector y tiempo de respuesta.
  * Exportacion de datos (Excel, PDF).

### **7. Gestion de Periodos Academicos**

* **Descripcion**: Configuracion de fechas limite para recepcion de proyectos.
* **Criterios de Aceptacion**:

  * Calendario interactivo con periodos activos/inactivos.
  * Recordatorios automaticos a empresas y estudiantes.

---

## **Modulo de Estudiantes**

Permite a los estudiantes postularse a proyectos y gestionar su participacion.

### **8. Postulacion a Proyectos**

* **Descripcion**: Los estudiantes pueden ver proyectos disponibles y aplicar.
* **Criterios de Aceptacion**:

  * Opcion de postularse a los proyectos listados en la pestaña de Proyectos.

---

## Tecnologías utilizadas

| Area             | Tecnologias                                     |
|------------------|-------------------------------------------------|
| **Arquitectura** | Microservicios + Arquitectura Hexagonal (DDD)   |
| **Comunicacion** | REST (sincronico) + RabbitMQ (asincronico)      |
| **Frontend**     | Java With Maven + Java Swing + Material UI      |
| **Backend**      | Spring Boot + Java With Maven                   |
| **BD**           | PostgreSQL                                      |
| **DevOps**       | Docker                                          |

## Caracteristicas Clave

- **Arquitectura escalable**: Modelo hibrido sincronico/asincronico
- **DDD aplicado**: Microservicio de Projects con arquitectura hexagonal
- **Patrones avanzados**: State, Observer, Strategy, Factory , Proxy, Adapter
- **Escalabilidad**: Diseñado para crecimiento nacional

### Patrones de Diseño Implementados
1. **State**: Gestion de estados de proyectos (ej: "En revision", "Aprobado").
2. **Observer**: Notificaciones automaticas a usuarios ante cambios relevantes.
3. **Proxy**: Control de acceso al servicio de autenticacion.
4. **Adapter**: Conexion con APIs externas sin afectar el nucleo del sistema.
5. **Factory**: Centralizacion de la creacion de repositorios.
6. **Strategy**: Validacion dinamica de datos.

## Documentacion Tecnica

- [Decisiones de Arquitectura](https://docs.google.com/document/d/1R4yLteDbi5kCjZ0_aYPhGcBWfqUeNgPaAy6oHvkTNcw/edit?usp=sharing)
- [Diagramas del Sistema](https://drive.google.com/drive/folders/1Q4XbRvnvD693VKftSgEncQ41jGtSvG4P?usp=sharing)

## Equipo

Equipo de Ingenieria de Software de la Universidad del Cauca:
- [Juan Esteban Mera Morales]()
- [Jesus Alberto Zufiiga Daza]()
- [Everson Leandro Restrepo Gaviria]()
- [Brayan Hernan Meneses]()

*Asesorado por: Wilson Libardo Pantoja Yepez*
