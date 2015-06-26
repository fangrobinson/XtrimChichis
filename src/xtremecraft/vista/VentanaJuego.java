package xtremecraft.vista;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import xtremecraft.mapa.Mapa;
import xtremecraft.partida.Partida;


public class VentanaJuego extends JFrame{
	
	private static final long serialVersionUID = -973667959098244571L;
	
	public VentanaJuego(Partida partida){
		
		Mapa mapa = partida.getMapa();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ALGO CRAFT");
		setVisible(true);
				
	    JPanel grid = new JPanel();
	    grid.setLayout(new GridLayout(mapa.ancho(), mapa.alto()));
	    
	    for (int i = 0; i < mapa.ancho(); i++) {
	        for (int n = 0; n < mapa.alto(); n++) {
	            grid.add(new JButton());
	        }
	    }
	   
	    add(grid);
	    pack();
	    setSize(800,800);
		
	}
	

	/*public static void main(String args[]){
		
		Mapa mapa = new Mapa(2);
		new VentanaJuego(mapa);
		
	}*/


}
