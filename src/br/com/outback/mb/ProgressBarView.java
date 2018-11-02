package br.com.outback.mb;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;


 
@ManagedBean
@ViewScoped
public class ProgressBarView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer progress;
	private int numTurnos = 80;
		
 
    public Integer getProgress() {
        if(progress == null) {
            progress = 0;
        }
        else {
            progress = progress + (int)(Math.random() * 35);
             
            if(progress > 100)
                progress = 100;
        }
         
        return progress;
    }
    
    
   
	public Integer getProgress2(){
	
		int resultado = 0;
		
		for(int i = 1; i <= 80; i++){
			
			float cont=i;
	        float turnos= numTurnos;
	        
	        float divisao =cont/turnos;
			
			 resultado = (int) (divisao*100);  
			
			
	        System.out.println(resultado);
			
		}
		
		return resultado;
	
	}
 
    public void setProgress(Integer progress) {
        this.progress = progress;
    }
     
    public void onComplete() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Escala gerada!"));
    }
     
    public void cancel() {
        progress = null;
    }
}
