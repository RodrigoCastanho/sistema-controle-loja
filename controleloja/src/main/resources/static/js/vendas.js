/**
* Rodrigo F Castanho
* 
****/

$('.tabelaVenda tbody').on('click','.listaitens',function(){
		
	var linha = $(this).closest('tr');
	
	var codvenda = linha.find('td:eq(0)').text();

    $("#codvenda").val(codvenda); 


});


$("#exibiritem").click(function(event){

  event.preventDefault();

  var codigovenda = $("#codvenda").val();
  var imprimir = false;
  
 $.get("itensvenda",{"codigovenda": codigovenda,
                      "imprimir": imprimir})

 	   .done (function(result){ 
         $("#itemvenda").html(result);
    
      }).fail (function(result){
           console.log("Erro ajax");
         }); 
});

$("#imprimiritem").click(function(){
	
  var codigovenda = $("#codvenda").val();	
  var imprimir = true;	
  $.get("itensvenda",{"codigovenda": codigovenda, 
	  				          "imprimir": imprimir})

 	  .done (function(result){ 
        $("#itemvenda").html(result);
		    
     }).fail (function(result){
         console.log("Erro ajax");
        }); 		
});



 $('.deletar').click(function(event){
     
     event.preventDefault();
     var valorVendaUrl = $(this).attr('href');

     $.confirm({
     	
		    title: '<h4>Deletar Venda<h4>',
		    content: 'Deseja deletar essa venda: ' +valorVendaUrl+'?',
		    type: 'red',
            typeAnimated: true,
            boxWidth: '30%',
            useBootstrap: false,

		    buttons: {        
        
		       SIM: function () {

		       	     window.location = valorVendaUrl;

               },
               NÃO: function () {},
         
            }

  });

}); 

$('.buscar').click(function(event){
   
     event.preventDefault();

    $.get("buscar")
        .done(function(result) {

           $("#tabelavendacaixa").html(result);


        }) .fail(function() {
                    console.log("Erro ajax");
        });
   
}); 

$('.buscarTudo').click(function(event){
   
     event.preventDefault();

    $.get("buscartudo")
        .done(function(result) {

           $("#tabelavendacaixa").html(result);


        }) .fail(function() {
                    console.log("Erro ajax");
        });
   
}); 

$('.buscarbaixas').click(function(event){
   
     event.preventDefault();

    $.get("buscarbaixascaixa")
        .done(function(result) {

           $("#tabelavendabxcaixa").html(result);


        }) .fail(function() {
                    console.log("Erro ajax");
        });
   
}); 







   