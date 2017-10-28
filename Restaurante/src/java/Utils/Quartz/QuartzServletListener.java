/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Quartz;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Web application lifecycle listener.
 *
 * @author Matheus
 */
public class QuartzServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        try{
        // Detalhes da tarefa
        JobDetail job = JobBuilder.newJob(ValidarReservaJob.class).withIdentity(
                "TarefaValidarReserva", "group1").build();
        
        JobDetail job2 = JobBuilder.newJob(NotificarReservaJob.class).withIdentity(
                    "TarefaNotificarEmail", "group2").build();
        
        // Gatilho - ou seja, quando irá chamar, neste caso, a cada 1 minuto
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(
                "TriggerValidarReserva", "group1").withSchedule(
                CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")).build();
        
         Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity(
                "TriggerNotificarEmail", "group2").withSchedule(
                CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")).build();
 
        // Agenda e voa lá!
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        
        
        scheduler.scheduleJob(job, trigger);
        scheduler.scheduleJob(job2, trigger2);
        
        }catch(SchedulerException e){
            e.printStackTrace();
        }     
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
