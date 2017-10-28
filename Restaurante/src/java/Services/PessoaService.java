/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Data.Pool;
import Models.Endereco;
import Models.DAO.EnderecoDAO;
import Models.Pessoa;
import Models.DAO.PessoaDAO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class PessoaService {
    private Connection connection;
    
    public PessoaService () {
    }
    
    public List<Pessoa> list() {
        PessoaDAO dao = getDao();
        List<Pessoa> pessoas = dao.selectAll();
        releaseDao(dao);
        return pessoas;
    }
    
    public Pessoa create(Pessoa pessoa) {
        PessoaDAO dao = getDao();        
        Endereco endereco = new EnderecoService().create(pessoa.getEndereco());
        pessoa.setEndereco(endereco);
        Pessoa p = dao.insert(pessoa);
        releaseDao(dao);
        return p;
    }
    
    public Pessoa update(Pessoa pessoa){
        PessoaDAO dao = getDao();
        Endereco endereco = new EnderecoService().update(pessoa.getEndereco());
        
        pessoa.setEndereco(endereco);
        Pessoa p = dao.update(pessoa);
        releaseDao(dao);
        return p;     
    }
    
    public void delete(Pessoa pessoa){
        PessoaDAO dao = getDao();
        EnderecoService end = new EnderecoService();
        end.delete(pessoa.getEndereco());
        dao.delete(pessoa);
        releaseDao(dao);
    }
   
    public Pessoa select (Pessoa pessoa){
        PessoaDAO dao = getDao();
        Pessoa p = dao.select(pessoa);
        releaseDao(dao);
        return p;
    }
    
    public Pessoa selectcpf (Pessoa pessoa){
        PessoaDAO dao = getDao();
        Pessoa p = dao.selectcpf(pessoa);
        releaseDao(dao);
        return p;
    }
    
    public Pessoa selectemail (Pessoa pessoa){
        PessoaDAO dao = getDao();
        Pessoa p = dao.selectemail(pessoa);
        releaseDao(dao);
        return p;
    }
    
    public String validarEmail(Pessoa p_email){
        if (p_email != null ){
            return "Este email já existe";
        }
        return "";
    }
    
    public String validarCpf(Pessoa p_cpf){
        if(p_cpf != null){
            return "Este CPF já existe";
        }
        return "";
    }
    
    public PessoaDAO getDao(){
        connection = Pool.get();
        return new PessoaDAO(connection);
    }
    
    public void releaseDao(PessoaDAO dao){
        if (dao != null) {
            if (connection != null) {
                Pool.release(connection);
            }
            dao = null;
        }
    }
}
