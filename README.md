Jsf Spring Boot Starter Example
=====
[![Heroku](http://heroku-badge.herokuapp.com/?app=heroku-badge)](https://jsf-spring-boot-starter-sample.herokuapp.com/helloWorld.jsf)
[![Build Status](https://travis-ci.org/persapiens/jsf-spring-boot-starter-example.svg?branch=master)](https://travis-ci.org/persapiens/jsf-spring-boot-starter)
[![Dependency Status](https://www.versioneye.com/user/projects/573c95eace8d0e004130bd98/badge.svg?style=flat)](https://www.versioneye.com/user/projects/573c95eace8d0e004130bd98)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This project illustrates JSF usage inside JAR packaged Spring Boot Application.

The [Jsf Spring Boot Starter](https://github.com/persapiens/jsf-spring-boot-starter) autoconfigure [Mojarra](https://javaserverfaces.java.net/), [Primefaces](http://primefaces.org/) and [Omnifaces](http://omnifaces.org/) libraries to run at embedded [Tomcat](http://tomcat.apache.org/).

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
java -jar target/jsf-spring-boot-starter-example-1.0.0-SNAPSHOT.jar
```

4- Access helloWorld page at **http://localhost:8080/helloWorld.jsf**

## Key Files and Directories

- **pom.xml**: includes jsf-spring-boot-starter dependency. spring-boot-starter-web, tomcat-embed-jasper and jstl dependencies are included transitively.

```xml
<properties>
    <jsf-spring-boot-starter.version>1.0.0</jsf-spring-boot-starter.version>
</properties>
<dependencies>
    <dependency>
      <groupId>com.github.persapiens</groupId>
      <artifactId>jsf-spring-boot-starter</artifactId>
      <version>${jsf-spring-boot-starter.version}</version>
    </dependency>
</dependencies>
```

- **src/main/resources/application.properties**: configure javax.faces.PROJECT_STATE and primefaces.THEME properties.

```properties
javax.faces.PROJECT_STAGE=Development
primefaces.theme=overcast
```

- **src/main/resources/META-INF/resources/helloWorld.xhtml**: example page. Note that xhtml, js, css and images files should be located at **src/main/resources/META-INF/resources** directory to JSF use them.

- **src/main/java/com/github/persapiens/example/JsfSpringBootStarterExampleApplication.java**: @ComponentScan(scopeResolver = CdiScopeResolver.class) enable CDI annotations usage.

<pre>
@EnableAutoConfiguration
<b>@ComponentScan(scopeResolver = CdiScopeResolver.class)</b>
@Configuration
public class JsfSpringBootStarterExampleApplication {
</pre>

- **src/main/java/com/github/persapiens/example/view/HelloWorldMBean.java**: managed bean using ViewScoped cdi annotation.

<pre>
@Named
<b>@ViewScoped</b>
public class HelloWorldMBean {
</pre>
