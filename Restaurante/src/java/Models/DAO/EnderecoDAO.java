/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;

import Models.Endereco;
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
public class EnderecoDAO {
    
    private final Connection connection;

    public EnderecoDAO(Connection connection) {
        this.connection = connection;
    }
    
    public Endereco construir(ResultSet result) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setId(result.getInt("id_endereco"));
        endereco.setCidade(result.getString("cidade"));
        endereco.setRua(result.getString("rua"));
        endereco.setNumero(result.getInt("numero"));
        endereco.setBairro(result.getString("bairro"));
        endereco.setCep(result.getString("cep"));
        return endereco;
    }
    
    public Endereco insert(Endereco endereco){
        PreparedStatement command;
        String sql = "INSERT INTO endereco(cidade, rua, numero, bairro, cep) VALUES(?, ?, ?, ?, ?)";
        Endereco novoEndereco = null;
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            command.setString(1, endereco.getCidade());
            command.setString(2, endereco.getRua());
            command.setInt(3, endereco.getNumero());
            command.setString(4, endereco.getBairro());
            command.setString(5, endereco.getCep());
            //command.setTimestamp(3, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            command.executeUpdate();

            ResultSet result = command.getGeneratedKeys();

            if (result.next()){
                novoEndereco = construir(result);
            }
        connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return novoEndereco; 
    }
    
    public Endereco update(Endereco endereco){
        PreparedStatement command;
        String sql = "UPDATE endereco SET cidade = ?, rua = ?, numero = ?, bairro = ?, cep = ? WHERE id_endereco = ?";
        Endereco enderecoAtt = null;
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            command.setString(1, endereco.getCidade());
            command.setString(2, endereco.getRua());
            command.setInt(3, endereco.getNumero());
            command.setString(4, endereco.getBairro());
            command.setString(5, endereco.getCep());
            command.setInt(6, endereco.getId());
            //command.setTimestamp(3, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            command.executeUpdate();

            ResultSet result = command.getGeneratedKeys();

            if (result.next()){
                enderecoAtt = construir(result);
            }
        connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enderecoAtt; 
    }
    
    public void delete(Endereco endereco){
        PreparedStatement command;
        String sql = "DELETE FROM endereco WHERE id_endereco = ?";
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setInt(1, endereco.getId());
            //command.setTimestamp(3, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            command.executeUpdate();

        connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Endereco select(Endereco endereco){
        PreparedStatement command;
        String sql = "SELECT id_endereco, cidade, rua, numero, bairro, cep FROM endereco WHERE id_endereco = ?";
        Endereco enderecoBanco = null;
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setInt(1, endereco.getId());
            //command.setTimestamp(3, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            ResultSet result = command.executeQuery();

            if (result.next()){
                enderecoBanco = construir(result);
            }
        connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enderecoBanco; 
    }
    
    public List<Endereco> selectAll(){
        PreparedStatement command;
        String sql = "SELECT id_endereco, cidade, rua, numero, bairro, cep FROM endereco";
        List<Endereco> enderecos = new ArrayList<Endereco>();
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            //command.setTimestamp(3, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            ResultSet result = command.executeQuery();

            while (result.next()){
                Endereco endereco = construir(result);
                enderecos.add(endereco);
            }
        connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enderecos; 
    }
}
