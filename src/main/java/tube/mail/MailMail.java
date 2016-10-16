package tube.mail;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.google.common.base.Throwables;

public class MailMail {
	private static final String EMAIL_SENDER = "youplayittalents@gmail.com";

	public static final String EMAIL_RECEPIENT = "youplay.office@gmail.com";

	private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(String from, String to, String subject, String msg) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
	}

	public String handle(Exception e) {
		this.sendMail(EMAIL_SENDER, MailMail.EMAIL_RECEPIENT, "Catch an Exception",
				Throwables.getStackTraceAsString(e));
		return "redirect:/";
	}
}
