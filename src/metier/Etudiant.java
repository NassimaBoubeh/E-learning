package metier;

public class Etudiant extends Personne{
	
	private int id;
	private String nom;
	private String prenom;
	
	public Etudiant() {
		super();
	}
	
	public Etudiant(String nom, String prenom,String cin, String login, String pwd) {
		super(login,pwd,cin);
		this.nom = nom;
		this.prenom = prenom;
	}

	public Etudiant(String nom, String prenom,String cin) {
		super(cin);
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Etudiant(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
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

	public int getId() {
		
		return id;
	}
	
	
	
}
