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

# Caracteristicas Principales

### Modulos Funcionales
- **Gestion de Empresas**: Registro de empresas, publicacion de proyectos y correciones mediante comentarios del coordinador.
- **Coordinador de Programa**: Revision, seleccion y asignacion de proyectos a estudiantes , Dashboard de estadisticas y  gestion de proyectos.
- **Estudiantes**: Postulacion a proyectos disponibles.

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

## Instrucciones
Para ejecutar el proyecto de manera exitosa, sigue estos pasos: (1) construye las imágenes con el comando docker-compose build; (2) levanta los contenedores en segundo plano usando docker-compose up -d; y (3) si es posible, vuelve a ejecutar docker-compose up -d para asegurarte de que los servicios estén corriendo correctamente. Asegúrate de que los puertos necesarios estén disponibles y, si deseas detener y eliminar los contenedores, puedes usar docker-compose down.
