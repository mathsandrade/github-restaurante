/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontController;

import Models.Mesa;
import Services.MesaService;
import ViewModel.MesaViewModel;
import java.io.IOException;
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
@WebServlet(name = "MesaController", urlPatterns = {"/mesas"})
public class MesaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");        
        
        String action = request.getParameter("a");
                if (action == null)
                action = "";
        
        MesaService service = new MesaService();
        MesaViewModel viewModel = new MesaViewModel();
        
        
        if (action.equals("new")) {
            viewModel.novo(request, response);
            
        } else if (action.equals("create")) {
            Mesa mesa = viewModel.getMesa(request);
            
            //Checa se o numero da mesa já existe no banco
            if(service.selectNum(mesa) != null){
                viewModel.validarMesa(mesa, "Este numero já existe.", request, response);
            } else{
                //Caso não existir, cria a mesa
                mesa = service.create(mesa);
                viewModel.create(mesa.getId(), request, response);
            }
            
        } else if (action.equals("edit")){
            Mesa mesa = viewModel.getMesa(request);
            mesa = service.select(mesa);
            viewModel.edit(mesa, request, response);
            
        } else if (action.equals("update")){
            Mesa produto = viewModel.getMesa(request);
            produto = service.update(produto);
            viewModel.update(produto.getId(), request, response);
           
        } else if (action.equals("delete")){
            Mesa mesa = viewModel.getMesa(request);
            service.delete(mesa);
            viewModel.delete(request, response);
        
        } else {
            List<Mesa> mesas = service.list();
            viewModel.list(mesas, request, response);
            
        }
      }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
