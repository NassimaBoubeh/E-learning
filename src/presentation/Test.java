package presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import metier.Classe;
import metier.Etudiant;
import service_com.MethodeCom;

public class Test extends JFrame{
	public Test() {
		JFrame frame = new JFrame();
		frame.setLocationRelativeTo(null); 
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
	    frame.add(classe); 
	    JButton prof = new JButton(" PROF ");
	    frame.add(prof); 
	    JButton etudiant = new JButton(" ETUDIANT ");
	    frame.add(etudiant); 
	    
	    classe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					//afficherCls();
					frame.dispose();
			}
	    	
	    });
	    
	    prof.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					//afficherProf();
					frame.dispose();
			}
	    	
	    });
	    
	    etudiant.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					//afficherCls();
					frame.dispose();
			}
	    	
	    });
	    
	    
}
	public static void main(String[] args) {
        //JFrame.setDefaultLookAndFeelDecorated(true);
        //JDialog.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("JComboBox Test");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("Select File");
        button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
              File selectedFile = fileChooser.getSelectedFile();
              System.out.println(selectedFile.getName());
              try {
                  // demander à l'utilisateur où enregistrer le fichier
                  JFileChooser fileChoose = new JFileChooser();
                  int result = fileChoose.showSaveDialog(null);
                  if (result == JFileChooser.APPROVE_OPTION) {
                    // obtenir le chemin sélectionné par l'utilisateur
                    String filePath = fileChoose.getSelectedFile().getAbsolutePath();
                    System.out.println(filePath);
                    // télécharger le fichier à partir du lien
                    //URL website = new URL("http://example.com/file.zip");
                    Files.copy(selectedFile.toPath(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                    
                    JOptionPane.showMessageDialog(null, "Le fichier a été téléchargé avec succès !");
                  }
                } catch (IOException ex) {
                  JOptionPane.showMessageDialog(null, "Erreur lors du téléchargement du fichier : " + ex.getMessage());
                }
            }
          }
        });
        frame.add(button);
        frame.pack();
        frame.setVisible(true);
    }    
}
