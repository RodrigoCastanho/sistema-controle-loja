package br.com.devrdgao.controleloja.service;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public  class BigDecimalConverter extends PropertyEditorSupport {
	
	private DecimalFormat df = new DecimalFormat("#,##0.00");	
	    
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
    	
    	if(StringUtils.isEmpty(text)) {
    		
    	 super.setValue(new BigDecimal("0.00"));
    				
    	}else {
    		
    		try {
    			
    			String valoformat = text.replace(".", "").replace(",",".");
                super.setValue(new BigDecimal(valoformat));
    						
    		}
    		catch (Exception e) {
    			
    			throw new IllegalArgumentException(" Valor inválido "+e);

			}
    				
    	}
    	
    }
    
    @Override
    public String getAsText() {
    	
    	if(super.getValue() != null) {
    	  
        	return df.format(getValue());
        	
    	}
		return null;
    	
    }
    	
	
}

	

	
	


