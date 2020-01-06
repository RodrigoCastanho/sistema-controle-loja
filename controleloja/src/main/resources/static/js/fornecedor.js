/**
* Rodrigo F Castanho
* 
****/

$('.tabelaFornecedor tbody').on('click','.editar',function(){

    var linha = $(this).closest('tr');
    
    //Colunas
    var cnpj = linha.find('td:eq(0)').text();
    var nome = linha.find('td:eq(1)').text();
    var data = linha.find('td:eq(2)').text();
    var email = linha.find('td:eq(3)').text();
    var telefone = linha.find('td:eq(4)').text();
    var site = linha.find('td:eq(5)').text();
    var endereco = linha.find('td:eq(6)').text();

     
     $("#codCnpj").val(cnpj); 
     $("#nomFor").val(nome); 
     $("#datFor").val(data); 
     $("#emailFor").val(email);
     $("#telFor").val(telefone); 
     $("#sitFor").val(site); 
     $("#endFor").val(endereco); 
      
       
});

$('.tabelaFornecedor tbody').on('click','.listaitens',function(){

   var linha = $(this).closest('tr');

   var cnpj = linha.find('td:eq(0)').text();

   $("#codcnpj").val(cnpj);
   $("#codcnpjadd").val(cnpj);

     
});

//Exibir os itens no modal itens do Fornecedor
$("#exibiritem").click(function(event){

   event.preventDefault();


  var codcnpj = $("#codcnpj").val();

  $.ajax({

        type : "GET",
        url : "exibiritens",
        data : {"cnpj": codcnpj},

        success: function(resul){

         $("#listaItens").html(resul);

        }, 
        error: function(resul){
              
         console.log("Erro Ajax");
        }

      });
});

//Adiciona item no modal do Fornecedor
$("#adicionaitem").click(function(event){

   event.preventDefault();


  var cnpjadd = $("#codcnpjadd").val();
  var coditem = $("#codItem").val();

  $.ajax({

        type : "POST",
        url : "additem",
        data : {"cnpj": cnpjadd, 
                "codigoitem": coditem },

        success: function(resul){

         $("#listaItens").html(resul);

         $("#codItem").val("");

        }, 
        error: function(resul){
              
         console.log("Erro Ajax");
        }

      });
});


 $('.deletar').click(function(event){
     
     event.preventDefault();
     var valorFornUrl = $(this).attr('href');

     $.confirm({
     	
		    title: '<h4>Deletar Fornecedor<h4>',
		    content: 'Deseja deletar esse fornecedor: ' +valorFornUrl+'?',
		    type: 'red',
            typeAnimated: true,
            boxWidth: '30%',
            useBootstrap: false,

		    buttons: {        
        
		       SIM: function () {

		       	     window.location = valorFornUrl;

               },
               NÃO: function () {},
         
            }

       });
   });  





