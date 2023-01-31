package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import presentation.AdminFenetre;
import presentation.LoginForm;
import presentation.ProfFenetre;
import presentation.Test;
import service_com.MethodeCom;

public class ClientRMI {
	public static void main(String[] args) {
//			LoginForm loginForm = new LoginForm();
//////		AdminFenetre af = new AdminFenetre();
//////		af.afficherEtuds();
////		Test test = new Test();
		ProfFenetre p = new ProfFenetre("nassima");
	}
}
