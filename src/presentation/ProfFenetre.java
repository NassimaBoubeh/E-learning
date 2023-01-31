package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import metier.Boite;
import metier.Etudiant;
import metier.Prof;
import service_com.MethodeCom;
import service_personne.PersonneRemote;

public class ProfFenetre {
	String urlPersonne = "rmi://localhost:1099/personne";
	String urlCommun = "rmi://localhost:1099/commun";
	
	public ProfFenetre(String login) {
		JFrame frame = new JFrame("POFESSEUR_PAGE");
		frame.setLocation(500,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
	    frame.setSize(600, 500);
	    frame.setVisible(true);
	    try {
	    JPanel panel1 = new JPanel();
	    panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
	    
	    JPanel panel2 = new JPanel();
	    panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
	    
	    
	    JLabel labelHead = new JLabel("La liste des etudiants :");
		labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));
		
		
		
			MethodeCom com = (MethodeCom) Naming.lookup(urlCommun);
			PersonneRemote personne = (PersonneRemote) Naming.lookup(urlPersonne);
			int numCls = personne.returnNumCls(login);
			List<Etudiant> etuds = com.listeEtu(numCls);
			
			DefaultListModel<String> nomPrenom = new DefaultListModel<>();
			for (Etudiant etu : etuds) {
				nomPrenom.addElement(etu.getNom()+"_"+etu.getPrenom());
			}
			
			JList<String> etudiants = new JList<>(nomPrenom);
			
			panel1.add(labelHead);
			panel1.add(etudiants);
			panel2.add(panel1);
			
			JButton button = new JButton("CHATROOM");
			panel2.add(button);
			
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
						chatRoom(login);
						frame.dispose();
				}
		    	
		    });
			
			frame.add(panel2);
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    
	}
	List<Boite> boitesMsg = new ArrayList<Boite>();
	List<Boite> boitesFile = new ArrayList<Boite>();
	JTextArea chatArea = new JTextArea();
	
//	Runnable runnable = () ->{
//		try {
//			PersonneRemote personne = (PersonneRemote) Naming.lookup(urlPersonne);
//			while(true) {
//				boitesMsg = personne.receptMsg("prof");
//				for(Boite boite: boitesMsg) {
//					chatArea.append("Moi : "+boite.getMessage() + "\n");
//				}
//				boitesFile = personne.receptFile("prof");
//				for(Boite boite: boitesFile) {
//					fichierArea.append("Moi : "+boite.getFichier() + "\n");
//				}
//		}
//		} catch (MalformedURLException | RemoteException | NotBoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	};
	
	public void chatRoom(String login) {
//		Thread thread = new Thread(runnable);
//		thread.start();
		
		JFrame frame = new JFrame("CHATROOM");
		frame.setLocation(500,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
	    frame.setSize(600, 500);
	    frame.setVisible(true);
	    
	    
        chatArea.setEditable(true);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 16));
        chatArea.setPreferredSize(new Dimension(500, 200));
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        //frame.add(scrollPane, BorderLayout.CENTER);
        PersonneRemote personne = null;
		try {
			personne = (PersonneRemote) Naming.lookup(urlPersonne);
			boitesMsg = personne.receptMsg("prof");
			 for(Boite boite: boitesMsg) {
				if(boite.getMessage()!=null) {
        		chatArea.append("  "+boite.getMessage() + "\n");
				}
		}
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (MalformedURLException |  NotBoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
       
        
        JLabel l = new JLabel("les fichiers");
        
        try {
			boitesFile = personne.receptFile("prof");
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		DefaultListModel<String> fichiers = new DefaultListModel<>();
		for(Boite boite: boitesFile) {
			if(boite.getFichier()!=null) {
			fichiers.addElement(boite.getFichier());
			}
		}
        
		JList<String> fichierArea = new JList<>(fichiers);
        //fichierArea.setEditable(true);
        fichierArea.setFont(new Font("Arial", Font.PLAIN, 16));
        fichierArea.setPreferredSize(new Dimension(500, 200));
        JScrollPane scrollPane1 = new JScrollPane(fichierArea);
        scrollPane1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        
        
		
	    
        JPanel panel2 = new JPanel();
	    panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
	    panel2.add(chatArea, BorderLayout.CENTER);
	    panel2.add(l);
	    panel2.add(fichierArea, BorderLayout.CENTER);
	    frame.add(panel2);
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField messageField = new JTextField();
        messageField.setFont(new Font("Arial", Font.PLAIN, 16));
        messageField.setPreferredSize(new Dimension(300, 25));
        bottomPanel.add(messageField);
        
        JButton sendButton = new JButton("ENVOYER");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                
                messageField.setText("");
                
                chatArea.append("Moi : "+message + "\n");
                
					PersonneRemote personne;
					try {
						personne = (PersonneRemote) Naming.lookup(urlPersonne);
						int numProf = personne.returnProf(login);
					     Prof prof = new Prof(numProf); 
					     personne.sendToEtud(message, "message", prof);
					} catch (MalformedURLException | RemoteException | NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
               
                
            }
        });
        
        bottomPanel.add(sendButton);
        
        JButton button = new JButton("FILE");
        button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
              File selectedFile = fileChooser.getSelectedFile();
              String chemin = selectedFile.getAbsolutePath();
              
				try {
					PersonneRemote personne = (PersonneRemote) Naming.lookup(urlPersonne);
					int numProf = personne.returnProf(login);
				     Prof prof = new Prof(numProf); 
				     personne.sendToEtud(chemin, "fichier", prof);
				} catch (MalformedURLException | RemoteException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
          }
        });
        bottomPanel.add(button);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        
	}
}
