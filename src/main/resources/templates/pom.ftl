<#ftl output_format="XML">
<properties>
   <joinfaces.version>${environment.getProperty("joinfaces.version")}</joinfaces.version>
</properties>

<dependencies>
<#list starterMBean.artifactIds as artifactId>
  <dependency>
    <groupId>org.joinfaces</groupId>
    <artifactId>${artifactId?lower_case}-spring-boot-starter</artifactId>
    <version>${"$"}{joinfaces.version}</version>
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
    <version>${"$"}{joinfaces.version}</version>
    </dependency>
</#if>
<#if !starterMBean.mojarraSelected>
  <dependency>
    <groupId>org.joinfaces</groupId>
    <artifactId>${starterMBean.jsfImplementation?lower_case}-spring-boot-starter</artifactId>
    <version>${"$"}{joinfaces.version}</version>
  </dependency>
</#if>
</dependencies>