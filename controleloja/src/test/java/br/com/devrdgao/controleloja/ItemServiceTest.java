//package br.com.devrdgao.controleloja;
//
//
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//@SpringBootTest
//public class ItemServiceTest {
//	
//	 
//   @Test
//	public void calculoValoresItemTest() {
//	  
//		   BigDecimal precoitem = new BigDecimal("23.00");
//		   BigDecimal quantitem = new BigDecimal("1");
//           BigDecimal totalitem = new BigDecimal("0.00");
//		   BigDecimal valortotal = new BigDecimal("0.00");
//
////package br.com.devrdgao.controleloja;
//
//
//
//import java.math.BigDecimal;
//
//
//@SpringBootTest
//public class ItemServiceTest {
//	
//	 
//   @Test
//	public void calculoValoresItemTest() {
//	  
//		   BigDecimal precoitem = new BigDecimal("23.00");
//		   BigDecimal quantitem = new BigDecimal("1");
//           BigDecimal totalitem = new BigDecimal("0.00");
//		   BigDecimal valortotal = new BigDecimal("0.00");
//
//
//		   BigDecimal esperado = new BigDecimal("23.00");
//
//		  
//		   //Descontos
//		   BigDecimal desc = new BigDecimal("0.00");
//		   BigDecimal descporcent = new BigDecimal("0.00");
//		   BigDecimal porcentagem = new BigDecimal("0.00");
//
//           
//		   totalitem = precoitem.multiply(quantitem);
//		   
//		   porcentagem = (new BigDecimal(0)).divide(new BigDecimal(100));
//		   descporcent = totalitem.multiply(porcentagem).setScale(2, RoundingMode.DOWN);
//		   
//		   valortotal = totalitem.subtract(descporcent).subtract(desc);
//		
//           org.junit.Assert.assertEquals(esperado,valortotal);
//
//	   	  	   
//		   System.out.println(" Total valor " + valortotal);
//		   
//		
//	}
//
//}

//		   BigDecimal esperado = new BigDecimal("23.00");
//
//		  
//		   //Descontos
//		   BigDecimal desc = new BigDecimal("0.00");
//		   BigDecimal descporcent = new BigDecimal("0.00");
//		   BigDecimal porcentagem = new BigDecimal("0.00");
//
//           
//		   totalitem = precoitem.multiply(quantitem);
//		   
//		   porcentagem = (new BigDecimal(0)).divide(new BigDecimal(100));
//		   descporcent = totalitem.multiply(porcentagem).setScale(2, RoundingMode.DOWN);
//		   
//		   valortotal = totalitem.subtract(descporcent).subtract(desc);
//		
//           org.junit.Assert.assertEquals(esperado,valortotal);
//
//	   	  	   
//		   System.out.println(" Total valor " + valortotal);
//		   
//		
//	}
//
//}
