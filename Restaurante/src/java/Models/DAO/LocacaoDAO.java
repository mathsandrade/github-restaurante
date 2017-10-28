/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.DAO;

import Models.DAO.MesaDAO;
import Models.Locacao;
import Models.Mesa;
import Models.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class LocacaoDAO {
    private Connection connection;
    private PessoaDAO pessoaDao;
    private MesaDAO mesaDao;
    
    public LocacaoDAO(Connection conn){
        this.connection = conn;
        this.pessoaDao = new PessoaDAO(conn);
        this.mesaDao = new MesaDAO(conn);
    }
    
    public Locacao construir(ResultSet result) throws SQLException {
        Locacao locacao = new Locacao();
        locacao.setId(result.getInt("id_locacao"));
        
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(result.getTimestamp("data"));
        locacao.setData(calendario);
        
        locacao.setStatus(result.getString("status_r"));
        locacao.setPessoa(pessoaDao.select(new Pessoa(){{
            setId(result.getInt("fk_pessoa"));
        }}));
        locacao.setMesa(mesaDao.select(new Mesa(){{
            setId(result.getInt("fk_mesa"));
        }})); 
        
     
        return locacao;
    }
    
    public Locacao insert(Locacao locacao){
        PreparedStatement command = null;
        String sql = "INSERT INTO locacao(status_r, data, fk_pessoa, fk_mesa) VALUES(?, ?, ?, ?)";
        Locacao novaLocacao = null;
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            command.setString(1, locacao.getStatus());
            command.setTimestamp(2, new Timestamp(locacao.getData().getTimeInMillis()));
            command.setInt(3, locacao.getPessoa().getId());
            command.setInt(4, locacao.getMesa().getId());
            command.executeUpdate();

            ResultSet result = command.getGeneratedKeys();

            if (result.next()){
                novaLocacao = construir(result);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return novaLocacao; 
    }
    
    public Locacao update(Locacao locacao){
        PreparedStatement command;
        String sql = "UPDATE locacao SET status_r = ?, data = ?, fk_pessoa = ?, fk_mesa = ? WHERE id_locacao = ?";
        Locacao locacaoAtt = null;
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            command.setString(1, locacao.getStatus());
            command.setTimestamp(2, new Timestamp(locacao.getData().getTimeInMillis()));
            command.setInt(3, locacao.getPessoa().getId());
            command.setInt(4, locacao.getMesa().getId());
            command.setInt(5, locacao.getId());
            //command.setTimestamp(3, new Timestamp(Calendar.getInstance().getTimeInMillis()));
            command.executeUpdate();

            ResultSet result = command.getGeneratedKeys();

            if (result.next()){
               locacaoAtt = construir(result);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locacaoAtt; 
    }
    
    public Locacao updateStatus (Locacao locacao){
        PreparedStatement command;
        String sql = "UPDATE locacao SET status_r=? where id_locacao = ?";
        Locacao locacaoStatusAtt = null;
        try{
            connection.setAutoCommit(false);
            command = connection.prepareStatement (sql, Statement.RETURN_GENERATED_KEYS);
            command.setString(1, locacao.getStatus());
            command.setInt(2, locacao.getId());
            
            command.executeUpdate();
            
            ResultSet result = command.getGeneratedKeys();
            
            if(result.next()){
                locacaoStatusAtt = construir(result);
            }
            connection.commit();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return locacaoStatusAtt;
    }
    
    public void delete(Locacao locacao){
        PreparedStatement command;
        String sql = "DELETE FROM locacao WHERE id_locacao = ?";
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setInt(1, locacao.getId());
            command.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Locacao select(Locacao locacao){
        PreparedStatement command;
        String sql = "SELECT id_locacao, status_r, data, fk_pessoa, fk_mesa " +
                "FROM locacao WHERE id_locacao = ?";
        
        Locacao locacaoBanco = null;
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setInt(1, locacao.getId());
            ResultSet result = command.executeQuery();

            if (result.next()){
                locacaoBanco = construir(result);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locacaoBanco; 
    }
    
    public List<Locacao> selectData(Locacao locacao){
        PreparedStatement command;
        String sql = "SELECT id_locacao, status_r, data, fk_pessoa, fk_mesa " +
                "FROM locacao WHERE data BETWEEN ? AND ? AND status_r <> 'CANCELADA' AND status_r <> 'CONCLUIDA'";
        
        List<Locacao> locacoes = new ArrayList<Locacao>();
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql); 
            
            locacao.getData().add(Calendar.HOUR_OF_DAY, -1);
            command.setTimestamp(1, new Timestamp(locacao.getData().getTimeInMillis()));
            
            locacao.getData().add(Calendar.HOUR_OF_DAY, 2);
            
            command.setTimestamp(2, new Timestamp(locacao.getData().getTimeInMillis()));
            
            locacao.getData().add(Calendar.HOUR_OF_DAY, -1);
            ResultSet result = command.executeQuery();

             while (result.next()){
                Locacao l = construir(result);
                locacoes.add(l);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locacoes;
    }
    

    //Checando se existe uma locação ativa com inner join
    public Locacao selectLocacaoAtiva(Locacao locacao){
        PreparedStatement command;
        Locacao loc = null;
        String sql = "SELECT id_locacao, status_r, data, fk_pessoa, fk_mesa " +
                "FROM locacao INNER JOIN pessoa ON locacao.fk_pessoa = pessoa.id_pessoa " +
                "INNER JOIN mesa ON locacao.fk_mesa = mesa.id_mesa WHERE pessoa.cpf = ? AND status_r <> 'CANCELADA' AND status_r <> 'CONCLUIDA'";
        
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setString(1, locacao.getPessoa().getCpf());
            ResultSet result = command.executeQuery();

            if (result.next()){
                loc = construir(result);
            }
        connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loc; 
    }
    
    
    //Checando se existe uma locação ativa sem inner join
    public Locacao testeAtiva(Locacao locacao){
        PreparedStatement command;
        Locacao loc = null;
        String sql = "SELECT id_locacao, status_r, data, fk_pessoa, fk_mesa " +
                "FROM locacao WHERE fk_pessoa = ? AND status_r <> 'CANCELADA' AND status_r <> 'CONCLUIDA'";
        
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            command.setInt(1, locacao.getPessoa().getId());
            ResultSet result = command.executeQuery();

            if (result.next()){
                loc = construir(result);
            }
        connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loc; 
    }
        
    public List<Locacao> selectAll(){
        PreparedStatement command;
        String sql = "SELECT id_locacao, status_r, data, fk_pessoa, fk_mesa " +
                "FROM locacao WHERE status_r = 'AGUARDANDO'";
        List<Locacao> locacoes = new ArrayList<Locacao>();
        try {
            connection.setAutoCommit(false);
            command = connection.prepareStatement(sql);
            ResultSet result = command.executeQuery();

            while (result.next()){
                Locacao locacao = construir(result);
                locacoes.add(locacao);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return locacoes; 
    }
    
}
