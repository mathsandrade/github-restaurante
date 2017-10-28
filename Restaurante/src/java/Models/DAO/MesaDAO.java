/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;

import Models.Mesa;
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
public class MesaDAO {
    
    private Connection connection;
    
    public MesaDAO(Connection conn){
        this.connection = conn;
    }
    
    public Mesa construir(ResultSet result) throws SQLException {
        Mesa mesa = new Mesa();
        mesa.setId(result.getInt("id_mesa"));
        mesa.setNumero(result.getInt("numero"));
        mesa.setStatus(result.getString("status_m"));
     
        return mesa;
    }
    
    public Mesa insert(Mesa mesa){
        PreparedStatement command = null;
        String sql = "INSERT INTO mesa(numero) VALUES(?)";
        Mesa novaMesa = null;
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            command.setInt(1, mesa.getNumero());
            command.executeUpdate();

            ResultSet result = command.getGeneratedKeys();

            if (result.next()){
                novaMesa = construir(result);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return novaMesa; 
    }
    
    public Mesa update(Mesa mesa){
        PreparedStatement command;
        String sql = "UPDATE mesa SET numero = ?, status_m = ? WHERE id_mesa = ?";
        Mesa mesaAtt = null;
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            command.setInt(1, mesa.getNumero());
            command.setString(2, mesa.getStatus());
            command.setInt(3, mesa.getId());
            command.executeUpdate();

            ResultSet result = command.getGeneratedKeys();

            if (result.next()){
               mesaAtt = construir(result);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mesaAtt; 
    }
    
    public void delete(Mesa mesa){
        PreparedStatement command;
        String sql = "DELETE FROM mesa WHERE id_mesa = ?";
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setInt(1, mesa.getId());
            command.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Mesa select(Mesa mesa){
        PreparedStatement command;
        String sql = "SELECT id_mesa, numero, status_m FROM mesa WHERE id_mesa = ?";
        Mesa mesaBanco = null;
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setInt(1, mesa.getId());
            ResultSet result = command.executeQuery();

            if (result.next()){
                mesaBanco = construir(result);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mesaBanco; 
    }
    
        public Mesa selectNum(Mesa mesa){
        PreparedStatement command;
        String sql = "SELECT id_mesa, numero, status_m FROM mesa WHERE numero = ?";
        Mesa mesaBanco = null;
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setInt(1, mesa.getNumero());
            ResultSet result = command.executeQuery();

            if (result.next()){
                mesaBanco = construir(result);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mesaBanco; 
    }

    public List<Mesa> selectAll(){
        PreparedStatement command;
        String sql = "SELECT id_mesa, numero, status_m FROM mesa WHERE numero > 0";
        List<Mesa> mesas = new ArrayList<Mesa>();
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            ResultSet result = command.executeQuery();

            while (result.next()){
                Mesa mesa = construir(result);
                mesas.add(mesa);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mesas; 
    }
    
    public Mesa updateStatus (Mesa mesa){
        PreparedStatement command;
        String sql = "UPDATE mesa SET status_m = ? where id_mesa = ?";
        Mesa mesaStatusAtt = null;
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);
            command.setString(1, mesa.getStatus());
            command.setInt(2, mesa.getId());
            
            command.executeUpdate();
            
            ResultSet result = command.getGeneratedKeys();
            
            if(result.next()){
                mesaStatusAtt = construir(result);
            }
            connection.commit();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return mesaStatusAtt;
    }
    
    
}
