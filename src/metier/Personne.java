package metier;

import java.io.Serializable;

public class Personne implements Serializable {
	private String login;
	private String password;
	private String CIN;
	
	public Personne() {
		super();
	}

	public Personne(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public Personne(String login, String password, String cIN) {
		super();
		this.login = login;
		this.password = password;
		this.CIN=cIN;
	}

	public Personne(String cin) {
		this.CIN=cin;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCIN() {
		return CIN;
	}

	public void setCIN(String cIN) {
		CIN = cIN;
	}

	
	
	
	
}
