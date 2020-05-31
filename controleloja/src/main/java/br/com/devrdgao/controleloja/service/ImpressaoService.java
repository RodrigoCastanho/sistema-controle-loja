package br.com.devrdgao.controleloja.service;


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.ModelAndView;

import br.com.devrdgao.controleloja.models.Pedido;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.view.JasperViewer;

@Service
public class ImpressaoService {
	
	
  public void impremirPedidos(List<Pedido> pedidos, HttpServletResponse response) {
		
		//Map não esta passando valor nenhum...
		Map<String, Object> params = new HashedMap<>();
			  
		
			try {
				//String path = "/home/rodrigo/";
				//File arquivo = ResourceUtils.getFile("classpath:impressaoPedidos.jrxml");
				InputStream arquivo = this.getClass().getResourceAsStream("/impressaoPedidos.jrxml");
				JasperReport jasperReport = JasperCompileManager.compileReport(arquivo);
				JRSaver.saveObject(jasperReport,"impressaoPedidos.jasper");						
				JRBeanCollectionDataSource listPedidos = new JRBeanCollectionDataSource(pedidos);
				JasperPrint print = JasperFillManager.fillReport(jasperReport, params, listPedidos);
				//JasperPrintManager.printPage(print, 0, false); 
				//JasperExportManager.exportReportToPdfFile(print, path+"/impressaoPedidos.pdf");
				response.reset();
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", String.format("inline; filename=\"Cupom não fiscal.pdf\""));
							
				BufferedOutputStream outimp = new BufferedOutputStream(response.getOutputStream()); 
				JasperExportManager.exportReportToPdfStream(print, outimp);
				
				outimp.flush();
				outimp.close();
						
			} catch (JRException e) { 
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
				
	 }
			
}
