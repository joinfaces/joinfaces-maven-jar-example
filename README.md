JoinFaces Example
=====
[![Heroku](http://heroku-badge.herokuapp.com/?app=joinfaces-example&root=index.jsf)](https://joinfaces-example.herokuapp.com)
[![Build Status](https://travis-ci.org/joinfaces/joinfaces-maven-jar-example.svg?branch=master)](https://travis-ci.org/joinfaces/joinfaces-maven-jar-example)
[![Coverage Status](https://coveralls.io/repos/github/joinfaces/joinfaces-maven-jar-example/badge.svg?branch=master)](https://coveralls.io/github/joinfaces/joinfaces-maven-jar-example?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/5a09afae0fb24f00502441b0/badge.svg?style=flat)](https://www.versioneye.com/user/projects/5a09afae0fb24f00502441b0)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This SAP (Single Page Application) illustrates JSF usage inside JAR packaged Spring Boot Application.

[JoinFaces](http://joinfaces.org) autoconfigures [PrimeFaces](http://primefaces.org/), [BootsFaces](http://bootsfaces.net/), [ButterFaces](http://butterfaces.org), [OmniFaces](http://omnifaces.org/), [AngularFaces](http://angularfaces.com/), [Mojarra](https://javaserverfaces.java.net/) and [MyFaces](http://myfaces.apache.org) libraries to run at embedded [Tomcat](http://tomcat.apache.org/), [Jetty](http://www.eclipse.org/jetty) or [Undertow](http://undertow.io/).

## See Example Application in the cloud

1- Access starter.jsf page at **https://joinfaces-example.herokuapp.com/index.jsf**. This page can help you to choose the JSF Spring Boot Starter that fits your needs. You may login with credentials

User | Password | Roles
-----| -------- | -----
persapiens | 123 | ROLE_ADMIN
nyilmaz | qwe | ROLE_USER

## Run Example Application locally

1- Clone this project
```Shell
git clone https://github.com/joinfaces/joinfaces-maven-jar-example.git
```

2- Build
```Shell
mvn clean install
```

3- Run
```Shell
java -jar target/joinfaces-example-3.0.0.RC1-SNAPSHOT.jar
```

4- Access starter page at **http://localhost:8080/index.jsf**

## Key Files

### pom.xml

Includes joinfaces starter dependency. All other jsf dependencies are included transitively.

```xml
<parent>
    <groupId>org.joinfaces</groupId>
    <artifactId>joinfaces-parent</artifactId>
    <version>3.0.0.RC1</version>
    <relativePath/>
</parent>

<dependencies>
    <dependency>
      <groupId>org.joinfaces</groupId>
      <artifactId>jsf-spring-boot-starter</artifactId>
    </dependency>
</dependencies>
```

If you prefer **Jetty** instead of **Tomcat**, exclude **tomcat-spring-boot-starter** artifactId and add **jetty-spring-boot-starter** in order to use **Jetty** servlet container. 

If you prefer **Undertow** instead of **Tomcat**, exclude **tomcat-spring-boot-starter** artifactId and add **undertow-spring-boot-starter** in order to use **Undertow** servlet container. 

If you prefer **MyFaces** instead of **Mojarra**, exclude **mojarra-spring-boot-starter** artifactId and add **myfaces-spring-boot-starter** in order to use **MyFaces** JSF Implementation. 

If you prefer **PrimeFaces**, add **primefaces-spring-boot-starter** in order to use **PrimeFaces** JSF Components. 

If you prefer **BootsFaces**, add **bootsfaces-spring-boot-starter** in order to use **BootsFaces** JSF Components. 

If you prefer **ButterFaces**, add **butterfaces-spring-boot-starter** in order to use **ButterFaces** JSF Components. 

If you prefer **AngularFaces**, add **angularfaces-spring-boot-starter** in order to use **AngularFaces** JSF Components. 

If you prefer **RichFaces**, add **richfaces-spring-boot-starter** in order to use **RichFaces** JSF Components. 

Note that **spring-boot-starter-security** is included to secure the application.

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
</dependencies>
```

### src/main/resources/application.yml

Configure jsf.PROJECT_STATE and jsf.primefaces.THEME properties.

```yml
jsf:
  PROJECT_STAGE: Development
  primefaces: 
    theme: overcast
```

### src/main/resources/META-INF/resources/content/starter.xhtml

Example page to help you choose the right JSF Spring Boot Starter for you. 

Note that xhtml, js, css and images files should be located at **src/main/resources/META-INF/resources** directory to JSF use them.

Look at **authorize** and **anonymous** jsf spring security facelet tags in action to secure page information.

```xhtml
  <sec:authorize access="hasRole('ROLE_ADMIN')">
    <p:panelGrid columns="1" rendered="#{sec:isFullyAuthenticated()}">
      <p:link title="Logout" href="/logout">
        <p:outputLabel value="You are logged in as an ADMIN" />
      </p:link>
    </p:panelGrid>
  </sec:authorize>
```

### src/main/java/org/joinfaces/example/JoinFacesExampleApplication.java

Very simple spring main application. Only SpringBootApplication annotation is required.

<pre>
@SpringBootApplication
public class JoinFacesExampleApplication {
</pre>

### src/main/java/org/joinfaces/example/SecurityConfig.java

Spring Security configuration class to secure authentication with credentials to persapiens and nyilmaz users.

### src/main/java/org/joinfaces/example/view/StarterMBean.java

Managed bean using ViewScoped CDI annotation. The equivalent spring scope of ViewScoped annotation is configured automatically by Jsf Spring Boot Starter.

<pre>
@Named
<b>@ViewScoped</b>
public class StarterMBean {
</pre>

## Getting Help

* Take a look at [JoinFaces Wiki](https://github.com/joinfaces/joinfaces/wiki).
* Report questions and bugs at [github.com/joinfaces/joinfaces-example/issues](https://github.com/joinfaces/joinfaces-maven-jar-example/issues).

## Contributing

* Report documentation, features, enhancement and bugs at [github.com/joinfaces/joinfaces-example/issues](https://github.com/joinfaces/joinfaces-maven-jar-example/issues).
* Pull requests are welcome.
