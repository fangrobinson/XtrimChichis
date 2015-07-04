package xtremecraft.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class VentanaSobreDesarrolladores extends JFrame {
	
	public VentanaSobreDesarrolladores(){
		setTitle("Sobre los Creadores");

		setMaximumSize(new Dimension (320, 100));
		setMinimumSize(new Dimension (320, 100));
		setPreferredSize(new Dimension (320, 100));

		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JTextArea texto = new JTextArea(
			    "Este trabajo practico fue hecho por: \n" +
			    "-Ana Czarnitzki\n" +
			    "-Robinson Fang\n" +
			    "-Eugenia Mariotti\n"
		);
		texto.setMaximumSize(new Dimension(320,180));
		texto.setEditable(false);
		panel.add(texto);

		panel.setVisible(true);
		this.add(panel);
		pack();
		setResizable(false);
		setBounds(400, 300, 320, 100);
		setVisible(true);
	
	}
	
	static class VentanaNombres extends JPanel{

		public VentanaNombres(String nombreDesarrollador){

			JTextArea texto = new JTextArea(nombreDesarrollador);
			add(texto, BorderLayout.CENTER);
			texto.setEditable(false);
			setVisible(true);
			
		}
	}
	
}
