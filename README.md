jersey_restful_webservice
=========================

Jersey 2.5.1 RESTful webservice

This example is an RESTful webservice using Jersey 2.5.1 for the JAX-WS implementation and Genson 0.98 for the JSON to Java and Java to JSON data conversion.

Using Genson means that it simply will just convert the data for you and you are not required to configure POJO mapping in the web.xml as well as not having to specify @XmlRootElement to POJO objects that are going to be transformed.

This also utilizes the Jersey Test Framework 2.5.1 that will execute unit tests and allow for the http response to be tested.  This has been combined with Mockito to mock out the services however if you wish based out your configuration you can also perform end to end tests.
 
