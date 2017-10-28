/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import Models.Mesa;
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
public class MesaViewModel {
    
    public void list(List<Mesa> mesas, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("mesas", mesas);
        request.getRequestDispatcher("mesas/list.jsp").forward(request, response);
    }
    
    
    public void novo(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        request.setAttribute("action", "create");
        request.getRequestDispatcher("mesas/new.jsp").forward(request, response);
    }
    
    public void create (int mesaId, HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/mesas");
    }
    
    public void edit (Mesa mesa, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setAttribute("action", "update");
        request.setAttribute("mesa", mesa);
        request.getRequestDispatcher("mesas/edit.jsp").forward(request, response);
    }
    
    public void update(int mesaId, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/mesas");
    }
    
    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/mesas");
    }
    
    public void validarMesa(Mesa mesa, String mensagem, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setAttribute("mensagem", mensagem);
        request.setAttribute("action", "create");
        request.getRequestDispatcher("mesas/new.jsp").forward(request, response);
    }
    
    public Mesa getMesa(HttpServletRequest request){
        
        Mesa mesa = new Mesa();
        try{
            int id = Integer.valueOf(request.getParameter("id"));
            mesa.setId(id);
        }catch(Exception e){
            mesa.setId(0);
        }
        
        try {
            int numero = Integer.valueOf(request.getParameter("mesa.numero"));
            mesa.setNumero(numero);
        } catch (Exception e) {
            mesa.setNumero(0);
        }
        
        mesa.setStatus(request.getParameter("mesa.status"));
        
        return mesa;
    }   
}
