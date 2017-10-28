/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;

import Models.DAO.EnderecoDAO;
import Models.Endereco;
import Models.Pessoa;
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
public class PessoaDAO {
    private Connection connection;
    private EnderecoDAO enderecoDao;
    
    public PessoaDAO(Connection conn){
        this.connection = conn;
        this.enderecoDao = new EnderecoDAO(conn);
    }
    
    public Pessoa construir(ResultSet result) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(result.getInt("id_pessoa"));
        pessoa.setNome(result.getString("nome"));
        pessoa.setIdade(result.getInt("idade"));
        pessoa.setCpf(result.getString("cpf"));
        pessoa.setEmail(result.getString("email"));
        pessoa.setTelefone(result.getString("telefone"));
        pessoa.setEndereco(enderecoDao.select(new Endereco(){{
            setId(result.getInt("fk_endereco"));
        }}));
        return pessoa;
    }
    
    public Pessoa insert(Pessoa pessoa){
        PreparedStatement command = null;
        String sql = "INSERT INTO pessoa(nome, idade, cpf, email, telefone, fk_endereco) VALUES(?,?,?,?, ?,?)";
        Pessoa novaPessoa = null;
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            command.setString(1, pessoa.getNome());
            command.setInt(2, pessoa.getIdade());
            command.setString(3, pessoa.getCpf());
            command.setString(4, pessoa.getEmail());
            command.setString(5, pessoa.getTelefone());
            command.setInt(6, pessoa.getEndereco().getId());
            command.executeUpdate();

            ResultSet result = command.getGeneratedKeys();

            if (result.next()){
                novaPessoa = construir(result);
            }
        connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return novaPessoa; 
    }
    
    public Pessoa update(Pessoa pessoa){
        PreparedStatement command;
        String sql = "UPDATE pessoa SET nome = ?, idade = ?, cpf = ?, email = ?, telefone = ?, fk_endereco = ? WHERE id_pessoa = ?";
        Pessoa pessoaAtt = null;
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            command.setString(1, pessoa.getNome());
            command.setInt(2, pessoa.getIdade());
            command.setString(3, pessoa.getCpf());
            command.setString(4, pessoa.getEmail());
            command.setString(5, pessoa.getTelefone());
            command.setInt(6, pessoa.getEndereco().getId());
            command.setInt(7, pessoa.getId());
            command.executeUpdate();

            ResultSet result = command.getGeneratedKeys();

            if (result.next()){
                pessoaAtt = construir(result);
            }
        connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoaAtt; 
    }
    
    public void delete(Pessoa pessoa){
        PreparedStatement command;
        String sql = "DELETE FROM pessoa WHERE id_pessoa = ?";
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setInt(1, pessoa.getId());
            command.executeUpdate();

        connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Pessoa select(Pessoa pessoa){
        PreparedStatement command;
        String sql = "SELECT id_pessoa, nome, idade, cpf, email, telefone, fk_endereco FROM pessoa WHERE id_pessoa = ?";
        Pessoa pessoaBanco = null;
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setInt(1, pessoa.getId());
            ResultSet result = command.executeQuery();

            if (result.next()){
                pessoaBanco = construir(result);
            }
        connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoaBanco; 
    }

    public List<Pessoa> selectAll(){
        PreparedStatement command;
        String sql = "SELECT id_pessoa, nome, idade, cpf, email, telefone, fk_endereco FROM pessoa WHERE idade > 0";
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            ResultSet result = command.executeQuery();

            while (result.next()){
                Pessoa pessoa = construir(result);
                pessoas.add(pessoa);
            }
        connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoas; 
    }
       
    public Pessoa selectcpf(Pessoa pessoa){
        PreparedStatement command;
        String sql = "SELECT id_pessoa, nome, idade, cpf, email, telefone, fk_endereco FROM pessoa WHERE cpf = ?";
        Pessoa pessoaBanco = null;
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setString(1, pessoa.getCpf());
            ResultSet result = command.executeQuery();

            if (result.next()){
                pessoaBanco = construir(result);
            }
        connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoaBanco; 
    }
    
    public Pessoa selectemail(Pessoa pessoa){
        PreparedStatement command;
        String sql = "SELECT id_pessoa, nome, idade, cpf, email, telefone, fk_endereco FROM pessoa WHERE email = ?";
        Pessoa pessoaBanco = null;
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setString(1, pessoa.getEmail());
            ResultSet result = command.executeQuery();

            if (result.next()){
                pessoaBanco = construir(result);
            }
        connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoaBanco; 
    }
}


