# jersey-api-example
Example of using Jersey with Guice and Jackson to create RESTful APIs with Java

##Purpose of Code
This is meant to be an example of how to set up a web application that uses the Jersey/JAX-RS framework.
Other frameworks this code uses includes Google Guice, Jackson, and Guava.
This also include a sample frontend in AngularJS and Bootstrap showing how easy it is to set up a RESTful frontend

##How to Run this.
This uses Gradle to build a WAR file. 
Since Gradle has a nifty feature where I can include a generated script that installs and runs gradle for you, this requires no additional setup.
To build the war file, do the following

``` 
./gradlew war
```

This creates a war file in ```build/libs``` call jersey-api-example.war.
Since this can create a war file, you can now deploy it using Apache Tomcat.
I've also added the google app engine plugin, allowing you to both test locally and deploy to the cloud.
To test locally, do the following

```
./gradlew appengineRun
```

This builds a war file and deploys it to a local context (specifically http://localhost:8080).
Now you can test your rest end points with either cURL or Postman, or go through the javascript example by navigating to that url and clicking around.
If you wish, you can make changes to the appengine-web.xml file and upload to the cloud using the following

```
./gradlw appengineUpdate
```

If you are asked to copy/paste a code from the google authentication, you can just paste it into the terminal and press enter (the plugin isn't very good at this, but it does work)
##What should I be looking at to understand this.
This project follows the standard project layout:
```
-src
    -main
        -java
        -webapp
            -WEB-INF
    -test
        -java
```
The basic principle is that all your java code goes in ```src/main/java``` and all your webapp code goes in ```src/main/webapp```.
The test directory is where all unit tests and testing code goes.

#### web.xml file
The first file you should take a look at is the web.xml file.
This is where we setup the webapp.
This includes setting up filters, creating servlet mappings, and declaring the welcome files.
In this case, this is where we set up the Jersey Application and the Guice filter.
For more information on the different settings you can set for jersey, see this appendix: https://jersey.java.net/documentation/latest/appendix-properties.html
For more information on the Guice Filter, see this: https://github.com/google/guice/wiki/Servlets

#### JerseyApplication class
This class is where we do some additional setup and link together Guice, Jersey, and Jackson.
We register the JacksonJaxbJsonProvider and ObjectMappResolver, which allows us to use the proper annotations provided by Jackson on our objects and to auto-convert request bodies into Java Beans and vice versa.
We also set up the Guice Bridge, which allows us to use dependency injection in our Web Services with classes registered with Guice.

#### Web Service class
There are two classes in this example, the HelloWorldWebService (setup for quick testing on my part) and the TodoListWebService.
The WebService class has a Path annotiation, which denotes the url to call to hit the methods in this class.
Jersey will auto add all classes that include the Path annotation that's in the package list in the web.xml file.
For each public method in this service class, there are a list of different annotations.
Annotations like GET, POST, PUT, and DELETE are defining the specific Http Method used with the url.
Extra Path annotations add onto the path given, allowing for sub paths (like adding an id, or getting a subresource).
The Produces annotation specifically tells the request the content type of the response body.
The Consumes annotation specifically tells the app what type of request body to expect.
This will lead to errors if the content-type headers on the http request are not set to the correct type.

#### Objects
There are two specific objects in this package that are just domain objects with getters, equals, and hashcode methods.
The key thing here is the JsonCreator and JsonProperty annotations.
They tell jackson how to create the JavaBean given a JSON object.
If the JSON object matches this object constructor and the web service method is expecting it, it'll build it fine.
If no JSON object matches the object expected in the web service method, you'll get a 400 bad request error.
You could also get a 415 Media Type error, which means you didn't set the content-type header in the request.
Jackson will look at both the getters and setters for generating objects by default, but if you do not have an empty constructor, you must use the JsonCreator constructor.

#### Other classes
We won't go into much detail on the other classes.
The Guice Module class allows you to bind classes or to give providers for other classes.
The Guice Servlet Config class sets up the class injector and is needed for Guice to work.
There is a backend set up that's supported by Google App Engine called Objectify.
More information on Objectify can be found here: https://github.com/objectify/objectify
