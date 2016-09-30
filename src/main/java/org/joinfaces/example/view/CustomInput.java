package org.joinfaces.example.view;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;
import java.util.Map;

@FacesComponent(createTag = true, namespace = "http://joinfaces.org/example",
        tagName = "custom-input", value = "org.joinfaces.example.view.CustomInput")
public class CustomInput extends UIInput {

    @Override
    public void decode(FacesContext context) {
        Map requestMap = context.getExternalContext().getRequestParameterMap();
        String clientId = getClientId(context);
        char sep = UINamingContainer.getSeparatorChar(context);
        String submitted_hello_msg = ((String) requestMap.get(clientId + sep + "inputfield"));
        setSubmittedValue(submitted_hello_msg);
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        String clientId = getClientId(context);
        char sep = UINamingContainer.getSeparatorChar(context);
        encodeInputField(context, clientId + sep + "inputfield");
        encodeSubmitButton(context, clientId + sep + "submit");
        encodeOutputField(context);
    }

    private void encodeInputField(FacesContext context, String clientId) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("input", this);
        writer.writeAttribute("type", "text", null);
        writer.writeAttribute("name", clientId, "clientId");
        writer.writeAttribute("size", "30", null);
        Object value = getValue();
        if (value != null) {
            writer.writeAttribute("value", value.toString(), "value");
        }
        writer.writeAttribute("size", "6", null);
        writer.endElement("input");
    }

    private void encodeSubmitButton(FacesContext context, String clientId) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("input", this);
        writer.writeAttribute("type", "Submit", null);
        writer.writeAttribute("name", clientId, "clientId");
        writer.writeAttribute("value", "Click Me!", null);
        writer.endElement("input");
    }

    private void encodeOutputField(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String hellomsg = (String) getAttributes().get("value");
        writer.startElement("p", this);
        writer.writeText("You entered: " + hellomsg, null);
        writer.endElement("p");
    }

}