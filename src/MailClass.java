import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.net.*;
import java.net.PasswordAuthentication;
import javax.swing.JOptionPane;

public class MailClass {
	public MailClass(String recp,int code){
		final String username = "hammatestapp@gmail.com";
		final String password = "med12345";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,new javax.mail.Authenticator() {
		protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
		return new javax.mail.PasswordAuthentication(username, password);
		}
		});
		try {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("hammatestapp@gmail.com"));
		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recp));
		message.setSubject("Account confirmation");
		message.setText("Hello, your confirmation code is: "+code);
		Transport.send(message);
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		}
		
	
}
