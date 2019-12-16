/**
* Rodrigo F Castanho
* 
****/

$('.tabelaVenda tbody').on('click','.pagamento',function(){
		
	var linha = $(this).closest('tr');
	
	var codvenda = linha.find('td:eq(0)').text();
    var dinheiro = linha.find('td:eq(3)').text();

    
    //$('.'+debito+'').toggle(100);

    $('.'+dinheiro+'').toggle();

});

   
	//var debito = linha.find('td:eq(2)').text();
    //var credito = linha.find('td:eq(4)').text();
