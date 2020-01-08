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
  
 $.get("itensvenda",{"codigovenda": codigovenda},

 	 function(result){ 
        $("#itemvenda").html(result);
    
  })
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

   