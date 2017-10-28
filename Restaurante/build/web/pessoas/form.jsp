<%-- 
    Document   : form
    Created on : 14/11/2016, 23:03:45
    Author     : Matheus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(document).ready(function() {

            function limpa_formulário_cep() {
                // Limpa valores do formulário de cep.
                $("#rua").val("");
                $("#bairro").val("");
                $("#cidade").val("");
                $("#uf").val("");
                $("#ibge").val("");
            }
            
            //Quando o campo cep perde o foco.
            $("#cep").blur(function() {

                //Nova variável "cep" somente com dígitos.
                var cep = $(this).val().replace(/\D/g, '');

                //Verifica se campo cep possui valor informado.
                if (cep != "") {

                    //Expressão regular para validar o CEP.
                    var validacep = /^[0-9]{8}$/;

                    //Valida o formato do CEP.
                    if(validacep.test(cep)) {

                        //Preenche os campos com "..." enquanto consulta webservice.
                        $("#rua").val("...");
                        $("#bairro").val("...");
                        $("#cidade").val("...");
                        $("#uf").val("...");
                        $("#ibge").val("...");

                        //Consulta o webservice viacep.com.br/
                        $.getJSON("//viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                            if (!("erro" in dados)) {
                                //Atualiza os campos com os valores da consulta.
                                $("#rua").val(dados.logradouro);
                                $("#bairro").val(dados.bairro);
                                $("#cidade").val(dados.localidade);
                                $("#uf").val(dados.uf);
                                $("#ibge").val(dados.ibge);
                            } //end if.
                            else {
                                //CEP pesquisado não foi encontrado.
                                limpa_formulário_cep();
                                alert("CEP não encontrado.");
                            }
                        });
                    } //end if.
                    else {
                        //cep é inválido.
                        limpa_formulário_cep();
                        alert("Formato de CEP inválido.");
                    }
                } //end if.
                else {
                    //cep sem valor, limpa formulário.
                    limpa_formulário_cep();
                }
            });
});
</script>
<form method="POST" action="${pageContext.request.contextPath}/pessoas?a=${action}" >
    <input type="hidden" name="id" value="${pessoa.id}">
    <input type="hidden" name="id_endereco" value="${pessoa.endereco.id}">
<div class="container-fluid"> 
    <div class="row">
      <div id="form_pessoa">
      <div class="form-group">
          <div class="col-sm-8">
              <label for="pessoa.nome">Nome:</label>
              <input type="text" class="form-control" name="pessoa.nome" value="${pessoa.nome}" required>
          </div>
          <div class="col-sm-4">
              <label for="pessoa.telefone">Telefone:</label>
              <input type="text" class="form-control phone" name="pessoa.telefone" value="${pessoa.telefone}">
          </div>
      </div>
      <div class="form-group">
          <div class="col-sm-2">
              <label for="pessoa.idade">Idade:</label>
              <input type="text" class="form-control" name="pessoa.idade" value="${pessoa.idade}">
          </div>
          <div class="col-sm-4">
              <label for="pessoa.cpf">CPF:</label><span style="color: red"> ${mensagem}</span>
              <input type="text" id="foo" class="form-control cpf" name="pessoa.cpf" value="${pessoa.cpf}" required>
          </div>
          <div class="col-sm-6">
              <label for ="pessoa.email">Email:</label><span style="color: red"> ${mensagem2}</span>
              <input type="email" class="form-control" name="pessoa.email" value="${pessoa.email}" required>
          </div>
      </div>
      </div>
    </div>
    
    <!-- Endereco -->
    <p><h4>Endereço:</h4>
	<div id="form_endereco">
    	<div class="row">
          <div class="form-group">
            <div class=col-sm-4>
              <label for="endereco.cep">CEP:</label>
              <input type="text" class="form-control cep" name="endereco.cep" value="${pessoa.endereco.cep}" id="cep" maxlength="9" size="10" required>
            </div>
            <div class="col-sm-6">
              <label for="endereco.cidade">Cidade:</label>
              <input type="text" class="form-control" name="endereco.cidade" value="${pessoa.endereco.cidade}" id="cidade" size="40" readonly>
            </div>
            <div class="col-sm-2">
              <label for="endereco.numero">Numero:</label>
              <input type="text" class="form-control" name="endereco.numero" value="${pessoa.endereco.numero}">
            </div>
        </div>
           <div class="form-group">
            <div class="col-sm-6">
              <label for="endereco.bairro">Bairro:</label>
              <input type="text" class="form-control" name="endereco.bairro" value="${pessoa.endereco.bairro}" id="bairro" size="40" readonly>
            </div>
            <div class="col-sm-6">
              <label for="endereco.rua">Rua:</label>
              <input type="text" class="form-control" name="endereco.rua" value="${pessoa.endereco.rua}"  id="rua" size="60" readonly> 
            </div>        
           </div> 
        </div>
        </div>
        <div class="row">
        <div class="col-sm-1" style="margin-top: 20px">
            <input type="submit" class="btn btn-success" name="commit" value="Salvar">
        </div>
        <div class="col-sm-1" style="margin-top: 20px">
            <c:if test="${action eq \"update\"}">
                <a class="btn btn-danger" href="${pageContext.request.contextPath}/pessoas?a=delete&id=${pessoa.id}&id_endereco=${pessoa.endereco.id}">Deletar</a>
                <script>
                $(document).ready(function() {
                    document.getElementById('foo').readOnly = true;
                  });
                </script>    
            </c:if>
        </div>
        </div>
</div>
</form>
