package rs.ac.uns.ftn.springsecurityexample.service.impl;

import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.springsecurityexample.model.Appointment;
import rs.ac.uns.ftn.springsecurityexample.model.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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

	public void sendAppointmentConfiramtion(User user, Appointment appointment) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Appointment confirmation");
		mail.setText("Hello, " + user.getFirstName() + "!\n\n"
				+ "We are pleased to inform you that your appointment has been successfully booked.\n"
				+ "We look forward to seeing you at your scheduled appointment for " + appointment.getDate() + " at " + appointment.getTime() + ".\n\n"
				+ "Best regards,");
		javaMailSender.send(mail);
	}

	public void sendAppointmentInquiry(User user, Appointment appointment){
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
		try {
			helper.setTo(user.getEmail());
			helper.setFrom(env.getProperty("spring.mail.username"));
			helper.setSubject("Appointment Inquiry");
			String content = getHtmlContent(user, appointment);
			helper.setText(content, true);
			javaMailSender.send(message);
		} catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

	public String getHtmlContent(User user, Appointment appointment) {
		return "<!DOCTYPE html>\n" +
				"<html lang=\"en\">\n" +
				"<head>\n" +
				"    <meta charset=\"UTF-8\">\n" +
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
				"    <title>Email Template</title>\n" +
				"    <style>\n" +
				"        /* Button styles */\n" +
				"        .button {\n" +
				"            display: inline-block;\n" +
				"            padding: 10px 20px;\n" +
				"            text-decoration: none;\n" +
				"            color: #fff;\n" +
				"            border-radius: 5px;\n" +
				"            transition: background-color 0.3s ease;\n" +
				"        }\n" +
				"        .confirm {\n" +
				"            background-color: #007bff;\n" +
				"        }\n" +
				"        .deny {\n" +
				"            background-color: #dc3545;\n" +
				"        }\n" +
				"        .button:hover {\n" +
				"            background-color: #0056b3;\n" +
				"        }\n" +
				"    </style>\n" +
				"</head>\n" +
				"<body>\n" +
				"    <p>Dear " + user.getFirstName() + ",</p>\n" +
				"    <p>We would like to confirm or deny your appointment scheduled for " + appointment.getDate() + " at " + appointment.getTime() + ".</p>\n" +
				"    <p>Please click one of the buttons below to confirm or deny the appointment:</p>\n" +
				"    <a href=\"http://localhost:5173/appointmentInquiryRejected/" + appointment.getId() + "\" class=\"button deny\">Deny</a>\n" +
				"    <a href=\"http://localhost:5173/appointmentInquiryAccepted/" + appointment.getId() + "\" class=\"button confirm\">Confirm</a>\n" +
				"    <p>Best regards,</p>\n" +
				"</body>\n" +
				"</html>";
	}
}
