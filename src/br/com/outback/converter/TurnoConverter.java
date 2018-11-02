package br.com.outback.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.outback.bean.Turno;
import br.com.outback.mb.PlantaoMB;
 
 
@FacesConverter("turnoConverter")
public class TurnoConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        
    	if(value != null && value.trim().length() > 0) {
        	try {
                FacesContext context = FacesContext.getCurrentInstance();
                PlantaoMB plantaoMB = (PlantaoMB) context.getELContext().getELResolver().getValue(context.getELContext(), null, "plantaoMB");
         
                System.out.println("======================");
                System.out.println(plantaoMB.getTurnoAtivo().get(Integer.parseInt(value) - 1));
                //System.out.println(plantaoMB.getTurnoAtivo().get(Integer.parseInt(value)));
                System.out.println("======================");
                
                return plantaoMB.getTurnoAtivo().get(Integer.parseInt(value) - 1);
                
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de convers�o", "Turno n�o v�lido."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Turno) object).getId());
        }
        else {
            return null;
        }
    }  
  
}         
