package util;

import java.util.Properties;

import javax.mail.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail {
	
	public String sendmail(String pass, String email) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ritishparmar34@gmail.com", "ritish@9891");
			}
		});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject("Reset Password");
			message.setText("Your New Password = "+pass);
			// send message
			Transport.send(message);
			return ("Sent message successfully....");
		} catch (MessagingException e) {
			System.out.print(e);
		}
		return ("Fail");
	}
}	
	
//	public static void main(String[] args) {
//			// email ID of Recipient.
//			String recipient = "ritishparmar34@gmail.com";
//
//			// email ID of Sender.
//			String sender = "ritishparmar34@gmail.com";
//
//			// using host as localhost
//			String host = "smtp.gmail.com";
//
//			// Getting system properties
//			Properties properties = System.getProperties();
//
//			// Setting up mail server
//			properties.setProperty("mail.smtp.host", host);
//			properties.put("mail.smtp.auth", "false");
////			properties.put("mail.smtp.starttls.enable", "true");
//			properties.put("mail.debug", "true");
//
//			properties.put("mail.smtp.port","25");
//
//
//			properties.put("mail.smtp.debug", "true");
//			
//			// creating session object to get properties
//			Session session = Session.getDefaultInstance(properties);
//
//			try
//			{
//				// MimeMessage object.
//				MimeMessage message = new MimeMessage(session);
//
//				// Set From Field: adding senders email to from field.
//				message.setFrom(new InternetAddress(sender));
//
//				// Set To Field: adding recipient's email to from field.
//				message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
//
//				// Set Subject: subject of the email
//				message.setSubject("This is Subject");
//
//				// set body of the email.
//				message.setText("This is a test mail");
//
//				// Send email.
//				Transport.send(message);
//				System.out.println("Mail successfully sent");
//			}
//			catch (MessagingException mex)
//			{
//				System.out.print(mex);
//			}

//		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.port", "465");
//		// get Session
//		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("ritishparmar34@gmail.com", "ritish@9891");
//			}
//		});
//		// compose message
//		try {
//			MimeMessage message = new MimeMessage(session);
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress("ritishparmar34@gmail.com"));
//			message.setSubject("subject");
//			message.setText("hello world!");
//			// send message
//			Transport.send(message);
//			System.out.println("message sent successfully");
//		} catch (MessagingException e) {
//			System.out.print(e);
//		}
//
//	}
//}
