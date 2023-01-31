package service_com;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connect;
import metier.Classe;
import metier.Etudiant;
import metier.Personne;
import metier.Prof;

public class MethodeComImpl extends UnicastRemoteObject implements MethodeCom{

	//les paramètres pour la connection avec la base de données
	 String url="jdbc:mysql://localhost:3307/learning?serverTimezone=UTC";
	 String userName="root";
	 String pwd="";
	 PreparedStatement ps ;
	 ResultSet rs ;
	
	public MethodeComImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public String connecter(Personne personne) throws RemoteException {
		    
		String rep=null;
		try {
		
		System.out.println("Connecter:::");
		Connection con = DriverManager.getConnection(url, userName, pwd);
		String requete="select * from personne where login='"+personne.getLogin()+"' and password='"+personne.getPassword()+"';";
		 ps = con.prepareStatement(requete);
		rs= ps.executeQuery();
		
			if(rs.next()) {
				rep= "vous êtes connecté.";
			}else {
				rep= "login ou password incorrect, réessayez";
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rep;
	}

	@Override
	public List<Classe> listeCls() throws RemoteException {
		List<Classe> classes = new ArrayList<Classe>();
		String requete = "select * from classe;";
		try {
		Connection con = DriverManager.getConnection(url, userName, pwd);
		ps=con.prepareStatement(requete);
		
			rs = ps.executeQuery();
			int num;
			String dep=null;
			String nom = null;
			String pre = null;
			
			while (rs.next()) {
				Classe classe=null;
				int numProf = rs.getInt("numProf");
				if(numProf!=23) {
					String requete1 = "select * from classe c, personne p where c.numProf=p.numPer and  c.numProf="+numProf;
					PreparedStatement ps1=con.prepareStatement(requete1);
					ResultSet rs1 = ps1.executeQuery();
					while(rs1.next()) {
					num = rs1.getInt("numCls");
					dep = rs1.getString("departement");
					nom = rs1.getString("nom"); pre= rs1.getString("prenom");
					Prof prof = new Prof(nom, pre);
					 classe = new Classe(num, prof, dep);
					 classes.add(classe);
					 
					}
				}else {
				num = rs.getInt("numCls");
				dep = rs.getString("departement");
					String variable = "pas encore affecter";
					Prof prof = new Prof(variable, variable);
					 classe = new Classe(num, prof, dep);
					classes.add(classe);
				}
				 
			}
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Classe classe : classes) {
			System.out.println(classe);
		}
		return classes;
	}

	@Override
	public List<Etudiant> listeEtu(int numCls) throws RemoteException {
		List<Etudiant> etus = new ArrayList<Etudiant>();
		String requete = "select * from personne p, cls_etu ce where numCls="+numCls+" and ce.numEtu=p.numPer ;";
		
		try {
			Connection con = DriverManager.getConnection(url, userName, pwd);
			ps = con.prepareStatement(requete);
			rs= ps.executeQuery();
			while(rs.next()) {
			Etudiant etu = new Etudiant();
			etu.setNom(rs.getString("nom"));
			etu.setPrenom(rs.getString("prenom"));
			etu.setCIN(rs.getString("CIN"));
			etus.add(etu);
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return etus;
	}

	@Override
	public List<Prof> listeProf() throws RemoteException {
		List<Prof> profs = new ArrayList<Prof>();
		String requete = "select * from personne where type='prof'";
		
		try {
			Connection con = DriverManager.getConnection(url, userName, pwd);
			ps = con.prepareStatement(requete);
			rs= ps.executeQuery();
			while(rs.next()) {
				String nom = rs.getString("nom");
				String prenom =rs.getString("prenom");
				String matiere =rs.getString("matiere");
				String cin =rs.getString("CIN");
				Prof prof = new Prof(nom, prenom, cin, matiere);
				profs.add(prof);
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return profs;
	}



	@Override
	public List<Etudiant> listeEtuds() throws RemoteException {
		List<Etudiant> etus = new ArrayList<Etudiant>();
		String requete = "select * from personne where  type='etudiant' ;";
		try {
		Connection con = DriverManager.getConnection(url, userName, pwd);
		ps = con.prepareStatement(requete);
		
		
			rs= ps.executeQuery();
			while(rs.next()) {
			Etudiant etu = new Etudiant();
			etu.setNom(rs.getString("nom"));
			etu.setPrenom(rs.getString("prenom"));
			etu.setCIN(rs.getString("CIN"));
			etus.add(etu);
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return etus;
		
	}

}
