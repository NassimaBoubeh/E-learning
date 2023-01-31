package service_admin;

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

public class AdminRemoteImpl extends UnicastRemoteObject implements AdminRemote{
	private Connect con = new Connect();
	private PreparedStatement ps ;
	private ResultSet rs ;
	
	public AdminRemoteImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public String connecter(Personne personne) throws RemoteException {
//		String rep=null;
//		try {
//		
//		System.out.println("Connecter:::");
//		String requete="select * from personne where login='"+personne.getLogin()+"';";
//		 ps = con.connecter(requete);
//		rs= ps.executeQuery();
//		
//			if(rs.next()) {
//				rep= "vous êtes connecté.";
//			}else {
//				rep= "login ou password incorrect, réessayez";
//			}
//			con.closeCon();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return rep;
//	}

	//lors de la création de la classe, l'admin doit l'affecter un prof et des etudiants
	@Override
	public void creeCls(String dep) throws RemoteException {
		String requete1="insert into classe(departement, numProf) values(?,?);";
		ps = con.connecter(requete1);
		try {
			ps.setString(1, dep);
			ps.setInt(2, 23);
		    ps.executeUpdate();
			ps.close();
			con.closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void creeProf(Prof prof) throws RemoteException {
		String requete = "insert into personne(login, password, nom, prenom, CIN, matiere, type) values(?,?,?,?,?,?,?)";
		ps= con.connecter(requete);
		
		try {
			ps.setString(1, prof.getLogin());
			ps.setString(2, prof.getPassword());
		    ps.setString(3, prof.getNom());
		    ps.setString(4, prof.getPrenom());
		    ps.setString(5, prof.getCIN() );
		    ps.setString(6, prof.getMetier() );
		    ps.setString(7, "prof" );
		    ps.executeUpdate();
			ps.close();
			con.closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void creeEtu(Etudiant etudiant) throws RemoteException {
		String requete = "insert into personne(login, password, nom, prenom, CIN, type) values(?,?,?,?,?,?)";
		ps= con.connecter(requete);
		
		try {
			ps.setString(1, etudiant.getLogin());
			ps.setString(2, etudiant.getPassword());
		    ps.setString(3, etudiant.getNom());
		    ps.setString(4, etudiant.getPrenom());
		    ps.setString(5, etudiant.getCIN() );
		    ps.setString(6, "etudiant");
		    ps.executeUpdate();
			ps.close();
			con.closeCon();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

//	@Override
//	public List<Integer> listeCls() throws RemoteException {
//		List<Integer> classes = new ArrayList<Integer>();
//		String requete = "select * from classe";
//		ps=con.connecter(requete);
//		try {
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				Integer idCls = rs.getInt("numCls");
//				classes.add(idCls);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return classes;
//	}

//	@Override
//	public List<Etudiant> listeEtu(int numCls) throws RemoteException {
//		List<Etudiant> etus = new ArrayList<Etudiant>();
//		String requete = "select * from personne p, cls_etu ce where numCls="+numCls+" and ce.numEtu=p.numPers ;";
//		ps = con.connecter(requete);
//		try {
//			rs= ps.executeQuery();
//			while(rs.next()) {
//			
//			String nom=rs.getString("nom");
//			String prenom=rs.getString("prenom");
//			String CIN=rs.getString("CIN");
//			Etudiant etu = new Etudiant(nom,prenom,CIN);
//			etus.add(etu);
//			}
//			ps.close();
//			con.closeCon();
//			for (Etudiant etu : etus) {
//				System.out.println(etu);
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		return etus;
//	}

//	@Override
//	public List<Prof> listeProf() throws RemoteException {
//		List<Prof> profs = new ArrayList<Prof>();
//		String requete = "select * from personne where type='prof'";
//		ps = con.connecter(requete);
//		try {
//			rs= ps.executeQuery();
//			while(rs.next()) {
//				String nom = rs.getString("nom");
//				String prenom =rs.getString("prenom");
//				String cin = rs.getString("CIN");
//				String metier =rs.getString("metier");
//				Prof prof = new Prof(nom, prenom,cin, metier);
//				profs.add(prof);
//			}
//			ps.close();
//			con.closeCon();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//		return profs;
//	}
	
	@Override
	public void ajouterProf(Prof prof, int numCls) throws RemoteException {
		String requete = "select * from personne where nom='"+prof.getNom()+"' and prenom='"+prof.getPrenom()+"';";
		
		ps = con.connecter(requete);
		try {
			rs= ps.executeQuery();
			while(rs.next()) {
				String requete1 = "update classe set numProf="+rs.getInt("numPer")+" where numCls="+numCls;
				ps = con.connecter(requete1);
				ps.execute();
			}
			ps.close();
			con.closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void ajouterEtu(Etudiant etudiant, int numCls) throws RemoteException {
		String requete = "select * from personne where nom='"+etudiant.getNom()+"' and prenom='"+etudiant.getPrenom()+"';";
		
		ps = con.connecter(requete);
		try {
			rs= ps.executeQuery();
			while(rs.next()) {
				String requete1 = "insert into cls_etu values(?,?);";
				ps = con.connecter(requete1);
				ps.setInt(1,numCls);
				ps.setInt(2, rs.getInt("numPer") );
				
				ps.executeUpdate();
			}
			ps.close();
			con.closeCon();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCls(int numCls) throws RemoteException {
		String requete = "delete from classe where numCls="+numCls;
		try {
			ps = con.connecter(requete);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void deleteProf(Prof prof) throws RemoteException {
		String requete = "delete from personne where nom='"+prof.getNom()+"' and prenom='"+prof.getPrenom()+"';";
		try {
			ps = con.connecter(requete);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteEtu(Etudiant etu) throws RemoteException {
		String requete = "delete from personne where nom='"+etu.getNom()+"' and prenom='"+etu.getPrenom()+"';";
		try {
			ps = con.connecter(requete);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

//	@Override
//	public List<Etudiant> listeEtuds() throws RemoteException {
//		List<Etudiant> etus = new ArrayList<Etudiant>();
//		String requete = "select * from personne where  type='etudiant' ;";
//		ps = con.connecter(requete);
//		try {
//			rs= ps.executeQuery();
//			while(rs.next()) {
//			Etudiant etu = new Etudiant();
//			etu.setNom(rs.getString("nom"));
//			etu.setPrenom(rs.getString("prenom"));
//			etus.add(etu);
//			}
//			ps.close();
//			con.closeCon();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		return etus;
//		
//	}

	

}
