package org.joinfaces.example.view;

import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class StarterMBean implements Serializable {
    
    private String servletContainer = "Tomcat";

    private String jsfImplementation = "Mojarra";
    
    private String jsfComponents = "PrimeFaces";

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
        
        if(!servletContainer.equals("Tomcat")) {
            result += "-" + servletContainer ;
        }
        
        if(!jsfImplementation.equals("Mojarra")) {
            result += "-" + jsfImplementation;
        }
        
        if(!jsfComponents.equals("PrimeFaces")) {
            result += "-" + jsfComponents;
        }
        
        return "jsf" + result.toLowerCase() + "-spring-boot-starter";
    }
}
