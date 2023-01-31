package service_admin;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import metier.Classe;
import metier.Etudiant;
import metier.Personne;
import metier.Prof;

public interface AdminRemote extends Remote{
//	//pour que la personne s'authentifier en tant qu'admin
//	public String connecter(Personne personne) throws RemoteException;
	
	//admin va cr�er une classe, en sp�cifiant dans quel departement
	public void creeCls(String dep) throws RemoteException ;
	
	//pour supprimer une classe
	public void deleteCls(int numCls) throws RemoteException ;
	
	//admin va cr�er un prof
	public void creeProf(Prof prof) throws RemoteException;
	
	//pour supprimer un professeur
    public void deleteProf(Prof prof) throws RemoteException ;
	
	//admin va cr�er un etudiant
	public void creeEtu(Etudiant etudiant) throws RemoteException;
	
	//pour supprimer un etudiant
	public void deleteEtu(Etudiant etu) throws RemoteException ;
	
//	//pour afficher la liste des classes existes
//	public List<Integer> listeCls() throws RemoteException;
//	
//	//pour afficher la liste des etudiants 
//	public List<Etudiant> listeEtuds() throws RemoteException;
//	
//	//pour afficher la liste des etudiants associ�s � une classe
//	public List<Etudiant> listeEtu(int numCls) throws RemoteException;
//	
//	//pour afficher la liste des profs 
//	public List<Prof> listeProf() throws RemoteException;
//	
	//pour affecter un prof � une classe
	public void ajouterProf(Prof prof, int numCls) throws RemoteException;
	
	//pour affecter un etudiant � une classe
	public void ajouterEtu(Etudiant etudiant, int numCls) throws RemoteException;
	
}
