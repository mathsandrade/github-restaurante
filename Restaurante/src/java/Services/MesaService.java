/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Data.Pool;
import Models.Endereco;
import Models.Mesa;
import Models.DAO.MesaDAO;
import Models.Pessoa;
import Models.DAO.PessoaDAO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class MesaService {
    private Connection connection;
    
    public MesaService () {
    }
    
    public List<Mesa> list() {
        MesaDAO dao = getDao();
        List<Mesa> mesas = dao.selectAll();
        releaseDao(dao);
        return mesas;
    }
    
    public Mesa update(Mesa mesa){
        MesaDAO dao = getDao();
        Mesa m = dao.update(mesa);
        releaseDao(dao);
        return m;     
    }
    
    public void delete(Mesa mesa){
        MesaDAO dao = getDao();
        dao.delete(mesa);
        releaseDao(dao);
    }
    
    public Mesa select(Mesa mesa){
        MesaDAO dao = getDao();
        Mesa mesa_selec = dao.select(mesa);
        releaseDao(dao);
        return mesa_selec;
    }
    
    public Mesa selectNum(Mesa mesaNum){
        MesaDAO dao = getDao();
        Mesa mesa_selec = dao.selectNum(mesaNum);
        releaseDao(dao);
        return mesa_selec;
    }
            
    public Mesa create(Mesa mesa) {
        MesaDAO dao = getDao();        
        Mesa m = dao.insert(mesa);
        releaseDao(dao);
        return m;
    }
    
    public Mesa ocuparMesa(Mesa mesa) {
      
        MesaDAO dao = getDao();
        mesa.setStatus("OCUPADA");
        Mesa mesaAtt = dao.updateStatus(mesa);
        releaseDao(dao);
        
        return mesaAtt;
    }
    
    
    public MesaDAO getDao(){
        connection = Pool.get();
        return new MesaDAO(connection);
    }
    
    public void releaseDao(MesaDAO dao){
        if (dao != null) {
            if (connection != null) {
                Pool.release(connection);
            }
            dao = null;
        }
    }
}