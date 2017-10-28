/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Models.Pessoa;
import java.util.Calendar;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
/**
 *
 * @author rafae
 */
public class EmailEnvia {
    

	public boolean enviar(String recebe, String recebeN, String data, String hora) {
		SimpleEmail email = new SimpleEmail();

		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);          
                email.setStartTLSEnabled(true);

		try {
			email.addTo(recebe);
			email.setFrom(EmailAutenticacao.email, EmailAutenticacao.nome);
			email.setSubject("Restaurante Gominho's - Reserva");
			email.setMsg("Olá Sr(a). "+recebeN+", sua reserva foi efetuada com sucesso.\n"
                                + "Agradecemos por escolher o nosso restaurante!\n"
                                + "Ela está marcada para: "+data+" às "+hora+"                      \n"
                                + "___________________________________________________________________\n"
                                + "Para mais informações entre em contato conosco.\n"
                                + "(11)98702-5690 - Celular\n"
                                + "(11)4633-5590 - Telefone"); 
                        
			System.out.println("autenticando...");
			email.setAuthentication(EmailAutenticacao.email,EmailAutenticacao.senha);
			System.out.println("enviando...");
			//System.out.println(email.send());
                        email.send();
			System.out.println("Email enviado!");
			
			return true;
		} catch (EmailException e) {
			e.printStackTrace();
			return false;
		}
	}
        
        public boolean enviar30(String recebe, String recebeN){
            SimpleEmail email = new SimpleEmail();

		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);          
                email.setStartTLSEnabled(true);

		try {
			email.addTo(recebe);
			email.setFrom(EmailAutenticacao.email, EmailAutenticacao.nome);
			email.setSubject("Restaurante Gominho's - Notificação");
			email.setMsg("Olá Sr(a). "+recebeN+"!\n"
                                +"\nFalta apenas trinta minutos para a chegada de sua reserva.\n"
                                +"\nPS: Tente não se atrasar, pois nosso restaurante possui tolerância de apenas cinco minutos. ;)"
                                +"\nContamos com sua presença!\n"
                                + "___________________________________________________________________\n"
                                + "Para mais informações entre em contato conosco.\n"
                                + "(11)98702-5690 - Celular\n"
                                + "(11)4633-5590 - Telefone");     
                        email.setAuthentication(EmailAutenticacao.email,EmailAutenticacao.senha);
                        email.send();
                        System.out.println("Reserva notificada com sucesso!");
                return true;
                }catch(EmailException e){
                    e.printStackTrace();
                    return false;
                }
        }

}
