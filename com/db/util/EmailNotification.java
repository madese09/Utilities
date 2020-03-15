package com.db.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailNotification {

	/**
	 * without authentication
	 * args to be passed is custom message 
	 * @param args
	 */
	public static void main(String[] args) {
		
		InputStream propFile;
		try {
			propFile = new FileInputStream(new File("<path to the property file>"));
			Properties prop = new Properties();
			prop.load(propFile);
			
			Session session = Session.getDefaultInstance(prop);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(prop.getProperty("email.from")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(prop.getProperty("email.to")));
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(prop.getProperty("email.cc")));
			message.setSubject("Test Mail");
			message.setText("Test mail");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

	}

}
