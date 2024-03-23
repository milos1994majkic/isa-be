package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.model.User;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;


	public void sendActivationCode(User user) {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Account activation");

		String activationURL = "http://localhost:5173/activateAccount/" + user.getActivationCode();

		mail.setText("Hello, " + user.getFirstName() + "! \n "
				+ "To acctivate your account, please click the following link:\n" + activationURL);

		javaMailSender.send(mail);
	}
	
	public void sendDenialReason(User user) {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Denial reason");


		mail.setText("Hello, " + user.getFirstName() + "! \n "
				+ "Your account have been denied.\n Reason: " + user.getDenialReason());

		javaMailSender.send(mail);
	}

	public String generateActivationCode() {
		String randomString = UUID.randomUUID().toString();

		long timestamp = System.currentTimeMillis();
		String uniqueCode = randomString + timestamp;

		byte[] uniqueCodeBytes = uniqueCode.getBytes();
		return Base64.getUrlEncoder().withoutPadding().encodeToString(uniqueCodeBytes);
	}

	
}
