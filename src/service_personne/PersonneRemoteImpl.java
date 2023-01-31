package service_personne;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.Connect;
import metier.Boite;
import metier.Classe;
import metier.Etudiant;
import metier.Prof;

public class PersonneRemoteImpl extends UnicastRemoteObject implements PersonneRemote{
	
	private Connect con = new Connect();
	private PreparedStatement ps;
	private ResultSet rs;
	
	public PersonneRemoteImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sendToEtud(String msg, String type, Prof prof) throws RemoteException {
		String requete=null;
		if(type.equals("message")) {
		    requete="insert into boiteEtud(message, Prof) values('"+msg+"',"+prof.getId()+");";
		}else if(type.equals("fichier")) {
			requete="insert into boiteEtud(fichier, Prof) values('"+msg+"',"+prof.getId()+");";
		}
		ps = con.connecter(requete);
		
		try {
			 ps.executeUpdate();
			 ps.close();
			 con.closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void sendToProf(String msg, Prof prof, String type, Etudiant etu) throws RemoteException {
		String requete=null;
		if(type.equals("message")) {
		    requete="insert into boiteProf(etud, message, prof) values(?,?,?);";
		}else if(type.equals("fichier")) {
			requete="insert into boiteProf(etud, fichier, prof) values(?,?,?);";
		}
		ps = con.connecter(requete);
		
		try {
			ps.setInt(1, etu.getId());
			ps.setString(2, msg);
			ps.setInt(3, prof.getId());
			 ps.executeQuery();
			 ps.close();
			 con.closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Boite> receptMsg(String type) throws RemoteException {
		List<Boite> boites = new ArrayList<Boite>();
		String requete=null;
		if(type.equals("prof")) {
			requete="select * from boiteEtud;";
		}else if(type.equals("etudiant")) { 
			requete="select * from boiteProf;";
		}
		ps = con.connecter(requete);
		try {
			rs = ps.executeQuery();
			while(rs.next()) {
				int id_etud = rs.getInt("prof");
				String message = rs.getString("message");
				int id_prof = rs.getInt("prof");
				Boite boite = new Boite(id_etud, message, "vide", id_prof);
				boites.add(boite);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boites;
	}

	@Override
	public List<Boite> receptFile(String type) throws RemoteException {
		List<Boite> boites = new ArrayList<Boite>();
		String requete=null;
		if(type.equals("prof")) {
			requete="select * from boiteetud;";
		}else if(type.equals("etudiant")) {
			requete="select * from boiteprof;";
			
		}
		ps = con.connecter(requete);
		try {
			rs = ps.executeQuery(); 
			while(rs.next()) {
				int id_etud = rs.getInt("prof");
				String file = rs.getString("fichier");
				int id_prof = rs.getInt("prof");
				Boite boite = new Boite(id_etud, "vide", file, id_prof);
				boites.add(boite);
			}
			for(Boite boite: boites) {
				System.out.println(boite);
			}
			ps.close();
			con.closeCon();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boites;
	}

	@Override
	public int returnNumCls(String login) throws RemoteException {
		int num=0;
		String requete = "select * from classe c, personne p where c.numProf=p.numPer and login='"+login+"'";
		ps = con.connecter(requete);
		try {
			rs = ps.executeQuery();
			while(rs.next()) {
				num = rs.getInt("numCls");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int returnProf(String login) throws RemoteException {
		int num=0;
		String requete = "select * from personne p where login='"+login+"'";
		ps = con.connecter(requete);
		try {
			rs = ps.executeQuery();
			while(rs.next()) {
				num = rs.getInt("numPer");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	

}
