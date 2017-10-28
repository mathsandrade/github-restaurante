/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

import Models.Usuario;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Matheus
 */
public class UsuarioViewModel {
    
    public void validar (Usuario usuarioAutenticado, HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
            
        HttpSession sessionUser = request.getSession();
        sessionUser.setAttribute("usuario", usuarioAutenticado);
        request.getRequestDispatcher("welcome.jsp").forward(request, response);
    }
    
    public void list(List<Usuario> usuarios, HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("admin/listUser.jsp").forward(request, response);
    }
    
        public void novo(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        request.setAttribute("action", "create");
        request.getRequestDispatcher("admin/usuarios/new.jsp").forward(request, response);
    }
    
    public void create (int usuarioId, HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/admin?a=listUsers");
    }
    
    public void edit (Usuario usuario, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setAttribute("action", "update");
        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("admin/usuarios/edit.jsp").forward(request, response);
    }
    
    public void update(int mesaId, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/admin/usuarios?a=listUsers");
    }
    
    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.sendRedirect(request.getContextPath() + "/admin/usuarios?a=listUsers");
    }
    
    
    public Usuario getUsuario(HttpServletRequest request){
        
        Usuario usuario = new Usuario();

        usuario.setLogin(request.getParameter("usuario.login"));
        usuario.setSenha(request.getParameter("usuario.senha"));
        usuario.setPerfilAcesso(request.getParameter("usuario.perfilacesso"));
        
        return usuario;
    } 
}
