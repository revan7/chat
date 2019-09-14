# Simple Java Chat Application

This is a demonstration of Java sockets and how you can create a simple Chat application. 

Prerequisites:

* JDK 1.8.0
* Maven 3.6.1


To build :


>Make sure you have JAVA_HOME and PATH correctly configured. 
>JAVA_HOME must point to the location of the jdk folder, whilst PATH must contain the location of the jdk/bin folder. 

`mvn clean && mvn install`

To run Server:

* Navigate to `/server/target/`
* Run `java -jar -Dserver.port=[port] server.jar`

To run Client:

* Navigate to `/client/target`
* `java -jar -D -Dserver.ip=[] -Dserver.port=[] -Dprofile=[] -D -Dusername=[] `
* profile can be `gui` if you want the graphical interface or `cmdline` for the command line (not recommended)