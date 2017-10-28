/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Data.Pool;
import Models.DAO.UsuarioDAO;
import Models.Usuario;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class UsuarioService {
    private Connection connection;
    
    public UsuarioService () {
    }
    
    public Usuario create(Usuario usuario){
        UsuarioDAO dao = getDao();
        Usuario novoUsuario = dao.insert(usuario);
        releaseDao(dao);
        return novoUsuario;
    }
    
    public List<Usuario> list() {
        UsuarioDAO dao = getDao();
        List<Usuario> mesas = dao.selectAll();
        releaseDao(dao);
        return mesas;
    }
    
    public Usuario select(Usuario usuario){
        UsuarioDAO dao = getDao();
        Usuario user = dao.select(usuario);
        releaseDao(dao);
        return user;
    }
        
    public UsuarioDAO getDao(){
        connection = Pool.get();
        return new UsuarioDAO(connection);
    }
    
    public void releaseDao(UsuarioDAO dao){
        if (dao != null) {
            if (connection != null) {
                Pool.release(connection);
            }
            dao = null;
        }
    }
}
