package metier;

import java.io.Serializable;

public class Boite implements Serializable{

	private int id_etud;
	private  String message;
	private String fichier;
	private int id_prof;
	
	
	
	
	public Boite(int id_etud, String message, String fichier, int id_prof) {
		super();
		this.id_etud = id_etud;
		this.message = message;
		this.fichier = fichier;
		this.id_prof = id_prof;
	}
	public int getId_etud() {
		return id_etud;
	}
	public void setId_etud(int id_etud) {
		this.id_etud = id_etud;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFichier() {
		return fichier;
	}
	public void setFichier(String fichier) {
		this.fichier = fichier;
	}
	public int getId_prof() {
		return id_prof;
	}
	public void setId_prof(int id_prof) {
		this.id_prof = id_prof;
	}
	
	
}
