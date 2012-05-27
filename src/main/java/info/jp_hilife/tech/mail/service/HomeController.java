package info.jp_hilife.tech.mail.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.mail.Header;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.icegreen.greenmail.util.GreenMailUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private MailService mailService;
	

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		List<MailModel> mails = new ArrayList<MailModel>();
		for (MimeMessage message : mailService.getReceivedMessages()) {
			try {	
				MailModel m = new MailModel();
				m.setId(message.getMessageID());
				m.setSubject(message.getSubject());
				m.setFrom(message.getFrom()[0].toString());
				m.setReceivedDate(message.getReceivedDate());
				mails.add(m);
			} catch (MessagingException e) {
				// TODO: handle exception
			}
		}
		
		model.addAttribute("mails", mails);
		return "home";
	}

	@RequestMapping("/send")
	public void main(@RequestParam(value="to", defaultValue="to@localhost.co") String to,
			@RequestParam(value="from", defaultValue="from@localhost.com") String from,
			@RequestParam(value="subject", defaultValue="subject") String subject,
			@RequestParam(value="body", defaultValue="body") String body,
			HttpServletResponse response) throws IOException {
		
		GreenMailUtil.sendTextEmailTest(to, from, subject, body);
		response.sendRedirect("");
	}
	
	@RequestMapping(value="/mail", method=RequestMethod.GET)
	public String getMail(@RequestParam("id") String id, Model model, HttpServletRequest request) {
		MimeMessage message = null;
		for (MimeMessage m : mailService.getReceivedMessages()) {
			try {
				if (id != null && id.equals(m.getMessageID())) {
					message = m;
					break;
				}
			} catch(MessagingException e) {
				throw new RuntimeException(e);
			}
		}
		if (message == null) {
			throw new RuntimeException();
		}
		try {
			model.addAttribute("id", message.getMessageID());
			model.addAttribute("subject", message.getSubject());
			List<MailHeaderModel> headers = new ArrayList<MailHeaderModel>();
			for (Enumeration<?> e = message.getAllHeaders(); e.hasMoreElements();) {
				Header h = (Header)e.nextElement();
				headers.add(MailHeaderModel.create(h));
			}
			model.addAttribute("headers", headers);
			model.addAttribute("plaiBody", message.getContent().toString());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "show";
	}
	
	@RequestMapping("/env")
	public void env(HttpServletResponse response) throws IOException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.println("System Properties:");
		for (Map.Entry<Object, Object> property : System.getProperties().entrySet()) {
			out.println(property.getKey() + ": " + property.getValue());
		}
		out.println();
		out.println("System Environment:");
		for (Map.Entry<String, String> envvar : System.getenv().entrySet()) {
			out.println(envvar.getKey() + ": " + envvar.getValue());
		}
		dumpServerInfo(out);
	}

	private void dumpServerInfo(PrintWriter out) {
		out.println();
		out.println("Server Version");
		
		try {
			Class<?> clazz = Class.forName("org.apache.catalina.util.ServerInfo");
			Method method = clazz.getMethod("getServerInfo");
			Object ret = method.invoke(null);
			out.println("ServerInfo: " + ret);
			
			method = clazz.getMethod("getServerBuilt");
			ret = method.invoke(null);
			out.println("ServerBuilt: " + ret);
			
			method = clazz.getMethod("getServerNumber");
			ret = method.invoke(null);
			out.println("ServerNumber: " + ret);
		} catch (SecurityException e) {
			out.println("Cannot get server info.");
		} catch (NoSuchMethodException e) {
			out.println("Cannot get server info.");
		} catch (IllegalArgumentException e) {
			out.println("Cannot get server info.");
		} catch (IllegalAccessException e) {
			out.println("Cannot get server info.");
		} catch (ClassNotFoundException e) {
			out.println("Cannot get server info.");
		} catch (InvocationTargetException e) {
			out.println("Cannot get server info.");
		} 
	}
}