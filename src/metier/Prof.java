package metier;

public class Prof extends Personne{
	private String nom;
	private String prenom;
	private String metier;
	private int id;
	
	public Prof() {
		super();
	}

	
	
	public Prof(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}



	public Prof(String nom, String prenom,String cin, String metier) {
		super(cin);
		this.nom = nom;
		this.prenom = prenom;
		this.metier = metier;
	}
	
	public Prof(String nom, String prenom,String cin, String metier, String login, String pwd) {
		super(login,pwd,cin);
		this.nom = nom;
		this.prenom = prenom;
		this.metier = metier;
	}

	


	public Prof(int numProf) {
		id=numProf;
	}



	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMetier() {
		return metier;
	}

	public void setMetier(String metier) {
		this.metier = metier;
	}



	public int getId() {
		return id;
	}
	
	
}
