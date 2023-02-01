package service_personne;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import metier.*;

public interface PersonneRemote extends Remote{
	
	
	//pour qu'un professeur envoyer un message à tout les membres d'une classe
	public void sendToEtud(String msg, String type, Prof prof)throws RemoteException;
	
	//pour qu'un professeur envoyer un fichier à tout les membres d'une classe
	public void sendToProf(String msg, Prof prof, String type, Etudiant etu)throws RemoteException;
	
	
	//pour recevoir un message d'un etudiant ou d'un professeur
	public List<Boite> receptMsg(String type)throws RemoteException;
		
	//pour recevoir un fichier envoyer par un etudiant ou par un professeur
	public List<Boite> receptFile(String type)throws RemoteException;
	
	//pour retourner le numero de classe pour un professeur
	public int returnNumCls(String login)throws RemoteException;

	public int returnProf(String login) throws RemoteException;

	public int idProf(String login) throws RemoteException;

	public int idEtud(String login) throws RemoteException;
}
