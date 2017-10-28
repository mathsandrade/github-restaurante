/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import Models.Endereco;
import Models.Pessoa;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matheus
 */
public class PessoaViewModel {
    
    public void list(List<Pessoa> pessoas, HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pessoas", pessoas);
        request.getRequestDispatcher("pessoas/list.jsp").forward(request, response);
    }
    
    public void novo (HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("action", "create");
        request.getRequestDispatcher("pessoas/new.jsp").forward(request, response);
    }
    
    public void create (int pessoaId, HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/pessoas");
    }
    
    public void edit (Pessoa pessoa, HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("action", "update");
        request.setAttribute("pessoa", pessoa);
        request.getRequestDispatcher("pessoas/edit.jsp").forward(request, response);
    }
    
    public void update(int pessoaId, HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/pessoas");
    }
    
    public void delete(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/pessoas");
    }
    
    public void validarPessoa(Pessoa pessoa, String erro_cpf, String erro_email, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setAttribute("mensagem", erro_cpf);
        request.setAttribute("mensagem2", erro_email);
        request.setAttribute("pessoa", pessoa);
        request.setAttribute("action", "create");
        request.getRequestDispatcher("pessoas/new.jsp").forward(request, response);
    }
    
    public Pessoa getPessoa(HttpServletRequest request) {
        Pessoa pessoa = new Pessoa();
        Endereco endereco = new Endereco();
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            pessoa.setId(id);
        } catch (Exception e) {
            pessoa.setId(0);
        }
        pessoa.setNome(request.getParameter("pessoa.nome"));
        pessoa.setEmail(request.getParameter("pessoa.email"));
        try {
            int idade = Integer.valueOf(request.getParameter("pessoa.idade"));
            pessoa.setIdade(idade);
        } catch (Exception e) {
            pessoa.setIdade(0);
        }
        pessoa.setTelefone(request.getParameter("pessoa.tele"
                + "fone"));
        pessoa.setCpf(request.getParameter("pessoa.cpf"));
        try {
            int numero = Integer.valueOf(request.getParameter("endereco.numero"));
            endereco.setNumero(numero);
        } catch (Exception e) {
            endereco.setNumero(0);
        }
         try {
            int id = Integer.valueOf(request.getParameter("id_endereco"));
            endereco.setId(id);
        } catch (Exception e) {
            endereco.setId(0);
        }
        endereco.setRua(request.getParameter("endereco.rua"));
        endereco.setBairro(request.getParameter("endereco.bairro"));
        endereco.setCep(request.getParameter("endereco.cep"));
        endereco.setCidade(request.getParameter("endereco.cidade"));
        pessoa.setEndereco(endereco);
        return pessoa;
    }
}
