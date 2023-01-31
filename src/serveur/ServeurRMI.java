package serveur;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import service_admin.AdminRemoteImpl;
import service_com.MethodeComImpl;
import service_personne.PersonneRemote;
import service_personne.PersonneRemoteImpl;

public class ServeurRMI {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			//pour tout les clients
			MethodeComImpl com = new MethodeComImpl();
			System.out.println(com.toString());
			Naming.bind("rmi://localhost:1099/commun", com);
			
			//pour l'admin
			AdminRemoteImpl adminDest = new AdminRemoteImpl();
			System.out.println(adminDest.toString());
			Naming.bind("rmi://localhost:1099/admin", adminDest);
			
			//pour le personne soit prof ou etudiant
			PersonneRemoteImpl persDest = new PersonneRemoteImpl();
			System.out.println(persDest.toString());
			Naming.bind("rmi://localhost:1099/personne", persDest);
			
		} catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
