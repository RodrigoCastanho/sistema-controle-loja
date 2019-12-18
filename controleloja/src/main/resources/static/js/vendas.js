/**
* Rodrigo F Castanho
* 
****/

$('.tabelaVenda tbody').on('click','.listaitens',function(){
		
	var linha = $(this).closest('tr');
	
	var codvenda = linha.find('td:eq(0)').text();

    $("#codvenda").val(codvenda); 





});

  
