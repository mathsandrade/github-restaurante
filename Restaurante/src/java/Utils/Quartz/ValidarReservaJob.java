/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Quartz;

import Models.Locacao;
import Services.LocacaoService;
import java.util.Calendar;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Matheus
 */
//Classe que terá a lógica da tarefa do quartz
public class ValidarReservaJob implements Job{

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        
        LocacaoService locserv = new LocacaoService();
        List<Locacao> loc = locserv.list();
        //SimpleDateFormat dataformat = new SimpleDateFormat("dd-MM-yyyy");
        
        Calendar now = Calendar.getInstance();
    
        
        for (Locacao l : loc) {
            System.out.println("Job de cancelar por atraso rodando.");
            l.getData().add(Calendar.MINUTE, 5);
            if (l.getData().before(now)) {
                   locserv.cancelarLocacao(l);
                //System.out.println("Ta rodando hein...");
            }
        }
       
    }
} 