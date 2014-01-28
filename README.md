jersey_restful_webservice
=========================

Jersey 2.5.1 RESTful Web Service with JPA 2.1 implemented using EclipseLink 2.5.2 and using Derby 10.10.1.1 In Memory Database

This is an example of an RESTful WebService using Jersey for the JAX-RS implementation, Genson for the JSON to Java / Java to JSON data binding, EclipseLink for the JPA implementation and Derby for the In Memory Database.

Jersey is Oracles implementation of the JAX-RS 2.0 API.

Genson simply binds the data from JSON to Java and vice versa, you do not have to configure POJO mapping in the web.xml or specify @XmlRootElement to POJO objects.

EclipseLink implements the Java JPA 2.0 API which is used for transaction management, persistence configuration and data binding between tables and POJOs via annotations.

Derby is used to store data also allowing you to run an embedded database, in memory database etc.

Jersey Test Framework 2.5.1 will execute unit tests and allow for the http response to be tested, this has been combined with Mockito to mock out the services however if you wish based out your configuration you can also perform end to end tests.
 
