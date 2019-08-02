package com.dal.mycareer.emailengine;

public interface EmailService {

	void sendHTML(String to, String subject, String body);
}
