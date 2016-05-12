# Jsf Spring Boot Starter Example

This project illustrates JSF usage inside JAR packaged Spring Boot Application.

The [Jsf Spring Boot Starter](https://github.com/persapiens/jsf-spring-boot-starter) autoconfigure [Mojarra](https://javaserverfaces.java.net/), [Primefaces](http://primefaces.org/) and [Omnifaces](http://omnifaces.org/) libraries to run at embedded [Tomcat](http://tomcat.apache.org/).

Key Files

- pom.xml: includes jsf-spring-boot-starter dependency. spring-boot-starter-web, tomcat-embed-jasper and jstl dependencies are included transitively.

- src/main/resources/application.properties: configure javax.faces.PROJECT_STATE and primefaces.THEME properties.

- src/main/resources/META-INF/resources/helloWorld.xhtml: example page. Note that xhtml, js, css and images files should be located at src/main/resources/META-INF/resources directory to JSF use them.

- src/main/java/com/github/persapiens/example/JsfSpringBootStarterExampleApplication.java: @ComponentScan(scopeResolver = CdiScopeResolver.class) enable CDI annotations usage.

- src/main/java/com/github/persapiens/example/view/HelloWorldMBean.java: managed bean using ViewScoped cdi annotation.


