package info.jp_hilife.tech.mail.service;

import javax.mail.Header;

public class MailHeaderModel {

	private String name;
	
	private String value;

	public MailHeaderModel() {
		super();
	}
	
	public static MailHeaderModel create(Header header) {
		MailHeaderModel m = new MailHeaderModel();
		m.name = header.getName();
		m.value = header.getValue();
		return m;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
