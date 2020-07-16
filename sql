create database magento;

CREATE TABLE articulos
(
  codigo_barra character varying(100) NOT NULL,
  descripcion character varying(100) NOT NULL,
  CONSTRAINT articulos_pkey PRIMARY KEY (codigo_barra)
)

CREATE TABLE pedidos
(
  num_pedido character varying(100) NOT NULL,
  supervisor character varying(100) NOT NULL,
  picker character varying(100) NOT NULL,
  fecha character varying(100) NOT NULL,
  sucursal character varying(100) NOT NULL,
  estado character varying(100) NOT NULL,
  CONSTRAINT pedidos_pkey PRIMARY KEY (num_pedido)
)


CREATE TABLE detalle_pedidos
(
  num_pedido character varying(100) NOT NULL,
  codigo_barra character varying(100) NOT NULL,
  descripcion character varying(100) NOT NULL,
  cantidad double precision NOT NULL,
  seccion character varying(100) NOT NULL,
  obs character varying(100) NOT NULL,
  CONSTRAINT fk_num_pedido FOREIGN KEY (num_pedido)
      REFERENCES pedidos (num_pedido) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE TABLE usuarios
(
  usuario character varying(100) NOT NULL,
  pass character varying(100) NOT NULL,
  tipo character varying(100) NOT NULL,
  sucursal character varying(100) NOT NULL,
  fecha character varying(100) NOT NULL,
  CONSTRAINT usuarios_pkey PRIMARY KEY (usuario)
)

   
    
    
INSERT INTO public.pedidos(num_pedido, supervisor, picker, fecha, sucursal, estado)
	VALUES ('0000001', 'SILVER', '-', '06-06-2020','VILLA MORRA', '-'),
			('0000002', '-', '-', '06-06-2020', 'VILLA MORRA', '-'),
			('0000003', '-', '-', '06-06-2020', 'VILLA MORRA', '-'),
			('0000004', '-', '-', '06-06-2020', 'VILLA MORRA', '-'),
			('0000005', '-', '-', '06-06-2020', 'VILLA MORRA', '-'),
		        ('0000006', '-', '-', '06-06-2020', 'VILLA MORRA', '-'),
			('0000007', '-', '-', '06-06-2020', 'VILLA MORRA', '-'),
			('0000008', '-', '-', '06-06-2020', 'VILLA MORRA', '-'),
			('0000009', '-', '-', '06-06-2020', 'VILLA MORRA', '-'),
			('0000010', '-', '-', '06-06-2020', 'VILLA MORRA', '-'),
			('0000011', '-', '-', '06-06-2020', 'SAN VICENTE', '-'),
			('0000012', '-', '-', '06-06-2020', 'SAN VICENTE', '-'),
			('0000013', '-', '-', '06-06-2020', 'SAN VICENTE', '-'),
			('0000014', '-', '-', '06-06-2020', 'SAN VICENTE', '-'),
			('0000015', '-', '-', '06-06-2020', 'SAN VICENTE', '-'),
			('0000016', '-', '-', '06-06-2020', 'SAN VICENTE', '-'),
			('0000017', '-', '-', '06-06-2020', 'SAN VICENTE', '-'),
			('0000018', '-', '-', '06-06-2020', 'SAN VICENTE', '-'),
			('0000019', '-', '-', '06-06-2020', 'SAN VICENTE', '-');
	
INSERT INTO public.detalle_pedidos(num_pedido, codigo_barra, descripcion, cantidad, seccion, obs)
	VALUES ('0000001', '8001', 'PEPSI', 5, '-', '-'),
	('0000001', '8002', 'PRINGLES', 4, '-', '-'),	
	('0000001', '8003', 'ADES', 2, '-', '-'),
	('0000001', '8004', 'NIKITO', 3, '-', '-'),	
	('0000001', '8005', 'SALADIX', 2, '-', '-'),
	('0000001', '8006', 'COCA COLA', 3, '-', '-'),	
	('0000001', '8007', 'YES YES', 2, '-', '-'),
	('0000001', '8004', 'NIKITO', 3, '-', '-'),	
	('0000001', '8005', 'SALADIX', 2, '-', '-');		
	
INSERT INTO public.detalle_pedidos(num_pedido, codigo_barra, descripcion, cantidad, seccion, obs)
	VALUES ('0000004', '8001', 'PEPSI', 5, '-', '-'),
	('0000004', '8002', 'PRINGLES', 4, '-', '-'),	
	('0000004', '8003', 'ADES', 2, '-', '-'),
	('0000004', '8004', 'NIKITO', 3, '-', '-'),	
	('0000004', '8002', 'PRINGLES', 4, '-', '-'),	
	('0000004', '8003', 'ADES', 2, '-', '-'),
	('0000004', '8004', 'NIKITO', 3, '-', '-');	

INSERT INTO public.detalle_pedidos(num_pedido, codigo_barra, descripcion, cantidad, seccion, obs)
	VALUES ('0000008', '8001', 'PEPSI', 5, '-', '-'),
	('0000008', '8002', 'PRINGLES', 4, '-', '-'),	
	('0000008', '8003', 'ADES', 2, '-', '-'),
	('0000008', '8004', 'NIKITO', 3, '-', '-');	

INSERT INTO usuarios(usuario, pass, tipo, sucursal, fecha)
    VALUES ('ADMIN', 'ADMIN', 'SUPERVISOR', 'VILLA MORRA', '-'),
    	('ATELLEZ', '123', 'SUPERVISOR', 'VILLA MORRA', '-'),
	('RCACERES', '123', 'SUPERVISOR', 'VILLA MORRA', '-'),
	('LCABRERA', '123', 'SUPERVISOR', 'SAN VICENTE', '-'),
	('RVALDEZ', '123', 'PICKER', 'SAN VICENTE', '-'),
	('ECARDOZO', '123', 'PICKER', 'SAN VICENTE', '-'),
	('DGIMENEZ', '123', 'PICKER', 'VILLA MORRA', '-'),
	('IGAMARRA', '123', 'PICKER', 'VILLA MORRA', '-')

INSERT INTO articulos(codigo_barra, descripcion)
    VALUES ('8001', 'PEPSI'),
	('8002', 'PRINGLES'),	
	('8003', 'ADES'),
	('8004', 'NIKITO'),	
	('8005', 'SALADIX'),
	('8006', 'COCA COLA'),	
	('8007', 'YES YES');    
