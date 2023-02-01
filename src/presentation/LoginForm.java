package presentation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import metier.Etudiant;
import metier.Personne;
import metier.Prof;
import service_com.MethodeCom;



public class LoginForm extends JFrame  {
	
	public  LoginForm() {
	   	setTitle("LOGIN_FORM");
	    setSize(600, 500);
	    setLocationRelativeTo(null);
	    setLayout(new FlowLayout());
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    
	    JLabel l = new JLabel("AUTHENTIFICATION :");
	    JPanel p = new JPanel();
	    p.add(l);
	    
	    JPanel p1 = new JPanel();
	   
	    JLabel usernameLabel = new JLabel("   LOGIN:");
	    JTextField usernameField = new JTextField(20);
	    p1.add(usernameLabel);
	    p1.add(usernameField);
	    
	    JPanel p2 = new JPanel();
	    
	    JLabel passwordLabel = new JLabel("PASSWORD:");
	    JPasswordField passwordField = new JPasswordField(20);
	    p2.add(passwordLabel);
	    p2.add(passwordField);
	    
	    
	    JPanel p3 = new JPanel();
	   
	    
	    JButton buttonValider = new JButton("CONNECTER");
	    p3.add(buttonValider);
	    
	    buttonValider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String login = usernameField.getText();
				char[] mdp = passwordField.getPassword();
				String pwd = new String(mdp);
				Personne personne = new Personne(login, pwd);
				try {
					String url="rmi://localhost:1099/commun";
					MethodeCom com = (MethodeCom) Naming.lookup(url);
					String rep = com.connecter(personne);
					JOptionPane.showMessageDialog(null, rep);
					if(rep.equalsIgnoreCase("vous êtes connecté.")) { 
					
						String type = com.typePers(login);
						if(type.equals("admin")) {
							(new AdminFenetre()).firstFenetre();
						}else if(type.equals("prof")){
							ProfFenetre prof =new ProfFenetre(login);
						}else if(type.equals("etudiant")){
							EtudiantFenetre prof =new EtudiantFenetre(login);
						}
					dispose();
					}
					else {
						new LoginForm();
						dispose();
					}
				} catch ( RemoteException | MalformedURLException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
					
				
			}
	    	
	    });
	    
	    
			
	    
	    panel.add(p);
	    panel.add(p1);
	    panel.add(p2);
	    panel.add(p3);
	    add(panel);
	    setVisible(true); 
   }
   

}
