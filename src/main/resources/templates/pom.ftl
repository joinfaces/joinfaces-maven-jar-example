<#ftl output_format="XML">
<#-- @ftlvariable name="starterService" type="org.joinfaces.example.view.JoinFacesStarterService" -->
<#-- @ftlvariable name="starterMBean" type="org.joinfaces.example.view.StarterMBean" -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>${starterService.springBootVersion}</version>
    <relativePath/>
</parent>

<properties>
   <joinfaces.version>${starterService.joinfacesVersion}</joinfaces.version>
</properties>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.joinfaces</groupId>
            <artifactId>joinfaces-dependencies</artifactId>
            <version>${"$"}{joinfaces.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<dependencies>
<#list starterMBean.componentArtifactIds as artifactId>
  <dependency>
    <groupId>org.joinfaces</groupId>
    <artifactId>${artifactId?lower_case}-spring-boot-starter</artifactId>
    <#if !starterMBean.tomcatSelected || !starterMBean.mojarraSelected>
    <exclusions>
      <#if !starterMBean.tomcatSelected>
      <exclusion>
        <groupId>org.joinfaces</groupId>
        <artifactId>tomcat-spring-boot-starter</artifactId>
      </exclusion>
      </#if>
      <#if !starterMBean.mojarraSelected>
      <exclusion>
        <groupId>org.joinfaces</groupId>
        <artifactId>mojarra-spring-boot-starter</artifactId>
      </exclusion>
      </#if>
    </exclusions>
    </#if>
  </dependency>
</#list>
<#if !starterMBean.tomcatSelected>
  <dependency>
    <groupId>org.joinfaces</groupId>
    <artifactId>${starterMBean.servletContainer?lower_case}-spring-boot-starter</artifactId>
    </dependency>
</#if>
<#if !starterMBean.mojarraSelected>
  <dependency>
    <groupId>org.joinfaces</groupId>
    <artifactId>${starterMBean.jsfImplementation?lower_case}-spring-boot-starter</artifactId>
  </dependency>
</#if>
<#list starterMBean.addonArtifactIds as artifactId>
  <dependency>
    <groupId>org.joinfaces</groupId>
    <artifactId>${artifactId?lower_case}-spring-boot-starter</artifactId>
  </dependency>
</#list>
</dependencies>