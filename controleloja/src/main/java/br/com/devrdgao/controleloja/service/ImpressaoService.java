package br.com.devrdgao.controleloja.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import br.com.devrdgao.controleloja.models.Pedido;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;

@Service
public class ImpressaoService {
	
	
	public void impremirPedidos(List<Pedido> pedidos) {
			  
		try {
		    
			String path = "/home/rodrigo/";
			File arquivo = ResourceUtils.getFile("classpath:impressaoPedidos.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(arquivo.getAbsolutePath());
			JRBeanCollectionDataSource listPedidos = new JRBeanCollectionDataSource(pedidos);
			Map<String, Object> params = new HashedMap<>();
			params.put("createdBy", "impressaopedido");
			JasperPrint print = JasperFillManager.fillReport(jasperReport, params, listPedidos);
			//JasperPrintManager.printPage(print, 0, true); 
			JasperExportManager.exportReportToPdfFile(print, path+"/impressaoPedidos.pdf");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}  
				
	}
	
}
