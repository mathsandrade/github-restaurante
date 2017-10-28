/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontController;

import Models.*;
import Services.*;
import Utils.IniciandoLocacao;
import ViewModel.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Matheus
 */
@WebServlet(name = "LocacaoController", urlPatterns = {"/locacoes"})
public class LocacaoController extends HttpServlet {

    private LocacaoService service;
    
    public LocacaoController(){
        super();
        service = new LocacaoService();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("a");
        if (action == null)
            action = "";
        
        LocacaoViewModel viewModel = new LocacaoViewModel();
 
        if (action.equals("new")) {
            PessoaService pservice = new PessoaService();
            PessoaViewModel pvm = new PessoaViewModel();
            Pessoa p = pvm.getPessoa(request);
            Pessoa pessoa_cpfconfirma = pservice.selectcpf(p);
            Locacao loc = viewModel.getLocacao(request);
            loc.setPessoa(pessoa_cpfconfirma);
            String mensagem2 = null;
                    
                    
            //caso o cpf não existir no banco, redireciona o usuario para o cadastro de uma pessoa 
            if(pessoa_cpfconfirma == null){     
                String cpf_valido = "O CPF digitado não existe.";
                pvm.validarPessoa(pessoa_cpfconfirma, cpf_valido, mensagem2, request, response);
            
            //caso já existir uma reserva no cpf em questão, notifica o usuario    
            } else if (service.checarLocacaoAtiva(loc) != null ){
                String mensagem = "Este CPF já possui uma reserva ativa.";
                viewModel.validarLocacao(loc, mensagem, request, response);
            
            } else{
                IniciandoLocacao locacao = service.iniciarLocacao(pessoa_cpfconfirma, loc);
                //faz a checagem em relação ao horario
                if (locacao != null){
                    //caso não houver nenhum empecilho, segue o jogo
                    locacao.getLocacao().setData(loc.getData());
                    viewModel.iniciarLocacao(locacao, request, response);
                } else {
                    //caso o horario for inferior a 2 horas perante a data atual (service para mais detalhes) impede de continuar
                    String mensagem = "Não há mesas disponiveis.";
                    viewModel.validarLocacao(loc, mensagem, request, response);
                }
            }
            
        } else if (action.equals("validarPessoa")){
            viewModel.validarPessoa(request, response);
            
        } else if (action.equals("create")){
            HttpSession session = request.getSession();
            Locacao locacao = (Locacao) session.getAttribute("locacao");
            session.removeAttribute("locacao");
            Locacao lmesa = viewModel.getLocacao(request);
            locacao.setMesa(lmesa.getMesa());
            //executa o método de inserir a locação no banco
            service.realizarLocacao(locacao);
            //executa o método para enviar email
            service.enviarEmail(locacao);
            viewModel.realizarLocacao(request, response);
        
        } else if (action.equals("success")){
            viewModel.success(request, response);
            
        } else if (action.equals("edit")) {
            Locacao locacao = viewModel.getLocacao(request);
            locacao = service.select(locacao);
            viewModel.edit(locacao,request, response);
            
        } else if (action.equals("update")) {         
            Locacao locacao = viewModel.getLocacao(request);
            locacao = service.update(service.select(locacao));
            viewModel.update(locacao.getId(), request, response);
        
            
        } else if (action.equals("cancelar")){  
            Locacao locacao = viewModel.getLocacao(request);
            locacao = service.cancelarLocacao(service.select(locacao));
            viewModel.update(locacao.getId(), request, response);
            
        } else if (action.equals("concluir")){
            Locacao locacao =  viewModel.getLocacao(request);
            locacao = service.concluirLocacao(service.select(locacao));
            viewModel.update(locacao.getId(), request, response);
        }
        //se não for nenhuma das ações, irá listar as locações
        else{
            List<Locacao> locacoes = service.list();
            viewModel.list(locacoes, request, response);
        } 
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
