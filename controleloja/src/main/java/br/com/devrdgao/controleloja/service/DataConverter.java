package br.com.devrdgao.controleloja.service;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DataConverter extends PropertyEditorSupport {
	
	private SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat dataformat = new SimpleDateFormat("dd/MM/yyyy");
	
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		
		if(!StringUtils.isEmpty(text)) {
		
			try {   
				  
				   super.setValue(data.parse(text));
				     
				   
				}catch (Exception e) {
					
	    			throw new IllegalArgumentException("Data inválida "+e);
					
			}			
			
		}else {
			//Tratar esse Erro quando o campo Data estiver vazio
			super.setValue(null);
		}			
		
	}
	
	@Override
	public String getAsText() {
		
		if(super.getValue() != null) {
			
			return dataformat.format(super.getValue());
			
			
		}else {
            return null;
        }
	}

}
