/**
* Rodrigo F Castanho
* 
****/

$('.tabelaItem tbody').on('click','.editar',function(){

    var linha = $(this).closest('tr');
    
    //Colunas
    var codigoitem = linha.find('td:eq(0)').text();
    var descricao = linha.find('td:eq(1)').text();
    var precovenda = linha.find('td:eq(2)').text();
    var valoritem = linha.find('td:eq(3)').text();
    var categoriaitem = linha.find('td:eq(4)').text();
    var quantidade = linha.find('td:eq(5)').text();
    var quantminima = linha.find('td:eq(6)').text();

     
     $("#codItem").val(codigoitem); 
     $("#descItem").val(descricao); 
     $("#preVenda").val(precovenda); 
     $("#valItem").val(valoritem);
     $("#quaItem").val(quantidade); 
     $("#quaEstoque").val(quantminima); 

      
       
});
  

 $('.deletar').click(function(event){
     
     event.preventDefault();
     var valorUrl = $(this).attr('href');

     $.confirm({
     	
		    title: '<h4>Deletar Item!<h4>',
		    content: 'Deseja deletar esse item: ' +valorUrl+'?',
		    type: 'red',
            typeAnimated: true,
            boxWidth: '30%',
            useBootstrap: false,

		    buttons: {        
        
		       SIM: function () {

		       	     window.location = valorUrl;

               },
               NÃO: function () {},
         
            }

  });

}); 



