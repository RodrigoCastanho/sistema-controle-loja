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
   