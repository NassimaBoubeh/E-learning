package presentation;

import javax.swing.table.DefaultTableModel;

import metier.Classe;
import metier.Etudiant;
import metier.Personne;
import metier.Prof;
import service_admin.AdminRemote;
import service_com.MethodeCom;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class AdminFenetre extends JFrame {
	
	public void firstFenetre() {
		JFrame frame = new JFrame("ADMIN");
		frame.setLocation(500,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
	    frame.setSize(600, 500);
	    frame.setVisible(true);
	    
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    
	    JLabel labelHead = new JLabel("Afficher la liste de :");
		labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,25));
		frame.getContentPane().add(labelHead,BorderLayout.PAGE_START);
		
		JButton classe = new JButton(" CLASSE ");
	    panel.add(classe); 
	    JButton prof = new JButton(" PROF ");
	    panel.add(prof); 
	    JButton etudiant = new JButton(" ETUDIANT ");
	    panel.add(etudiant); 
	    frame.add(panel);
	    classe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					afficherCls();
					frame.dispose();
			}
	    	
	    });
	    
	    prof.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					afficherProf();
					frame.dispose();
			}
	    	
	    });
	    
	    etudiant.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					afficherEtuds();
					frame.dispose();
			}
	    	
	    });
	}
	
	public void afficherCls() {
		try {
			String url="rmi://localhost:1099/commun";
			MethodeCom com = (MethodeCom) Naming.lookup(url);
			List<Classe> classes = com.listeCls();
			String[][] data = new String[classes.size()][4];
			String[] columnNames = {"Numero de classe", "Departement", "nom de Prof", "prenom de Prof"};
			int i=0;
			while(true) {
				for (Classe classe : classes) {
					System.out.println(classe.toString());
			    while(true) {
			        	int j=0;
						
						data[i][j]=String.valueOf(classe.getNumero());
						data[i][j+1]=classe.getDepartement();
						data[i][j+2]=classe.getProf().getNom();
						data[i][j+3]=classe.getProf().getPrenom();
						break;
						}
			    i++;
				}
				break;
			    }
			
		
		
		
			 JFrame frame = new JFrame("ADMIN");
			 frame.setLocation(500,200);
		//crée un JTable avec des données
        JTable table = new JTable(data, columnNames);
    
        JScrollPane scroll = new JScrollPane(table);
        table.setFillsViewportHeight(true);
 
        JLabel labelHead = new JLabel("Liste des classe");
        labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,25));
 
        frame.getContentPane().add(labelHead,BorderLayout.PAGE_START);
        //ajouter la table au frame
        frame.getContentPane().add(scroll,BorderLayout.CENTER);
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setVisible(true);
        
        //
        
        JPanel panel = new JPanel();
        
        Integer[] listeCls = new Integer[classes.size()];
        int k=0;
        for (Classe classe : classes) {
    		listeCls[k]=classe.getNumero();
    		k++;
    	}
        JLabel label = new JLabel("Affecter un professeur à la classe  :");
    	 label.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));
        JComboBox<Integer> listePP = new JComboBox<>(listeCls);
    	
        JPanel p1 = new JPanel();
        p1.add(label);
        p1.add(listePP);
        
        JLabel label1 = new JLabel("Affecter un etudiant à la classe  :");
   	 	label.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));
       JComboBox<Integer> listePE = new JComboBox<>(listeCls);
   	
       JPanel p2 = new JPanel();
       p2.add(label1);
       p2.add(listePE);
        
        panel.add(scroll);
        panel.add(p1);
        panel.add(p2);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);
        
        listePP.addActionListener(new ActionListener() {

    		@Override
    		public void actionPerformed(ActionEvent e) {
    			int num = (Integer) listePP.getSelectedItem();
    				ajouterProf(num);
    				frame.dispose();
    		}

    		
        	
        });
        listePE.addActionListener(new ActionListener() {

    		@Override
    		public void actionPerformed(ActionEvent e) {
    			int num = (Integer) listePE.getSelectedItem();
    				ajouterEtud(num);
    				frame.dispose();
    		}

    		
        	
        });
        
     //   
        
        JButton creer = new JButton("CREER UNE CLASSE");
	    add(creer);
	    
	    JButton supprimer = new JButton("SUPPRIMER UNE CLASSE");
	    add(supprimer);
	    
	    JButton retour = new JButton("RETOUR");
	    add(retour);
	    
	    JPanel p = new JPanel();
	    p.add(creer);
	    p.add(supprimer);
	    p.add(retour);
	    frame.add(p, BorderLayout.SOUTH);
	    
	    creer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					creerClasse();
					frame.dispose();
			}
	    	
	    });
	    
	    supprimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					supprimerCls();
					frame.dispose();
			}
	    	
	    });
	    
	    retour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				    firstFenetre();
					frame.dispose();
			}
	    	
	    });
	    frame.validate();
		} catch ( RemoteException | MalformedURLException | NotBoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       
	}
	
	public void creerClasse() {
		 JFrame frame = new JFrame("ADMIN");
		 frame.setLocation(500,200);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(600, 500);
	        frame.setVisible(true);
		 JLabel labelHead = new JLabel("Créer une classe:");
		 labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,25));
		 frame.getContentPane().add(labelHead,BorderLayout.PAGE_START);
		 JPanel p1 = new JPanel();
		   
		    JLabel dep = new JLabel("Departement:");
		    JTextField depField = new JTextField(20);
		    p1.add(dep);
		    p1.add(depField);
		    frame.add(p1);
		    
		    JPanel p = new JPanel();
		    JButton retour = new JButton(" RETOUR ");
		    add(retour); 
		    JButton valider = new JButton(" CREER ");
		    add(valider); 
		    p.add(valider);
		    p.add(retour);
		    frame.add(p, BorderLayout.SOUTH);
		    
		    valider.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String url="rmi://localhost:1099/admin";
						AdminRemote ar = (AdminRemote) Naming.lookup(url);
						String dep = depField.getText();
						System.out.println(dep);
						ar.creeCls(dep);
						afficherCls();
						frame.dispose();
					} catch ( RemoteException | MalformedURLException | NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						
						
					
				}
		    	
		    });
		    retour.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
						afficherCls();
						frame.dispose();
				}
		    	
		    });
		   
	}
	
	public void supprimerCls() {
		setTitle("LOGIN_FORM");
	    setSize(600, 500);
	    setLocation(500,200);
	    setLayout(new FlowLayout());
	    
	    String url="rmi://localhost:1099/commun";
		MethodeCom com;
		try {
			com = (MethodeCom) Naming.lookup(url);
			List<Classe> classes = com.listeCls();
	    
	    Integer[] listeCls = new Integer[classes.size()];
	    int i=0;
	    for (Classe classe : classes) {
			listeCls[i]=classe.getNumero();
			i++;
		}
	    
	    JComboBox listeClasse = new JComboBox<>(listeCls);
	    JLabel l1 = new JLabel("Choisir la classe que vous voulez supprimer : ");
	    
	    JPanel p = new JPanel();
	    p.add(l1);
	    p.add(listeClasse);
	    add(p);
	    
	    listeClasse.addActionListener(new ActionListener() {     
            @Override
            public void actionPerformed(ActionEvent e) {
          
            	Integer num = (Integer) listeClasse.getSelectedItem();
            	String url="rmi://localhost:1099/admin";
				try {
					AdminRemote ar = (AdminRemote) Naming.lookup(url);
					ar.deleteCls(num);
				} catch (MalformedURLException | RemoteException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	JOptionPane.showMessageDialog(null, "la classe est supprimée");
            	afficherCls();
				dispose();
            }
          });
	    
	    
	    } catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setVisible(true); 
	}
	
	public void afficherProf() {
		try {
			String url="rmi://localhost:1099/commun";
			MethodeCom com = (MethodeCom) Naming.lookup(url);
			List<Prof> profs = com.listeProf();
			String[][] data = new String[profs.size()][4];
			String[] columnNames = { "nom de Prof", "prenom de Prof", "matière", "CIN"};
			int i=0;
			while(true) {
				for (Prof prof : profs) {
			    while(true) {
			        	int j=0;
						
						data[i][j]=String.valueOf(prof.getNom());
						data[i][j+1]=prof.getPrenom();
						data[i][j+3]=prof.getCIN();
						data[i][j+2]=prof.getMetier();
						break;
						}
			    i++;
				}
				break;
			    }
			
		
		
		
			 JFrame frame = new JFrame("ADMIN");
			 frame.setLocation(500,200);
		//crée un JTable avec des données
        JTable table = new JTable(data, columnNames);
    
        JScrollPane scroll = new JScrollPane(table);
        table.setFillsViewportHeight(true);
 
        JLabel labelHead = new JLabel("Liste des profs");
        labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,25));
 
        frame.getContentPane().add(labelHead,BorderLayout.PAGE_START);
        //ajouter la table au frame
        frame.getContentPane().add(scroll,BorderLayout.CENTER);
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setVisible(true);
        
        JButton creer = new JButton("CREER UN Prof");
	    add(creer);
	    
	    JButton supprimer = new JButton("SUPPRIMER UN Prof");
	    add(supprimer);
	    
	    JButton retour = new JButton("RETOUR");
	    add(retour);
	    
	    JPanel p = new JPanel();
	    p.add(creer);
	    p.add(supprimer);
	    p.add(retour);
	    frame.add(p, BorderLayout.SOUTH);
	    
	    creer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					creerProf();
					frame.dispose();
			}

			
	    	
	    });
	    
	    supprimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				supProf();
					frame.dispose();
			}

			
	    	
	    });
	    
        retour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				    firstFenetre();
					frame.dispose();
			}
	    	
	    });
		} catch ( RemoteException | MalformedURLException | NotBoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 
	}
	
	public void creerProf() {
		 JFrame frame = new JFrame("ADMIN");
		 frame.setLocation(500,200);
		 setLocationRelativeTo(null);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setSize(600, 500);
	     frame.setVisible(true);
		 JLabel labelHead = new JLabel("Créer un professeur:");
		 labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,25));
		 frame.getContentPane().add(labelHead,BorderLayout.PAGE_START);
		 	
		 	JPanel pannel = new JPanel();
		 	pannel.setLayout(new BoxLayout(pannel, BoxLayout.Y_AXIS));
		 	
		 	JPanel p1 = new JPanel();
		    JLabel login = new JLabel("Login:");
		    JTextField loginField = new JTextField(20);
		   
		    p1.add(login);
		    p1.add(loginField);
		    pannel.add(p1);
		    
		    JPanel p2 = new JPanel();
		    JLabel password = new JLabel("Password:");
		    JPasswordField pwdField = new JPasswordField(20);
		    
		    p2.add(password);
		    p2.add(pwdField);
		    pannel.add(p2);
		    
		    JPanel p3 = new JPanel();
		    JLabel nom = new JLabel("Nom:");
		    JTextField nomField = new JTextField(20);
		    
		    p3.add(nom);
		    p3.add(nomField);
		    pannel.add(p3);
		    
		    JPanel p4 = new JPanel();
		    JLabel prenom = new JLabel("Prenom:");
		    JTextField preField = new JTextField(20);
		    
		    p4.add(prenom);
		    p4.add(preField);
		    pannel.add(p4);
		    
		    JPanel p5 = new JPanel();
		    JLabel matiere = new JLabel("Matière:");
		    JTextField matField = new JTextField(20);
		    
		    p5.add(matiere);
		    p5.add(matField);
		    pannel.add(p5);
		    
		    JPanel p6 = new JPanel();
		    JLabel cin = new JLabel("CIN:");
		    JTextField cinField = new JTextField(20);
		    
		    p6.add(cin);
		    p6.add(cinField);
		    pannel.add(p6);
		    
		    frame.add(pannel);
		    
		    JPanel p = new JPanel();
		    JButton retour = new JButton(" RETOUR ");
		    add(retour); 
		    JButton valider = new JButton(" CREER ");
		    add(valider); 
		    p.add(valider);
		    p.add(retour);
		    frame.add(p, BorderLayout.SOUTH);
		    
		    valider.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try { 
						String Login = loginField.getText();
						String Matiere = matField.getText();
						String Prenom = preField.getText();
						String Nom = nomField.getText();
						String cin = cinField.getText();
						char[] mdp = pwdField.getPassword();
					    String Password = new String(mdp);
						String url="rmi://localhost:1099/admin";
						AdminRemote ar = (AdminRemote) Naming.lookup(url);
						Prof prof = new Prof(Nom, Prenom, cin, Matiere, Login, Password);
						ar.creeProf(prof);
						afficherProf();
						frame.dispose();
					} catch ( RemoteException | MalformedURLException | NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						
						
					
				}
		    	
		    });
		    retour.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
						afficherProf();
						frame.dispose();
				}
		    	
		    });	
				
	}
	
	public void supProf() {
		JFrame frame = new JFrame("ADMIN");
		frame.setLocation(500,200);
		 setLocationRelativeTo(null);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setSize(600, 500);
	     frame.setVisible(true);
		 JLabel labelHead = new JLabel("Saisir le nom et le prenom de prof que vou voulez supprimer:");
		 labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));
		 frame.getContentPane().add(labelHead,BorderLayout.PAGE_START);
		 	
		 	//JPanel pannel1 = new JPanel();
		 	JPanel pannel = new JPanel();
		 	pannel.setLayout(new BoxLayout(pannel, BoxLayout.Y_AXIS));
		    
		    JPanel p3 = new JPanel();
		    JLabel nom = new JLabel("Nom:");
		    JTextField nomField = new JTextField(20);
		    
		    p3.add(nom);
		    p3.add(nomField);
		    pannel.add(p3);
		    
		    JPanel p4 = new JPanel();
		    JLabel prenom = new JLabel("Prenom:");
		    JTextField preField = new JTextField(20);
		    
		    p4.add(prenom);
		    p4.add(preField);
		    pannel.add(p4);
		    
		    
		    frame.add(pannel);
		    //frame.add(pannel1);
		    
		    JPanel p = new JPanel();
		    JButton retour = new JButton(" RETOUR ");
		    add(retour); 
		    JButton valider = new JButton(" VALIDER ");
		    add(valider); 
		    p.add(valider);
		    p.add(retour);
		    frame.add(p, BorderLayout.SOUTH);
		    
	    valider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try { 
					String Prenom = preField.getText();
					String Nom = nomField.getText();
					String url="rmi://localhost:1099/admin";
					AdminRemote ar = (AdminRemote) Naming.lookup(url);
					Prof prof = new Prof(Nom, Prenom);
					ar.deleteProf(prof);
					afficherProf();
					frame.dispose();
				} catch ( RemoteException | MalformedURLException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
					
				
			}
	    	
	    });
	    
	    
	    retour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					afficherProf();
					frame.dispose();
			}
	    	
	    });
	    
	}

//pour afficher la liste des etudiants associés à une classe	
	public void afficherEtudCls(int num) {
	    
		try {
			String url="rmi://localhost:1099/commun";
			MethodeCom com = (MethodeCom) Naming.lookup(url);
			List<Etudiant> etuds = com.listeEtu(num);
			String[][] data = new String[etuds.size()][4];
			String[] columnNames = { "nom de l'etudiant", "prenom de l'etudiant", "CIN"};
			int i=0;
			while(true) {
				for (Etudiant etu : etuds) {
			    while(true) {
			        	int j=0;
						data[i][j]=etu.getNom();
						data[i][j+1]=etu.getPrenom();
						data[i][j+2]=etu.getCIN();
						break;
						}
			    i++;  
				}
				break;
			    }
			
		
		
		
			 JFrame frame = new JFrame("ADMIN");
			 frame.setLocation(500,200);
		//crée un JTable avec des données
        JTable table = new JTable(data, columnNames);
    
        JScrollPane scroll = new JScrollPane(table);
        table.setFillsViewportHeight(true);
 
        JLabel labelHead = new JLabel("Liste des etudiants pour la classe"+num);
        labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,25));
 
        frame.getContentPane().add(labelHead,BorderLayout.PAGE_START);
        //ajouter la table au frame
        frame.getContentPane().add(scroll,BorderLayout.CENTER);
 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setVisible(true);
        
        JButton retour = new JButton("RETOUR");
	    add(retour);
	    
	    
	    
	    JPanel p = new JPanel();
	    p.add(retour);
	   
	    frame.add(p, BorderLayout.SOUTH);
	    
	    retour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					afficherEtuds();
					frame.dispose();
			}
	    	
	    });
        
		} catch ( RemoteException | MalformedURLException | NotBoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}


//pour afficher la liste de tout etudiants 
public void afficherEtuds() {
	    

	
	try {
		String url="rmi://localhost:1099/commun";
		MethodeCom com = (MethodeCom) Naming.lookup(url);
		List<Etudiant> etuds = com.listeEtuds();
		String[][] data = new String[etuds.size()][4];
		String[] columnNames = { "nom de l'etudiant", "prenom de l'etudiant", "CIN"};
		int i=0;
		while(true) {
			for (Etudiant etu : etuds) {
		    while(true) {
		        	int j=0;
					data[i][j]=etu.getNom();
					data[i][j+1]=etu.getPrenom();
					data[i][j+2]=etu.getCIN();
					break;
					}
		    i++;
			}
			break;
		    }
		
	
	
	
		 JFrame frame = new JFrame("ADMIN");
		 frame.setLocation(500,200);
		JPanel panel = new JPanel();
	//crée un JTable avec des données
    JTable table = new JTable(data, columnNames);

    JScrollPane scroll = new JScrollPane(table);
    table.setFillsViewportHeight(true);

    JLabel labelHead = new JLabel("Liste des etudiants ");
    labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,25));

    frame.getContentPane().add(labelHead,BorderLayout.PAGE_START);
    //ajouter la table au frame
    frame.getContentPane().add(scroll,BorderLayout.CENTER);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 500);
    
    
    
//
    List<Classe> classes = com.listeCls();
    
    Integer[] listeCls = new Integer[classes.size()];
    int k=0;
    for (Classe classe : classes) {
		listeCls[k]=classe.getNumero();
		k++;
	}
    JLabel label = new JLabel("Liste des etudiant pour la classe :");
	 label.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));
    JComboBox<Integer> liste = new JComboBox<>(listeCls);
	
    JPanel p1 = new JPanel();
    p1.add(label);
    p1.add(liste);
    
    panel.add(scroll);
    panel.add(p1);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    frame.add(panel);
    
    liste.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int num = (Integer) liste.getSelectedItem();
			afficherEtudCls(num);
				frame.dispose();
		}

		
    	
    });
    
 //   
    JButton creer = new JButton("CREER UN Etudiant");
    add(creer);
    
    JButton supprimer = new JButton("SUPPRIMER UN Etudiant");
    add(supprimer);
    
    JButton retour = new JButton("RETOUR");
    add(retour);
    
    JPanel p = new JPanel();
    p.add(creer);
    p.add(supprimer);
    p.add(retour);
    frame.add(p, BorderLayout.SOUTH);
    
    creer.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			(new AdminFenetre()).creerEtud();
				frame.dispose();
		}

		
    	
    });
    
    supprimer.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			(new AdminFenetre()).supEtud();
				frame.dispose();
		}

		
    	
    });
    
    retour.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			    firstFenetre();
				frame.dispose();
		}
    	
    });
    
    frame.setVisible(true);
    
    frame.validate();
	} catch ( RemoteException | MalformedURLException | NotBoundException  e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		
		 
	}

	public void creerEtud() {
		 JFrame frame = new JFrame("ADMIN");
		 frame.setLocation(500,200);
		 setLocationRelativeTo(null);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setSize(600, 500);
	     frame.setVisible(true);
		 JLabel labelHead = new JLabel("Créer un etudiant:");
		 labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,25));
		 frame.getContentPane().add(labelHead,BorderLayout.PAGE_START);
		 	
		 	JPanel pannel = new JPanel();
		 	pannel.setLayout(new BoxLayout(pannel, BoxLayout.Y_AXIS));
		 	
		 	JPanel p1 = new JPanel();
		    JLabel login = new JLabel("Login:");
		    JTextField loginField = new JTextField(20);
		   
		    p1.add(login);
		    p1.add(loginField);
		    pannel.add(p1);
		    
		    JPanel p2 = new JPanel();
		    JLabel password = new JLabel("Password:");
		    JPasswordField pwdField = new JPasswordField(20);
		    
		    p2.add(password);
		    p2.add(pwdField);
		    pannel.add(p2);
		    
		    JPanel p3 = new JPanel();
		    JLabel nom = new JLabel("Nom:");
		    JTextField nomField = new JTextField(20);
		    
		    p3.add(nom);
		    p3.add(nomField);
		    pannel.add(p3);
		    
		    JPanel p4 = new JPanel();
		    JLabel prenom = new JLabel("Prenom:");
		    JTextField preField = new JTextField(20);
		    
		    p4.add(prenom);
		    p4.add(preField);
		    pannel.add(p4);
		    
		    JPanel p5 = new JPanel();
		    JLabel cin = new JLabel("CIN:");
		    JTextField cinField = new JTextField(20);
		    
		    p5.add(cin);
		    p5.add(cinField);
		    pannel.add(p5);
		    
		    
		    frame.add(pannel);
		    
		    JPanel p = new JPanel();
		    JButton retour = new JButton(" RETOUR ");
		    add(retour); 
		    JButton valider = new JButton(" CREER ");
		    add(valider); 
		    p.add(valider);
		    p.add(retour);
		    frame.add(p, BorderLayout.SOUTH);
		    
		    valider.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try { 
						String Login = loginField.getText();
						String Prenom = preField.getText();
						String Nom = nomField.getText();
						char[] mdp = pwdField.getPassword();
					    String Password = new String(mdp);
					    String cin = cinField.getText();
						String url="rmi://localhost:1099/admin";
						AdminRemote ar = (AdminRemote) Naming.lookup(url);
						Etudiant etudiant = new Etudiant(Nom, Prenom,cin, Login, Password);
						ar.creeEtu(etudiant);
						afficherEtuds();
						frame.dispose();
					} catch ( RemoteException | MalformedURLException | NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						
						
					
				}
		    	
		    });
		    retour.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
						afficherEtuds();
						frame.dispose();
				}
		    	
		    });	
				
	}
	
	public void supEtud() {
		JFrame frame = new JFrame("ADMIN");
		frame.setLocation(500,200);
		 setLocationRelativeTo(null);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setSize(600, 500);
	     frame.setVisible(true);
		 JLabel labelHead = new JLabel("Saisir le nom et le prenom de l'etudiant que vou voulez supprimer:");
		 labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));
		 frame.getContentPane().add(labelHead,BorderLayout.PAGE_START);
		 	
		 	//JPanel pannel1 = new JPanel();
		 	JPanel pannel = new JPanel();
		 	pannel.setLayout(new BoxLayout(pannel, BoxLayout.Y_AXIS));
		    
		    JPanel p3 = new JPanel();
		    JLabel nom = new JLabel("Nom:");
		    JTextField nomField = new JTextField(20);
		    
		    p3.add(nom);
		    p3.add(nomField);
		    pannel.add(p3);
		    
		    JPanel p4 = new JPanel();
		    JLabel prenom = new JLabel("Prenom:");
		    JTextField preField = new JTextField(20);
		    
		    p4.add(prenom);
		    p4.add(preField);
		    pannel.add(p4);
		    
		    
		    frame.add(pannel);
		    //frame.add(pannel1);
		    
		    JPanel p = new JPanel();
		    JButton retour = new JButton(" RETOUR ");
		    add(retour); 
		    JButton valider = new JButton(" VALIDER ");
		    add(valider); 
		    p.add(valider);
		    p.add(retour);
		    frame.add(p, BorderLayout.SOUTH);
		    
	    valider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try { 
					String Prenom = preField.getText();
					String Nom = nomField.getText();
					String url="rmi://localhost:1099/admin";
					AdminRemote ar = (AdminRemote) Naming.lookup(url);
					Etudiant etudiant = new Etudiant(Nom, Prenom);
					ar.deleteEtu(etudiant);
					afficherEtuds();
					frame.dispose();
				} catch ( RemoteException | MalformedURLException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
					
				
			}
	    	
	    });
	    
	    
	    retour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					afficherEtuds();
					frame.dispose();
			}
	    	
	    });
	    
	}
	
	public void ajouterProf(int num) {
		JFrame frame = new JFrame("ADMIN");
		frame.setLocation(500,200);
		 setLocationRelativeTo(null);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setSize(600, 500);
	     frame.setVisible(true);
		 JLabel labelHead = new JLabel("Saisir le nom et le prenom de professeur que vou voulez ajouter à la classe:"+num);
		 labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));
		 frame.getContentPane().add(labelHead,BorderLayout.PAGE_START);
		 
		//JPanel pannel1 = new JPanel();
		 	JPanel pannel = new JPanel();
		 	pannel.setLayout(new BoxLayout(pannel, BoxLayout.Y_AXIS));
		    
		    JPanel p3 = new JPanel();
		    JLabel nom = new JLabel("Nom:");
		    JTextField nomField = new JTextField(20);
		    
		    p3.add(nom);
		    p3.add(nomField);
		    pannel.add(p3);
		    
		    JPanel p4 = new JPanel();
		    JLabel prenom = new JLabel("Prenom:");
		    JTextField preField = new JTextField(20);
		    
		    p4.add(prenom);
		    p4.add(preField);
		    pannel.add(p4);
		    
		    
		    frame.add(pannel);
		    //frame.add(pannel1);
		    
		    JPanel p = new JPanel();
		    JButton retour = new JButton(" RETOUR ");
		    add(retour); 
		    JButton valider = new JButton(" VALIDER ");
		    add(valider); 
		    p.add(valider);
		    p.add(retour);
		    frame.add(p, BorderLayout.SOUTH);
		    
	    valider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try { 
					String Prenom = preField.getText();
					String Nom = nomField.getText();
					String url="rmi://localhost:1099/admin";
					AdminRemote ar = (AdminRemote) Naming.lookup(url);
					Prof prof = new Prof(Nom, Prenom);
					ar.ajouterProf(prof, num);
					afficherCls();
					frame.dispose();
				} catch ( RemoteException | MalformedURLException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
					
				
			}
	    	
	    });
	    
	    
	    retour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					afficherCls();
					frame.dispose();
			}
	    	
	    });
	}
	
	public void ajouterEtud(int num) {
		JFrame frame = new JFrame("ADMIN");
		frame.setLocation(500,200);
		 setLocationRelativeTo(null);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.setSize(600, 500);
	     frame.setVisible(true);
		 JLabel labelHead = new JLabel("Saisir le nom et le prenom de etudiant que vou voulez ajouter à la classe:"+num);
		 labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));
		 frame.getContentPane().add(labelHead,BorderLayout.PAGE_START);
		 
		//JPanel pannel1 = new JPanel();
		 	JPanel pannel = new JPanel();
		 	pannel.setLayout(new BoxLayout(pannel, BoxLayout.Y_AXIS));
		    
		    JPanel p3 = new JPanel();
		    JLabel nom = new JLabel("Nom:");
		    JTextField nomField = new JTextField(20);
		    
		    p3.add(nom);
		    p3.add(nomField);
		    pannel.add(p3);
		    
		    JPanel p4 = new JPanel();
		    JLabel prenom = new JLabel("Prenom:");
		    JTextField preField = new JTextField(20);
		    
		    p4.add(prenom);
		    p4.add(preField);
		    pannel.add(p4);
		    
		    
		    frame.add(pannel);
		    //frame.add(pannel1);
		    
		    JPanel p = new JPanel();
		    JButton retour = new JButton(" RETOUR ");
		    add(retour); 
		    JButton valider = new JButton(" VALIDER ");
		    add(valider); 
		    p.add(valider);
		    p.add(retour);
		    frame.add(p, BorderLayout.SOUTH);
		    
	    valider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try { 
					String Prenom = preField.getText();
					String Nom = nomField.getText();
					String url="rmi://localhost:1099/admin";
					AdminRemote ar = (AdminRemote) Naming.lookup(url);
					Etudiant etudiant = new Etudiant(Nom, Prenom);
					ar.ajouterEtu(etudiant, num);
					afficherEtudCls(num);
					frame.dispose();
				} catch ( RemoteException | MalformedURLException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
					
				
			}
	    	
	    });
	    
	    
	    retour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					afficherCls();
					frame.dispose();
			}
	    	
	    });
	}
	
	
}
