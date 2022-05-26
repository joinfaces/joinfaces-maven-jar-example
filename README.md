JoinFaces Example
=====
[![Heroku](http://heroku-badge.herokuapp.com/?app=joinfaces-example&root=starter.jsf)](https://joinfaces-example.herokuapp.com)
[![Build Status](https://github.com/joinfaces/joinfaces-maven-jar-example/actions/workflows/maven.yml/badge.svg)](https://github.com/joinfaces/joinfaces-maven-jar-example/actions)
[![Codecov](https://codecov.io/gh/joinfaces/joinfaces-maven-jar-example/branch/4.7.x/graph/badge.svg)](https://codecov.io/gh/joinfaces/joinfaces-maven-jar-example)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=joinfaces_joinfaces-maven-jar-example&metric=bugs)](https://sonarcloud.io/dashboard?id=joinfaces_joinfaces-maven-jar-example)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This SAP (Single Page Application) illustrates JSF usage inside JAR packaged Spring Boot Application.

[JoinFaces](http://joinfaces.org) autoconfigures [PrimeFaces](http://primefaces.org/), [PrimeFaces Extensions](http://primefaces-extensions.github.io/), [AdminFaces](https://adminfaces.github.io/site/), [BootsFaces](http://bootsfaces.net/), [ButterFaces](http://butterfaces.org), [IceFaces](http://www.icesoft.org/java/projects/ICEfaces/overview.jsf), [RichFaces](https://github.com/richfaces/richfaces), [RichFaces-AYG](https://github.com/albfernandez/richfaces), [Tobago](https://myfaces.apache.org/#/tobago), [OmniFaces](http://omnifaces.org/), [AngularFaces](http://angularfaces.com/), [Mojarra](https://javaserverfaces.java.net/) and [MyFaces](http://myfaces.apache.org) libraries to run at embedded [Tomcat](http://tomcat.apache.org/), [Jetty](http://www.eclipse.org/jetty) or [Undertow](http://undertow.io/). It autoconfigures [Weld](http://weld.cdi-spec.org) and [Rewrite](https://www.ocpsoft.org/rewrite/) too.

## See Example Application in the cloud

1- Access starter.jsf page at **https://joinfaces-example.herokuapp.com/**. This page can help you to choose the JoinFaces Starter that fits your needs. You may log in with credentials

| User       | Password | Roles      |
|------------|----------|------------|
| persapiens | 123      | ROLE_ADMIN |
| nyilmaz    | qwe      | ROLE_USER  |

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
java -jar target/joinfaces-example-4.7.x.jar
```

4- Access starter page at **http://localhost:8080/**

Optional: If your IDE is showing build errors install [Lombok](https://projectlombok.org/setup/overview)

## Key Files

### pom.xml

Includes joinfaces starter dependency. All other jsf dependencies are included transitively.

```xml
<properties>
   <joinfaces.version>4.7.0</joinfaces.version>
</properties>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.joinfaces</groupId>
            <artifactId>joinfaces-dependencies</artifactId>
            <version>${joinfaces.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<dependencies>
  <dependency>
    <groupId>org.joinfaces</groupId>
    <artifactId>jsf-spring-boot-starter</artifactId>
  </dependency>
</dependencies>
```

Note that **security-spring-boot-starter** is included to secure the application.

```xml
<dependencies>
    <dependency>
        <groupId>org.joinfaces</groupId>
        <artifactId>security-spring-boot-starter</artifactId>
    </dependency>
</dependencies>
```

### src/main/resources/application.yml

Configure jsf.PROJECT_STATE and jsf.primefaces.THEME properties.

```yml
joinfaces:
  jsf:
    PROJECT_STAGE: Development
  primefaces: 
    theme: overcast
```

### src/main/resources/META-INF/resources/content/starter.xhtml

Example page to help you choose the right JoinFaces Starter for you. 

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

Managed bean using ViewScoped CDI annotation. The equivalent spring scope of ViewScoped annotation is configured automatically by JoinFaces Starter.

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
