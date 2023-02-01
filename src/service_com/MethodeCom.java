package service_com;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import metier.Classe;
import metier.Etudiant;
import metier.Personne;
import metier.Prof;

public interface MethodeCom extends Remote{
	
		//pour retourner le type de personne
		public String typePers(String login) throws RemoteException;
	
	    //pour que la personne s'authentifier 
		public String connecter(Personne personne) throws RemoteException;
		
		//pour afficher la liste des classes existes
		public List<Classe> listeCls() throws RemoteException;
		
		//pour afficher la liste des etudiants 
		public List<Etudiant> listeEtuds() throws RemoteException;
		
		//pour afficher la liste des etudiants associés à une classe
		public List<Etudiant> listeEtu(int numCls) throws RemoteException;
		
		//pour afficher la liste des profs 
		public List<Prof> listeProf() throws RemoteException;

		//retourner la liste des classe pour un client identifié par son login
		public List<Integer> listeClsParEtud(String login) throws RemoteException;
		
}
