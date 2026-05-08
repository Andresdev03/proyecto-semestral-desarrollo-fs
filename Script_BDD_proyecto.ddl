-- Generado por Oracle SQL Developer Data Modeler 24.3.1.351.0831
--   en:        2026-05-03 21:43:06 CLT
--   sitio:      Oracle Database 21c
--   tipo:      Oracle Database 21c



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE boleta 
    ( 
     id_boleta           NUMBER (6)  NOT NULL , 
     fec_creacion_boleta DATE , 
     valor_total_boleta  NUMBER (12,2)  NOT NULL , 
     metodo_pago_boleta  VARCHAR2 (50)  NOT NULL , 
     id_pedido           NUMBER (6)  NOT NULL 
    ) 
;

ALTER TABLE boleta 
    ADD CONSTRAINT boleta_total_CK 
    CHECK (valor_total_boleta > 0)
;


ALTER TABLE boleta 
    ADD CONSTRAINT boleta_pago_CK 
    CHECK (metodo_pago_boleta IN ('Efectivo','Debito','Credito','Transferencia')
)
;
ALTER TABLE boleta 
    ADD CONSTRAINT boleta_PK PRIMARY KEY ( id_boleta ) ;

ALTER TABLE boleta 
    ADD CONSTRAINT id_pedido_UN UNIQUE ( id_pedido ) ;

CREATE TABLE comuna 
    ( 
     id_comuna     NUMBER (5)  NOT NULL , 
     nombre_comuna VARCHAR2 (50)  NOT NULL , 
     id_region     NUMBER (4)  NOT NULL 
    ) 
;

ALTER TABLE comuna 
    ADD CONSTRAINT comuna_PK PRIMARY KEY ( id_comuna ) ;

CREATE TABLE contrato 
    ( 
     id_contrato      NUMBER (6)  NOT NULL , 
     tipo_contrato    VARCHAR2 (50)  NOT NULL , 
     cargo_contrato   VARCHAR2 (50)  NOT NULL , 
     fec_ini_contrato DATE  NOT NULL , 
     fec_fin_contrato DATE , 
     salario_contrato NUMBER (10,2)  NOT NULL , 
     run_empleado     VARCHAR2 (8)  NOT NULL , 
     id_sucursal      NUMBER (6)  NOT NULL 
    ) 
;

ALTER TABLE contrato 
    ADD CONSTRAINT contrato_tipo_CK 
    CHECK (tipo_contrato IN ('Indefinido','Plazo Fijo','Por Obra','Honorarios'))
;


ALTER TABLE contrato 
    ADD CONSTRAINT contrato_salario_CK 
    CHECK (salario_contrato > 0)
;


ALTER TABLE contrato 
    ADD CONSTRAINT contrato_fechas_CK 
    CHECK ((fec_fin_contrato IS NULL OR fec_fin_contrato > fec_ini_contrato))
;
ALTER TABLE contrato 
    ADD CONSTRAINT contrato_PK PRIMARY KEY ( id_contrato ) ;

CREATE TABLE detalle_pedido 
    ( 
     id_detalle      NUMBER (8)  NOT NULL , 
     id_pedido       NUMBER (6)  NOT NULL , 
     id_producto     NUMBER (6)  NOT NULL , 
     cantidad        NUMBER (3)  NOT NULL , 
     precio_unitario NUMBER (10,2)  NOT NULL , 
     subtotal        NUMBER (10,2)  NOT NULL 
    ) 
;

ALTER TABLE detalle_pedido 
    ADD CONSTRAINT detalle_cant_CK 
    CHECK (cantidad > 0)
;


ALTER TABLE detalle_pedido 
    ADD CONSTRAINT detalle_subtotal_CK 
    CHECK (subtotal > 0)
;
ALTER TABLE detalle_pedido 
    ADD CONSTRAINT detalle_pedido_PK PRIMARY KEY ( id_detalle ) ;

CREATE TABLE empleado 
    ( 
     run_empleado       VARCHAR2 (8)  NOT NULL , 
     dv_runEmpleado     CHAR (1)  NOT NULL , 
     correo_empleado    VARCHAR2 (100)  NOT NULL , 
     telefono_empleado  VARCHAR2 (12) , 
     pnombre_empleado   VARCHAR2 (50)  NOT NULL , 
     snombre_empleado   VARCHAR2 (50) , 
     appaterno_empleado VARCHAR2 (50)  NOT NULL , 
     apmaterno_empleado VARCHAR2 (50) , 
     fecha_nac_empleado DATE  NOT NULL 
    ) 
;

ALTER TABLE empleado 
    ADD CONSTRAINT empleado_PK PRIMARY KEY ( run_empleado ) ;

ALTER TABLE empleado 
    ADD CONSTRAINT correo_UN UNIQUE ( correo_empleado ) ;

ALTER TABLE empleado 
    ADD CONSTRAINT numero_UN UNIQUE ( telefono_empleado ) ;

CREATE TABLE factura 
    ( 
     id_factura          NUMBER (6)  NOT NULL , 
     fec_emision_factura DATE  NOT NULL , 
     rut_receptor        VARCHAR2 (12)  NOT NULL , 
     razon_social        VARCHAR2 (100)  NOT NULL , 
     giro_receptor       VARCHAR2 (100)  NOT NULL , 
     monto_neto          NUMBER (12,2)  NOT NULL , 
     monto_iva           NUMBER (12,2)  NOT NULL , 
     monto_total         NUMBER (12,2)  NOT NULL , 
     id_pedido           NUMBER (6)  NOT NULL , 
     id_proveedor        NUMBER (6)  NOT NULL 
    ) 
;

ALTER TABLE factura 
    ADD CONSTRAINT factura_neto_CK 
    CHECK (monto_neto > 0)
;


ALTER TABLE factura 
    ADD CONSTRAINT factura_iva_CK 
    CHECK (monto_iva >= 0)
;


ALTER TABLE factura 
    ADD CONSTRAINT factura_total_CK 
    CHECK (monto_total > 0)
;
ALTER TABLE factura 
    ADD CONSTRAINT factura_PK PRIMARY KEY ( id_factura ) ;

CREATE TABLE guia_despacho 
    ( 
     id_guia          NUMBER (6)  NOT NULL , 
     fec_emision_guia DATE , 
     descripcion_guia VARCHAR2 (300) , 
     estado_guia      VARCHAR2 (20)  NOT NULL , 
     id_proveedor     NUMBER (6)  NOT NULL , 
     id_sucursal      NUMBER (6)  NOT NULL , 
     id_factura       NUMBER (6) 
    ) 
;

ALTER TABLE guia_despacho 
    ADD CONSTRAINT guia_estado_CK 
    CHECK (estado_guia IN ('Pendiente','En Transito','Recibida','Rechazada'))
;
ALTER TABLE guia_despacho 
    ADD CONSTRAINT guia_despacho_PK PRIMARY KEY ( id_guia ) ;

CREATE TABLE ingrediente 
    ( 
     id_ingrediente          NUMBER (6)  NOT NULL , 
     nombre_ingrediente      VARCHAR2 (100)  NOT NULL , 
     descripcion_ingrediente VARCHAR2 (50) , 
     unidad_medida           VARCHAR2 (50)  NOT NULL , 
     stock_ingrediente       NUMBER (10,2)  NOT NULL , 
     precio_unitario         NUMBER (10,2)  NOT NULL , 
     id_proveedor            NUMBER (6)  NOT NULL 
    ) 
;

ALTER TABLE ingrediente 
    ADD CONSTRAINT ingrediente_stock_CK 
    CHECK (stock_ingrediente >= 0)
;


ALTER TABLE ingrediente 
    ADD CONSTRAINT ingrediente_precio_CK 
    CHECK (precio_unitario > 0)
;
ALTER TABLE ingrediente 
    ADD CONSTRAINT ingrediente_PK PRIMARY KEY ( id_ingrediente ) ;

CREATE TABLE pedido 
    ( 
     id_pedido     NUMBER (6)  NOT NULL , 
     fec_pedido    DATE  NOT NULL , 
     estado_pedido VARCHAR2 (50)  NOT NULL , 
     tipo_pedido   VARCHAR2 (50)  NOT NULL , 
     num_mesa      NUMBER (3) , 
     run_empleado  VARCHAR2 (8)  NOT NULL , 
     id_sucursal   NUMBER (6)  NOT NULL 
    ) 
;

ALTER TABLE pedido 
    ADD CONSTRAINT pedido_estado_CK 
    CHECK (estado_pedido IN ('Pendiente','En Preparacion','Listo','Entregado','Cancelado'))
;


ALTER TABLE pedido 
    ADD CONSTRAINT pedido_tipo_CK 
    CHECK (tipo_pedido IN ('Mesa','Delivery','Retiro'))
;
ALTER TABLE pedido 
    ADD CONSTRAINT pedido_PK PRIMARY KEY ( id_pedido ) ;

CREATE TABLE producto 
    ( 
     id_producto          NUMBER (6)  NOT NULL , 
     nombre_producto      VARCHAR2 (50)  NOT NULL , 
     descripcion_producto VARCHAR2 (50) , 
     precio_producto      NUMBER (10,2)  NOT NULL , 
     categoria_producto   VARCHAR2 (50)  NOT NULL , 
     disponible_producto  CHAR (1)  NOT NULL 
    ) 
;

ALTER TABLE producto 
    ADD CONSTRAINT producto_precio_CK 
    CHECK (precio_producto > 0)
;


ALTER TABLE producto 
    ADD CONSTRAINT producto_disp_CK 
    CHECK (disponible_producto IN ('S','N'))
;
ALTER TABLE producto 
    ADD CONSTRAINT producto_PK PRIMARY KEY ( id_producto ) ;

CREATE TABLE producto_ingrediente 
    ( 
     id_producto    NUMBER (6)  NOT NULL , 
     id_ingrediente NUMBER (6)  NOT NULL , 
     cantidad       NUMBER (10,2)  NOT NULL 
    ) 
;

ALTER TABLE producto_ingrediente 
    ADD CONSTRAINT producto_ingrediente_CK_1 
    CHECK (cantidad > 0)
;
ALTER TABLE producto_ingrediente 
    ADD CONSTRAINT producto_ingrediente_PK PRIMARY KEY ( id_producto, id_ingrediente ) ;

CREATE TABLE proveedor 
    ( 
     id_proveedor        NUMBER (6)  NOT NULL , 
     rut_proveedor       VARCHAR2 (12)  NOT NULL , 
     dv_proveedor        CHAR (1)  NOT NULL , 
     email_proveedor     VARCHAR2 (50)  NOT NULL , 
     nombre_proveedor    VARCHAR2 (100)  NOT NULL , 
     direccion_proveedor VARCHAR2 (50) , 
     telefono_proveedor  VARCHAR2 (50) , 
     id_region           NUMBER (4)  NOT NULL , 
     id_comuna           NUMBER (5)  NOT NULL 
    ) 
;

ALTER TABLE proveedor 
    ADD CONSTRAINT proveedor_PK PRIMARY KEY ( id_proveedor ) ;

ALTER TABLE proveedor 
    ADD CONSTRAINT rut_proveedor_UN UNIQUE ( rut_proveedor ) ;

ALTER TABLE proveedor 
    ADD CONSTRAINT email_proveedorUN UNIQUE ( email_proveedor ) ;

CREATE TABLE region 
    ( 
     id_region     NUMBER (4)  NOT NULL , 
     nombre_region VARCHAR2 (50)  NOT NULL 
    ) 
;

ALTER TABLE region 
    ADD CONSTRAINT region_PK PRIMARY KEY ( id_region ) ;

CREATE TABLE sucursal 
    ( 
     id_sucursal           NUMBER (6)  NOT NULL , 
     email_sucursal        VARCHAR2 (100)  NOT NULL , 
     telefono_sucursal     VARCHAR2 (12)  NOT NULL , 
     nombre_sucursal       VARCHAR2 (50)  NOT NULL , 
     direccion_sucursal    VARCHAR2 (50)  NOT NULL , 
     fec_apertura_sucursal DATE  NOT NULL , 
     id_comuna             NUMBER (5)  NOT NULL , 
     id_region             NUMBER (4)  NOT NULL 
    ) 
;

ALTER TABLE sucursal 
    ADD CONSTRAINT sucursal_PK PRIMARY KEY ( id_sucursal ) ;

ALTER TABLE sucursal 
    ADD CONSTRAINT telefono_UN UNIQUE ( telefono_sucursal ) ;

ALTER TABLE sucursal 
    ADD CONSTRAINT correo_UNv1 UNIQUE ( email_sucursal ) ;

CREATE TABLE turno 
    ( 
     id_turno       NUMBER (6)  NOT NULL , 
     fec_turno      DATE  NOT NULL , 
     hora_ini_turno VARCHAR2 (5)  NOT NULL , 
     hora_fin_turno VARCHAR2 (5)  NOT NULL , 
     tipo_turno     VARCHAR2 (20)  NOT NULL , 
     run_empleado   VARCHAR2 (8)  NOT NULL , 
     id_sucursal    NUMBER (6)  NOT NULL 
    ) 
;

ALTER TABLE turno 
    ADD CONSTRAINT turno_tipo_CK 
    CHECK (tipo_turno IN ('Mañana','Tarde','Noche'))
;
ALTER TABLE turno 
    ADD CONSTRAINT turno_PK PRIMARY KEY ( id_turno ) ;

ALTER TABLE boleta 
    ADD CONSTRAINT boleta_pedido_FK FOREIGN KEY 
    ( 
     id_pedido
    ) 
    REFERENCES pedido 
    ( 
     id_pedido
    ) 
;

ALTER TABLE comuna 
    ADD CONSTRAINT comuna_region_FK FOREIGN KEY 
    ( 
     id_region
    ) 
    REFERENCES region 
    ( 
     id_region
    ) 
;

ALTER TABLE contrato 
    ADD CONSTRAINT contrato_empleado_FK FOREIGN KEY 
    ( 
     run_empleado
    ) 
    REFERENCES empleado 
    ( 
     run_empleado
    ) 
;

ALTER TABLE contrato 
    ADD CONSTRAINT contrato_sucursal_FK FOREIGN KEY 
    ( 
     id_sucursal
    ) 
    REFERENCES sucursal 
    ( 
     id_sucursal
    ) 
;

ALTER TABLE detalle_pedido 
    ADD CONSTRAINT detalle_pedido_pedido_FK FOREIGN KEY 
    ( 
     id_pedido
    ) 
    REFERENCES pedido 
    ( 
     id_pedido
    ) 
;

ALTER TABLE detalle_pedido 
    ADD CONSTRAINT detalle_pedido_producto_FK FOREIGN KEY 
    ( 
     id_producto
    ) 
    REFERENCES producto 
    ( 
     id_producto
    ) 
;

ALTER TABLE factura 
    ADD CONSTRAINT factura_pedido_FK FOREIGN KEY 
    ( 
     id_pedido
    ) 
    REFERENCES pedido 
    ( 
     id_pedido
    ) 
;

ALTER TABLE factura 
    ADD CONSTRAINT factura_proveedor_FK FOREIGN KEY 
    ( 
     id_proveedor
    ) 
    REFERENCES proveedor 
    ( 
     id_proveedor
    ) 
;

ALTER TABLE guia_despacho 
    ADD CONSTRAINT guia_despacho_factura_FK FOREIGN KEY 
    ( 
     id_factura
    ) 
    REFERENCES factura 
    ( 
     id_factura
    ) 
;

ALTER TABLE guia_despacho 
    ADD CONSTRAINT guia_despacho_proveedor_FK FOREIGN KEY 
    ( 
     id_proveedor
    ) 
    REFERENCES proveedor 
    ( 
     id_proveedor
    ) 
;

ALTER TABLE guia_despacho 
    ADD CONSTRAINT guia_despacho_sucursal_FK FOREIGN KEY 
    ( 
     id_sucursal
    ) 
    REFERENCES sucursal 
    ( 
     id_sucursal
    ) 
;

ALTER TABLE ingrediente 
    ADD CONSTRAINT ingrediente_proveedor_FK FOREIGN KEY 
    ( 
     id_proveedor
    ) 
    REFERENCES proveedor 
    ( 
     id_proveedor
    ) 
;

ALTER TABLE pedido 
    ADD CONSTRAINT pedido_empleado_FK FOREIGN KEY 
    ( 
     run_empleado
    ) 
    REFERENCES empleado 
    ( 
     run_empleado
    ) 
;

ALTER TABLE pedido 
    ADD CONSTRAINT pedido_sucursal_FK FOREIGN KEY 
    ( 
     id_sucursal
    ) 
    REFERENCES sucursal 
    ( 
     id_sucursal
    ) 
;

ALTER TABLE producto_ingrediente 
    ADD CONSTRAINT prod_ingr_ingrediente_FK FOREIGN KEY 
    ( 
     id_ingrediente
    ) 
    REFERENCES ingrediente 
    ( 
     id_ingrediente
    ) 
;

ALTER TABLE producto_ingrediente 
    ADD CONSTRAINT prod_ingr_producto_FK FOREIGN KEY 
    ( 
     id_producto
    ) 
    REFERENCES producto 
    ( 
     id_producto
    ) 
;

ALTER TABLE proveedor 
    ADD CONSTRAINT proveedor_comuna_FK FOREIGN KEY 
    ( 
     id_comuna
    ) 
    REFERENCES comuna 
    ( 
     id_comuna
    ) 
;

ALTER TABLE proveedor 
    ADD CONSTRAINT proveedor_region_FK FOREIGN KEY 
    ( 
     id_region
    ) 
    REFERENCES region 
    ( 
     id_region
    ) 
;

ALTER TABLE sucursal 
    ADD CONSTRAINT sucursal_comuna_FK FOREIGN KEY 
    ( 
     id_comuna
    ) 
    REFERENCES comuna 
    ( 
     id_comuna
    ) 
;

ALTER TABLE sucursal 
    ADD CONSTRAINT sucursal_region_FK FOREIGN KEY 
    ( 
     id_region
    ) 
    REFERENCES region 
    ( 
     id_region
    ) 
;

ALTER TABLE turno 
    ADD CONSTRAINT turno_empleado_FK FOREIGN KEY 
    ( 
     run_empleado
    ) 
    REFERENCES empleado 
    ( 
     run_empleado
    ) 
;

ALTER TABLE turno 
    ADD CONSTRAINT turno_sucursal_FK FOREIGN KEY 
    ( 
     id_sucursal
    ) 
    REFERENCES sucursal 
    ( 
     id_sucursal
    ) 
;



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            15
-- CREATE INDEX                             0
-- ALTER TABLE                             63
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
-- CREATE MATERIALIZED VIEW LOG             0
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
