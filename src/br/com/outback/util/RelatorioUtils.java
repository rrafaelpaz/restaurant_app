package br.com.outback.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioUtils {
	
	
	public static void gerarRelatorio(String path, List lista, String nomeRelatorio, HashMap map) throws JRException, IOException{
		
		 // compilacao do JRXML
        String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath(path);
        
        JasperReport report = JasperCompileManager.compileReport(caminho);

        JasperPrint print = JasperFillManager.fillReport(report, map,new JRBeanCollectionDataSource(lista));
                    
        FacesContext context = FacesContext.getCurrentInstance();

        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

        ServletOutputStream responseStream = response.getOutputStream();

        response.setContentType("application/pdf");
     
        response.setHeader("Content-Disposition",nomeRelatorio);

        JasperExportManager.exportReportToPdfStream(print,responseStream);

        responseStream.flush();

        responseStream.close();

        context.renderResponse();

        context.responseComplete();
		
		
	}

}
