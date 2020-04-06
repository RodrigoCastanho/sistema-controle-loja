/**
* Rodrigo F Castanho
* 
****/

  $("#pedido").click(function(event){

    event.preventDefault();

    habilitaBotao();

    var codigoitem = $("#codItem").val();

    $.ajax({

        type : "GET",
        url : "itemcaixa",
        data : {"buscaritem": codigoitem},

        success: function(resul){

         $("#item").html(resul);

        }, 
        error: function(resul){
              
         console.log("Erro Ajax");
        }

      });

  });
   
   //Habilita botao "Adicionar Pedido", quando botao "Pedido" for precionado
   function habilitaBotao(){
  
    
      $("#adicionapedido").removeAttr('disabled');

   }

  formaPagamento(); //Função usada para controlar acesso as forma de pagamento no click dos botões.
  pagamentoDinheiro();//Função que calcula o Troco da forma de pagamento em Dinheiro.
  pagamentoCredito();//Função que calcula quantidade de parcelas de uma compra, forma de pagamento Crédito.
   
  function formaPagamento(){

      $('#pagdinheiro').click(function() {
        $('.dinheiro').toggle(100);
        $("#valordinheiro").val("Dinheiro");
        $("#valordebito").val("");
        $("#valorcredito").val("");
        $('.debito').hide();
        $('.credito').hide();


      });

       $('#pagdebito').click(function() {

         $('.debito').toggle(100);
         $("#valordebito").val("Débito");
         $("#valorcredito").val("");
         $("#valordinheiro").val("");
         $('.credito').hide();
         $('.dinheiro').hide();

 
       });
          

       $('#pagcredito').click(function() {
        $('.credito').toggle(100);
        $("#valorcredito").val("Crédito");
        $("#valordebito").val("");
        $("#valordinheiro").val("");
        $('.dinheiro').hide();
        $('.debito').hide();

       });
   }

  function pagamentoDinheiro(){

      
      $('input#valReb').on('keyup',function(){
           

           var valorrecebido = $("#valReb").val().replace(".","").replace(",",".");
            
           var valorvenda = $("#valVenda").html().replace(".","").replace(",",".");
           
           if(valorrecebido != "") {
        	   
        	   var valortroco = (parseFloat(valorvenda || 0) - parseFloat(valorrecebido || 0));  
        	   
        	   $("#valTro").val(valortroco.toFixed(2).replace(".",",").replace("-",""));
        	   
           } else { 
        	   
        	   $("#valTro").val("");
           }     

      });

  }

  function pagamentoCredito(){

   $('.nparcelas').change(function(){

        var valorvenda = $("#valVenda").html().replace(".","").replace(",",".");

        var parcelas = ($(this).val());

        var valorparcelado = (parseFloat(valorvenda) / parcelas);

        $("#valorparcela").val(valorparcelado.toFixed(2).replace(".",","));

    });
  }

  var usuario = $('.usuario').html();
  $('.sessao').val(usuario); 

  exibirValorAberturaCaixa();
   
  function exibirValorAberturaCaixa(){
          

      $('.btiniciar').click(function(){

          event.preventDefault();


          var usuario = $('.usuario').html();
          $('.sessao').val(usuario); 


          var valorCaixa = $("#iniCaixa").val();
          var sessaoUsuario =  $(".sessao").val();
         
           localStorage.setItem('iniciarcaixa',valorCaixa);  

           $.get("valorcaixa",{"valorinicial": valorCaixa, "sessaousuario": sessaoUsuario})
                .done(function() {

                     $('.valordocaixa').html(localStorage.getItem('iniciarcaixa')); 
                     $("#iniCaixa").val('');

                  }) .fail(function() {
                        console.log("Erro ajax");
                      });

      });

      $('.opcoes').click(function(){
          $('.valordocaixa').html(localStorage.getItem('iniciarcaixa'));

      });
      
   
  
  }