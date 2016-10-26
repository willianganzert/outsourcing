# Spring 4 MVC (Java Configuration) + JMS + Restful API

###1. Technologies
 
*	Spring 4.2.8.RELEASE
*	Maven 3.3.3
*	JSTL 1.2
*	Hibernate 4.2.1.Final
*	Junit 4.12


###2. To Run project
######2.1 Application Server - use one of this two servers
*	Tomcat v7.0
*	Jboss AS 7.1.1 (Final)*
*	Access ```http://localhost:8080/outsourcing```

######2.2. JMS Server
*	JMS - Apache ActiveMQ 5.9.0

######2.3. DataBase
*	PostgreSQL 9.4.5

###3. Step by Step to start with Outsourcing Application

1. First of all, clone the project into your workspace ("git clone GIT_URL .")
2. Import the project into your Eclipse IDE **Maven - existing projects into workspace**
3. Install DataBase(PostgreSQL)
3.1. Check if DB has 'postgres' user with password 'postgres'
3.2. Run SQL script on postgres(\src\main\resources\generate_ddl_postgres.sql) 
3.2. Run SQL Default data script on postgres(\outsourcing\src\main\resources\insert_default.sql) 
4. Start ActiveMQ
4.1. Do the Download (http://archive.apache.org/dist/activemq/apache-activemq/5.9.0/apache-activemq-5.9.0-bin.zip)
4.2. Unzip
4.3. Start application (apache-activemq-5.9.0\bin\win64\activemq.bat)	
5. Start Application Server
*Tomcat
5.1. Download file (http://www-eu.apache.org/dist/tomcat/tomcat-7/v7.0.70/bin/apache-tomcat-7.0.70.zip)
5.2. Unzip
5.3. Add server in Eclipse IDE **Servers**
5.4. Run Outsourcing Application on Tomcat Server.
*Jboss
5.1. Download Jboss file (http://download.jboss.org/jbossas/7.1/jboss-as-7.1.1.Final/jboss-as-7.1.1.Final.zip)
5.2. Download jdbc postgres(https://jdbc.postgresql.org/download/postgresql-9.4.1211.jre6.jar)
5.3. Unzip Jboss file
5.4. Configure and copy jar file to a new module in Jboss modules.(create_new_module_jboss.txt)
5.3. Add server in Eclipse IDE **Servers**
5.4. Run Outsourcing Application on Jboss.
6. Tests can be executed over Eclipse IDE(Junit)
6.1 Junits tests can be fount in "br.com.webapp.test.*" directories.
7. Restful services
* GET - listall - http://localhost:8080/outsourcing/api/user
* GET - listone - http://localhost:8080/outsourcing/api/user/{id}
* POST - persistone - http://localhost:8080/outsourcing/api/user/{id}
* PUT - updateone - http://localhost:8080/outsourcing/api/user/{id}
* DELETE - deleteone - http://localhost:8080/outsourcing/api/user/{id}