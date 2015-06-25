package xtremecraft.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class InicioJuego extends JFrame{
	
	public static ArrayList<String> nombresJugadores = new ArrayList<String>();
	JPanel panel = new JPanel();
	Integer numeroDeJugadores[] = {2,3,4};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox menu = new JComboBox(numeroDeJugadores);
	
	
	public InicioJuego(){
		
		setTitle("Cantidad de jugadores");
		setSize(400,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		panel.add(menu);
		
		menu.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent seleccionUsuario){
				
				int elementoSeleccionado = menu.getSelectedIndex();
				int cantidadDeJugadores = numeroDeJugadores[elementoSeleccionado];
				setVisible(false);
				new IngresoNombres(nombresJugadores,cantidadDeJugadores);
				
			}
			
		});
		
		add(panel);
		
	}
	
	public static void main(String args[]){
				
		new InicioJuego();
		
	}

}
