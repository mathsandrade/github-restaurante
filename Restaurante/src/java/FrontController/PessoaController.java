/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontController;

import Models.Pessoa;
import Services.PessoaService;
import ViewModel.PessoaViewModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matheus
 */
//pessoas?a=new
//pessoas?a=create
//pessoas?a=edit
//pessoas?a=update
//pessoas?a=delete
@WebServlet(name = "PessoaController", urlPatterns = {"/pessoas"})
public class PessoaController extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
        String action = request.getParameter("a");
        if (action == null)
            action = "";
        PessoaService service = new PessoaService();
        PessoaViewModel viewModel = new PessoaViewModel();
        
        if (action.equals("new")) {
            viewModel.novo(request, response);
            
        } else if (action.equals("create")) {
            Pessoa pessoa = viewModel.getPessoa(request);
            Pessoa valida_cpf = service.selectcpf(viewModel.getPessoa(request));
            Pessoa valida_email = service.selectemail(viewModel.getPessoa(request));
                
            if( valida_cpf != null || valida_email !=null){
                String erro_cpf =  service.validarCpf(valida_cpf);
                String erro_email = service.validarEmail(valida_email);
                viewModel.validarPessoa(pessoa, erro_cpf, erro_email, request, response);
            } else {
                    pessoa = service.create(pessoa);
                    viewModel.create(pessoa.getId(), request, response);
            }
     
        } else if (action.equals("edit")) {
            Pessoa pessoa = viewModel.getPessoa(request);
            pessoa = service.select(pessoa);
            viewModel.edit(pessoa,request, response);
            
        } else if (action.equals("update")) {
            Pessoa pessoa = viewModel.getPessoa(request);
            pessoa = service.update(pessoa);
            viewModel.update(pessoa.getId(), request, response);
            
        } else if (action.equals("delete")) {
            Pessoa pessoa = viewModel.getPessoa(request);
            service.delete(pessoa);
            viewModel.delete(request, response);
            
        } else {
            List<Pessoa> pessoas = service.list();
            viewModel.list(pessoas, request, response);
        }
    }
}
