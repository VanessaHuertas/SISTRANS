-- Generado por Oracle SQL Developer Data Modeler 4.1.5.907
--   en:        2017-04-12 07:18:50 COT
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g


CREATE TABLE BANCOS
  (
    idBanco                NUMBER NOT NULL ,
    cuenta                 NUMBER NOT NULL ,
    saldoCuenta            NUMBER NOT NULL ,
    FESTIVANDES_idFestival NUMBER NOT NULL
  ) ;
COMMENT ON TABLE BANCOS
IS
  'Almacena los datos de la entidad financiera de FestivAndes.' ;
  COMMENT ON COLUMN BANCOS.idBanco
IS
  'Identificador del banco.' ;
  COMMENT ON COLUMN BANCOS.cuenta
IS
  'Número de la cuenta' ;
  COMMENT ON COLUMN BANCOS.saldoCuenta
IS
  'Saldo de la cuenta.' ;
CREATE UNIQUE INDEX BANCOS__IDX ON BANCOS
  (
    FESTIVANDES_idFestival ASC
  )
  ;
ALTER TABLE BANCOS ADD CONSTRAINT BANCOS_PK PRIMARY KEY ( idBanco, FESTIVANDES_idFestival ) ;


CREATE TABLE CATEGORIAS
  (
    idCategoria         NUMBER NOT NULL ,
    descripcion         VARCHAR2 (10) NOT NULL ,
    ESPECTACULOS_nombre VARCHAR2 (40) NOT NULL
  ) ;
COMMENT ON COLUMN CATEGORIAS.idCategoria
IS
  'Identificador de la categoria.' ;
  COMMENT ON COLUMN CATEGORIAS.descripcion
IS
  'Especificación sobre cuál es la categoría a la que pertenece el espectáculo, tales como: Drama, Teatro mudo, Títeres, Comparsas, Ópera, Musical, Circo, Zarzuela, Otra. ' ;
ALTER TABLE CATEGORIAS ADD CONSTRAINT CATEGORIAS_PK PRIMARY KEY ( idCategoria ) ;


CREATE TABLE COMPANHIAS
  (
    nombre             VARCHAR2 (30) NOT NULL ,
    nombreRep          VARCHAR2 (35) NOT NULL ,
    paisOrigen         VARCHAR2 (20) NOT NULL ,
    paginaWeb          CHAR (1) NOT NULL ,
    fechaSalida        DATE NOT NULL ,
    fechaLlegada       DATE NOT NULL ,
    USUARIOS_idUsuario NUMBER NOT NULL
  ) ;
COMMENT ON TABLE COMPANHIAS
IS
  'Almacena la información de las compañías de teatros que se encargan de presentar los espectáculos en ciertas funciones acordadas.' ;
  COMMENT ON COLUMN COMPANHIAS.nombre
IS
  'Nombre de la compañia' ;
  COMMENT ON COLUMN COMPANHIAS.nombreRep
IS
  'Nombre del representante de la compañia' ;
  COMMENT ON COLUMN COMPANHIAS.paisOrigen
IS
  'País de origen de la compañía.' ;
  COMMENT ON COLUMN COMPANHIAS.paginaWeb
IS
  'Informa si una compañia tiene pagina web (1), si no la tiene (0).' ;
  COMMENT ON COLUMN COMPANHIAS.fechaSalida
IS
  'Fecha en la que la compañia se va del lugar en el que va a realizar el espectaculo.' ;
  COMMENT ON COLUMN COMPANHIAS.fechaLlegada
IS
  'Fecha en la que la compañia llega al lugar en el que va a realizar el espectaculo.' ;
ALTER TABLE COMPANHIAS ADD CONSTRAINT COMPANHIAS_PK PRIMARY KEY ( nombre ) ;


CREATE TABLE COMPANHIAS_ESPECTACULOS
  (
    COMPANHIAS_nombre   VARCHAR2 (30) NOT NULL ,
    ESPECTACULOS_nombre VARCHAR2 (40) NOT NULL
  ) ;


CREATE TABLE ESPECTACULOS
  (
    nombre         VARCHAR2 (40) NOT NULL ,
    duracion       NUMBER NOT NULL ,
    idioma         CHAR (1) NOT NULL ,
    costo          CHAR (1) NOT NULL ,
    descripcion    VARCHAR2 (30) NOT NULL ,
    requerimientos VARCHAR2 (100) NOT NULL
  ) ;
COMMENT ON COLUMN ESPECTACULOS.nombre
IS
  'Nombre del espectaculo.' ;
  COMMENT ON COLUMN ESPECTACULOS.duracion
IS
  'Duració del espectaculo.' ;
  COMMENT ON COLUMN ESPECTACULOS.idioma
IS
  '1 si es necesario prestar un servicio de traducción personalizado, ya sea mediante subtitulos o audifonos, 0 de lo contrario.' ;
  COMMENT ON COLUMN ESPECTACULOS.costo
IS
  '1 si el costo de realización  es obligatorio, 0 de lo contrario.' ;
  COMMENT ON COLUMN ESPECTACULOS.descripcion
IS
  'Breve descripción del espectaculo.' ;
  COMMENT ON COLUMN ESPECTACULOS.requerimientos
IS
  'Son las condiciones necesarias para que el espectáculo se lleve a cabo, tales pueden ser: Tarima, Vestuario, Implementos varios y en general cosas que necesiten los actores.' ;
ALTER TABLE ESPECTACULOS ADD CONSTRAINT ESPECTACULOS_PK PRIMARY KEY ( nombre ) ;


CREATE TABLE FESTIVANDES
  (
    idFestival NUMBER NOT NULL ,
    dia        VARCHAR2 (10 BYTE) NOT NULL ,
    horaInicio VARCHAR2 (7) NOT NULL ,
    horaFin    VARCHAR2 (7) NOT NULL
  ) ;
COMMENT ON TABLE FESTIVANDES
IS
  'Almacena la información relacionada con los festivales programados.' ;
  COMMENT ON COLUMN FESTIVANDES.idFestival
IS
  'Identificador del festival.' ;
  COMMENT ON COLUMN FESTIVANDES.dia
IS
  'Día en el que se realiza el festival.' ;
  COMMENT ON COLUMN FESTIVANDES.horaInicio
IS
  'Hora en la que inicia el festival.' ;
  COMMENT ON COLUMN FESTIVANDES.horaFin
IS
  'Hora en la que termina el festival' ;
ALTER TABLE FESTIVANDES ADD CONSTRAINT FESTIVANDES_PK PRIMARY KEY ( idFestival ) ;


CREATE TABLE FUNCIONES
  (
    idFuncion           NUMBER NOT NULL ,
    fechaR              DATE NOT NULL ,
    realizada           CHAR (1) NOT NULL ,
    ESPECTACULOS_nombre VARCHAR2 (40) NOT NULL ,
    SITIOS_idSitio      NUMBER NOT NULL
  ) ;
COMMENT ON TABLE FUNCIONES
IS
  'Almacena la información de las funciones en las que se van a presentar los espectáculos.' ;
  COMMENT ON COLUMN FUNCIONES.idFuncion
IS
  'Identificador de la función.' ;
  COMMENT ON COLUMN FUNCIONES.fechaR
IS
  'Fecha en la que se realiza la función.' ;
  COMMENT ON COLUMN FUNCIONES.realizada
IS
  'Indica si la función fue realizada o no.' ;
ALTER TABLE FUNCIONES ADD CONSTRAINT FUNCIONES_PK PRIMARY KEY ( idFuncion ) ;


CREATE TABLE LOCALIDADES
  (
    idLocalidad NUMBER NOT NULL ,
    descripcion VARCHAR2 (50) NOT NULL ,
    numerada    CHAR (1) NOT NULL
  ) ;
COMMENT ON TABLE LOCALIDADES
IS
  'Almacena la información de las localidades disponibles en el sitio en el que se va a realizar el festival.' ;
  COMMENT ON COLUMN LOCALIDADES.idLocalidad
IS
  'Identificador de la localidad.' ;
  COMMENT ON COLUMN LOCALIDADES.descripcion
IS
  'Breve descripción sobre el tipo de localidad, aquí se especifica si es VIP, Platea, Platea lateral, Intermedia, Intermedia lateral, General, etc...' ;
  COMMENT ON COLUMN LOCALIDADES.numerada
IS
  '1 si la localidad se encuentra numerada, 0 de lo contrario.' ;
ALTER TABLE LOCALIDADES ADD CONSTRAINT LOCALIDADES_PK PRIMARY KEY ( idLocalidad ) ;


CREATE TABLE PREFERENCIASCLIENTES
  (
    idPreferencia      NUMBER NOT NULL ,
    tipo               VARCHAR2 (10) NOT NULL ,
    valor              VARCHAR2 (11) NOT NULL ,
    USUARIOS_idUsuario NUMBER NOT NULL
  ) ;
COMMENT ON TABLE PREFERENCIASCLIENTES
IS
  'Almacena la información de las preferencias que tienen los clientes sobre un espectaculo, función, etc...' ;
  COMMENT ON COLUMN PREFERENCIASCLIENTES.idPreferencia
IS
  'Identificador de una preferencia de un cliente.' ;
  COMMENT ON COLUMN PREFERENCIASCLIENTES.tipo
IS
  'Tipo de una preferencia de un cliente.' ;
  COMMENT ON COLUMN PREFERENCIASCLIENTES.valor
IS
  'Valor de la preferencia del cliente.' ;
ALTER TABLE PREFERENCIASCLIENTES ADD CONSTRAINT PREFERENCIASCLIENTES_PK PRIMARY KEY ( idPreferencia ) ;


CREATE TABLE ROLES
  (
    idRol       NUMBER NOT NULL ,
    descripcion VARCHAR2 (15) NOT NULL
  ) ;
COMMENT ON TABLE ROLES
IS
  'Almacena la información de los roles que los usuarios pueden tener.' ;
  COMMENT ON COLUMN ROLES.idRol
IS
  'Identificador del rol de un usuario.' ;
  COMMENT ON COLUMN ROLES.descripcion
IS
  'Nombre del rol del usuario' ;
ALTER TABLE ROLES ADD CONSTRAINT ROLES_PK PRIMARY KEY ( idRol ) ;

CREATE TABLE SILLAS
  (
    idSilla                 NUMBER NOT NULL ,
    precio                  NUMBER NOT NULL ,
    disponible              CHAR (1) NOT NULL ,
    fechaR                  DATE NOT NULL ,
    devolucion              CHAR (1) NOT NULL ,
    asistio              CHAR (1) NOT NULL ,
    FUNCIONES_idFuncion     NUMBER NOT NULL ,
    USUARIOS_idUsuario      NUMBER NOT NULL ,
    LOCALIDADES_idLocalidad NUMBER NOT NULL
  ) ;
COMMENT ON TABLE SILLAS
IS
  'Almacena la información sobre las boletas vendidas o reservadas para el festival.' ;
  COMMENT ON COLUMN SILLAS.idSilla
IS
  'Identificador de la silla (boleta).' ;
  COMMENT ON COLUMN SILLAS.precio
IS
  'Precio de la boleta.' ;
  COMMENT ON COLUMN SILLAS.disponible
IS
  '1 si la silla se encuentra disponible, 0 en caso de que ya haya sido elegida por otro cliente.' ;
  COMMENT ON COLUMN SILLAS.fechaR
IS
  'Fecha en la que se realiza la compra o la reserva.' ;
  COMMENT ON COLUMN SILLAS.devolucion
IS
  '1 si se va a hacer devolución de la boleta, 0 en caso de que se encuentre disponible o haya sido comprada por un cliente.' ;
    COMMENT ON COLUMN SILLAS.asistio
IS
  '1 si el cliente asistiova a la función, 0 en caso de que se encuentre disponible o haya sido comprada por un cliente.' ;
ALTER TABLE SILLAS ADD CONSTRAINT SILLAS_PK PRIMARY KEY ( idSilla ) ;


CREATE TABLE SITIOS
  (
    idSitio                NUMBER NOT NULL ,
    nombreS                VARCHAR2 (35) NOT NULL ,
    capacidad              NUMBER NOT NULL ,
    esAbierto              CHAR (1) NOT NULL ,
    apto                   CHAR (1) NOT NULL ,
    horaInicio             VARCHAR2 (7) NOT NULL ,
    horaFin                VARCHAR2 (7) NOT NULL ,
    condiciones            VARCHAR2 (50) NOT NULL ,
    proteccionClima        CHAR (1) NOT NULL ,
    TIPOSSILLETERIA_idTipo NUMBER NOT NULL
  ) ;
COMMENT ON TABLE SITIOS
IS
  'Almacena la información del llugar en el que se se va a realizar el festival.' ;
  COMMENT ON COLUMN SITIOS.idSitio
IS
  'Identificador del sitio' ;
  COMMENT ON COLUMN SITIOS.nombreS
IS
  'Nombre del sitio' ;
  COMMENT ON COLUMN SITIOS.capacidad
IS
  'Capacidad del sitio' ;
  COMMENT ON COLUMN SITIOS.esAbierto
IS
  '1 si el sitio en el que se va a realizar el festival es abierto, 0 de lo contrario.' ;
  COMMENT ON COLUMN SITIOS.apto
IS
  '1 si el sitio es apto para realizar el festival, 0 de lo contrario.' ;
  COMMENT ON COLUMN SITIOS.horaInicio
IS
  'Hora en la que comienza el festival.' ;
  COMMENT ON COLUMN SITIOS.horaFin
IS
  'Hora en la que termina el festival.' ;
  COMMENT ON COLUMN SITIOS.condiciones
IS
  'Condiciones necesarias con las que debe cumplir el sitio para que se lleve a cabo el festival, tales son: Amplificación de sonido, Efectos 3D, Efectos 4D, Efectos de luz, Fuegos artificiales, Efectos en alturas, etc...' ;
  COMMENT ON COLUMN SITIOS.proteccionClima
IS
  '1 si el sitio se encuentra protegido ante situaciones graves de lluvia o atmosféricas, 0 de lo contrario' ;
ALTER TABLE SITIOS ADD CONSTRAINT SITIOS_PK PRIMARY KEY ( idSitio ) ;


CREATE TABLE TIPOSSILLETERIA
  (
    idTipo      NUMBER NOT NULL ,
    descripcion VARCHAR2 (15) NOT NULL
  ) ;
COMMENT ON TABLE TIPOSSILLETERIA
IS
  'Almacena la información que permite tipificar la silletería que va a estar en el sitio.' ;
  COMMENT ON COLUMN TIPOSSILLETERIA.idTipo
IS
  'Identificador del tipo de silleteria.' ;
  COMMENT ON COLUMN TIPOSSILLETERIA.descripcion
IS
  'Breve descripción sobre el tipo de silletería del sitio, aqui se especifica si es Móvil, Fija o Removible.' ;
ALTER TABLE TIPOSSILLETERIA ADD CONSTRAINT TIPOSSILLETERIA_PK PRIMARY KEY ( idTipo ) ;


CREATE TABLE USUARIOS
  (
    idUsuario              NUMBER NOT NULL ,
    nombre                 VARCHAR2 (35) NOT NULL ,
    email                  VARCHAR2 (35) NOT NULL ,
    FESTIVANDES_idFestival NUMBER NOT NULL ,
    ROLES_idRol            NUMBER NOT NULL
  ) ;
COMMENT ON TABLE USUARIOS
IS
  'Almacena la información de los usuarios diferenciados por el rol que desempeñan en el festival.' ;
  COMMENT ON COLUMN USUARIOS.idUsuario
IS
  'Identificador del usuario' ;
  COMMENT ON COLUMN USUARIOS.nombre
IS
  'Nombre del usuario' ;
  COMMENT ON COLUMN USUARIOS.email
IS
  'Correo electrónico del usuario' ;
ALTER TABLE USUARIOS ADD CONSTRAINT USUARIOS_PK PRIMARY KEY ( idUsuario ) ;


ALTER TABLE BANCOS ADD CONSTRAINT BANCOS_FESTIVANDES_FK FOREIGN KEY ( FESTIVANDES_idFestival ) REFERENCES FESTIVANDES ( idFestival ) ;

ALTER TABLE CATEGORIAS ADD CONSTRAINT CATEGORIAS_ESPECTACULOS_FK FOREIGN KEY ( ESPECTACULOS_nombre ) REFERENCES ESPECTACULOS ( nombre ) ;

ALTER TABLE COMPANHIAS_ESPECTACULOS ADD CONSTRAINT CMPNS_ESPEC_CMPNS_FK FOREIGN KEY ( COMPANHIAS_nombre ) REFERENCES COMPANHIAS ( nombre ) ;

ALTER TABLE COMPANHIAS_ESPECTACULOS ADD CONSTRAINT CMPNS_ESPEC_ESPEC_FK FOREIGN KEY ( ESPECTACULOS_nombre ) REFERENCES ESPECTACULOS ( nombre ) ;

ALTER TABLE COMPANHIAS ADD CONSTRAINT COMPANHIAS_USUARIOS_FK FOREIGN KEY ( USUARIOS_idUsuario ) REFERENCES USUARIOS ( idUsuario ) ;

ALTER TABLE FUNCIONES ADD CONSTRAINT FUNCIONES_ESPECTACULOS_FK FOREIGN KEY ( ESPECTACULOS_nombre ) REFERENCES ESPECTACULOS ( nombre ) ;

ALTER TABLE FUNCIONES ADD CONSTRAINT FUNCIONES_SITIOS_FK FOREIGN KEY ( SITIOS_idSitio ) REFERENCES SITIOS ( idSitio ) ;

ALTER TABLE PREFERENCIASCLIENTES ADD CONSTRAINT PRFRNCSCLNTS_USUS_FK FOREIGN KEY ( USUARIOS_idUsuario ) REFERENCES USUARIOS ( idUsuario ) ;

ALTER TABLE SILLAS ADD CONSTRAINT SILLAS_FUNCIONES_FK FOREIGN KEY ( FUNCIONES_idFuncion ) REFERENCES FUNCIONES ( idFuncion ) ;

ALTER TABLE SILLAS ADD CONSTRAINT SILLAS_LOCALIDADES_FK FOREIGN KEY ( LOCALIDADES_idLocalidad ) REFERENCES LOCALIDADES ( idLocalidad ) ;

ALTER TABLE SILLAS ADD CONSTRAINT SILLAS_USUARIOS_FK FOREIGN KEY ( USUARIOS_idUsuario ) REFERENCES USUARIOS ( idUsuario ) ;

ALTER TABLE SITIOS ADD CONSTRAINT SITIOS_TIPOSSILLETERIA_FK FOREIGN KEY ( TIPOSSILLETERIA_idTipo ) REFERENCES TIPOSSILLETERIA ( idTipo ) ;

ALTER TABLE USUARIOS ADD CONSTRAINT USUARIOS_FESTIVANDES_FK FOREIGN KEY ( FESTIVANDES_idFestival ) REFERENCES FESTIVANDES ( idFestival ) ;

ALTER TABLE USUARIOS ADD CONSTRAINT USUARIOS_ROLES_FK FOREIGN KEY ( ROLES_idRol ) REFERENCES ROLES ( idRol ) ;


-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            14
-- CREATE INDEX                             1
-- ALTER TABLE                             27
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
