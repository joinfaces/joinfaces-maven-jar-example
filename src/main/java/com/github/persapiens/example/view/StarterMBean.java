package com.github.persapiens.example.view;

import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class StarterMBean implements Serializable {
    
    private String servletContainer = "tomcat";

    private String jsfImplementation = "mojarra";
    
    private String jsfComponents = "primefaces";

    public String getServletContainer() {
        return servletContainer;
    }

    public void setServletContainer(String servletContainer) {
        this.servletContainer = servletContainer;
    }

    public String getJsfImplementation() {
        return jsfImplementation;
    }

    public void setJsfImplementation(String jsfImplementation) {
        this.jsfImplementation = jsfImplementation;
    }

    public String getJsfComponents() {
        return jsfComponents;
    }

    public void setJsfComponents(String jsfComponents) {
        this.jsfComponents = jsfComponents;
    }        
     
    public String getStarter() {
        String result = "";
        
        if(!servletContainer.equals("tomcat")) {
            result += "-" + servletContainer ;
        }
        
        if(!jsfImplementation.equals("mojarra")) {
            result += "-" + jsfImplementation;
        }
        
        if(!jsfComponents.equals("primefaces")) {
            result += "-" + jsfComponents;
        }
        
        return "jsf" + result + "-spring-boot-starter";
    }
}
