/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;

import Models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class UsuarioDAO {
    
    private final Connection connection;
    
    public UsuarioDAO(Connection connection){
        this.connection = connection;
    }
    
        public Usuario construir(ResultSet result)throws SQLException{
        Usuario usuario = new Usuario();
        usuario.setLogin(result.getString("login"));
        usuario.setSenha(result.getString("senha"));
        usuario.setPerfilAcesso(result.getString("perfil_acesso"));
        
        return usuario;
    }
        
    public Usuario select(Usuario usuario){
        PreparedStatement command;
        String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";
        Usuario usuarioBanco = null;
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setString(1, usuario.getLogin());
            command.setString(2, usuario.getSenha());
    
            ResultSet result = command.executeQuery();

            if (result.next()){
                usuarioBanco = construir(result);
            }
        connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarioBanco; 
    }
    
    public List<Usuario> selectAll(){
        PreparedStatement command;
        String sql = "SELECT id, login, senha, perfil_acesso FROM usuario WHERE perfil_acesso <> 'admin' ";
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            ResultSet result = command.executeQuery();

            while (result.next()){
                Usuario usuario = construir(result);
                usuarios.add(usuario);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios; 
    }

    public Usuario insert(Usuario usuario){
        PreparedStatement command;
        String sql = "INSERT INTO usuario(login, senha, perfil_acesso) VALUES(?, ?, ?)";
        Usuario novoUsuario = null;
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            command.setString(1, usuario.getLogin());
            command.setString(2, usuario.getSenha());
            command.setString(3, usuario.getPerfilAcesso());
            command.executeUpdate();

            ResultSet result = command.getGeneratedKeys();

            if (result.next()){
                novoUsuario = construir(result);
            }
        connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return novoUsuario; 
    }
}
