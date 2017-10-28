/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Quartz;

import Models.Locacao;
import Services.LocacaoService;
import Utils.EmailEnvia;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author Matheus
 */
public class NotificarReservaJob implements Job{
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
    
        LocacaoService locserv = new LocacaoService();
        List<Locacao> loc = locserv.list();
        
        Calendar now = Calendar.getInstance();
                now.clear(Calendar.SECOND);
                now.add(Calendar.MINUTE, 30);
        EmailEnvia email = new EmailEnvia();
        
        for (Locacao l : loc) {
                System.out.println("Job da notificação por email rodando.");
                System.out.println(now.getTime());
                System.out.println(l.getData().getTime());    
                if(l.getData().get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR) && l.getData().get(Calendar.MINUTE) == now.get(Calendar.MINUTE)){
                    email.enviar30(l.getPessoa().getEmail(), l.getPessoa().getNome());
                    System.out.println("Enviando...");         
                }        
        }
    }
 }
