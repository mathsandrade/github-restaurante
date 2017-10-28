/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Data.Pool;
import Models.Endereco;
import Models.DAO.EnderecoDAO;
import Models.DAO.PessoaDAO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class EnderecoService {
    private Connection connection;
    
    public EnderecoService () {
    }
    
    public Endereco create(Endereco endereco) {
        EnderecoDAO dao = getDao();
        Endereco novoEndereco = dao.insert(endereco);
        releaseDao(dao);
        return novoEndereco;
    }
    
    public Endereco update(Endereco endereco){
        EnderecoDAO dao = getDao();
        Endereco enderecoAtualizado = dao.update(endereco);
        releaseDao(dao);
        return enderecoAtualizado;
    }
    
    public void delete(Endereco endereco){
        EnderecoDAO dao = getDao();
        dao.delete(endereco);
        releaseDao(dao);
    }
    
    public EnderecoDAO getDao(){
        connection = Pool.get();
        return new EnderecoDAO(connection);
    }
    
    public void releaseDao(EnderecoDAO dao){
        if (dao != null) {
            if (connection != null) {
                Pool.release(connection);
            }
            dao = null;
        }
    }
}
