package com.dal.mycareer.emailengine;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dal.mycareer.propertiesparser.PropertiesParser;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

public class SendGridEmailService implements EmailService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private SendGrid sendGridClient;

	public SendGridEmailService() {
		this.sendGridClient = SendGridSingleton.Instance();
	}

	@Override
	public void sendHTML(String to, String subject, String body) {
		Response response = sendEmail(to, subject, new Content("text/html", body));

		LOGGER.debug("Status Code: " + response.getStatusCode() + ", Body: " + response.getBody() + ", Headers: "
				+ response.getHeaders());
	}

	private Response sendEmail(String to, String subject, Content content) {
		Mail mail = new Mail(new Email(PropertiesParser.getPropertyMap().get("replyto").toString()), subject,
				new Email(to), content);
		mail.setReplyTo(new Email(PropertiesParser.getPropertyMap().get("replyto").toString()));
		Request request = new Request();
		Response response = null;
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			response = this.sendGridClient.api(request);
		} catch (IOException ex) {
			LOGGER.error("Error Sending email to:" + to);
			LOGGER.error("Error Sending email -- Exception:" + ex);
		}
		return response;
	}
}