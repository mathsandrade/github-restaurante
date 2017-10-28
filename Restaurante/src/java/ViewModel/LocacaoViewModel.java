/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import Models.Locacao;
import Models.Mesa;
import Models.Pessoa;
import Utils.IniciandoLocacao;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matheus
 */
public class LocacaoViewModel {
 
    public void list(List<Locacao>  locacoes, HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("locacoes", locacoes);
        request.getRequestDispatcher("locacoes/list.jsp").forward(request, response);
    }
    
    public void iniciarLocacao(IniciandoLocacao locacao, HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        request.setAttribute("mesas", locacao.getMesas());
        request.getSession().setAttribute("locacao", locacao.getLocacao());
        request.getRequestDispatcher("locacoes/new.jsp").forward(request, response);
    }
    
    public void validarPessoa(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        request.getRequestDispatcher("locacoes/validarPessoa.jsp").forward(request, response);
    }
    
    public void realizarLocacao(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/locacoes?a=success");
    }
    
     public void success(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        request.getRequestDispatcher("locacoes/success.jsp").forward(request, response);
    }
     
    public void edit (Locacao locacao, HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("action", "update");
        request.setAttribute("locacao", locacao);
        request.getRequestDispatcher("locacoes/edit.jsp").forward(request, response);
    }
    
    public void update(int locacaoId, HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/locacoes");
    }
        
    public void validarLocacao(Locacao locacao, String mensagem, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setAttribute("mensagem", mensagem);
        request.setAttribute("pessoa", locacao.getPessoa());
        request.setAttribute("action", "new");
        request.getRequestDispatcher("locacoes/validarPessoa.jsp").forward(request, response);
    }
      
    public Locacao getLocacao(HttpServletRequest request){
        Locacao locacao = new Locacao();
        Pessoa pessoa = new Pessoa();
        Mesa mesa = new Mesa();
        try{
            locacao.setId(Integer.valueOf(request.getParameter("locacao.id")));
        }catch (Exception e){
            locacao.setId(0);
        }
        
        
        
        String data = request.getParameter("locacao.data");
            if(data != null){
                   data = data.replace("T"," ");
                   DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                   try{
                        Date date = formatter.parse(data);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        locacao.setData(calendar);
                   } catch(ParseException e){
                        e.printStackTrace();
                   }        
            }else{
                Calendar calendar = Calendar.getInstance();
                locacao.setData(calendar);
            }
        /**try{
            
            String data = request.getParameter("locacao.data");
            data = data.replace("T"," ");
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = formatter.parse(data);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            locacao.setData(calendar);
            
        }catch(Exception e){
            e.printStackTrace();
            Calendar calendar = Calendar.getInstance();
            locacao.setData(calendar);
        }*/    
        locacao.setStatus(request.getParameter("locacao.status"));
        
        try{
            int id = Integer.valueOf(request.getParameter("pessoa.id"));
            pessoa.setId(id);
           // pessoa.setId(Integer.valueOf(request.getParameter("pessoa.id")));
        } catch(Exception e){
            pessoa.setId(0);
        }
        
        try{
            int id = Integer.valueOf(request.getParameter("mesa.id"));
            mesa.setId(id);
           // mesa.setId(Integer.valueOf(request.getParameter("mesa.id")));
        }catch(Exception e){
            mesa.setId(0);
        }
        
        locacao.setPessoa(pessoa);
        locacao.setMesa(mesa);
        
        return locacao;
    }
    
}
