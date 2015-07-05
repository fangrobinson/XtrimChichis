package xtremecraft.vista;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MensajeGanador extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JPanel panel;
	JLabel label;
	
	public MensajeGanador(String nombreGanador) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setMinimumSize(new Dimension(500,50));

		setBounds(380, 350, 300, 100);
		
		panel = new JPanel();
		label = new JLabel(nombreGanador + " has ganado la partida!");
		
		panel.add(label);
		add(panel);
		
		setVisible(true);
		
	}

}
