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
   
   //Habilita botao "Adicionar Pedido", quando botao "Pedido" for prescionado
   function habilitaBotao(){
      $("#adicionapedido").removeAttr('disabled');
   }

  
  formasPagamento();//Funçao controla interaçao das formas de pagamentos
  exibirValorAberturaCaixa();

   
   var valoresArray = [];
   function formasPagamento() {
       
       $('.formdin').on('click',function() {
         if($( ".formdin:checked" ).val()) {

            $('#valordinheiro').val("Dinheiro"); 
            $('.tbformpg .dinheirotr').show();
            $('#descontofinal').show();


            //Calculo do troco se necessario
             pagamentoDinheiro("valReb", "valTro");
             exibeDescontoValorTotal("descvalortotal");
      
             $('#totaldinheiro').on('blur',function() {
                  var valordinheiro = $(this).val().replace(".","").replace(",",".");
                  soma(valordinheiro,"Dinheiro");   
              });


          }else {
             $('.tbformpg .dinheirotr').hide(); 
             $('#totaldinheiro').val("");
             $('#valordinheiro').val("");
             $('#descontofinal').hide();
             $('#descvalortotal').val("");
             valoresArray.splice(0,1);

          } 
       });

       $('.formdeb').on('click',function() {
          if($( ".formdeb:checked" ).val()){

            $('#valordebito').val("Débito");
            $('.tbformpg .debitotr').show();
            $('#descontofinal').show();

            exibeDescontoValorTotal("descvalortotal");

            $('#totaldebito').on('blur',function() {
               var valordebito = $(this).val().replace(".","").replace(",",".");
                soma(valordebito,"Debito");

            });

           }else {
              $('.tbformpg .debitotr').hide(); 
              $('#totaldebito').val("");
              $('#valordebito').val("");
              $('#descontofinal').hide();
              $('#descvalortotal').val("");
              valoresArray.splice(1,1);

            }   
          
       });

       $('.formcred').on('click',function() {
          if($( ".formcred:checked" ).val()) { 
              $('#valorcredito').val("Crédito");
              $('.tbformpg .creditotr').show();

               //Calculo das parcelas se necessario
               pagamentoCredito("parcelas", "valorparc", "totalcredito");

              $('#totalcredito').on('blur',function(){
                  var valorcredito = $(this).val().replace(".","").replace(",",".");
                  soma(valorcredito,"Credito");  

              });
           
            }else {
                $('.tbformpg .creditotr').hide(); 
                $('#totalcredito').val("");
                $('#valorcredito').val("");
                $('#descontofinal').hide();
                $('#descvalortotal').val("");
                valoresArray.splice(2,1);

             }         

        }); 


      function soma(valormisto, pagamento) {
         
         switch (pagamento) {
             case 'Dinheiro':   
             valoresArray[0] = valormisto;
             break;

             case 'Debito':
             valoresArray[1] = valormisto; 
             break;

             case 'Credito':
             valoresArray[2] = valormisto;
             break;

         }

         var total = valoresArray.reduce(function(ant, at) {
            return valortotal = (parseFloat(ant || 0) + parseFloat(at || 0)).toFixed(2).replace(".",",");        
         });
          
         $('#totalmisto').html(" R$ "+total); 

      }

   }
    

  function pagamentoDinheiro(valReb, valTro) {

      $('#'+valReb).on('keyup',function() {
           
           var valorrecebido = $('#'+valReb).val().replace(".","").replace(",",".");
            
           var valorvenda = $("#valVenda").html().replace(".","").replace(",",".");
           
           if(valorrecebido != "") {
        	   
        	   var valortroco = (parseFloat(valorvenda || 0) - parseFloat(valorrecebido || 0));  
        	   
        	   $('#'+valTro).val(valortroco.toFixed(2).replace(".",",").replace("-",""));
        	   
           } else {
        	   $('#'+valTro).val("");
           }
           

      });
                   
  }
  
  
  function pagamentoCredito(parcelas, valorparc, totalcredito) {

   $('#'+parcelas).change(function() {

          
            var valorparcela = $('#'+valorparc).val().replace(".","").replace(",",".");
            var parcelas = ($(this).val());
            var valorparcelado = (valorparcela * parcelas);
            $('#'+totalcredito).val(valorparcelado.toFixed(2).replace(".",","));   
             
      
     }); 

     //  $('.nparcelas').change(function() {

    //     var valorvenda = $("#valVenda").html().replace(".","").replace(",",".");

    //     var parcelas = ($(this).val());

    //     var valorparcelado = (parseFloat(valorvenda) / parcelas);

    //     $("#valorparcela").val(valorparcelado.toFixed(2).replace(".",","));

    //   });


  }
   
  function exibeDescontoValorTotal(descValorTotal) {
     
      $('#'+descValorTotal).on('keyup',function() { 
        
          var valorvenda = $("#valVenda").html().replace(".","").replace(",",".");                
          var porcentagem = (valorvenda * (parseFloat($('#'+descValorTotal).val()/100)));
          
        if($('#'+descValorTotal).val() != "") {
          
          $('#resultdesc').html("R$:" +(valorvenda - porcentagem).toFixed(2).replace(".",",").replace("-","")); 
          
        } else {
        
          $('#resultdesc').html("");   
          
        }
          
      });

  }

  var usuario = $('.usuario').html();
  $('.sessao').val(usuario); 

   
  function exibirValorAberturaCaixa() {
          

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