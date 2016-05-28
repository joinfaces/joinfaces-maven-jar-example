Jsf Spring Boot Starter Example
=====
[![Heroku](http://heroku-badge.herokuapp.com/?app=heroku-badge)](https://jsf-spring-boot-starter-sample.herokuapp.com/helloWorld.jsf)
[![Build Status](https://travis-ci.org/persapiens/jsf-spring-boot-starter-example.svg?branch=master)](https://travis-ci.org/persapiens/jsf-spring-boot-starter)
[![Dependency Status](https://www.versioneye.com/user/projects/573c95eace8d0e004130bd98/badge.svg?style=flat)](https://www.versioneye.com/user/projects/573c95eace8d0e004130bd98)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This project illustrates JSF usage inside JAR packaged Spring Boot Application.

The [Jsf Spring Boot Starter](https://github.com/persapiens/jsf-spring-boot-starter) autoconfigure [PrimeFaces](http://primefaces.org/), [OmniFaces](http://omnifaces.org/), [Mojarra](https://javaserverfaces.java.net/) and [MyFaces](http://myfaces.apache.org) libraries to run at embedded [Tomcat](http://tomcat.apache.org/), [Jetty](http://www.eclipse.org/jetty) or [Undertow](http://undertow.io/).

## See Example Application in the cloud

1- Access helloWorld page at **https://jsf-spring-boot-starter-sample.herokuapp.com/helloWorld.jsf**

## Run Example Application locally

1- Clone this project
```Shell
git clone https://github.com/persapiens/jsf-spring-boot-starter-example.git
```

2- Build
```Shell
mvn clean install
```

3- Run
```Shell
java -jar target/jsf-spring-boot-starter-example-1.3.0-SNAPSHOT.jar
```

4- Access helloWorld page at **http://localhost:8080/helloWorld.jsf**

## Key Files and Directories

- **pom.xml**: includes jsf-spring-boot-starter dependency. All other dependencies are included transitively.

```xml
<properties>
    <jsf-spring-boot-starter.version>1.3.0</jsf-spring-boot-starter.version>
</properties>
<dependencies>
    <dependency>
      <groupId>com.github.persapiens</groupId>
      <artifactId>jsf-spring-boot-starter</artifactId>
      <version>${jsf-spring-boot-starter.version}</version>
    </dependency>
</dependencies>
```

If you prefer **Jetty** instead of **Tomcat**, change artifactId jsf-spring-boot-starter to **jsf-jetty-spring-boot-starter** in order to use **Jetty** servlet container. 

If you prefer **Undertow** instead of **Tomcat**, change artifactId jsf-spring-boot-starter to **jsf-undertow-spring-boot-starter** in order to use **Undertow** servlet container. 

If you prefer **MyFaces** instead of **Mojarra**, change artifactId jsf-spring-boot-starter to **jsf-myfaces-spring-boot-starter** in order to use **MyFaces** JSF Implementation. 

If you prefer **Jetty** instead of **Tomcat** and **MyFaces** instead of **Mojarra**, change artifactId jsf-spring-boot-starter to **jsf-jetty-mojarra-spring-boot-starter**. 

If you prefer **Undertow** instead of **Tomcat** and **MyFaces** instead of **Mojarra**, change artifactId jsf-spring-boot-starter to **jsf-undertow-mojarra-spring-boot-starter**. 

- **src/main/java/com/github/persapiens/example/JsfSpringBootStarterExampleApplication.java**: very simple spring main application. No extra configuration is required.

<pre>
@SpringBootApplication
public class JsfSpringBootStarterExampleApplication {
</pre>

- **src/main/resources/application.yml**: configure jsf.PROJECT_STATE and jsf.primefaces.THEME properties.

```yml
jsf:
  PROJECT_STAGE: Development
  primefaces: 
    theme: overcast
```

- **src/main/resources/META-INF/resources/helloWorld.xhtml**: example page. Note that xhtml, js, css and images files should be located at **src/main/resources/META-INF/resources** directory to JSF use them.

- **src/main/java/com/github/persapiens/example/view/HelloWorldMBean.java**: managed bean using ViewScoped CDI annotation. The equivalent spring scope of ViewScoped annotation is configured automatically by Jsf Spring Boot Starter.

<pre>
@Named
<b>@ViewScoped</b>
public class HelloWorldMBean {
</pre>

## Getting Help

* Report questions and bugs at [github.com/jsf-spring-boot-starter-example/issues](https://github.com/persapiens/jsf-spring-boot-starter-example/issues).

## Contributing

* Report documentation, features, enhancement and bugs at [github.com/jsf-spring-boot-starter-example/issues](https://github.com/persapiens/jsf-spring-boot-starter-example/issues).
* Pull requests are welcome.
