CREATE SCHEMA test_volvo
       AUTHORIZATION postgres;

CREATE TABLE test_volvo.department
(
	id serial NOT NULL,
	
	name text,
	description text,
  	
	CONSTRAINT id_department PRIMARY KEY (id)
);

CREATE TABLE test_volvo.permission
(
	id serial NOT NULL,  
	
	name text,
  	description text,
  
  	CONSTRAINT id_permission PRIMARY KEY (id)
);

CREATE TABLE test_volvo.app_user
(
  	id serial NOT NULL,
  	id_department integer not null,
  
  	name text,
  	description text,
  
  	CONSTRAINT id_user PRIMARY KEY (id)
);

CREATE TABLE test_volvo.permissionuser
(
	id serial NOT NULL,
	id_user integer NOT NULL,
  	id_permission integer NOT NULL,
  
  	CONSTRAINT id_permissionuser PRIMARY KEY (id)
);



-- SET start value for sequences 'tables' to avoid duplicate keys 
SELECT setval('test_volvo.app_user_id_seq', 100, true);
SELECT setval('test_volvo.department_id_seq', 100, true);
SELECT setval('test_volvo.permission_id_seq', 100, true);
SELECT setval('test_volvo.permissionuser_id_seq', 100, true);

