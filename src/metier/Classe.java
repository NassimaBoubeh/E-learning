package metier;

import java.io.Serializable;
import java.util.List;

public class Classe implements Serializable{
	

	private int numero;
	private String departement;
	private Prof prof;
	private List<Etudiant> etudiants;
	
	public Classe() {
		super();
	}

	public Classe(int numero, Prof prof, String departement) {
		super();
		this.numero = numero;
		this.prof = prof;
		this.setDepartement(departement);
	}
	
	public Classe(int numero, Prof prof) {
		super();
		this.numero = numero;
		this.prof = prof;
	}

	public Classe(int num, String dep) {
		super();
		this.numero = num;
		this.setDepartement(departement);
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Prof getProf() {
		return prof;
	}

	public void setProf(Prof prof) {
		this.prof = prof;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}
	
	@Override
	public String toString() {
		return "Classe [numero=" + numero + ", departement=" + departement + ", prof=" + prof + "]";
	}
	
}
