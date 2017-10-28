/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Filters;

import Models.Usuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Matheus
 */
public class AcessoLogadoControl implements Filter{
    
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException{
        
            HttpSession userSession = ((HttpServletRequest)request).getSession();
            Usuario userLogado = (Usuario) userSession.getAttribute("usuario");
            
            if(userLogado != null){
                chain.doFilter(request, response);
            } else{
                ((HttpServletResponse)response).sendRedirect("HTTP403.html");
            }
    }
    
    @Override
    public void destroy(){
    }                  
}
