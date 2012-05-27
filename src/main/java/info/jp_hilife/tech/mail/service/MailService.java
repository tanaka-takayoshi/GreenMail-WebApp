package info.jp_hilife.tech.mail.service;

import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;

@Service("MailService")
public class MailService {

	private GreenMail greenMail;
	
	public MailService() {
		super();
		greenMail = new GreenMail(ServerSetupTest.ALL);
		greenMail.start();
	}

	public MimeMessage[] getReceivedMessages() {
		return greenMail.getReceivedMessages();
	}
	
	
}
