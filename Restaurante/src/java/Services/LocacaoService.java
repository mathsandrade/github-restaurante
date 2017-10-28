    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Data.Pool;
import Models.Locacao;
import Models.DAO.LocacaoDAO;
import Models.Mesa;
import Models.Pessoa;
import Utils.EmailEnvia;
import Utils.IniciandoLocacao;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Matheus
 */
public class LocacaoService {

    private Connection connection;


    public LocacaoService() {
    }
    
    public List<Locacao> list() {
        LocacaoDAO dao = getDao();
        List<Locacao> locacoes = dao.selectAll();
        releaseDao(dao);
        return locacoes;
    }
    
    //ver se tem mesas disponiveis
    //atualizar as mesas que estão indisponiveis
    //STATUS: NADA
    public IniciandoLocacao iniciarLocacao(Pessoa pessoa, Locacao loc_data) {
        List<Mesa> mesasLivres = new ArrayList<>();
        Locacao locacao = new Locacao();
        Calendar now = Calendar.getInstance();
                now.clear(Calendar.SECOND);
                now.add(Calendar.MINUTE, 59);
                now.add(Calendar.HOUR, 1);

        LocacaoDAO dao = getDao();
        
        List<Locacao> locacoes = dao.selectData(loc_data);
        List<Mesa> mesas = new MesaService().list();
        boolean[] mesasOcupadas = new boolean[mesas.size()];

        for (int i = 0; i < mesas.size(); i++) {
            for (int j = 0; j < locacoes.size(); j++) {
                if (mesas.get(i).getNumero() == locacoes.get(j).getMesa().getNumero()) {
                    mesasOcupadas[i] = true;
                }
            }
        }

        
        for (int i = 0; i < mesas.size(); i++) {
             if (mesasOcupadas[i] == false && loc_data.getData().after(now)){     // UTILIZAÇÃO DA DATA COM DUAS HORAS DE ANTECEDENCIA, AS MESAS SÓ SERÃO ADICIONADAS        
                    mesasLivres.add(mesas.get(i));                                              //COM DUAS HORAS DE ANTECEDENCIA NA RESERVA
             }
         }

        locacao.setStatus("ABERTA");
        locacao.setPessoa(pessoa);
        
        
        if (mesasLivres.isEmpty()){    //checando se as mesas livres estão vazias, com base na hora
            return null;                //se estiverem vazias, retornará nulo
        } else {
            return new IniciandoLocacao() { //caso contrario, irá fazer o retorno de um novo objeto populado
                {
                    setLocacao(locacao);
                    setMesas(mesasLivres);
                }
            };
        }
    }
    
    public void enviarEmail(Locacao locacao){
        //DATA
        SimpleDateFormat dataformat = new SimpleDateFormat("dd-MM-yyyy");
        dataformat.setCalendar(locacao.getData());
        String data = dataformat.format(locacao.getData().getTime());
        //HORA
        SimpleDateFormat horaformat = new SimpleDateFormat("HH:mm");
        horaformat.setCalendar(locacao.getData());
        String hora = horaformat.format(locacao.getData().getTime());
            
        //Envio de Email
        EmailEnvia email = new EmailEnvia();
        email.enviar(locacao.getPessoa().getEmail(), locacao.getPessoa().getNome(), data, hora); 
        //        
    }

    public Locacao select (Locacao locacao){
        LocacaoDAO dao = getDao();
        Locacao loc = dao.select(locacao);
        releaseDao(dao);
        return loc;
    }
    
    public Locacao checarLocacaoAtiva (Locacao locacao_ativa){
        LocacaoDAO dao = getDao();
        Locacao loc = dao.testeAtiva(locacao_ativa);
        releaseDao(dao);
        return loc;
    }
    
    public Locacao update(Locacao locacao){
        LocacaoDAO dao = getDao();
        Mesa mesa = new MesaService().update(locacao.getMesa());
        Pessoa pessoa = new PessoaService().update(locacao.getPessoa());
        locacao.setMesa(mesa);
        locacao.setPessoa(pessoa);
        Locacao loc = dao.update(locacao);
        releaseDao(dao);
        return loc;     
    }
    
    
    //STATUS: AGUARDANDO
    public Locacao realizarLocacao(Locacao locacao) {

        LocacaoDAO dao = getDao();
        locacao.setStatus("AGUARDANDO");
        Locacao locacaoInsert = dao.insert(locacao);
        releaseDao(dao);
        return locacaoInsert;
    }

    
    //STATUS: CANCELADA
    public Locacao cancelarLocacao(Locacao locacao) {
      
        LocacaoDAO dao = getDao();
        locacao.setStatus("CANCELADA");
        Locacao locacaoAtt = dao.updateStatus(locacao);
        releaseDao(dao);
        
        return locacaoAtt;
    }

    //STATUS: CONCLUIDA
    public Locacao concluirLocacao(Locacao locacao) {
      
        LocacaoDAO dao = getDao();
        locacao.getMesa().setStatus("OCUPADA");
        Mesa mesa = new MesaService().update(locacao.getMesa());
        Pessoa pessoa = new PessoaService().update(locacao.getPessoa());
        locacao.setMesa(mesa);
        locacao.setPessoa(pessoa);
        locacao.setStatus("CONCLUIDA");
        Locacao locacaoAtt = dao.update(locacao);
        releaseDao(dao);
        
        return locacaoAtt;
    }

    public LocacaoDAO getDao() {
        connection = Pool.get();
        return new LocacaoDAO(connection);
    }

    public void releaseDao(LocacaoDAO dao) {
        if (dao != null) {
            if (connection != null) {
                Pool.release(connection);
            }
            dao = null;
        }
    }

}
